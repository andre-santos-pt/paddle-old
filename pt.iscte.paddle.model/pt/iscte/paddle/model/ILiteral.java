package pt.iscte.paddle.model;

import pt.iscte.paddle.model.impl.Literal;

public interface ILiteral extends ISimpleExpression {
	String getStringValue();
	
	static ILiteral matchValue(String string) {
		for(IValueType t : IType.VALUE_TYPES)
			if(t.matchesLiteral(string))
				return createValue(t, string);
		return null;
	}
	
	static ILiteral createValue(IValueType type, String value) {
		return new Literal(type, value);
	}
	
	@Override
	default boolean includes(IVariableDeclaration variable) {
		return false;
	}

	@Override
	default boolean isSame(IProgramElement e) {
		return e instanceof ILiteral && getStringValue().equals(((ILiteral) e).getStringValue());
	}

	static ILiteral getNull() {
		return Literal.NULL;
	}
}
