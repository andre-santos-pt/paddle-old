package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ILineComment;
import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.IType;

public class Comment extends ProgramElement  implements ILineComment {

	private final IProgramElement parent;
	private final String text;
	
	public Comment(IProgramElement parent, String text) {
		this.parent = parent;
		this.text = text;
	}

	@Override
	public IProgramElement getParent() {
		return parent;
	}
	
	@Override
	public List<IExpression> getExpressionParts() {
		return List.of();
	}


	@Override
	public String getText() {
		return text;
	}

}
