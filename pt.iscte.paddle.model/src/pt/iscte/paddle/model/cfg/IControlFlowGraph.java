package pt.iscte.paddle.model.cfg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pt.iscte.paddle.model.IArrayElementAssignment;
import pt.iscte.paddle.model.IBlockElement;
import pt.iscte.paddle.model.IControlStructure;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProcedureCall;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IStatement;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.cfg.IControlFlowGraph.Path;
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

	default void display() {
		getNodes().forEach(n -> System.out.println(n));
	}

	class Path {
		private List<INode> nodes = new ArrayList<INode>();

		void addNode(INode node) {
			nodes.add(node);
		}

		public boolean existsInPath(INode node) {
			return node != null ? nodes.contains(node) : false;
		}

		public List<INode> getNodes() {
			return nodes;
		}
	}
	
	default List<INode> generateSubCFG(INode source, INode destination){
		List<INode> nodes = new ArrayList<INode>();
		nodes.add(getEntryNode());
		
		List<Path> paths = pathsBetweenNodes(source, destination);
		
		for (Path path : paths) {
			path.nodes.forEach(node -> {
				if(!nodes.contains(node)) nodes.add(node);
			});
		}
		nodes.add(getExitNode());
		return nodes;
	}
	
	default boolean usedOrChangedBetween(INode source, INode destiny, IVariableDeclaration variable) {
		List<Path> paths = pathsBetweenNodes(source, destiny);
		if(paths.size() == 0) return true;
		for (Path path : paths) {

			INode first = path.getNodes().remove(0);
			INode last = path.getNodes().remove(path.getNodes().size() - 1);

			if(!((IBlockElement) first.getElement()).getParent().isSame(((IBlockElement) last.getElement()).getParent())) return true;

			for (INode node : path.getNodes()) {

				if(node.getElement() instanceof IArrayElementAssignment 
						&& (((IArrayElementAssignment) node.getElement()).getTarget().isSame(variable) 
								|| ((IArrayElementAssignment) node.getElement()).getExpression().includes(variable))) 
					return true;
				else if(node.getElement() instanceof IVariableAssignment 
						&& (((IVariableAssignment) node.getElement()).getTarget().isSame(variable) 
								|| ((IVariableAssignment) node.getElement()).getExpression().includes(variable))) 
					return true;

				else if(node.getElement() instanceof IProcedureCall) 
					for (IExpression argument : ((IProcedureCall) node.getElement()).getArguments()) 
						if(argument.includes(variable)) return true;
						else if((node.getElement() instanceof ISelection || node.getElement() instanceof ILoop) 
								&& ((IControlStructure) node.getElement()).getGuard().includes(variable)) 
							return true;
			}
		}
		return false;
	}
	
	/**
	 * Goes through the ControlFlowGraph nodes searching for all the possible paths between the supplied source and destination nodes.
	 * @param source The source node where the search will start.
	 * @param destination The destination node where the search will start.
	 * @return List with all the possible paths between the source and destination nodes.
	 */
	default List<Path> pathsBetweenNodes(INode source, INode destination) {
		List<Path> paths = new ArrayList<Path>();
		pathsBetweenNodes(paths, source, destination);
		return paths;
	}

	private static void pathsBetweenNodes(List<Path> paths, INode source, INode destination) {
		if(source.isEquivalentTo(destination)) throw new IllegalArgumentException("Source and destination nodes can't be the same!");

		Path path = new Path();
		path.addNode(source);

		if(source instanceof IBranchNode && ((IBranchNode) source).hasBranch()) pathFinder(paths, path, ((IBranchNode) source).getAlternative(), destination);
		if(source.getNext() != null && !source.getNext().isExit()) pathFinder(paths, path, source.getNext(), destination);
	}

	private static void pathFinder(List<Path> paths, Path path, INode source, INode destination) {
		boolean beenHere = false;
		if(!path.existsInPath(source))
			path.addNode(source);
		else beenHere = true;
		
		if(source.isEquivalentTo(destination)) {
			paths.add(path);
			return;
		}

		if(!beenHere && source instanceof IBranchNode && ((IBranchNode) source).hasBranch()) {
			Path replica = new Path();
			path.nodes.forEach(node -> replica.addNode(node));
			pathFinder(paths, replica, ((IBranchNode) source).getAlternative(), destination);
		}
		if(source.getNext() != null && !source.getNext().isExit()) pathFinder(paths, path, source.getNext(), destination);

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
