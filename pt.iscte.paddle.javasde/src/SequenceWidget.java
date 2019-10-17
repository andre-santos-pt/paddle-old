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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class SequenceWidget extends EditorWidget {

//	private Menu menu;

	public SequenceWidget(Composite parent) {
		super(parent);
//		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
//		rowLayout.marginLeft = 40;
//		rowLayout.marginRight = 40;
//		setLayout(rowLayout);
		GridLayout layout = new GridLayout(2, false);
		setLayout(layout);
		
//		setBackground(Display.getDefault().getSystemColor(SWT.COLOR_YELLOW));
		
//		menu = new Menu(this);
//		
//		addMenuItem("assignment", () -> new AssignmentWidget(this));
//		addMenuItem("if statement", () -> new ControlWidget(this, false));
//		addMenuItem("while loop", () -> new ControlWidget(this, true));
//		addMenuItem("procedure call", () -> new CallWidget(this, true));
//		addMenuItem("return statement", () -> new ReturnWidget(this));
		
		Label addLabel = ClassWidget.createAddLabel(this);
		addLabel.setLayoutData(ALIGN_TOP);
		addLabel.setMenu(createMenu(addLabel));
		
	}
	
	
	private static GridData ALIGN_TOP = new GridData(SWT.LEFT, SWT.TOP, false, false);
	
	private Menu createMenu(Label label) {
		Menu menu = new Menu(label);
		MenuItem del = new MenuItem(menu, SWT.NONE);
		del.setText("delete");
		del.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				EditorWidget parent = (EditorWidget) label.getParent();
				Control[] children = parent.getChildren();
				for (int i = 0; i < children.length; i++) {
					if(children[i] == label && i + 1 < children.length) {
						children[i].dispose();
						children[i+1].dispose();
						parent.pack();
						parent.getRootParent().layout();
						return;
					}
				}
			}
		});
		new MenuItem(menu, SWT.SEPARATOR);
		addMenuItem(menu, "variable declaration", () -> new DeclarationWidget(this), label);
		addMenuItem(menu, "variable assignment", () -> new AssignmentWidget(this), label);
		addMenuItem(menu, "if statement", () -> new ControlWidget(this, false), label);
		addMenuItem(menu, "while loop", () -> new ControlWidget(this, true), label);
		addMenuItem(menu, "procedure call", () -> new CallWidget(this, true), label);
		addMenuItem(menu, "return statement", () -> new ReturnWidget(this), label);
		
		addDragNDrop(label);
		return menu;
	}
	
	private Supplier<DeclarationWidget> SUPPLIER_DECLARATION = () -> new DeclarationWidget(this);
	
	
	
	private void addDragNDrop(Label label) {
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
	
	
	private void addMenuItem(Menu menu, String text, Supplier<StatementWidget> provider, Control statement) {
		MenuItem item = new MenuItem(menu, SWT.NONE);
		item.setText(text);
		item.setData(statement);
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
//				Point cursor = Display.getDefault().getCursorControl().getLocation();
//				System.out.println("-- " + cursor);
//				for(Control c : SequenceWidget.this.getChildren())
//					System.out.println(c.getLocation());
				MenuItem item = (MenuItem) e.getSource();
				Menu m = ((Menu) ((MenuItem) e.getSource()).getParent());
//				System.out.println(((Menu) ((MenuItem) e.getSource()).getParent()));
				Label addLabel = null;
				
//				if(SequenceWidget.this.getChildren().length != 1) {
//					SequenceWidget.this.getChildren()[0].setMenu(null);
//					SequenceWidget.this.getChildren()[0].dispose();
//					addLabel = ClassWidget.createAddLabel(SequenceWidget.this);
//					addLabel.setMenu(createMenu(addLabel));
//				}
								
				StatementWidget stat =  provider.get();
//				if(addLabel != null)
				
				stat.moveBelow((Label) item.getData());
				
				addLabel = ClassWidget.createAddLabel(SequenceWidget.this);
				addLabel.setLayoutData(ALIGN_TOP);

				addLabel.setMenu(createMenu(addLabel));
				addLabel.moveBelow(stat);
				
				pack();
				getRootParent().getParent().layout();
			};
		});
	}
}
