package pt.iscte.paddle.javasde;

public class ArrayAllocationExpression extends EditorWidget {
	private EditorWidget id;

	public ArrayAllocationExpression(EditorWidget parent) {
		super(parent, parent.mode);
		new Token(this, "new");
		id = new Id(this, "type", Constants.PRIMITIVE_TYPES_SUPPLIER);
		new Token(this, "[");
		new ExpressionWidget(this);
		new Token(this, "]");
		
	}
	
	@Override
	public boolean setFocus() {
		id.setFocus();
		return true;
	}
	
}
