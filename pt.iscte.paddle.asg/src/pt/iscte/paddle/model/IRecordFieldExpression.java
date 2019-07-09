package pt.iscte.paddle.model;

public interface IRecordFieldExpression extends ISimpleExpression {

	IVariable getVariable();
	IVariable getField();

}