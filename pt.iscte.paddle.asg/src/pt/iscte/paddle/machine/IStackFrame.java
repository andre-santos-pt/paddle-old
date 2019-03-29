package pt.iscte.paddle.machine;

import java.util.List;
import java.util.Map;

import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IStatement;
import pt.iscte.paddle.asg.IRecordType;
import pt.iscte.paddle.asg.IVariable;

public interface IStackFrame {
	ICallStack getCallStack();
	
	IProcedure getProcedure();
	
	Map<IVariable, IValue> getVariables();
	
	IReference getVariableStore(IVariable variable);
//	void setVariable(String identifier, IValue value);
	

	
	IValue getReturn();
	void setReturn(IValue value);
	
	default int getMemory() {
		int bytes = 0;
		for (IVariable var : getProcedure().getVariables()) {
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

	IArray allocateArray(IDataType baseType, int[] dimensions);
	
	IRecord allocateRecord(IRecordType type);
	
	IValue evaluate(IExpression expression, List<IValue> expressions) throws ExecutionError;
	
	
	void stepIn() throws ExecutionError;
	
	boolean isOver();
	
	void addListener(IListener listener);

	interface IListener {
		default void variableAdded(IVariable variable, IDataType type) { }
		
		default void variableModified(IVariable variable, IDataType type, IValue newValue) { }
		
		default void statementExecutionStart(IStatement statement) { }

		default void statementExecutionEnd(IStatement statement) { }

		default void expressionEvaluationStart(IExpression expression) { }

		default void expressionEvaluationEnd(IExpression expression, IValue result) { }
		
		default void started(IValue result) { }
		
		default void terminated(IValue result) { }
	}

	
}
