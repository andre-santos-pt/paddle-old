package pt.iscte.paddle.javasde;

import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.model.IArrayType;
import pt.iscte.paddle.model.IType;

public class DeclarationWidget extends EditorWidget {
	private final Id type;
	private final Id id;
	private final ExpressionWidget expression;

	DeclarationWidget(EditorWidget parent) {
		this(parent, IType.UNBOUND, "variable", "expression");
	}
	
	DeclarationWidget(EditorWidget parent, IType type, String id, String expression) {
		super(parent);
		String typeId = type.getId();
		int dims = 0;
		if(type instanceof IArrayType) {
			typeId = ((IArrayType) type).getComponentType().getId();
			dims = ((IArrayType) type).getDimensions();
		}
		this.type = createType(this, typeId, Constants.PRIMITIVE_TYPES_SUPPLIER);
		while(dims-- > 0)
			this.type.addDimension();
		this.id = createId(this, id);
		new FixedToken(this, "=");
		this.expression = new ExpressionWidget(this, expression);
		new FixedToken(this, ";");
	}
	
	@Override
	public boolean setFocus() {
		return type.setFocus();
	}
	
	public void focusId() {
		id.setFocus();
	}
	
	@Override
	public void toCode(StringBuffer buffer) {
		buffer.append(type).append(" ").append(id).append(" = ");
		expression.toCode(buffer);
		buffer.append(";");
	}
}
