package pt.iscte.paddle.javasde;

import org.eclipse.swt.widgets.Composite;

import pt.iscte.paddle.model.IArrayType;
import pt.iscte.paddle.model.IType;

public class DeclarationWidget extends EditorWidget {
	private final Id type;
	private final Id id;
	private final ExpressionWidget expression;

	DeclarationWidget(Composite parent) {
		this(parent, IType.UNBOUND, "variable", "expression");
	}
	
	DeclarationWidget(Composite parent, IType type, String id, String expression) {
		super(parent);
		setLayout(Constants.ROW_LAYOUT_H);
		String typeId = type.getId();
		int dims = 0;
		if(type instanceof IArrayType) {
			typeId = ((IArrayType) type).getComponentType().getId();
			dims = ((IArrayType) type).getDimensions();
		}
		this.type = new Id(this, typeId, true, Constants.PRIMITIVE_TYPES_SUPPLIER);
		while(dims-- > 0)
			this.type.addDimension();
		this.id = new Id(this, id, false);
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
