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
import pt.iscte.paddle.model.IRecordFieldAssignment;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IStatement;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariable;
import pt.iscte.paddle.model.IVariableAssignment;

class Block extends ProgramElement implements IBlock {
	private final ProgramElement parent;
	private final List<IBlockElement> children;

	Block(ProgramElement parent, boolean addToParent) {
		assert !addToParent || parent instanceof Block;
		
		this.parent = parent;
		this.children = new ArrayList<>();
		if(parent != null && addToParent)
			((Block) parent).addChild(this);
	}

	@Override
	public ProgramElement getParent() {
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
	
	void addChild(IBlockElement statement) {
		assert statement != null;
		children.add(statement);
	}

	@Override
	public IBlock addBlock() {
		return new Block(this, true);
	}
	
	IBlock addLooseBlock() {
		return new Block(this, false);
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
	
	public Procedure getProcedure() {
		if(parent instanceof Procedure)
			return (Procedure) parent;
		else if(parent == null)
			return null;
		else
			return ((Block)  parent).getProcedure();
	}

	@Override
	public IVariable addVariable(IType type) {		
		Variable var = new Variable(this, type);
		Procedure procedure = getProcedure();
		procedure.addVariableDeclaration(var);
		children.add(var);
		return var;
	}
	
	
	@Override
	public IVariableAssignment addAssignment(IVariable variable, IExpression expression) {
		// TODO OCL: variable must be owned by the same procedure of expression

		return new VariableAssignment(this, variable, expression);
	}

	@Override
	public IArrayElementAssignment addArrayElementAssignment(IVariable var, IExpression exp, List<IExpression> indexes) {
		// TODO OCL: variable must be owned by the same procedure of expression
		return new ArrayElementAssignment(this, var, indexes, exp);
	}
	
	@Override
	public IRecordFieldAssignment addRecordMemberAssignment(IVariable var, IVariable field, IExpression exp) {
		// TODO OCL: variable must be owned by the same procedure of expression
		return new RecordFieldAssignment(this, var, field, exp);
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
