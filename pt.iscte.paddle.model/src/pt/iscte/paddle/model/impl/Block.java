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
import pt.iscte.paddle.model.IStatementContainer;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariable;
import pt.iscte.paddle.model.IVariableAssignment;

class Block extends ListenableProgramElement<IBlock.IListener> implements IBlock {
	private final IProgramElement parent;
	private final List<IBlockElement> children;

	Block(IProgramElement parent, boolean addToParent) {
		assert !addToParent || parent instanceof Block;
		
		this.parent = parent;
		this.children = new ArrayList<>();
		if(parent != null && addToParent)
			((Block) parent).addChild(this);
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
	
	void addChild(IBlockElement e) {
		assert e != null;
		children.add(e);
		getListeners().forEachRemaining(l -> l.elementAdded(e, children.size()-1));
	}

	void remove(Statement e) {
		int i = children.indexOf(e);
		if(children.remove(e)) {
			getListeners().forEachRemaining(l -> l.elementRemoved(e, i));
		}
	}
	
	@Override
	public IBlock addBlock(IProgramElement parent) {
		return new Block(parent, true);
	}
	
	IBlock addLooseBlock(IProgramElement parent) {
		return new Block(parent, false);
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
	public IVariable addVariable(IType type) {		
		Variable var = new Variable(this, type);
		IProcedure procedure = getProcedure();
		((Procedure) procedure).addVariableDeclaration(var);
		addChild(var);
		return var;
	}
	
	@Override
	public IVariableAssignment addAssignment(IVariable variable, IExpression expression) {
		// TODO OCL: variable must be owned by the same procedure of expression
		return new VariableAssignment(this, variable, expression);
	}

	@Override
	public IArrayElementAssignment addArrayElementAssignment(IExpression target, IExpression exp, List<IExpression> indexes) {
		// TODO OCL: variable must be owned by the same procedure of expression
		return new ArrayElementAssignment(this, target, indexes, exp);
	}
		
	@Override
	public IRecordFieldAssignment addRecordFieldAssignment(IRecordFieldExpression target, IExpression exp) {
		return new RecordFieldAssignment(this, target, exp);
	}
	
	@Override
	public ISelection addSelection(IExpression guard) {
		return new Selection(this, guard, false);
	}

	@Override
	public ISelection addSelectionWithAlternative(IExpression guard) {
		return new Selection(this, guard, true);
	}
	
	@Override
	public ILoop addLoop(IExpression guard) {
		return new Loop(this, guard);
	}

	@Override
	public IReturn addReturn() {
		return new Return(this);
	}
	
	@Override
	public IReturn addReturn(IExpression expression) {
		return new Return(this, expression);
	}
	
	@Override
	public IProcedureCall addCall(IProcedure procedure, List<IExpression> args) {
		return new ProcedureCall(this, procedure, args);
	}
	
	@Override
	public IBreak addBreak() {
		return new Break(this);
	}
	
	@Override
	public IContinue addContinue() {
		return new Continue(this);
	}
}
