package pt.iscte.paddle.javasde;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class ExpressionWidget extends EditorWidget {
	boolean test = false;
	EditorWidget expression;
	String initLiteral = "0";

	public ExpressionWidget(EditorWidget parent) {
		this(parent, "expression");
	}

	public ExpressionWidget(EditorWidget parent, String literal) {
		super(parent);
		setLayout(ROW_LAYOUT_H_ZERO);
		initLiteral = literal;
		//		expression = ClassWidget.createId(this, "expression");
		expression = new SimpleExpression(this, literal);
		addMenu(expression);
	}

	private void addMenu(EditorWidget widget) {
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
		
		widget.setMenu(menu);
	}

	@Override
	public void toCode(StringBuffer buffer) {
		expression.toCode(buffer);
	}
}
