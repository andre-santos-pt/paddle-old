package pt.iscte.paddle.asg.impl;

import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.IModel2CodeTranslator;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProcedureCall;
import pt.iscte.paddle.asg.IProcedureDeclaration;
import pt.iscte.paddle.asg.IProgramElement;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ExecutionError.Type;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IEvaluable;
import pt.iscte.paddle.machine.IExecutable;
import pt.iscte.paddle.machine.IValue;

class ProcedureCall extends Expression implements IProcedureCall, IEvaluable, IExecutable {
	private final IBlock parent;
	private final IProcedureDeclaration procedure;
	private final ImmutableList<IExpression> arguments;
	
	public ProcedureCall(Block parent, IProcedure procedure, List<IExpression> arguments) {
		assert procedure != null;
		this.parent = parent;
		this.procedure = procedure;
		this.arguments = ImmutableList.copyOf(arguments);
		if(parent != null)
			parent.addChild(this);
	}

	@Override
	public IProcedureDeclaration getProcedure() {
		return procedure;
	}

	@Override
	public List<IExpression> getArguments() {
		return arguments;
	}

	@Override
	public String toString() {
		return procedure.getId() + "(...)";
	}
	
	private String argsToString(IModel2CodeTranslator t) {
		String args = "";
		for(IExpression e : arguments) {
			if(!args.isEmpty())
				args += ", ";
			args += t.expression(e);
		}
		return args;
	}
	
	@Override
	public String translate(IModel2CodeTranslator t) {
		return procedure.getId() + "(" + argsToString(t) + ")";
	}
	
	@Override
	public List<IExpression> getExpressionParts() {
		return arguments;
	}
	
	@Override
	public void execute(ICallStack stack, List<IValue> expressions) throws ExecutionError {
		executeInternal(stack, getProcedure(), expressions);
	}
	
	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		// excepcional case when null: for pending value from created stack
		// if builtin procedure returns value
		return ProcedureCall.executeInternal(stack, getProcedure(), values); 
	}
	
	static IValue executeInternal(ICallStack stack, IProcedureDeclaration procedure, List<IValue> args) throws ExecutionError {
		IProcedure p = stack.getProgramState().getProgram().resolveProcedure(procedure);
		if(p instanceof BuiltinProcedure) {
			try {
				return ((BuiltinProcedure) p).hookAction(args);
			} catch (Exception e) {
				throw new ExecutionError(Type.BUILT_IN_PROCEDURE, procedure, e.getMessage());
			}
		}
		else {
			stack.newFrame(p, args);
			return null;
		}
	}

	@Override
	public IType getType() {
		return procedure.getReturnType();
	}

	@Override
	public int getNumberOfParts() {
		return arguments.size();
	}


	@Override
	public IProgramElement getParent() {
		return parent;
	}

	@Override
	public List<IExpression> decompose() {
		return arguments;
	}
}
