package pt.iscte.paddle.javasde;

public class ArrayAllocationExpression extends EditorWidget {
	private EditorWidget id;

	public ArrayAllocationExpression(EditorWidget parent) {
		super(parent, parent.mode);
		new Token(this, Keyword.NEW);
		id = createId(this, "Type", Constants.PRIMITIVE_TYPES_SUPPLIER);
		new FixedToken(this, "[");
		new ExpressionWidget(this, "expression");
		new FixedToken(this, "]");
		
	}
	
	@Override
	public boolean setFocus() {
		id.setFocus();
		return true;
	}
	
}
