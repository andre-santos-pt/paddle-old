package pt.iscte.paddle.model;

public interface IConstantDeclaration extends IProgramElement, IExpressionView {

	IModule getProgram();
	
	IType getType();
	
	ILiteral getValue();
	
	void setValue(ILiteral value);
	
	IConstantExpression expression();
}
