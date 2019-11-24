package pt.iscte.paddle.javasde;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class UnaryExpressionWidget extends EditorWidget {
	private Token op;
	private ExpressionWidget expression;

	public UnaryExpressionWidget(EditorWidget parent, String operator, String target) {
		super(parent);
		setLayout(Constants.ROW_LAYOUT_H_ZERO);
		op = new Token(this, operator, Constants.UNARY_OPERATORS);
		expression = new ExpressionWidget(this, target);

		Menu menu = op.getMenu();
		new MenuItem(menu, SWT.SEPARATOR);
		MenuItem simple = new MenuItem(menu, SWT.NONE);
		simple.setText("simple expression");
		simple.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				dispose(); // TODO
			}
		});
	}

	@Override
	public void toCode(StringBuffer buffer) {
		buffer.append(op + " ");
		expression.toCode(buffer);
	}

}
