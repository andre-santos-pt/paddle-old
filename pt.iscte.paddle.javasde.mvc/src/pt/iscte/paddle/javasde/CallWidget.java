package pt.iscte.paddle.javasde;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

// TODO incomplete
public class CallWidget extends EditorWidget {
	private EditorWidget id;
	private InsertWidget addLabel;
	private EditorWidget args;
	
	public CallWidget(EditorWidget parent, String id, boolean statement) {
		super(parent);
		setLayout(Constants.ROW_LAYOUT_H_ZERO);
		
		this.id = createId(this, id); // TODO validation keyword / empty
		new Token(this, "(");
		args = new EditorWidget(this);
		addLabel = addInsert(this, false);
		addLabel.addFocusListener(Constants.ADD_HIDE);
		new Token(this, ")");
		if(statement)
			new Token(this, ";");
		
		Menu menu = addLabel.createMenu();
		MenuItem delete = new MenuItem(menu, SWT.NONE);
		delete.setText("delete");
		delete.setAccelerator(Constants.DEL_KEY);
		delete.setEnabled(false);
		delete.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// TODO del arg
//				Control[] children = params.getChildren();
//				children[children.length-2].dispose();
//				delete.setEnabled(children.length > 2);
//				requestLayout();
			}
		});
		// TODO setData
		
		new MenuItem(menu, SWT.SEPARATOR);
		MenuItem argItem = new MenuItem(menu, SWT.PUSH);
		argItem.setText("argument");
		argItem.setAccelerator('a');
		SelectionListener l = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(args.getChildren().length != 0)
					new FixedToken(args, ",");
				ExpressionWidget exp = new ExpressionWidget(args, "argument");
				exp.setFocus();
				exp.requestLayout();
			}
		};
		argItem.addSelectionListener(l);
		argItem.setData(l);
		
		addLabel.setMenu(menu);
		addLabel.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == Constants.MENU_KEY)
					popup(menu, addLabel.text);
			}
		});
	}
	
	@Override
	public void toCode(StringBuffer buffer) {
		buffer.append(id).append("()"); // TODO call arguments
	}
	
	@Override
	public String toString() {
		return id + "(...)";
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
		super.accept(visitor);
	}

	public void focusArgument() {
		addLabel.setFocus();
	}
}
