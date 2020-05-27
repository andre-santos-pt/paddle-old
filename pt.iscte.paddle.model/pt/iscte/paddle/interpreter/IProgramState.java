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
	
	
	default IArray allocateArray(IType baseType, int ... dimensions) throws ExecutionError {
		return getHeapMemory().allocateArray(baseType, dimensions);
	}

	default IRecord allocateRecord(IRecordType type) throws ExecutionError {
		return getHeapMemory().allocateRecord(type);
	}
	
	IExecutionData getExecutionData();
	
	void setupExecution(IProcedure procedure, Object... args) throws ExecutionError;

	IExecutionData execute(IProcedure p, Object...args) throws ExecutionError;

	void stepIn() throws ExecutionError;

	boolean isOver();
	
	default int getMemory() {
		return getCallStack().getMemory() + getHeapMemory().getMemory();
	}

	IExpressionEvaluator createExpressionEvaluator(IExpression e);
	
	String nextStepExplanation();
	
	void addListener(IListener listener);
	
	interface IListener {
		default void programStarted() { }
		default void step(IProgramElement currentInstruction) { }
		default void executionError(ExecutionError e) { }
		default void infiniteLoop() { }
		default void programEnded() { }
	}

	



}
