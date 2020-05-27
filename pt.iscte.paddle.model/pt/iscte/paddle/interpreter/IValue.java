package pt.iscte.paddle.interpreter;

import pt.iscte.paddle.interpreter.impl.Value;
import pt.iscte.paddle.model.IType;

public interface IValue {
	// TODO value overflow error
	IType getType();
	Object getValue();
	
	IValue copy();
	
	default boolean isNull() {
		return false;
	}
	
	default boolean isTrue() {
		return getValue().equals(Boolean.TRUE);
	}
	
	default boolean isFalse() {
		return getValue().equals(Boolean.FALSE);
	}
	
	default int getMemory() {
		return getType().getMemoryBytes();
	}
	
	IValue NULL = new IValue() {
		final Object NULL_OBJECT = new Object();
		
		@Override
		public IType getType() {
			return null;
		}

		@Override
		public Object getValue() {
			return NULL_OBJECT;
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
	
	static IValue create(IType type, Object value) {
		return Value.create(type, value);
	}
}
