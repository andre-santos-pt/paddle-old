package pt.iscte.paddle.model;

public interface IVariableDereference extends ISimpleExpression {

	IVariableExpression getTarget();

	default IExpression length(IExpressionView ... indexes) {
		return getTarget().length(indexes);
	}

	default IExpressionView element(IExpressionView ... views) {
		return getTarget().element(views);
	}
	
}
