package pt.iscte.paddle.model;

public interface IConstant extends IProgramElement, ISimpleExpression {

	IModule getProgram();
	
	IType getType();
	
	ILiteral getValue();
	
}
