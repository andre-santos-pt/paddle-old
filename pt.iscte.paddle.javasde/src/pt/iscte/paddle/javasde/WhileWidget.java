package pt.iscte.paddle.javasde;

import org.eclipse.swt.widgets.Control;

public class WhileWidget extends EditorWidget implements StatementContainer {
	
	private final SequenceWidget block;
	private ExpressionWidget expression;
	private Control closeBlock;
	
	WhileWidget(SequenceWidget parent, String expression) {
		super(parent);
		setLayout(ROW_LAYOUT_V_ZERO);
		EditorWidget header = new EditorWidget(this);
		header.setLayout(ROW_LAYOUT_H_ZERO);
		new Token(header, "while");
		new Token(header, "(");
		this.expression = new ExpressionWidget(header, expression);
		new Token(header, ")");
		Token openBlock = new Token(header, "{");
		block = new SequenceWidget(this);
		closeBlock = createAddLabel(this, "}");
		closeBlock.setMenu(block.createMenu(closeBlock, false));
		openBlock.setSibling(closeBlock);
	}
	
	@Override
	public boolean setFocus() {
		expression.setFocus();
		return true;
	}

	@Override
	public SequenceWidget getBody() {
		return block;
	}
	
	@Override
	public Control getTail() {
		return closeBlock;
	}
	
	@Override
	public void toCode(StringBuffer buffer) {
		buffer.append("while" + "(");
		expression.toCode(buffer);
		buffer.append(") {").append(System.lineSeparator());
		block.toCode(buffer);
		buffer.append("}").append(System.lineSeparator());
	}
	
}
