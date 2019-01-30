package pt.iscte.paddle.asg;

public interface IStructMemberExpression extends ISimpleExpression {

	IVariable getVariable();
	String getMemberId();

}