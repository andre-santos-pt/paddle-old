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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class SequenceWidget extends EditorWidget {

	private static final GridData ALIGN_TOP = new GridData(SWT.LEFT, SWT.TOP, false, false);

	public SequenceWidget(EditorWidget parent) {
		super(parent, parent.mode);
		GridLayout layout = new GridLayout(2, false);
		setLayout(layout);
		Label addLabel = ClassWidget.createAddLabel(this);
		addLabel.setLayoutData(ALIGN_TOP);
		addLabel.setMenu(createMenu(addLabel));
	}
	
	
	
	private Menu createMenu(Label label) {
		Menu menu = new Menu(label);
		addDeleteItem(label, menu);
		new MenuItem(menu, SWT.SEPARATOR);
		
		// TODO ideia: plugins
		addMenuItem(menu, label, "variable declaration", () -> new DeclarationWidget(this));
		addMenuItem(menu, label, "variable assignment", () -> new AssignmentWidget(this));
		addMenuItem(menu, label, "if statement", () -> new ControlWidget(this, false));
		addMenuItem(menu, label, "while loop", () -> new ControlWidget(this, true));
		addMenuItem(menu, label, "function call", () -> new CallWidget(this, "function", true));
		addMenuItem(menu, label, "procedure call", () -> new CallWidget(this, "procedure", true));
		addMenuItem(menu, label, "return statement", () -> new ReturnWidget(this));
		
		addDragNDrop(label);
		return menu;
	}



	private void addDeleteItem(Label label, Menu menu) {
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
						parent.requestLayout();
						return;
					}
				}
			}
		});
	}
	
	
	
	
	private void addMenuItem(Menu menu, Control statement, String text, Supplier<StatementWidget> provider) {
		MenuItem item = new MenuItem(menu, SWT.NONE);
		item.setText(text);
		item.setData(statement);
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MenuItem item = (MenuItem) e.getSource();
				Menu m = ((Menu) ((MenuItem) e.getSource()).getParent());
				StatementWidget stat =  provider.get();
				stat.moveBelow((Label) item.getData());
				
				Label addLabel = ClassWidget.createAddLabel(SequenceWidget.this);
				addLabel.setLayoutData(ALIGN_TOP);
				addLabel.setMenu(createMenu(addLabel));
				addLabel.moveBelow(stat);
				
				stat.requestLayout();
				stat.setFocus();
				
			};
		});
	}
	
	public void toCode(StringBuffer buffer, int level) {
		for (Control control : getChildren())
			if(control instanceof EditorWidget)
				((EditorWidget) control).toCode(buffer, level);
	}
	
	
	// TODO drag n drop to move statements
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
}
