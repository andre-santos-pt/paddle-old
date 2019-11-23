package pt.iscte.paddle.javasde;

public class InstructionWidget extends EditorWidget {
	private ExpressionWidget expressionWidget;
	private Token keyword;
	
	InstructionWidget(EditorWidget parent, String keyword) {
		this(parent, keyword, null);
	}
	
	InstructionWidget(EditorWidget parent, String keyword, String expression) {
		super(parent);
		setLayout(Constants.ROW_LAYOUT_H_ZERO);
		this.keyword = new Token(this, keyword);
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
	
	boolean is(String keyword) {
		return this.keyword.isKeyword(keyword);
	}
}
