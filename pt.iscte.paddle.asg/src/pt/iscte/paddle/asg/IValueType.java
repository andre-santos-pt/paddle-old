package pt.iscte.paddle.asg;

public interface IValueType extends IType {
	boolean matchesPrimitiveType(Class<?> clazz);

	boolean matches(Object object);

	boolean matchesLiteral(String literal);

	// pre: matchesLiteral(literal)
	Object create(String literal);

	// TODO generic
	ILiteral literal(Object obj);
}