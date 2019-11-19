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
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariable;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.commands.IAddCommand;

class Block extends ListenableProgramElement<IBlock.IListener> implements IBlock {
	private final IProgramElement parent;
	private final List<IBlockElement> children;

	Block(IProgramElement parent, boolean addToParent, int index) {
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
			children.add(index, element);
			getListeners().forEachRemaining(l -> l.elementAdded(element, children.size()-1));
		}

		@Override
		public void undo() {
			int i = children.indexOf(element);
			children.remove(element);
			getListeners().forEachRemaining(l -> l.elementRemoved(element, i));
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
//		children.add(e);
//		getListeners().forEachRemaining(l -> l.elementAdded(e, children.size()-1));
	}

	void remove(Statement e) {
		int i = children.indexOf(e);
		if(children.remove(e)) {
			getListeners().forEachRemaining(l -> l.elementRemoved(e, i));
		}
	}
	
	@Override
	public IBlock addBlock(IProgramElement parent, int index) {
		return new Block(parent, true, index);
	}
	
	IBlock addLooseBlock(IProgramElement parent, int index) {
		return new Block(parent, false, index);
	}
	
	@Override
	public String toString() {
		String tabs = "";
		int d = getDepth();
		for(int i = 0; i < d; i++)
			tabs += "\t";
		String text = "{\n";
		for(IBlockElement s : children) {
			if(s instanceof IVariable) {
				IVariable v = (IVariable) s;
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
	public IVariable addVariable(IType type, int index) {		
		Variable var = new Variable(this, type);
		IProcedure procedure = getProcedure();
		((Procedure) procedure).addVariableDeclaration(var);
		addChild(var, index);
		return var;
	}
	
	@Override
	public IVariableAssignment addAssignment(IVariable variable, IExpression expression, int index) {
		// TODO OCL: variable must be owned by the same procedure of expression
		return new VariableAssignment(this, variable, expression, index);
	}

	@Override
	public IArrayElementAssignment addArrayElementAssignment(IExpression target, IExpression exp, int index, List<IExpression> indexes) {
		// TODO OCL: variable must be owned by the same procedure of expression
		return new ArrayElementAssignment(this, target, exp, index, indexes);
	}
		
	@Override
	public IRecordFieldAssignment addRecordFieldAssignment(IRecordFieldExpression target, IExpression exp, int index) {
		return new RecordFieldAssignment(this, target, exp, index);
	}
	
	@Override
	public ISelection addSelection(IExpression guard, int index) {
		return new Selection(this, guard, false, index);
	}

	@Override
	public ISelection addSelectionWithAlternative(IExpression guard, int index) {
		return new Selection(this, guard, true, index);
	}
	
	@Override
	public ILoop addLoop(IExpression guard, int index) {
		return new Loop(this, guard, index);
	}

	@Override
	public IReturn addReturn(int index) {
		return new Return(this, index);
	}
	
	@Override
	public IReturn addReturn(IExpression expression, int index) {
		return new Return(this, expression, index);
	}
	
	@Override
	public IProcedureCall addCall(IProcedure procedure, int index, List<IExpression> args) {
		return new ProcedureCall(this, procedure, index, args);
	}
	
	@Override
	public IBreak addBreak(int index) {
		return new Break(this, index);
	}
	
	@Override
	public IContinue addContinue(int index) {
		return new Continue(this, index);
	}
}
