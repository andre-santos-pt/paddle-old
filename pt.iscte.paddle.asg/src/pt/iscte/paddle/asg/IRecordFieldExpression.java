package pt.iscte.paddle.asg;

public interface IRecordFieldExpression extends ISimpleExpression {

	IVariable getVariable();
	String getMemberId();

}