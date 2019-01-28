package pt.iscte.paddle.asg;

public interface IConstant extends IIdentifiableElement {

	IModule getProgram();
	
	IDataType getType();
	
	ILiteral getValue();
	
	IConstantExpression expression();
}
