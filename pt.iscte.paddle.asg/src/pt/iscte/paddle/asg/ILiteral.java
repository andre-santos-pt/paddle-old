package pt.iscte.paddle.asg;

import pt.iscte.paddle.asg.impl.Literal;

public interface ILiteral extends ISimpleExpression {
	String getStringValue();
	
	static ILiteral nullLiteral() {
		return Literal.NULL;
	}
	
	static ILiteral matchValue(String string) {
		for(IValueType t : IType.VALUE_TYPES)
			if(t.matchesLiteral(string))
				return createValue(t, string);
		return null;
	}
	
	static ILiteral createValue(IValueType type, String value) {
		return new Literal(type, value);
	}
}
