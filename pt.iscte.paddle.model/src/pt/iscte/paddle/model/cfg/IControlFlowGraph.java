package pt.iscte.paddle.model.cfg;

import java.util.Set;

import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IStatement;
import pt.iscte.paddle.model.cfg.impl.ControlFlowGraph;

public interface IControlFlowGraph {
	IProcedure getProcedure();
	Set<INode> getNodes();
	INode getEntryNode();
	INode getExitNode();
//	default Set<Node> getDeadNodes() {
//		HashSet<Node> dead = new HashSet<>();
//		getNodes().forEach(n -> i);
//	}

	IStatementNode newStatement(IStatement statement);
	
	IBranchNode newBranch(IExpression expression);
	
	static IControlFlowGraph create(IProcedure procedure) {
		return new ControlFlowGraph(procedure);
	}
	
}
