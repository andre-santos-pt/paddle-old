package pt.iscte.paddle.interpreter;

import java.util.List;
import java.util.Map;

import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IStatement;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariableDeclaration;

public interface IStackFrame {
	ICallStack getCallStack();
	
	IProcedure getProcedure();
	
	Map<IVariableDeclaration, IValue> getVariables();
	
	IReference getVariableStore(IVariableDeclaration variable);
//	void setVariable(String identifier, IValue value);
	

	
	IValue getReturn();
	void setReturn(IValue value);
	
	default int getMemory() {
		int bytes = 0;
		for (IVariableDeclaration var : getProcedure().getVariables()) {
			bytes += var.getType().getMemoryBytes();
		}
		return bytes;
	}
	
	IStackFrame newChildFrame(IProcedure procedure, List<IValue> args) throws ExecutionError;
	
	void terminateFrame();
	
	default boolean isTopFrame() {
		return this == getCallStack().getTopFrame();
	}
	
	IValue getValue(String literal);
	IValue getValue(Object object);

	IArray allocateArray(IType baseType, int[] dimensions);
	
	IRecord allocateRecord(IRecordType type);
	
	IValue evaluate(IExpression expression, List<IValue> expressions) throws ExecutionError;
	
	
	void stepIn() throws ExecutionError;
	
	boolean isOver();
	
	void addListener(IListener listener);

	interface IListener {
		default void variableAdded(IVariableDeclaration variable, IType type) { }
		
		default void variableModified(IVariableDeclaration variable, IType type, IValue newValue) { }
		
		default void statementExecutionStart(IStatement statement) { }

		default void statementExecutionEnd(IStatement statement) { }

		default void expressionEvaluationStart(IExpression expression) { }

		default void expressionEvaluationEnd(IExpression expression, IValue result) { }
		
		default void started(IValue result) { }
		
		default void terminated(IValue result) { }
	}

	
}
