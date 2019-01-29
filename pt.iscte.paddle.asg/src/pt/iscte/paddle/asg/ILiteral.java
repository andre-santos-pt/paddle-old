package pt.iscte.paddle.asg;

import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.asg.impl.Literal;

public interface ILiteral extends IExpression {
	String getStringValue();
	
	@Override
	default List<IExpression> decompose() {
		return ImmutableList.of();
	}
	
	@Override
	default boolean isDecomposable() {
		return false;
	}
	
	static ILiteral literal(int value) {
		return new Literal(IDataType.INT, Integer.toString(value));
	}
	
	static ILiteral literal(double value) {
		return new Literal(IDataType.DOUBLE, Double.toString(value));
	}

	static ILiteral literal(boolean value) {
		return new Literal(IDataType.BOOLEAN, Boolean.toString(value));
	}
	
	static ILiteral nullLiteral() {
		return Literal.NULL;
	}
	
	static ILiteral matchValue(String string) {
		for(IValueType t : IDataType.VALUE_TYPES)
			if(t.matchesLiteral(string))
				return createValue(t, string);
		return null;
	}
	
	static ILiteral createValue(IValueType type, String value) {
		return new Literal(type, value);
	}
}
