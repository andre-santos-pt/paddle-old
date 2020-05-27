package pt.iscte.paddle.model.cfg;

import pt.iscte.paddle.model.IExpression;

public interface IBranchNode extends INode {
	INode getAlternative(); // true
	IExpression getElement();
	void setBranch(INode node);
	boolean hasBranch();
}
