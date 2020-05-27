package pt.iscte.paddle.model.cfg.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IStatement;
import pt.iscte.paddle.model.cfg.IBranchNode;
import pt.iscte.paddle.model.cfg.IControlFlowGraph;
import pt.iscte.paddle.model.cfg.INode;
import pt.iscte.paddle.model.cfg.IStatementNode;

public class ControlFlowGraph implements IControlFlowGraph {

	private final IProcedure procedure;
	private final INode entry;
	private final INode exit;
	private final List<INode> nodes;
	
	public ControlFlowGraph(IProcedure procedure) {
		this.procedure = procedure;
		entry = new Node();
		exit = new Node();
		nodes = new ArrayList<>();
		nodes.add(entry);
		nodes.add(exit);
	}

	@Override
	public IProcedure getProcedure() {
		return procedure;
	}
	
	@Override
	public List<INode> getNodes() {
		return Collections.unmodifiableList(nodes);
	}

	@Override
	public INode getEntryNode() {
		return entry;
	}

	@Override
	public INode getExitNode() {
		return exit;
	}

	public IStatementNode newStatement(IStatement statement) {
		IStatementNode n = new StatementNode(statement);
		nodes.add(nodes.size()-1, n);
		return n;
	}
	
	public IBranchNode newBranch(IExpression expression) {
		IBranchNode n = new BranchNode(expression);
		nodes.add(nodes.size()-1, n);
		return n;
	}
}
