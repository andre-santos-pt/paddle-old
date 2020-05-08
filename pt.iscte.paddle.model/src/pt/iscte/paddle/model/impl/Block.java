package pt.iscte.paddle.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pt.iscte.paddle.model.IArrayElementAssignment;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IBlockElement;
import pt.iscte.paddle.model.IBreak;
import pt.iscte.paddle.model.IContinue;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProcedureCall;
import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.IRecordFieldAssignment;
import pt.iscte.paddle.model.IRecordFieldExpression;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IStatement;
import pt.iscte.paddle.model.ITargetExpression;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.commands.IAddCommand;
import pt.iscte.paddle.model.commands.IDeleteCommand;

class Block extends ListenableProgramElement<IBlock.IListener> implements IBlock {
	private final IProgramElement parent;
	private final List<IBlockElement> children;

	Block(IProgramElement parent, boolean addToParent, int index, String ... flags) {
		super(flags);
		assert !addToParent || parent instanceof Block;
		
		this.parent = parent;
		this.children = new ArrayList<>();
		
		if(parent != null && addToParent)
			((Block) parent).addChild(this, index);
	}

	@Override
	public IProgramElement getParent() {
		return parent;
	}
	
	@Override
	public boolean isEmpty() {
		return onlyEmptyBlocks();
	}
	
	private boolean onlyEmptyBlocks() {
		for(IBlockElement child : children)
			if(!(child instanceof Block) || child instanceof Block && !((Block) child).onlyEmptyBlocks())
				return false;
		return true;
	}
	
	@Override
	public List<IBlockElement> getChildren() {
		return Collections.unmodifiableList(children);
	}
	
	@Override
	public int getSize() {
		return children.size();
	}
	
	private class AddChild implements IAddCommand<IBlockElement> {
		final IBlockElement element;
		final int index;
		
		AddChild(IBlockElement element, int index) {
			this.element = element;
			this.index = index;
		}
		
		@Override
		public void execute() {
			assert index >= 0 && index <= Block.this.getChildren().size() : index + ": block contains " + Block.this.getChildren().size() + " children";
			children.add(index, element);
			getListeners().forEachRemaining(l -> l.elementAdded(element, index));
		}

		@Override
		public void undo() {
			children.remove(index);
			getListeners().forEachRemaining(l -> l.elementRemoved(element, index));
		}

		@Override
		public IBlockElement getElement() {
			return element;
		}

		@Override
		public IProgramElement getParent() {
			return Block.this;
		}
	}
	
	void addChild(IBlockElement e, int index) {
		assert e != null;
		((Module) getProcedure().getModule()).executeCommand(new AddChild(e, index));
	}

	private class RemoveChild implements IDeleteCommand<IBlockElement> {
		final IBlockElement element;
		final int index;
		
		RemoveChild(IBlockElement element) {
			this.element = element;
			this.index = children.indexOf(element);
			assert this.index != -1 : "element not in block";
		}
		
		@Override
		public void execute() {
			assert index >= 0 && index <= Block.this.getChildren().size() : index + ": block contains " + Block.this.getChildren().size() + " children";
			
			children.remove(element);
			getListeners().forEachRemaining(l -> l.elementRemoved(element, index));
		}

		@Override
		public void undo() {
			new AddChild(element, index).execute();
		}

		@Override
		public IBlockElement getElement() {
			return element;
		}
	}
	
	void remove(IBlockElement e) {
		assert e != null;
		((Module) getProcedure().getModule()).executeCommand(new RemoveChild(e));
	}
	
	public void removeElement(IBlockElement child)  {
		assert children.contains(child);
		remove(child);
	}
	
	@Override
	public IBlock addBlockAt(int index, String ... flags) {
		return new Block(this, true, index, flags);
	}
	
	IBlock addLooseBlock(IProgramElement parent) {
		return new Block(parent, false, -1);
	}
	
	@Override
	public String toString() {
		String tabs = "";
		int d = getDepth();
		for(int i = 0; i < d; i++)
			tabs += "\t";
		String text = "{\n";
		for(IBlockElement s : children) {
			if(s instanceof IVariableDeclaration) {
				IVariableDeclaration v = (IVariableDeclaration) s;
				text += tabs + v.getType() + " " + v.getId() + ";\n";
			}
			else if(s instanceof IStatement) {
				text += tabs + s + ";\n";
			}
			else {
				text += tabs + s;
			}
		}
		return text + tabs.substring(1) + "}\n";
	}
	
	public int getDepth() {
		if(!(parent instanceof Block))
			return 1;
		else
			return 1 + ((Block) parent).getDepth();
	}
	
	public IProcedure getProcedure() {
		if(parent instanceof Procedure)
			return (Procedure) parent;
		else if(parent == null)
			return null;
		else if(parent instanceof ControlStructure)
			return ((ControlStructure) parent).getParent().getProcedure();
		else
			return ((Block)  parent).getProcedure();
	}

	@Override
	public IVariableDeclaration addVariableAt(IType type, int index, String ... flags) {		
		return addVariableWithIdAt(type, null, index, flags);
	}
	
	@Override
	public IVariableDeclaration addVariableWithIdAt(IType type, String id, int index, String ... flags) {		
		VariableDeclaration var = new VariableDeclaration(this, type, flags);
		IProcedure procedure = getProcedure();
		((Procedure) procedure).addVariableDeclaration(var);
		var.setId(id);
		addChild(var, index);
		return var;
	}
	
	@Override
	public IVariableAssignment addAssignmentAt(IVariableDeclaration variable, IExpression expression, int index, String ... flags) {
		// TODO OCL: variable must be owned by the same procedure of expression
		return new VariableAssignment(this, variable, expression, index, flags);
	}

	@Override
	public IArrayElementAssignment addArrayElementAssignmentAt(ITargetExpression target, IExpression exp, int index, List<IExpression> indexes) {
		// TODO OCL: variable must be owned by the same procedure of expression
		return new ArrayElementAssignment(this, target, exp, index, indexes);
	}
		
	@Override
	public IRecordFieldAssignment addRecordFieldAssignmentAt(IRecordFieldExpression target, IExpression exp, int index) {
		return new RecordFieldAssignment(this, target, exp, index);
	}
	
	@Override
	public ISelection addSelectionAt(IExpression guard, int index) {
		return new Selection(this, guard, false, index);
	}

	@Override
	public ISelection addSelectionWithAlternativeAt(IExpression guard, int index) {
		return new Selection(this, guard, true, index);
	}
	
	@Override
	public ILoop addLoopAt(IExpression guard, int index, String...flags) {
		return new Loop(this, guard, index, flags);
	}

	@Override
	public IReturn addReturnAt(int index) {
		return new Return(this, index);
	}
	
	@Override
	public IReturn addReturnAt(IExpression expression, int index) {
		return new Return(this, expression, index);
	}
	
	@Override
	public IProcedureCall addCallAt(IProcedure procedure, int index, List<IExpression> args) {
		return new ProcedureCall(this, procedure, index, args);
	}
	
	@Override
	public IBreak addBreakAt(int index) {
		return new Break(this, index);
	}
	
	@Override
	public IContinue addContinueAt(int index) {
		return new Continue(this, index);
	}
}
