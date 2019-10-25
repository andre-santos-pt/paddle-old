package pt.iscte.paddle.javasde;

public class ControlWidget extends StatementWidget implements Selectable {
	
	private final boolean loop;
	private final SequenceWidget block;
	private ExpressionWidget expression;

	public ControlWidget(SequenceWidget parent, boolean loop) {
		super(parent);
		this.loop = loop;
		setLayout(ROW_LAYOUT_V_ZERO);
		EditorWidget header = new EditorWidget(this);
		header.setLayout(ROW_LAYOUT_H_ZERO);
		new Token(header, loop ? "while" : "if");
		new Token(header, "(");
		expression = new ExpressionWidget(header, "true");
		new Token(header, ")");
		new Token(header, "{");
		block = new SequenceWidget(this);
		new Token(this, "}");
	}
	
	@Override
	public boolean setFocus() {
		expression.setFocus();
		return true;
	}
	
	@Override
	public void toCode(StringBuffer buffer) {
		buffer.append((loop ? "while" : "if") + "(");
		expression.toCode(buffer);
		buffer.append(") {").append(System.lineSeparator());
		block.toCode(buffer);
		buffer.append("}").append(System.lineSeparator());
	}
	
}
