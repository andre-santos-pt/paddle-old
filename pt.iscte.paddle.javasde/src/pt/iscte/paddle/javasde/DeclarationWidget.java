package pt.iscte.paddle.javasde;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class DeclarationWidget extends EditorWidget {
	
	private Id type;
	private EditorWidget id;
	private ExpressionWidget expression;

	DeclarationWidget(SequenceWidget parent) {
		this(parent, "type", "variable", "expression");
	}
	
	DeclarationWidget(SequenceWidget parent, String type, String id, String expression) {
		super(parent);
		this.type = createId(this, type, PRIMITIVE_TYPES_SUPPLIER);
		this.type.addArrayPart();
		this.id = createId(this, id);
		Token token = new Token(this, "=");
		this.expression = new ExpressionWidget(this, expression);
		new Token(this, ";");
		Menu menu = token.getMenu();
		MenuItem item = new MenuItem(menu, SWT.NONE);
		item.setText("to assignment"); // TODO convert to assignment
		token.setMenu(menu);
	}

	@Override
	public void toCode(StringBuffer buffer) {
		buffer.append(type).append(" ").append(id).append(" = ");
		expression.toCode(buffer);
		buffer.append(";");
	}
	
	@Override
	public boolean setFocus() {
		return type.setFocus();
	}
}
