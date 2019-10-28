package pt.iscte.paddle.javasde;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class AssignmentWidget extends EditorWidget {

	private EditorWidget id;
	private ExpressionWidget expression;

	AssignmentWidget(EditorWidget parent) {
		this(parent, "variable", "expression");
	}

	AssignmentWidget(EditorWidget parent, String id, String expression) {
		super(parent);
		this.id = createId(this, id);
		Token token = new Token(this, "=");
		this.expression = new ExpressionWidget(this, expression);
		new Token(this, ";");

		Menu menu = token.getMenu();
		MenuItem item = new MenuItem(menu, SWT.NONE);
		item.setText("to declaration"); // TODO convert to declaration
		token.setMenu(menu);
	}
	
	@Override
	public void toCode(StringBuffer buffer) {
		buffer.append(id).append(" = ");
		expression.toCode(buffer);
	}

}
