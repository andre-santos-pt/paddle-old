package pt.iscte.paddle.javasde;

import org.eclipse.swt.widgets.Control;

public class WhileWidget extends EditorWidget implements StatementContainer {
	
	private final SequenceWidget block;
	private ExpressionWidget expression;
	private Control addLabel;
	
	WhileWidget(SequenceWidget parent, String expression) {
		super(parent);
		setLayout(ROW_LAYOUT_V_ZERO);
		EditorWidget header = new EditorWidget(this);
		header.setLayout(ROW_LAYOUT_H_ZERO);
		new Token(header, "while");
		new Token(header, "(");
		this.expression = new ExpressionWidget(header, expression);
		new Token(header, ")");
		new Token(header, "{");
		block = new SequenceWidget(this);
		addLabel = createAddLabel(this, "}");
		addLabel.setMenu(block.createMenu(addLabel, false));
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
		return addLabel;
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
