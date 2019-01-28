package pt.iscte.paddle.machine;

import pt.iscte.paddle.asg.IDataType;

public interface IValue {
	// TODO value overflow error
	IDataType getType();
	Object getValue();
	
	void setValue(Object o);
	
	IValue copy();
	
	default boolean isNull() {
		return getValue() == null;
	}
	
	default int getMemory() {
		return getType().getMemoryBytes();
	}
	
	IValue NULL = new IValue() {
		@Override
		public IDataType getType() {
			return null;
		}

		@Override
		public Object getValue() {
			return null;
		}
		@Override
		public void setValue(Object o) {
			assert false;
		}
		
		@Override
		public String toString() {
			return "null";
		}
		@Override
		public IValue copy() {
			return this;
		}
	};
}
