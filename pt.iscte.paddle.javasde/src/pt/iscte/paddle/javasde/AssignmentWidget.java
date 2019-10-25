package pt.iscte.paddle.javasde;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class AssignmentWidget extends StatementWidget implements Selectable {

	private Id id;
	private ExpressionWidget expression;

	public AssignmentWidget(EditorWidget parent) {
		super(parent);
		id = ClassWidget.createId(this, "variable");
		Token token = new Token(this, "=");
		expression = new ExpressionWidget(this);
		new Token(this, ";");

		Menu menu = token.getMenu();
		MenuItem item = new MenuItem(menu, SWT.NONE);
		item.setText("to declaration");
		token.setMenu(menu);
	}

	@Override
	public EditorWidget initControl() {
		return id;
	}

	@Override
	public void toCode(StringBuffer buffer) {
		buffer.append(id).append(" = ");
		expression.toCode(buffer);
	}

}
