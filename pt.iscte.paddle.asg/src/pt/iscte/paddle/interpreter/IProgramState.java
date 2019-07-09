package pt.iscte.paddle.interpreter;

import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IType;

public interface IProgramState {
	IModule getProgram();
	ICallStack getCallStack();
	IHeapMemory getHeapMemory();
	int getCallStackMaximum();
	int getLoopIterationMaximum();
	int getAvailableMemory();
	int getUsedMemory();
	
	IProgramElement getInstructionPointer();
	IValue getValue(String literal);
	IValue getValue(Object object);
	
	IArray allocateArray(IType baseType, int ... dimensions);
	IRecord allocateObject(IRecordType type);
	
	IExecutionData getExecutionData();
	
	void setupExecution(IProcedure procedure, Object... args) throws ExecutionError;

	IExecutionData execute(IProcedure p, Object...args) throws ExecutionError;

	void stepIn() throws ExecutionError;

	boolean isOver();
	
	default int getMemory() {
		return getCallStack().getMemory() + getHeapMemory().getMemory();
	}

	IExpressionEvaluator createExpressionEvaluator(IExpression e);
	
	void addListener(IListener listener);
	
	interface IListener {
		default void programEnded() { }
		default void step(IProgramElement currentInstruction) { }
		default void infiniteLoop() { }
		
	}



}
