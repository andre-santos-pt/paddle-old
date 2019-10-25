package pt.iscte.paddle.javasde;

import java.util.Arrays;
import java.util.Collections;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class DeclarationWidget extends StatementWidget implements Selectable {
	
	private Id type;
	private Id id;
	private ExpressionWidget expression;

	public DeclarationWidget(EditorWidget parent) {
		super(parent);
		type = ClassWidget.createId(this, "type", PRIMITIVE_TYPES_SUPPLIER);
		type.addArrayPart();
		id = ClassWidget.createId(this, "variable");
		Token token = new Token(this, "=");
		expression = new ExpressionWidget(this);
		new Token(this, ";");
		Menu menu = token.getMenu();
		MenuItem item = new MenuItem(menu, SWT.NONE);
		item.setText("to assignment"); // TODO convert to assignment
		token.setMenu(menu);
	}
	
	@Override
	public EditorWidget initControl() {
		return id;
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
