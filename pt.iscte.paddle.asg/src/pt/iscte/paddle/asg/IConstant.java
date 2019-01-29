package pt.iscte.paddle.asg;

public interface IConstant extends IIdentifiableElement, IExpression {

	IModule getProgram();
	
	IDataType getType();
	
	ILiteral getValue();
	
//	IConstantExpression expression();
}
