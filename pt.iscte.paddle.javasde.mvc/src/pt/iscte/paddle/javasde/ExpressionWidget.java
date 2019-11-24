package pt.iscte.paddle.javasde;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class ExpressionWidget extends EditorWidget {
	boolean test = false;
	Composite expression;
	String initLiteral = "0";

	public ExpressionWidget(EditorWidget parent) {
		this(parent, "expression");
	}

	public ExpressionWidget(EditorWidget parent, String literal) {
		super(parent);
		setLayout(Constants.ROW_LAYOUT_H_ZERO);
		initLiteral = literal;
		expression = new SimpleExpressionWidget(this, literal, false);
		addMenu(expression);
	}

	public void set(String expression) {
		this.expression.dispose();
		this.expression = new SimpleExpressionWidget(this, expression, false);
		this.expression.requestLayout();
	}
	
	private void addMenu(Control widget) {
		Menu menu = new Menu(widget);
		MenuItem item1 = new MenuItem(menu, SWT.NONE);
		item1.setText("unary expression");
		item1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String target = expression.toString();
				expression.dispose();
				expression = new UnaryExpressionWidget(ExpressionWidget.this, "!", target);
				addMenu(expression);
				expression.requestLayout();
				expression.setFocus();
			}
		});
		MenuItem item2 = new MenuItem(menu, SWT.NONE);
		item2.setText("binary expression");
		item2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				expression.dispose();
				expression = new BinaryExpressionWidget(ExpressionWidget.this, "+");
				addMenu(expression);
				expression.requestLayout();
				expression.setFocus();
			}
		});
		
		MenuItem item3 = new MenuItem(menu, SWT.NONE);
		item3.setText("function call");
		item3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				expression.dispose();
				expression = new CallWidget(ExpressionWidget.this, "func", false);
				addMenu(expression);
				expression.requestLayout();
				expression.setFocus();
			}
		});
		
		MenuItem item4 = new MenuItem(menu, SWT.NONE);
		item4.setText("array allocation");
		item4.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				expression.dispose();
				expression = new ArrayAllocationExpression(ExpressionWidget.this);
				addMenu(expression);
				expression.requestLayout();
				expression.setFocus();
			}
		});
		
		MenuItem item5 = new MenuItem(menu, SWT.NONE);
		item5.setText("array element");
		item5.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				expression.dispose();
				expression = new ArrayElementExpression(ExpressionWidget.this, "variable");
				addMenu(expression);
				expression.requestLayout();
				expression.setFocus();
			}
		});
		
		widget.setMenu(menu);
	}

	@Override
	public void setForeground(Color color) {
		expression.setForeground(color);
	}
	
	@Override
	public boolean setFocus() {
		return expression.setFocus();
	}
	
//	@Override
//	public void toCode(StringBuffer buffer) {
//		expression.toCode(buffer);
//	}

	
}
