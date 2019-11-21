package pt.iscte.paddle.javasde;

public class ArrayElementExpression extends EditorWidget {

	private EditorWidget id;
	private ExpressionWidget expression;

	public ArrayElementExpression(EditorWidget parent, String varId) {
		super(parent, parent.mode);
		id = createId(this, varId);
		new Token(this, "[");
		expression = new ExpressionWidget(this);
		new Token(this, "]");
		
	}

	@Override
	public boolean setFocus() {
		id.setFocus();
		return true;
	}
	

	public void focusExpression() {
		id.setForeground(Constants.FONT_COLOR);
		id.requestLayout();
		expression.setFocus();
	}
}
