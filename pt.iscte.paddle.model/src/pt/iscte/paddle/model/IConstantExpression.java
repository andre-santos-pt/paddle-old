package pt.iscte.paddle.model;

// TODO
public interface IConstantExpression extends ISimpleExpression {

	IConstantDeclaration getConstant();
	
	default IType getType() {
		return getConstant().getType();
	}
	
	default String getStringValue() {
		return getConstant().getValue().getStringValue();
	}
}
