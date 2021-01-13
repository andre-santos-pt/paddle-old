package pt.iscte.paddle.model.cfg.impl;

import java.util.ArrayList;
import java.util.List;

import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IBlockElement;
import pt.iscte.paddle.model.IControlStructure;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.cfg.IBranchNode;
import pt.iscte.paddle.model.cfg.IControlFlowGraph;
import pt.iscte.paddle.model.cfg.INode;
import pt.iscte.paddle.model.cfg.impl.Visitor.SelectionNode;

public class Handler {

	IControlFlowGraph cfg;
	Visitor visitor;

	public Handler(IControlFlowGraph cfg, Visitor visitor) {
		this.cfg = cfg;
		this.visitor = visitor;

		if(cfg == null) 
			throw new NullPointerException("The provided Control Flow Graph must be a valid one!");
	}

	/**
	 * @function HandleStatementVisit
	 * @description Checks for the type of the previous node, and acts accordingly. This meaning that it will place itself has the next Statement node for the previous one.
	 * @param INode The statement that is has been visited and will be inserted in the Control Flow Graph.
	 */
	public void handleStatementVisit(INode statement) {

		if(visitor.getCurrentBranchType() != BranchType.ALTERNATIVE) {
			this.setLastSelectionBranch(statement);
			this.handleOrphansAdoption(statement);
		}


		this.setLastBreakNext(statement);
		this.setLastSelectionNext(statement);
		this.setLastLoopNext(statement);

		INode lastNode = visitor.getLastNode();


		if(lastNode == null) 
			this.cfg.getEntryNode().setNext(statement);
		else if(lastNode instanceof IBranchNode && !((IBranchNode) lastNode).hasBranch()) {

			//			if(visitor.getCurrentBranchType() != null && visitor.getCurrentBranchType().equals(BranchType.ALTERNATIVE)) ((IBranchNode) lastNode).setNext(statement); 
			//			else {
			((IBranchNode) lastNode).setBranch(statement);
			//			}
		}


		/* The middle condition is duo to the possibility of having an assignment inside an else, that can't be set as the lastNode's next.*/
		else if(lastNode != null && !(lastNode instanceof IBranchNode) 
				&& (visitor.getSelectionNodeStack().size() == 0 || !visitor.getSelectionNodeStack().peek().orphans.contains(lastNode)) 
				&& lastNode.getNext() == null)
			lastNode.setNext(statement);

	}

	/**
	 * @function handleLoopBranchVisit
	 * @description Checks for the type of the previous node, and acts accordingly. 
	 * This meaning that it will place itself has the next loop Branch node for the previous one.
	 * @param IBranchNode branch The statement that is has been visited and will be inserted in the Control Flow Graph.
	 */
	public void handleBranchVisit(IBranchNode branch) {

		this.setLastSelectionNext(branch);
		this.setLastLoopNext(branch);
		this.setLastBreakNext(branch);

		if(visitor.getCurrentBranchType() != BranchType.ALTERNATIVE)
			this.handleOrphansAdoption(branch);

		INode lastNode = visitor.getLastNode();
		if(lastNode instanceof IBranchNode && !((IBranchNode) lastNode).hasBranch()) ((IBranchNode) lastNode).setBranch(branch);
		else if(lastNode != null && lastNode.getNext() == null) lastNode.setNext(branch);
		else if( lastNode == null ) this.cfg.getEntryNode().setNext(branch);
	}

	static private List<IBlockElement> getRawChildren(IBlock block) {
		ArrayList<IBlockElement> children = new ArrayList<IBlockElement>();
		if(block == null || block != null && block.getChildren().size() == 0) return children;

		for(IBlockElement el: block.getBlock().getChildren()) {
			if(el instanceof IBlock) children.addAll(getRawChildren((IBlock) el));
			else {
				boolean contains = false;
				for(IBlockElement el2: children) {
					if(el.isSame(el2)) contains = true;
				}
				if(!contains) children.add(el);
			}
		}
		return children;
	}

	public void setLastLoopNext(INode node) {
		if(visitor.getLastLoopNode() == null) return;
		// A maior martelada da minha vida.
		
		
		if(visitor.currentSelectionNode != null) {
			
			IControlStructure s =  visitor.currentSelectionNode.node.getElement().getProperty(IControlStructure.class);
			IControlStructure s2 =  visitor.getLastLoopNode().getElement().getProperty(IControlStructure.class);
			
			ISelection selection = (ISelection) s;
//			System.out.println();
//			System.out.println("AQUIIII2: " + node.getElement() + " - "+ s);
//			System.out.println();
			if( selection.hasAlternativeBlock() 
					&& getRawChildren(selection.getAlternativeBlock()).contains(node.getElement())
					&& !getRawChildren(selection.getAlternativeBlock()).contains(s2))
			{
				//				visitor.globalOrphans.add(visitor.getLastLoopNode());
				
				return;
			}
			if(selection.hasAlternativeBlock()) {
				for(IBlockElement el : selection.getAlternativeBlock().getChildren()) {
					if(el instanceof IControlStructure && (
							((IControlStructure) el).getBlock().getChildren().contains(node.getElement())
							|| ((IControlStructure) el).getGuard().isSame(node.getElement())
							) && !getRawChildren(selection.getAlternativeBlock()).contains(s2)) {
						//						visitor.globalOrphans.add(visitor.getLastLoopNode());
//						System.out.println("AQUIIII1: " + node.getElement());
						return;
					}
				}
			}
		}
//		System.out.println("lastLoopNExt: " + node.getElement() + " - " + visitor.getLastLoopNode());
		if(visitor.getLastLoopNode().getNext() == null || visitor.getLastNode().getNext() != null) {
			visitor.getLastLoopNode().setNext(node);
			visitor.setLastLoopNode(null);
		}
	}

	public void setLastSelectionNext(INode node) {
		
		//		System.out.println();
		//		System.out.println("node: " + node.getElement());
		//		System.out.println("lastLoop: " + visitor.getLastLoopNode());
		//		System.out.println("lastSelection: " 
		//				+ ( (visitor.getLastSelectionNode() != null && visitor.getLastSelectionNode().node != null) ? visitor.getLastSelectionNode().node : null) );
		//		System.out.println(visitor.getLastSelectionBranch() != null
		//				&& visitor.getLastSelectionBranch().getNext() == null);
		//		System.out.println();
		if(visitor.getLastSelectionNode() == null) return;
		//		IControlStructure s =  visitor.getLastSelectionNode().node.getElement().getProperty(IControlStructure.class);
		//		if(s != null && s instanceof ISelection) {
		//			ISelection selection = (ISelection) s;
		//			if(selection.hasAlternativeBlock()  
		//					&& node.getElement() instanceof IBlockElement 
		//					&& selection.getAlternativeBlock().getChildren().contains((IBlockElement) node.getElement()))
		//				{
		//				System.out.println("EU PAREI AQUI!!!!! " + node.getElement());
		////				return;
		//				}
		//		}
		if(visitor.getLastSelectionBranch().getNext() == null) {
			visitor.getLastSelectionNode().node.setNext(node);
			if(visitor.getLastSelectionOrphans().isEmpty()) visitor.setlastSelectionNode(null);
		}
	}

	public void setLastSelectionBranch(INode node) {
		if(visitor.getLastSelectionBranch() != null && !visitor.getLastSelectionBranch().hasBranch()) {
			visitor.getLastSelectionBranch().setBranch(node);
		}
	}
	
	static boolean containsNumber(int[] vector, int number) {
		boolean contains = false;
		for(int i = 0; i < vector.length; i++ ) {
			if(vector[i] == number)
				contains = true;
		}
		return contains;
	}


	public void setLastBreakNext(INode node) {
		if(visitor.getLastBreakStatement() != null) {
			visitor.getLastBreakStatement().setNext(node);
			visitor.setLastBreakStatement(null);
		}
	}

	public void handleOrphansAdoption(INode node) {
		//		System.out.println("quer adotar: " + node.getElement() + " mas : " + (visitor.getLastSelectionNode() != null
		//				&& visitor.getLastSelectionOrphans().size() > 0));
		if(visitor.getLastSelectionNode() != null
				&& visitor.getLastSelectionOrphans().size() > 0) {
//			System.out.println("o nó : " + node.getElement() + " vai adotar: " + visitor.getLastSelectionNode().orphans);
			adoptOrphans(visitor.getLastSelectionNode(), node);
		}

	}

	public void adoptOrphans(SelectionNode selection, INode parent) {
		if(visitor.getCurrentBranchType() != BranchType.ALTERNATIVE) {
			selection.orphans.forEach(node -> {
				IControlStructure s = node.getElement().getProperty(IControlStructure.class);
//				System.out.println("XICOOOOOO: " + parent.getElement() + " - " + node.getElement() + " - " + s);

				//				System.out.println("orphan: " + node.getElement());
				if(node.getNext() == null) node.setNext(parent);
			});

			selection.orphans.clear();
		}
	}

	public void updateOrphansList(INode orphan) {
//		System.out.println("i am orphan: " + orphan.getElement() + " - " + (orphan.getNext() == null));
		/* Pushes the last node to the current level orphans list. */
		if(orphan.getNext() == null
				//				&& visitor.getLastSelectionNode() == null
				&& (!visitor.getSelectionNodeStack().isEmpty())
				&& !visitor.getSelectionNodeStack().peek().orphans.contains(orphan))
		{
//			System.out.println("pushed an orphan: " + orphan.getElement());
			visitor.getSelectionNodeStack().peek().orphans.add(orphan);
		}
	}

	public void setReturnStatementNext(INode ret) {
		INode lastNode = visitor.getLastNode();
		setLastSelectionNext(ret);
		setLastLoopNext(ret);
		handleOrphansAdoption(ret);
		
		if(lastNode instanceof IBranchNode && !((IBranchNode) lastNode).hasBranch()) ((IBranchNode) lastNode).setBranch(ret);
		else if(lastNode != null && lastNode.getNext() == null) {
			
			if(visitor.getLastSelectionNode() != null) {
				IControlStructure s =  visitor.getLastSelectionNode().node.getElement().getProperty(IControlStructure.class);
				if(s != null && s instanceof ISelection) {
					ISelection selection = (ISelection) s;
					if(selection.hasAlternativeBlock()  
							&& lastNode.getElement() instanceof IBlockElement 
							&& getRawChildren(selection.getAlternativeBlock()).contains((IBlockElement) ret.getElement()))
					{
						updateOrphansList(lastNode);
						return;
					}
				}
			}
			
			lastNode.setNext(ret);
		}
		else if (lastNode == null) cfg.getEntryNode().setNext(ret);
		//		else lastNode.getNext().setNext(ret);
	}

}
