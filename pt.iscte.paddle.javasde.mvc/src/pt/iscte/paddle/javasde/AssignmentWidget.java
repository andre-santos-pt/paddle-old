package pt.iscte.paddle.javasde;

public class AssignmentWidget extends EditorWidget {

	private ExpressionWidget id;
	private ExpressionWidget expression;

	AssignmentWidget(EditorWidget parent) {
		this(parent, "variable", "expression", true);
	}

	AssignmentWidget(EditorWidget parent, String id, String expression, boolean statement) {
		super(parent);
//		this.id = createId(this, id);
		this.id = new ExpressionWidget(this);
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
