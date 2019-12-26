package pt.iscte.paddle.javasde;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;

// TODO incomplete
public class CallWidget extends EditorWidget {
	private EditorWidget id;
	private InsertWidget insert;
	private EditorWidget args;
	
	public CallWidget(Composite parent, String id, boolean statement) {
		super(parent);
		setLayout(Constants.ROW_LAYOUT_H_ZERO);
		
		this.id = new Id(this, id, false); // TODO validation keyword / empty
		new FixedToken(this, "(");
		args = new EditorWidget(this);
		insert = new InsertWidget(args, true);
		
//		insert.addFocusListener(Constants.ADD_HIDE);
		new FixedToken(this, ")");
		if(statement)
			new Token(this, ";");
		
		insert.addAction(new InsertWidget.Action("argument", (char) 0) {
			public boolean isEnabled(char c, String text, int index, int caret, int selection) {
				return !Keyword.is(text) && (c == SWT.SPACE || c == ',');
			}
			
			public void run(char c, String text, int index, int caret, int selection) {
				insert.dispose();
				ExpressionWidget arg = addArgument(text);
				if(c == ',') {
					new FixedToken(args, ",");
					ExpressionWidget arg2 = addArgument("expression");
					arg2.setFocus();
				}
				else
					arg.setFocus();
			}

			private ExpressionWidget addArgument(String text) {
				ExpressionWidget exp = new ExpressionWidget(args, text);
				exp.requestLayout();
				exp.setFocus();
				exp.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						if(e.character == ',') {
							new FixedToken(args, ",");
							addArgument("expression");
						}
					}
				});
				return exp;
			}
		});
//		Menu menu = insert.createMenu();
//		MenuItem delete = new MenuItem(menu, SWT.NONE);
//		delete.setText("delete");
//		delete.setAccelerator(Constants.DEL_KEY);
//		delete.setEnabled(false);
//		delete.addSelectionListener(new SelectionAdapter() {
//			public void widgetSelected(SelectionEvent e) {
//				// TODO del arg
//			}
//		});
//		
//		new MenuItem(menu, SWT.SEPARATOR);
//		MenuItem argItem = new MenuItem(menu, SWT.PUSH);
//		argItem.setText("argument");
//		argItem.setAccelerator('a');
//		SelectionListener l = new SelectionAdapter() {
//			public void widgetSelected(SelectionEvent e) {
//				if(args.getChildren().length != 0)
//					new FixedToken(args, ",");
//				ExpressionWidget exp = new ExpressionWidget(args, "expression");
//				exp.setFocus();
//				exp.requestLayout();
//			}
//		};
//		argItem.addSelectionListener(l);
//		argItem.setData(l);
		
//		insert.setMenu(menu);
//		insert.addKeyListener(new KeyAdapter() {
//			public void keyPressed(KeyEvent e) {
//				if(e.keyCode == Constants.MENU_KEY)
//					popup(menu, insert.text);
//			}
//		});
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
		insert.setFocus();
	}
}
