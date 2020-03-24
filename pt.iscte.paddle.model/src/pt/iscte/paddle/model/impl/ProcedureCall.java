package pt.iscte.paddle.model.impl;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ExecutionError.Type;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IEvaluable;
import pt.iscte.paddle.interpreter.IExecutable;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IModel2CodeTranslator;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProcedureCall;
import pt.iscte.paddle.model.IProcedureCallExpression;
import pt.iscte.paddle.model.IProcedureDeclaration;
import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariableDeclaration;

public class ProcedureCall extends Expression implements IProcedureCall, IProcedureCallExpression, IEvaluable, IExecutable {
	private final IBlock parent;
	private IProcedureDeclaration procedure;
	private final ImmutableList<IExpression> arguments;
	
	public ProcedureCall(Block parent, IProcedure procedure, int index, List<IExpression> arguments) {
		this.parent = parent;
		this.procedure = procedure;
		this.arguments = ImmutableList.copyOf(arguments);
		if(parent != null)
			parent.addChild(this, index);
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
		return translate(IModel2CodeTranslator.JAVA);
	}
	
	
	
	@Override
	public String translate(IModel2CodeTranslator t) {
		return procedure.getId() + "(" + IProcedureCall.argsToString(t, arguments) + ")";
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
	public List<IExpression> getParts() {
		return arguments;
	}
}
