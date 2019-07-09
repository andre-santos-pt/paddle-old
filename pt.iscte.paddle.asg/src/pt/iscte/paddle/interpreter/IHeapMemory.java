package pt.iscte.paddle.interpreter;

import com.google.common.util.concurrent.ExecutionError;

import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IType;

public interface IHeapMemory {

	IArray allocateArray(IType baseType, int ... dimensions) throws ExecutionError;

	IRecord allocateRecord(IRecordType type) throws ExecutionError;
	
	default int getMemory() {
		return 0;
	}
	
	interface IListener {
		default void allocated(IValue value) { }
		default void deallocated(IValue value) { }
	}
}
