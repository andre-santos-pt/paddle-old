package pt.iscte.paddle.javasde;

import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class InstructionWidget extends EditorWidget {
	private final Token keyword;
	private final ExpressionWidget expressionWidget;
	
	InstructionWidget(EditorWidget parent, Keyword keyword) {
		this(parent, keyword, null);
	}
	
	InstructionWidget(EditorWidget parent, Keyword keyword, String expression) {
		super(parent);
		setLayout(Constants.ROW_LAYOUT_H_ZERO);
		this.keyword = new Token(this, keyword);
		if(expression != null)
			expressionWidget = new ExpressionWidget(this, expression);
		else
			expressionWidget = null;
		new FixedToken(this, ";");
	}
	
	@Override
	public boolean setFocus() {
		return keyword.setFocus();
	}
	
	void focusExpression() {
		if(expressionWidget != null)
			expressionWidget.setFocus();
	}
	
	boolean is(String keyword) {
		return this.keyword.isKeyword(keyword);
	}
	
	@Override
	void addTokenKeyHandler(KeyListener listener) {
		keyword.addKeyListener(listener);
	}
}
