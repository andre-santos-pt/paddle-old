package pt.iscte.paddle.machine;

import com.google.common.util.concurrent.ExecutionError;

import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IStructType;

public interface IHeapMemory {

	

	IArray allocateArray(IDataType baseType, int ... dimensions) throws ExecutionError;

	IStructObject allocateObject(IStructType type) throws ExecutionError;
	
	default int getMemory() {
		return 0;
	}
	
	interface IListener {
		default void allocated(IValue value) { }
		default void deallocated(IValue value) { }
	}
}
