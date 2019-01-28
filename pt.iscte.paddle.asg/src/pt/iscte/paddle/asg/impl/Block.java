package pt.iscte.paddle.asg.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pt.iscte.paddle.asg.IArrayElementAssignment;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IBreak;
import pt.iscte.paddle.asg.IContinue;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IInstruction;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProcedureCall;
import pt.iscte.paddle.asg.IProgramElement;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.ISelection;
import pt.iscte.paddle.asg.ISelectionWithAlternative;
import pt.iscte.paddle.asg.IStatement;
import pt.iscte.paddle.asg.IStructMemberAssignment;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IValue;

class Block extends ProgramElement implements IBlock {
	private final ProgramElement parent;
	private final List<IInstruction> instructions;

	Block(ProgramElement parent, boolean addToParent) {
		this.parent = parent;
		this.instructions = new ArrayList<>();
		if(parent != null && addToParent)
			((Block) parent).addInstruction(this);
	}

	@Override
	public ProgramElement getParent() {
		return parent;
	}
	
	@Override
	public boolean isEmpty() {
		return instructions.isEmpty();
	}
	
	@Override
	public List<IInstruction> getInstructionSequence() {
		return Collections.unmodifiableList(instructions);
	}
	
	void addInstruction(IInstruction statement) {
		assert statement != null;
		instructions.add(statement);
	}

	IBlock addLooseBlock() {
		return new Block(this, false);
	}
	
	@Override
	public String toString() {
		String tabs = "";
		int d = getDepth();
//		int d = 0; // FIXME
		for(int i = 0; i < d; i++)
			tabs += "\t";
		String text = tabs.substring(1) + "{";
		for(IProgramElement s : instructions)
			text += tabs + s + (s instanceof IStatement ? ";" : "");
		return text + "}";
	}
	
	
	
	private int getDepth() {
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
	public IVariable addVariable(String name, IDataType type) {		
		Variable var = new Variable(this, name, type);
		Procedure procedure = getProcedure();
		if(procedure != null)
			procedure.addVariableDeclaration(var);
		return var;
	}
	
//	@Override
//	public IVariable addReferenceVariable(String name, IDataType type) {
//		Variable var = Variable.pointer(this, name, type);
//		Procedure procedure = getProcedure();
//		if(procedure != null)
//			procedure.addVariableDeclaration(var);
//		return var;
//	}
	
//	@Override
//	public IArrayVariable addArrayVariable(String name, IArrayType type) {
//		ArrayVariable var = new ArrayVariable(this, name, type);
//		Procedure procedure = getProcedure();
//		if(procedure != null)
//			procedure.addVariableDeclaration(var);
//		return var;
//	}
//	
//	@Override
//	public IArrayVariable addReferenceArrayVariable(String name, IArrayType type) {
//		ArrayVariable var = ArrayVariable.reference(this, name, type);
//		Procedure procedure = getProcedure();
//		if(procedure != null)
//			procedure.addVariableDeclaration(var);
//		return var;
//	}

	@Override
	public IBlock addBlock() {
		return new Block(this, true);
	}

	
	@Override
	public IVariableAssignment addAssignment(IVariable variable, IExpression expression) {
		return new VariableAssignment(this, variable, expression);
	}

	@Override
	public IArrayElementAssignment addArrayElementAssignment(IVariable var, IExpression exp, List<IExpression> indexes) {
		return new ArrayElementAssignment(this, var, indexes, exp);
	}
	
	@Override
	public IStructMemberAssignment addStructMemberAssignment(IVariable var, String memberId, IExpression exp) {
		return new StructMemberAssignment(this, var, memberId, exp);
	}
	
	@Override
	public ISelection addSelection(IExpression guard) {
		return new Selection(this, guard);
	}

	@Override
	public ISelectionWithAlternative addSelectionWithAlternative(IExpression guard) {
		return new SelectionWithAlternative(this, guard);
	}
	
	@Override
	public ILoop addLoop(IExpression guard) {
		return new Loop(this, guard);
	}

	@Override
	public IReturn addReturnStatement(IExpression expression) {
		return new Return(this, expression);
	}
	
	@Override
	public IProcedureCall addProcedureCall(IProcedure procedure, List<IExpression> args) {
		return new ProcedureCall(this, procedure, args);
	}
	@Override
	public IBreak addBreakStatement() {
		return new Break(this);
	}
	
	@Override
	public IContinue addContinueStatement() {
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
		public boolean execute(ICallStack stack, List<IValue> expressions) throws ExecutionError {
			return true;
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
		public boolean execute(ICallStack stack, List<IValue> expressions) throws ExecutionError {
			return true;
		}
	}
}
