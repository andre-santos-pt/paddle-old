package pt.iscte.paddle.javasde;
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class SequenceWidget extends EditorWidget {

	private static final GridData ALIGN_TOP = new GridData(SWT.LEFT, SWT.TOP, false, false);

	public SequenceWidget(EditorWidget parent) {
		super(parent, parent.mode);
		GridLayout layout = new GridLayout(2, false);
		setLayout(layout);
	}

	Menu createMenu(Control label, boolean delete) {
		Menu menu = new Menu(label);
		if(delete) {
			addDeleteItem(label, menu);
			new MenuItem(menu, SWT.SEPARATOR);
		}
		
		// TODO if-else
		addMenuItem(menu, label, "variable declaration", 'v', () -> new DeclarationWidget(this));
		addMenuItem(menu, label, "variable assignment", 'a', () -> new AssignmentWidget(this));
		addMenuItem(menu, label, "while loop", 'w', () -> new WhileWidget(this, "true"));
		addMenuItem(menu, label, "procedure call", 'c', () -> new CallWidget(this, "procedure", true));
		addMenuItem(menu, label, "return statement", 'r', () -> new ReturnWidget(this));

		addDragNDrop(label);
		return menu;
	}

	private void addDeleteItem(Control label, Menu menu) {
		MenuItem del = new MenuItem(menu, SWT.NONE);
		del.setText("delete");
		del.setAccelerator('d');
		del.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				EditorWidget parent = (EditorWidget) label.getParent();
				Control[] children = parent.getChildren();
				for (int i = 0; i < children.length; i++) {
					if(children[i] == label && i + 1 < children.length) {
						children[i].dispose();
						children[i+1].dispose();
						parent.requestLayout();
//						parent.getChildren()[i].setFocus(); // FIXME
						return;
					}
				}
			}
		});
	}




	private void addMenuItem(Menu menu, Control statement, String text, char key, Supplier<EditorWidget> provider) {
		MenuItem item = new MenuItem(menu, SWT.NONE);
		item.setText(text);
		item.setData(statement);
		item.setAccelerator(key);
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MenuItem item = (MenuItem) e.getSource();
				Control label = (Control) item.getData();
				createStatement(provider, label);
			};
		});
	}
	
	public <T extends Control> T createStatement(Supplier<T> provider, Control location) {
		T stat =  provider.get();
		stat.moveAbove(location);
		
		Control addLabel = createAddLabel(SequenceWidget.this);
		addLabel.setLayoutData(ALIGN_TOP);
		addLabel.setMenu(createMenu(addLabel, true));
		addLabel.moveAbove(stat);
		addLabel.requestLayout();
		
		stat.requestLayout();
		stat.setFocus();
		return stat;
	}
	
	public DeclarationWidget createDeclaration(String type, String id, String expression, Control location) {
		return createStatement(() -> new DeclarationWidget(this, type, id, expression), location);
	}
	
	public AssignmentWidget createAssignment(String id, String expression, Control location) {
		return createStatement(() -> new AssignmentWidget(this, id, expression), location);
	}
	
	public WhileWidget createLoop(String expression, Control location) {
		return createStatement(() -> new WhileWidget(this, expression), location);
	}
	
	public CallWidget createCall(String id, Control location) { // TODO arguments
		return createStatement(() -> new CallWidget(this, id, true), location);
	}
	
	public ReturnWidget createReturn(String expression, Control location) {
		return createStatement(() -> new ReturnWidget(this, expression), location);
	}
	

	public void toCode(StringBuffer buffer, int level) {
		for (Control control : getChildren())
			if(control instanceof EditorWidget)
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
