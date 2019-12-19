package pt.iscte.paddle.javasde;

import static java.lang.System.lineSeparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import pt.iscte.paddle.model.IConstant;
import pt.iscte.paddle.model.ILiteral;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IType;

public class ClassWidget extends EditorWidget {

	private IModule module;
	private Id id;
	private SequenceWidget body;
	private UiMode mode;

	public ClassWidget(Composite parent, IModule module, UiMode mode) {
		super(parent, mode);
		this.module = module;
		this.mode = mode;
		GridLayout layout = new GridLayout(1, true);
		layout.verticalSpacing = 10;
		setLayout(layout);

		Token openBlock = null;
		if (!mode.staticClass) {
			EditorWidget header = new EditorWidget(this, mode);
			header.setLayout(Constants.ROW_LAYOUT_H_ZERO);
			new Token(header, "class");
			id = createId(header, module.getId());
			openBlock = new Token(header, "{");
		}

		body = new SequenceWidget(this, mode.staticClass ? 0 : Constants.TAB);
		body.addChildCommand("constant", 'c', (i, p) -> module.addConstant(IType.INT, ILiteral.matchValue("1")));
		body.addChildCommand("procedure", 'p', (i, p) -> module.addProcedure(IType.VOID));

		module.getConstants().forEach(c -> body.addElement(new ConstantWidget(body, c)));
		module.getProcedures().forEach(p -> body.addElement(new MethodWidget(body, p)));

		if (!mode.staticClass)
			new Token(this, "}");

		module.addListener(new IModule.IListener() {
			public void constantAdded(IConstant constant) {
				body.addElement(new ConstantWidget(body, constant));
			}

			public void constantDeleted(IConstant constant) {
				body.delete(e -> e instanceof ConstantWidget && ((ConstantWidget) e).constant == constant);
			}

			public void procedureAdded(IProcedure procedure) {
				body.addElement(new MethodWidget(body, procedure));
			}

			public void procedureDeleted(IProcedure procedure) {
				body.delete(e -> e instanceof MethodWidget && ((MethodWidget) e).procedure == procedure);
			}
		});

		Display.getDefault().addFilter(SWT.KeyDown, new Listener() {

			@Override
			public void handleEvent(Event event) {
				if ((event.stateMask & SWT.MODIFIER_MASK) == SWT.CTRL && event.keyCode == 'z') {
					System.out.println("UNDO");
					module.undo();
				}
			}
		});
	}

	public UiMode getMode() {
		return mode;
	}

	private boolean editMode = true;

	private List<Control> addLabels = new ArrayList<>();
	private List<Id> idWidgets = new ArrayList<>();

	private static final Supplier<List<String>> EMPTY_SUPPLIER = () -> Collections.emptyList();

	Id createId(EditorWidget parent, String id) {
		return createId(parent, id, EMPTY_SUPPLIER);
	}

	Id createId(EditorWidget parent, String id, Supplier<List<String>> idProvider) {
		Id w = new Id(parent, id, idProvider);
		idWidgets.add(w);
		return w;
	}

	private String spaces(int n) {
		String t = "";
		while(n-- > 0)
			t += " ";
		return t;
	}

	Text createAddLabel(Composite parent, int spaces, boolean root) {
		Text label = new Text(parent, SWT.NONE);
		label.setForeground(Constants.FONT_COLOR);
		label.setBackground(Constants.COLOR_ADDLABEL);
		label.setEditable(true);
		label.setFont(Constants.FONT);
		String t = spaces(spaces);
		label.setText(t);
		label.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				e.doit = label.getData() == Boolean.TRUE || Constants.isLetter(e.character) || e.character == '=' || e.character == Constants.DEL_KEY || e.character == '/';
			}
		});
		label.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				label.setData(true);
				label.setText("");
				label.setData(false);
			}
			public void focusLost(FocusEvent e) {
				label.setData(true);
				label.setText("");
				label.setData(false);
			}
		});

		label.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				Menu menu = label.getMenu();
				boolean action = true;
				if(menu != null) {
					if(e.character == '=')
						runAction(menu, 'a', e.widget, label.getText());

					else if((e.character == '(' || e.character == SWT.SPACE) && Keyword.IF.match(label))
						runAction(menu, Keyword.IF.getAccelerator(), e.widget, null);

					else if((e.character == '(' || e.character == SWT.SPACE) && label.getText().equals("while"))
						runAction(menu, 'w', e.widget, null);

					else if((e.character == '(' || e.character == SWT.SPACE) && Keyword.FOR.match(label))
						runAction(menu, 'f', e.widget, null);
					
					else if(e.character == SWT.SPACE && label.getText().equals("return"))
						runAction(menu, 'r', e.widget, null);

					else if(e.character == '(' && label.getText().length() > 0)
						runAction(menu, 'p', e.widget, label.getText());
					
					else if (e.keyCode == Constants.MENU_KEY)
						showMenu(label, menu);

					else if (e.keyCode == Constants.DEL_KEY)
						deleteAction(menu);
					else
						action = false;
				}
				if(!action) {
					if (e.keyCode == SWT.ARROW_UP)
						moveCursorUp(label);
					else if (e.keyCode == SWT.ARROW_DOWN)
						moveCursorDown(label);
				}
			}

			private void runAction(Menu menu, char accelarator, Widget widget, String param) {
				for (MenuItem menuItem : menu.getItems()) {
					if(menuItem.isEnabled() && menuItem.getAccelerator() == accelarator) {
						((SequenceWidget.MenuCommand) menuItem.getData()).run(param);
						label.setText(spaces(spaces));
						if(!root) {
							Control control = (Control) widget;
							control.setMenu(null); // to not dispose menu
							control.dispose();
						}
						break;
					}
				}
			}
		});
		addLabels.add(label);
		return label;
	}

	private void showMenu(Text label, Menu menu) {
		int c = 0;
		MenuItem item = null;
		for (MenuItem menuItem : menu.getItems()) {
			if (menuItem.isEnabled() && menuItem.getStyle() != SWT.SEPARATOR
					&& menuItem.getAccelerator() != Constants.DEL_KEY
					&& (menuItem.getData() instanceof SelectionListener
							|| menuItem.getData() instanceof SequenceWidget.MenuCommand)) {
				item = menuItem;
				c++;
			}
		}
		if (c == 1) {
			((SelectionListener) item.getData()).widgetSelected(null);
		} 
		else {
			menu.setLocation(label.toDisplay(0, 20));
			menu.setVisible(true);
		}
	}

	private void deleteAction(Menu menu) {
		for (MenuItem menuItem : menu.getItems()) {
			if (menuItem.isEnabled() && menuItem.getAccelerator() == Constants.DEL_KEY) {
				((SelectionListener) menuItem.getData()).widgetSelected(null);
				break;
			}
		}
	}

	private void moveCursorUp(Text label) {
		Composite seq = label.getParent();
		Control[] children = seq.getChildren();
		for (int i = 2; i < children.length; i++) {
			if (children[i] == label) {
				children[i - 2].setFocus();
				break;
			}
		}
	}

	private void moveCursorDown(Text label) {
		Composite p = label.getParent();
		Control[] children = p.getChildren();
		for (int i = 0; i < children.length - 2; i++) {
			if (children[i] == label) {
				children[i + 2].setFocus();
				break;
			}
		}
	}

	private static final Predicate<Control> isDisposed = c -> c.isDisposed();

	public void hideAddLabels() {
		// dispose
		editMode = !editMode;
		addLabels.removeIf(isDisposed);
		for (Control l : addLabels) {
			// l.setForeground(editMode ? WHITE : FONT_COLOR);
			l.setBackground(editMode ? Constants.COLOR_ADDLABEL : Constants.COLOR_BACKGROUND);
			// l.setVisible(editMode);
		}
		idWidgets.removeIf(isDisposed);
		// for(Id id : idWidgets)
		// id.setEditable(!editMode);
	}


	public void toCode(StringBuffer buffer) {
		buffer.append("public class ").append(id.toString()).append(" {").append(lineSeparator());
		for (Control c : getChildren())
			if (c instanceof MethodWidget)
				((MethodWidget) c).toCode(buffer);

		buffer.append("}").append(lineSeparator()).append(lineSeparator());
	}


}
