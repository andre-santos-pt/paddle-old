package pt.iscte.paddle.machine;

import pt.iscte.paddle.asg.IArrayType;

public interface IArray extends IValue {
	IArrayType getType();
	int getLength();
	IValue getElement(int i);
	void setElement(int i, IValue value);
	
	@Override
	default int getMemory() {
		return 4 + getLength() * getType().getMemoryBytes();
	}
	
	interface IListener {
		void elementChanged(int index, IValue newValue);
	}
}
