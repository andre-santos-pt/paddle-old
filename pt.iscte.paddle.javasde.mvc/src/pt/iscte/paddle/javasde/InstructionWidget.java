package pt.iscte.paddle.javasde;

public class InstructionWidget extends EditorWidget {
	private ExpressionWidget expressionWidget;
	
	InstructionWidget(EditorWidget parent, String keyword) {
		this(parent, keyword, null);
	}
	
	InstructionWidget(EditorWidget parent, String keyword, String expression) {
		super(parent);
		setLayout(Constants.ROW_LAYOUT_H_ZERO);
		new Token(this, keyword);
		if(expression != null)
			expressionWidget = new ExpressionWidget(this, expression);
		new Token(this, ";");
	}
	
	@Override
	public boolean setFocus() {
		if(expressionWidget != null)
			expressionWidget.setFocus();
		return expressionWidget != null;
	}
}
