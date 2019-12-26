package pt.iscte.paddle.model.cfg.impl;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

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
	private final Set<INode> nodes;
	
	public ControlFlowGraph(IProcedure procedure) {
		this.procedure = procedure;
		entry = new Node();
		exit = new Node();
		nodes = new LinkedHashSet<>();
		nodes.add(entry);
		nodes.add(exit);
	}

	@Override
	public IProcedure getProcedure() {
		return procedure;
	}
	
	@Override
	public Set<INode> getNodes() {
		return Collections.unmodifiableSet(nodes);
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
		nodes.add(n);
		return n;
	}
	
	public IBranchNode newBranch(IExpression expression) {
		IBranchNode n = new BranchNode(expression);
		nodes.add(n);
		return n;
	}
}
