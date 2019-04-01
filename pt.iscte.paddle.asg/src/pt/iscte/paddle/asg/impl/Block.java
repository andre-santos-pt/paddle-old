package pt.iscte.paddle.asg.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pt.iscte.paddle.asg.IArrayElementAssignment;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IBlockChild;
import pt.iscte.paddle.asg.IBreak;
import pt.iscte.paddle.asg.IContinue;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProcedureCall;
import pt.iscte.paddle.asg.IRecordFieldAssignment;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.ISelection;
import pt.iscte.paddle.asg.IStatement;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IValue;

class Block extends ProgramElement implements IBlock {
	private final ProgramElement parent;
	private final List<IBlockChild> children;

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
//		return children.isEmpty();
		return onlyEmptyBlocks();
	}
	
	// TODO
	private boolean onlyEmptyBlocks() {
		for(IBlockChild child : children)
			if(!(child instanceof Block) || child instanceof Block && !((Block) child).onlyEmptyBlocks())
				return false;
		return true;
	}
	
	@Override
	public List<IBlockChild> getChildren() {
		return Collections.unmodifiableList(children);
	}
	
	void addChild(IBlockChild statement) {
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
		for(IBlockChild s : children) {
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
	public IVariable addVariable(IDataType type) {		
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
	public IRecordFieldAssignment addStructMemberAssignment(IVariable var, IVariable field, IExpression exp) {
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
//		return new SelectionWithAlternative(this, guard);
	}
	
	@Override
	public ILoop addLoop(IExpression guard) {
		return new Loop(this, guard);
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
	
	private static class Break extends Statement implements IBreak {
		public Break(IBlock parent) {
			super(parent, true);
		}
		
		@Override
		public String toString() {
			return "break";
		}
		
		@Override
		public void execute(ICallStack stack, List<IValue> expressions) throws ExecutionError {

		}
	}
	
	private static class Continue extends Statement implements IContinue {
		public Continue(IBlock parent) {
			super(parent, true);
		}
		
		@Override
		public String toString() {
			return "continue";
		}
		
		@Override
		public void execute(ICallStack stack, List<IValue> expressions) throws ExecutionError {

		}
	}
}
