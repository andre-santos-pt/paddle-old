package pt.iscte.paddle.javasde;

import org.eclipse.swt.events.KeyListener;

import pt.iscte.paddle.model.IBlock;

public class ControlWidget extends EditorWidget implements StatementContainer {
	
	private final SequenceWidget blockSeq;
	private ExpressionWidget expression;
	private Token keyword;
	
	ControlWidget(SequenceWidget parent, Keyword keyword, String expression, IBlock block) {
		super(parent);
		setLayout(Constants.ROW_LAYOUT_V_ZERO);
		EditorWidget header = new EditorWidget(this);
		header.setLayout(Constants.ROW_LAYOUT_H_ZERO);
		this.keyword = new Token(header, keyword);
		if(expression != null) {
			new Token(header, "(");
			this.expression = new ExpressionWidget(header, expression);
			new Token(header, ")");
		}
		new Token(header, "{");
		blockSeq = new SequenceWidget(this, Constants.TAB);
		blockSeq.addStatementCommands(block);
		blockSeq.addBlockListener(block);
		new Token(this, "}");
	}
	
	@Override
	public boolean setFocus() {
		if(expression != null)
			expression.setFocus();
		return expression != null;
	}

	@Override
	public void toCode(StringBuffer buffer) {
		buffer.append("while" + "(");
		expression.toCode(buffer);
		buffer.append(") {").append(System.lineSeparator());
		blockSeq.toCode(buffer);
		buffer.append("}").append(System.lineSeparator());
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
		super.accept(visitor);
	}
	
	boolean is(Keyword keyword) {
		return this.keyword.isKeyword(keyword.toString());
	}
	
	@Override
	void addTokenKeyHandler(KeyListener listener) {
		keyword.addKeyListener(listener);
//		keyword.addClickToFocus();
	}
}
