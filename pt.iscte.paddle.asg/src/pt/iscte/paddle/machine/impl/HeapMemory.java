package pt.iscte.paddle.machine.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.util.concurrent.ExecutionError;

import pt.iscte.paddle.asg.IArrayType;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IStructType;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.IHeapMemory;
import pt.iscte.paddle.machine.IStructObject;
import pt.iscte.paddle.machine.IValue;

public class HeapMemory implements IHeapMemory {
	private ProgramState state;
	private List<IValue> objects;

	public HeapMemory(ProgramState state) {
		this.state = state;
		objects = new ArrayList<>();
	}

	@Override
	public IArray allocateArray(IDataType baseType, int... dimensions) throws ExecutionError {
		assert dimensions.length > 0;
		IArrayType arrayType = baseType.array();
		for(int i = 1; i < dimensions.length; i++)
			arrayType = arrayType.array();
		Array array = new Array(arrayType);
		if(dimensions.length == 1) {
			if(dimensions[0] != -1) {
				IValue[] vals = new IValue[dimensions[0]];
				for(int i = 0; i < dimensions[0]; i++) {
					IValue val = Value.create(baseType, baseType.getDefaultValue());
					//				array.setElement(i, val);
					vals[i] = val;
				}
				array.setValue(vals);
			}
		}
		for(int i = 1; i < dimensions.length; i++) {
			int[] remainingDims = Arrays.copyOfRange(dimensions, i, dimensions.length);
			IValue[] vals = new IValue[dimensions[0]];
			for(int j = 0; j < dimensions[0]; j++)
				vals[j] = allocateArray(baseType, remainingDims);
			//				array.setElement(j, allocateArray(baseType, remainingDims));
			array.setValue(vals);
		}
		objects.add(array);
		return array;
	}

	@Override
	public IStructObject allocateObject(IStructType type) throws ExecutionError {
		StructObject object = new StructObject(type);
		objects.add(object);
		return object;
	}
}
