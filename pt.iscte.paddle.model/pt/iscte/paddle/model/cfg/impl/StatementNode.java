package pt.iscte.paddle.model.cfg.impl;

import pt.iscte.paddle.model.IStatement;
import pt.iscte.paddle.model.cfg.INode;
import pt.iscte.paddle.model.cfg.IStatementNode;

public class StatementNode extends Node implements IStatementNode {

	private final IStatement statement;
	
	StatementNode(IStatement statement) {
		assert statement != null;
		this.statement = statement;
	}
	
	@Override
	public IStatement getElement() {
		return statement;
	}
	
	@Override
	public String toString() {
		INode next = getNext();
		if(next == null)
			return statement + " (NO NEXT!)";
		else
			return statement + " >>>> " + (!next.isExit() ? next.getElement().toString() : next.toString());
	}

}
