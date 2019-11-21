package pt.iscte.paddle.javasde;

import pt.iscte.paddle.model.IBlock;

public class ForWidget extends EditorWidget implements StatementContainer {
	
	private final SequenceWidget blockSeq;
	private ExpressionWidget guard;
	private DeclarationWidget dec;
	private AssignmentWidget assignment;
	
	ForWidget(SequenceWidget parent, String type, String id, String expression, String guard, IBlock block) {
		super(parent);
		setLayout(Constants.ROW_LAYOUT_V_ZERO);
		EditorWidget header = new EditorWidget(this);
		header.setLayout(Constants.ROW_LAYOUT_H_ZERO);
		new Token(header, "for");
		new Token(header, "(");
		dec = new DeclarationWidget(header, type, id, expression);
		this.guard = new ExpressionWidget(header, guard);
		new Token(header, ";");
		assignment = new AssignmentWidget(header,id,id+"+1", false);
		new Token(header, ")");
		new Token(header, "{");
		blockSeq = new SequenceWidget(this, Constants.TAB);
		blockSeq.addStatementCommands(block);
		blockSeq.addBlockListener(block);
		new Token(this, "}");
	}
	
	@Override
	public boolean setFocus() {
		dec.setFocus();
		return true;
	}

	@Override
	public void toCode(StringBuffer buffer) {
		buffer.append("for" + "(");
		guard.toCode(buffer);
		buffer.append(") {").append(System.lineSeparator());
		blockSeq.toCode(buffer);
		buffer.append("}").append(System.lineSeparator());
	}
}
