package pt.iscte.paddle.javasde;

import static pt.iscte.paddle.model.IType.BOOLEAN;
import static pt.iscte.paddle.model.IType.INT;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MenuListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IBlockElement;
import pt.iscte.paddle.model.IBreak;
import pt.iscte.paddle.model.IContinue;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IOperator;
import pt.iscte.paddle.model.IProcedureCall;
import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IVariable;
import pt.iscte.paddle.model.IVariableAssignment;

public class SequenceWidget extends EditorWidget {

	private class MenuCommand {
		final String text;
		final char accelerator;
		final Consumer<Integer> action;
		final Function<Integer, Boolean> enabled;
		
//		MenuCommand(String text, char accelerator, Consumer<Integer> action) {
//			this(text, accelerator, action, () -> true);
//		}

		MenuCommand(String text, char accelerator, Consumer<Integer> action, Function<Integer, Boolean> enabled) {
			this.text = text;
			this.accelerator = accelerator;
			this.action = action;
			this.enabled = enabled;
		}

		MenuItem createItem(Menu parent, Control location) {
			if (text == null)
				return new MenuItem(parent, SWT.SEPARATOR);

			int i = findIndex(location);
			MenuItem item = new MenuItem(parent, SWT.NONE);
			item.setText(text);
			item.setAccelerator(accelerator);
			item.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					action.accept(i);
				}
			});
			item.setEnabled(enabled.apply(i));
			return item;
		}
	}

	private static int findIndex(Control location) {
		int index = -1;
		int i = 0;
		for (Control c : location.getParent().getChildren()) {
			if (c == location) {
				index = i;
				break;
			}
			i++;
		}
		return index / 2;
	}

	private List<MenuCommand> commands;
	private Text rootAddLabel;

	GridData data = new GridData(SWT.LEFT, SWT.TOP, false, false);

	public SequenceWidget(EditorWidget parent, int margin) {
		super(parent, parent.mode);
		GridLayout layout = new GridLayout(2, false);
		layout.marginLeft = margin;
		layout.verticalSpacing = 4;
		layout.horizontalSpacing = 2;
		setLayout(layout);
		commands = new ArrayList<>();

		rootAddLabel = createAddLabel(this, " ");
		rootAddLabel.setLayoutData(data);
		rootAddLabel.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				data.exclude = true;
				SequenceWidget.this.requestLayout();
			}

			public void focusGained(FocusEvent e) {
				data.exclude = false;
				SequenceWidget.this.requestLayout();
			}
		});
	}

	public Control getTail() {
		return rootAddLabel;
	}

	void addChildCommand(String text, char accelerator, Consumer<Integer> action) {
		addChildCommand(text, accelerator, action, i -> true);
	}
	void addChildCommand(String text, char accelerator, Consumer<Integer> action, Function<Integer, Boolean> enabled) {
		commands.add(new MenuCommand(text, accelerator, action, enabled));
		rootAddLabel.setMenu(createMenu(rootAddLabel, false));
	}

	void addStatementCommands(IBlock block) {
		addChildCommand("variable declaration", 'v', i -> block.addVariableAt(null, i));
		addChildCommand("variable assignment", 'a', i -> block.addAssignmentAt(null, null, i));
		addChildCommand("if statement", 'i', i -> block.addSelectionAt(BOOLEAN.literal(true), i));
		addChildCommand("else statement", 'e', i -> {
				IBlockElement e = block.getChildren().get(i - 1);
			if (e instanceof ISelection && !((ISelection) e).hasAlternativeBlock()) {
				((ISelection) e).createAlternativeBlock();
			}
		},i -> {
			if(i > 0) {
				IBlockElement e = block.getChildren().get(i - 1);
				return e instanceof ISelection && !((ISelection) e).hasAlternativeBlock();
			}
			return false;
		});

		addChildCommand("procedure call", 'p', i -> block.addCallAt(null, i));
		addChildCommand("return statement", 'r', i -> block.addReturnAt(null, i));

		addChildCommand(null, '0', null);

		addChildCommand("while loop", 'w', i -> block.addLoopAt(BOOLEAN.literal(true), i));

		addChildCommand("for loop", 'f', i -> {
			IBlock forBlock = block.addBlockAt(i, Constants.FOR_FLAG);
			IVariable progVar = forBlock.addVariable(INT, Constants.FOR_FLAG);
			ILoop loop = forBlock.addLoop(BOOLEAN.literal(true), Constants.FOR_FLAG);
			IVariableAssignment inc = loop.addAssignment(progVar, IOperator.ADD.on(progVar, INT.literal(1)),
					Constants.FOR_FLAG);
		});

		addChildCommand("break", 'b', i -> block.addBreakAt(i), i -> block.isInLoop());
		addChildCommand("continue", 'c', i -> block.addContinueAt(i), i -> block.isInLoop());

	}

	void addBlockListener(IBlock block) {
		block.addListener(new IBlock.IListener() {
			public void elementAdded(IProgramElement element, int index) {
				if (element instanceof IVariable && element.not(Constants.FOR_FLAG)) {
					IVariable v = (IVariable) element;
					String type = v.getType() != null && v.getType().getId() != null ? v.getType().getId() : "type";
					String id = v.getId() != null ? v.getId() : "variable";
					String exp = "expression";
					addElement(new DeclarationWidget(SequenceWidget.this, type, id, exp), index);
				} else if (element instanceof IVariableAssignment && element.not(Constants.FOR_FLAG)) {
					IVariableAssignment a = (IVariableAssignment) element;
					String id = "variable";
					String exp = "expression";
					addElement(new AssignmentWidget(SequenceWidget.this, id, exp, true), index);
				} else if (element instanceof ISelection) {
					ISelection s = (ISelection) element;
					addElement(new ControlWidget(SequenceWidget.this, "if", "true", s.getBlock()));
					if (s.hasAlternativeBlock())
						addElement(new ControlWidget(SequenceWidget.this, "else", null, s.getAlternativeBlock()));
					s.addPropertyListener((k,n,o) -> {
						if(k.equals("ELSE"))
							addElement(new ControlWidget(SequenceWidget.this, "else", null, s.getAlternativeBlock()));
					});
				} else if (element instanceof ILoop && element.not(Constants.FOR_FLAG)) {
					ILoop l = (ILoop) element;
					addElement(new ControlWidget(SequenceWidget.this, "while", "true", l.getBlock()));
				} else if (element instanceof IBlock && element.is(Constants.FOR_FLAG)) {
					addElement(new ForWidget(SequenceWidget.this, "int", "i", "0", "true", (IBlock) element));
				} else if (element instanceof IBreak) {
					addElement(new InstructionWidget(SequenceWidget.this, "break"));
				} else if (element instanceof IContinue) {
					addElement(new InstructionWidget(SequenceWidget.this, "continue"));
				} else if (element instanceof IProcedureCall) {
					IProcedureCall call = (IProcedureCall) element;
					addElement(new CallWidget(SequenceWidget.this, call.getProcedure().getId(), true));
				} else if (element instanceof IReturn) {
					IReturn ret = (IReturn) element;
					if (ret.isVoid())
						addElement(new InstructionWidget(SequenceWidget.this, "return"));
					else
						addElement(new InstructionWidget(SequenceWidget.this, ret.getExpression().toString()));
				} else
					System.err.println("unhandled: " + element);
			}
		});
	}

	void addElement(EditorWidget w) {
		addElement(w, (getChildren().length - 1) / 2);
	}

	void addElement(EditorWidget w, int index) {
		Control location = getChildren()[index * 2];

		w.moveAbove(location);

		Control addLabel = createAddLabel(this);
		addLabel.setLayoutData(Constants.ALIGN_TOP);
		addLabel.setMenu(createMenu(addLabel, true));
		addLabel.moveAbove(w);
		addLabel.requestLayout();

		addKeyListenerMenu(addLabel);
		
		w.requestLayout();
		w.setFocus();
	}

	private void addDeleteItem(Control label, Menu menu) {
		MenuItem del = new MenuItem(menu, SWT.NONE);
		del.setText("delete");
		del.setAccelerator('d');
		del.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				EditorWidget parent = (EditorWidget) label.getParent();
				Control[] children = parent.getChildren();
				for (int i = 0; i < children.length; i += 2) {
					if (children[i] == label && i + 1 < children.length) {
						children[i].dispose();
						children[i + 1].dispose();
						parent.requestLayout();
						if (children.length > i + 1)
							parent.getChildren()[i].setFocus();
						return;
					}
				}
			}
		});
	}

	void delete(Predicate<EditorWidget> pred) {
		Control[] children = getChildren();
		for (int i = 1; i < children.length; i += 2) {
			if (pred.test((EditorWidget) children[i])) {
				if (i != 1)
					children[i - 1].dispose();
				children[i].dispose();
			}
		}
		requestLayout();
	}

	void delete(EditorWidget e) {
		Control[] children = getChildren();
		for (int i = 1; i < children.length; i += 2) {
			if (children[i] == e) {
				children[i - 1].dispose();
				children[i].dispose();
				requestLayout();
				return;
			}
		}
	}

	Menu createMenu(Control label, boolean delete) {
		Menu menu = new Menu(label);
		if (delete) {
			addDeleteItem(label, menu);
			new MenuItem(menu, SWT.SEPARATOR);
		}
		commands.forEach(c -> c.createItem(menu, label));
		menu.addMenuListener(new MenuAdapter() {
			
			@Override
			public void menuShown(MenuEvent e) {
				menu.getItem(0).setEnabled(false);
				System.out.println("menu " + Display.getDefault().getFocusControl());
			}
		});
		return menu;
	}

	void addKeyListenerMenu(Control label) {
		label.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				commands.forEach(c -> {
					int i = findIndex(label);
					if(c.accelerator == e.character && c.enabled.apply(i)) {
						c.action.accept(i);
					}
				});
			}
		});
	}

	public void toCode(StringBuffer buffer, int level) {
		for (Control control : getChildren())
			if (control instanceof EditorWidget)
				((EditorWidget) control).toCode(buffer, level);
	}

	// TODO drag n drop to move statements
	private void addDragNDrop(Control label) {
		DragSource source = new DragSource(label, DND.DROP_NONE);
		source.setTransfer(TextTransfer.getInstance());
		source.addDragListener(new DragSourceListener() {

			@Override
			public void dragStart(DragSourceEvent event) {
				System.out.println("start - " + event);
			}

			@Override
			public void dragSetData(DragSourceEvent event) {
				System.out.println("set");
				event.data = "test";
			}

			@Override
			public void dragFinished(DragSourceEvent event) {
				System.out.println("end - " + event);
			}
		});

		DropTarget target = new DropTarget(label, DND.DROP_NONE);
		target.setTransfer(TextTransfer.getInstance());
		target.addDropListener(new DropTargetListener() {

			@Override
			public void dropAccept(DropTargetEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void drop(DropTargetEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void dragOver(DropTargetEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void dragOperationChanged(DropTargetEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void dragLeave(DropTargetEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void dragEnter(DropTargetEvent event) {
				// TODO Auto-generated method stub

			}
		});
	}

}
