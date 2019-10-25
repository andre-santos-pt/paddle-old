package pt.iscte.paddle.javasde;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

public class ArrayAllocationExpression extends EditorWidget {


	private Id id;

	public ArrayAllocationExpression(EditorWidget parent) {
		super(parent, parent.mode);
		setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		new Token(this, "new");
		id = new Id(this, "type", PRIMITIVE_TYPES_SUPPLIER);
		new Token(this, "[");
		new ExpressionWidget(this);
		new Token(this, "]");
		
	}

	@Override
	public void toCode(StringBuffer buffer) {

	}
	
	@Override
	public boolean setFocus() {
		id.setFocus();
		return true;
	}
	
}
