package pt.iscte.paddle.asg;

public interface IConstant extends IProgramElement, ISimpleExpression {

	IModule getProgram();
	
	IDataType getType();
	
	ILiteral getValue();
	
}
