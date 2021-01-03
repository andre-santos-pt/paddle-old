package pt.iscte.paddle.model;

public interface IConstantDeclaration extends INamespaceElement, IExpressionView<IConstantExpression> {

	IModule getProgram();
	
	IType getType();
	
	IExpression getValue();
	
	void setValue(IExpression value);
	
	IConstantExpression expression();
}
