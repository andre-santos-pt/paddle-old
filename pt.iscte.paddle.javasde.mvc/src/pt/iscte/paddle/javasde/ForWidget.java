package pt.iscte.paddle.javasde;

import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IType;

public class ForWidget extends EditorWidget implements StatementContainer {
	
	private final SequenceWidget blockSeq;
	private ExpressionWidget guard;
	private DeclarationWidget dec;
//	private AssignmentWidget statement;
	private ExpressionWidget statement;
	ForWidget(SequenceWidget parent, IType type, String id, String expression, String guard, IBlock block) {
		super(parent);
		setLayout(Constants.ROW_LAYOUT_V_ZERO);
		EditorWidget header = new EditorWidget(this);
		header.setLayout(Constants.ROW_LAYOUT_H_ZERO);
		new Token(header, Keyword.FOR);
		new FixedToken(header, "(");
		dec = new DeclarationWidget(header, type, id, expression);
		this.guard = new ExpressionWidget(header, guard);
		new FixedToken(header, ";");
		statement = new ExpressionWidget(header, "statement");
		new FixedToken(header, ")");
		new FixedToken(header, "{");
		blockSeq = new SequenceWidget(this, Constants.TAB);
		blockSeq.addStatementCommands(block);
		blockSeq.addBlockListener(block);
		new FixedToken(this, "}");
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
