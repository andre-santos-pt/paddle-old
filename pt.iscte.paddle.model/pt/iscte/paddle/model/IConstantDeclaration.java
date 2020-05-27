package pt.iscte.paddle.model;

public interface IConstantDeclaration extends INamespaceElement, IExpressionView<IConstantExpression> {

	IModule getProgram();
	
	IType getType();
	
	ILiteral getValue();
	
	void setValue(ILiteral value);
	
	IConstantExpression expression();
}
