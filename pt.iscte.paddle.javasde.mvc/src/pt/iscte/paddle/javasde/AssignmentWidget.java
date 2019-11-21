package pt.iscte.paddle.javasde;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class AssignmentWidget extends EditorWidget {

	private EditorWidget id;
	private ExpressionWidget expression;

	AssignmentWidget(EditorWidget parent) {
		this(parent, "variable", "expression", true);
	}

	AssignmentWidget(EditorWidget parent, String id, String expression, boolean statement) {
		super(parent);
		this.id = createId(this, id);
		new Token(this, "=");
		this.expression = new ExpressionWidget(this, expression);
		if(statement)
			new Token(this, ";");
	}
	
	@Override
	public void toCode(StringBuffer buffer) {
		buffer.append(id).append(" = ");
		expression.toCode(buffer);
	}

}
