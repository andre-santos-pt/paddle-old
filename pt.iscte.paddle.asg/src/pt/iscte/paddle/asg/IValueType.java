package pt.iscte.paddle.asg;

public interface IValueType extends IDataType {
	boolean matchesPrimitiveType(Class<?> clazz);

	boolean matches(Object object);

	boolean matchesLiteral(String literal);

	// pre: matchesLiteral(literal)
	Object create(String literal);

}