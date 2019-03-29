package pt.iscte.paddle.asg;

public interface IRecordFieldExpression extends ISimpleExpression {

	IVariable getVariable();
	IVariable getField();

}