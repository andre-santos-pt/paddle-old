package pt.iscte.paddle.interpreter;

import java.util.Map;

import pt.iscte.paddle.model.IOperator.OperationType;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IVariableDeclaration;

public interface IExecutionData {
//	Map<IProcedure, Integer> getNumberOfProcedureCalls();
	Map<IProcedure, Integer> getAssignmentData();
//	Map<IProcedure, Integer> getNumberOfComparisons();
	
	int getTotalAssignments();
	int getOperationCount(OperationType operation);
	
	int getTotalProcedureCalls();
	
	int getCallStackDepth();
	IValue getReturnValue();
	IValue getVariableValue(IVariableDeclaration id);
//	int getTotalMemory();
	
	default void printResult() {
		System.out.println(getReturnValue());
	}
}
