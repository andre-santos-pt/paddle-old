package pt.iscte.paddle.model;

public interface IValueType extends IType {
	
	@Override
	default boolean isSame(IType type) {
		return equals(type);
	}

	boolean matchesPrimitiveType(Class<?> clazz);

	boolean matches(Object object);

	boolean matchesLiteral(String literal);

	// pre: matchesLiteral(literal)
	Object create(String literal);

	// TODO generic
	ILiteral literal(Object obj);
}