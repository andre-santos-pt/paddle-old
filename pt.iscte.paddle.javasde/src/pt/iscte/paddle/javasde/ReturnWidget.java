package pt.iscte.paddle.javasde;

public class ReturnWidget extends EditorWidget {
	private ExpressionWidget expressionWidget;
	
	ReturnWidget(EditorWidget parent) {
		this(parent, "expression");
	}
	
	ReturnWidget(EditorWidget parent, String expression) {
		super(parent);
		setLayout(ROW_LAYOUT_H_ZERO);
		new Token(this, "return");
		expressionWidget = new ExpressionWidget(this, expression);
		new Token(this, ";");
	}
	
	@Override
	public boolean setFocus() {
		expressionWidget.setFocus();
		return true;
	}
}
