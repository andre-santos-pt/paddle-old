package pt.iscte.paddle.interpreter.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.util.concurrent.ExecutionError;

import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.IHeapMemory;
import pt.iscte.paddle.interpreter.IRecord;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IArrayType;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IType;

public class Memory implements IHeapMemory {
	private final List<IValue> objects;

	public Memory() {
		objects = new ArrayList<>();
	}

	@Override
	public IArray allocateArray(IType baseType, int... dimensions) throws ExecutionError {
		assert dimensions.length > 0 && dimensions[0] >= 0;
		IArrayType arrayType = baseType.array();
		for (int i = 1; i < dimensions.length; i++)
			arrayType = arrayType.array();

		Array array = new Array(arrayType, dimensions[0]);
		if (dimensions.length == 1) {
			for (int i = 0; i < dimensions[0]; i++) {
				IValue val = Value.create(baseType, baseType.getDefaultValue());
				array.setElement(i, val);
			}
		}

		for(int i = 1; i < dimensions.length; i++) {
			int[] remainingDims = Arrays.copyOfRange(dimensions, i, dimensions.length);
			if(remainingDims[0] != -1)
				for(int j = 0; j < dimensions[0]; j++)
					array.setElement(j, allocateArray(baseType, remainingDims));

		}
		objects.add(array);
		return array;
	}

	@Override
	public IRecord allocateRecord(IRecordType type) throws ExecutionError {
		Record object = new Record(type);
		objects.add(object);
		return object;
	}
}
