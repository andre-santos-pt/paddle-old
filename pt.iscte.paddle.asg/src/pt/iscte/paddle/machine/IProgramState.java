package pt.iscte.paddle.machine;

import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProgramElement;
import pt.iscte.paddle.asg.IRecordType;

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
	
	IArray allocateArray(IDataType baseType, int ... dimensions);
	IStructObject allocateObject(IRecordType type);
	
	IExecutionData execute(IProcedure p, Object...args);

	void setupExecution(IProcedure mockProcedure, Object... args)  throws ExecutionError;

	//	void launchExecution(IProcedure procedure, Object ... args);
//
//	void stepIn();
	
	default int getMemory() {
		return getCallStack().getMemory() + getHeapMemory().getMemory();
	}

	IExpressionEvaluator createExpressionEvaluator(IExpression e);
	
	interface IListener {
		default void programEnded() { }
		default void instructionPointerMoved(IProgramElement currentInstruction) { }
		default void infiniteLoop() { }
		
	}



}
