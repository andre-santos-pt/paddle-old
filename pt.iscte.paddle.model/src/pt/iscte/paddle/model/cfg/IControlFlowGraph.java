package pt.iscte.paddle.model.cfg;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.IStatement;
import pt.iscte.paddle.model.cfg.impl.ControlFlowGraph;

public interface IControlFlowGraph {
	IProcedure getProcedure();
	List<INode> getNodes();
	INode getEntryNode();
	INode getExitNode();
	IStatementNode newStatement(IStatement statement);
	IBranchNode newBranch(IExpression expression);

	static IControlFlowGraph create(IProcedure procedure) {
		return new ControlFlowGraph(procedure);
	}

	default List<INode> reachability() {
		return reachability(getEntryNode());
	}

	default List<INode> reachability(INode startNode) {
		ArrayList<INode> list = new ArrayList<>();
		reachability(startNode, list);
		return list;
	}

	static void reachability(INode n, List<INode> list) {
		if(!list.contains(n)) {
			list.add(n);
			if(n.getNext() != null) {
				if(n instanceof IBranchNode)
					reachability(((IBranchNode) n).getAlternative(), list);
				
				reachability(n.getNext(), list);
			}
		}
	}
	
	default List<INode> deadNodes() {
		List<INode> set = new ArrayList<>(getNodes());
		List<INode> reachability = reachability();
		set.removeIf(n -> reachability.contains(n));
		return set;
	}
	
	default boolean isEquivalentTo(IControlFlowGraph cfg) {
		return
				checkEquivalentNodes(reachability(), cfg.reachability()) &&
				checkEquivalentNodes(deadNodes(), cfg.deadNodes());
	}
	
	static boolean checkEquivalentNodes(List<INode> a, List<INode> b) {
		if(a.size() != b.size())
			return false;
		
		for(int i = 0; i < a.size(); i++)
			if(!a.get(i).isEquivalentTo(b.get(i)))
				return false;	
		
		return true;
	}
}
