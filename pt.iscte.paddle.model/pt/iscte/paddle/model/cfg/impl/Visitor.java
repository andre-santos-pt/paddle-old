package pt.iscte.paddle.model.cfg.impl;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IProcedureCall;
import pt.iscte.paddle.model.IRecordFieldAssignment;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IVariableAssignment;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import pt.iscte.paddle.model.IBlock.IVisitor;
import pt.iscte.paddle.model.IArrayElementAssignment;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IBreak;
import pt.iscte.paddle.model.IContinue;
import pt.iscte.paddle.model.cfg.IBranchNode;
import pt.iscte.paddle.model.cfg.IControlFlowGraph;
import pt.iscte.paddle.model.cfg.INode;
import pt.iscte.paddle.model.cfg.IStatementNode;

public class Visitor implements IVisitor {

	private IControlFlowGraph cfg;
	private Handler handler;

	private INode lastNode = null;
	private SelectionNode lastSelectionNode = null;
	private IBranchNode lastLoopNode = null;
	private IStatementNode lastBreakStatement = null;
	private BranchType currentBranchType = null;

	private Deque<SelectionNode> selectionNodeStack;
	private Deque<IBranchNode> loopNodeStack;
	private Deque<BreakNode> breakNodeStack;

	SelectionNode currentSelectionNode;

	public static class SelectionNode {
		final IBranchNode node;

		final List<INode> orphans;

		SelectionNode(IBranchNode if_branch) {
			node = if_branch;
			orphans = new ArrayList<INode>();
		}
	}

	public static class BreakNode {
		IStatementNode node;
		final IBlock parent;

		BreakNode(IStatementNode breakStatement, IBlock parentBlock) {
			node = breakStatement;
			parent = parentBlock;
		}
	}

	public Visitor(IControlFlowGraph cfg) { 
		this.cfg = cfg;
		this.handler = new Handler(cfg, this);

		this.selectionNodeStack = new ArrayDeque<>();
		this.loopNodeStack = new ArrayDeque<>();
		this.breakNodeStack = new ArrayDeque<>();
	}

	@Override
	public boolean visit(IArrayElementAssignment assignment) {
		IStatementNode assignment_statement = cfg.newStatement(assignment);
		handler.handleStatementVisit(assignment_statement);
		setLastNode(assignment_statement);
		return true;
	}

	@Override
	public boolean visit(IRecordFieldAssignment assignment) {
		IStatementNode assignment_statement = cfg.newStatement(assignment);

		handler.handleStatementVisit(assignment_statement);
		setLastNode(assignment_statement);
		return false;
	}


	@Override
	public boolean visit(IProcedureCall call) {
		for(INode node: cfg.getNodes()) {
			if(node.getElement() != null && node.getElement().hashCode() == call.hashCode())
				return false;
		}

		IStatementNode assignment_statement = cfg.newStatement(call);

		handler.handleStatementVisit(assignment_statement);
		setLastNode(assignment_statement);
		return false;
	}

	@Override
	public boolean visit(IVariableAssignment assignment) {
		IStatementNode assignment_statement = cfg.newStatement(assignment);

		handler.handleStatementVisit(assignment_statement);
		setLastNode(assignment_statement);
		return false;
	}

	@Override
	public boolean visit(ISelection selection) {
		setCurrentBranchType(BranchType.SELECTION);

		IBranchNode if_branch = cfg.newBranch(selection.getGuard());

		/* Checks if the previous node is a statement or a branch, and acts accordingly. */
		handler.handleBranchVisit(if_branch);		

		SelectionNode sNode = new SelectionNode(if_branch);
		this.selectionNodeStack.push(sNode);
		this.currentSelectionNode = sNode;
		setLastNode(if_branch);
		return true;
	}

	// Se houver loop acima, definir o next do last node para esse loop, sem por a o lastLoopNode a null
	@Override
	public void endVisit(ISelection selection) {
		setCurrentBranchType(null);

		//		if(getLastSelectionBranch() != null && getLastSelectionBranch().getNext() == null) 
		//			selectionNodeStack.peek().orphans.add(getLastSelectionBranch());

		if(!selectionNodeStack.isEmpty()) {
			if(getLastSelectionNode() != null) 
				handler.updateOrphansList(getLastSelectionNode().node); 
			
			SelectionNode n = selectionNodeStack.pop();
			setlastSelectionNode(n);
			this.currentSelectionNode = selectionNodeStack.peek();
		}
		else this.currentSelectionNode = null;
	}

	@Override
	public void endVisitBranch(ISelection selection) {
		if(selectionNodeStack.size() > 0) setCurrentBranchType(BranchType.SELECTION);
		else setCurrentBranchType(null);

		handler.updateOrphansList(lastNode);

		/* Because of else's after selection, that needs to be set as next.*/
		setlastSelectionNode(selectionNodeStack.peek());
	}

	@Override
	public boolean visitAlternative(ISelection selection) {
		setCurrentBranchType(BranchType.ALTERNATIVE);

		handler.updateOrphansList(lastNode);

		return IVisitor.super.visitAlternative(selection);
	}

	@Override
	public void endVisitAlternative(ISelection selection) {
		if(selectionNodeStack.size() > 0) setCurrentBranchType(BranchType.SELECTION);
		else setCurrentBranchType(null);

		if(!selection.getAlternativeBlock().isEmpty()) 
			handler.updateOrphansList(getLastNode());
	}

	@Override
	public boolean visit(ILoop loop) {
		IBranchNode loop_branch = cfg.newBranch(loop.getGuard());
		loopNodeStack.push(loop_branch);

		handler.handleBranchVisit(loop_branch);

		setLastNode(loop_branch);
		return true;
	}

	@Override
	public void endVisit(ILoop loop) {
		if(loopNodeStack.isEmpty()) return;

		if(getLastLoopNode() != null)
			handler.updateOrphansList(getLastLoopNode()); 

		IBranchNode finishedLoopBranch = loopNodeStack.pop();

		/* When inside a loop and there isn't a statement after the selections. */
		handler.setLastBreakNext(finishedLoopBranch);
		handler.handleOrphansAdoption(finishedLoopBranch);

		/* When a loop inside another loop finishes and there no statements of branches left before ending the wrapping loop, so
		 * the inside loop's next needs to become the wrapping loop.*/
		handler.setLastLoopNext(finishedLoopBranch);
		handler.setLastSelectionBranch(finishedLoopBranch);

		if(lastNode != null && lastNode.getNext() == null) lastNode.setNext(finishedLoopBranch);

		/* Checks for the existance of break nodes, that set's the last one as the lastBreakStatement node. */
		if(breakNodeStack.size() > 0 && !breakNodeStack.peekLast().parent.equals(loop.getParent()))
			setLastBreakStatement(breakNodeStack.pollLast().node);

		setLastLoopNode(finishedLoopBranch);
	}

	@Override
	public boolean visit(IReturn returnStatement) {
		IStatementNode ret = cfg.newStatement(returnStatement);


		handler.setReturnStatementNext(ret);

		ret.setNext(cfg.getExitNode());
		setLastNode(ret);

		return false;
	}

	@Override
	public void visit(IContinue continueStatement) {
		IStatementNode continue_statement = cfg.newStatement(continueStatement);

		if(lastNode instanceof IBranchNode) ((IBranchNode) lastNode).setBranch(continue_statement);
		else lastNode.setNext(continue_statement);

		if(loopNodeStack.peek() != null) continue_statement.setNext(loopNodeStack.peek());

		setLastNode(continue_statement);
	}

	@Override
	public void visit(IBreak breakStatement) {
		IStatementNode break_statement = cfg.newStatement(breakStatement);

		if(lastNode instanceof IBranchNode) ((IBranchNode) lastNode).setBranch(break_statement);
		else lastNode.setNext(break_statement);

		breakNodeStack.add(new BreakNode(break_statement, breakStatement.getParent()));
	}

	public IBranchNode getLastLoopNode() {
		return lastLoopNode;
	}
	public void setCurrentBranchType(BranchType currentBranchType) {
		this.currentBranchType = currentBranchType;
	}
	public BranchType getCurrentBranchType() {
		return currentBranchType;
	}
	public Deque<SelectionNode> getSelectionNodeStack() {
		return selectionNodeStack;
	}
	public void setSelectionNodeStack(Deque<SelectionNode> selectionNodeStack) {
		this.selectionNodeStack = selectionNodeStack;
	}
	public Deque<IBranchNode> getLoopNodeStack() {
		return loopNodeStack;
	}
	public void setLoopNodeStack(Deque<IBranchNode> loopNodeStack) {
		this.loopNodeStack = loopNodeStack;
	}
	public Deque<BreakNode> getBreakNodeStack() {
		return breakNodeStack;
	}
	public void setBreakNodeStack(Deque<BreakNode> breakNodeStack) {
		this.breakNodeStack = breakNodeStack;
	}
	public INode getLastNode() {
		return lastNode;
	}
	public IStatementNode getLastBreakStatement() {
		return lastBreakStatement;
	}
	public void setLastSelectionNode(SelectionNode lastSelectionNode) {
		this.lastSelectionNode = lastSelectionNode;
	}
	public SelectionNode getLastSelectionNode() {
		return lastSelectionNode;
	}
	public IBranchNode getLastSelectionBranch() {
		if(this.lastSelectionNode != null && lastSelectionNode.node != null) return lastSelectionNode.node;
		else return null;
	}
	public List<INode> getLastSelectionOrphans() {
		if(this.lastSelectionNode != null && lastSelectionNode.orphans != null) return lastSelectionNode.orphans;
		else return null;
	}
	protected void setlastSelectionNode(SelectionNode newSelectionNode) {
		if(lastSelectionNode != null && lastSelectionNode.node.getNext() == null) newSelectionNode.orphans.add(lastSelectionNode.node);
		if(newSelectionNode != null && lastSelectionNode != null && lastSelectionNode.orphans.size() > 0)
			newSelectionNode.orphans.addAll(lastSelectionNode.orphans);
		this.lastSelectionNode = newSelectionNode;
	}
	void setLastLoopNode(IBranchNode lastLoopNode) {
		this.lastLoopNode = lastLoopNode;
	}
	public void setLastBreakStatement(IStatementNode lastBreakStatement) {
		this.lastBreakStatement = lastBreakStatement;
	}
	private void setLastNode(INode lastNode) {
		this.lastNode = lastNode;
	}
}
