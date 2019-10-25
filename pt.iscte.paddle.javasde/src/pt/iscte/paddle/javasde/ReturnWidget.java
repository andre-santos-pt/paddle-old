package pt.iscte.paddle.javasde;

public class ReturnWidget extends StatementWidget implements Selectable {
	private ExpressionWidget expressionWidget;
	
	public ReturnWidget(EditorWidget parent) {
		super(parent);
		setLayout(ROW_LAYOUT_H_ZERO);
		new Token(this, "return");
		expressionWidget = new ExpressionWidget(this);
		new Token(this, ";");
	}
	
	@Override
	public boolean setFocus() {
		expressionWidget.setFocus();
		return true;
	}
}
