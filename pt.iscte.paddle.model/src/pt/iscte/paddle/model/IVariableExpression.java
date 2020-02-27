package pt.iscte.paddle.model;

import java.util.Arrays;
import java.util.List;

public interface IVariableExpression extends ISimpleExpression {

	IVariableDeclaration getVariable();
	
	IVariableAddress address();

	IVariableDereference dereference();

	IArrayLength length(List<IExpression> indexes);
	default IArrayLength length(IExpression ... indexes) {
		return length(Arrays.asList(indexes));
	}
	default IArrayLength length(IExpressionView ... views) {
		return length(IExpressionView.toList(views));
	}
	
	IArrayElement element(List<IExpression> indexes);
	default IArrayElement element(IExpression ... indexes) {
		return element(Arrays.asList(indexes));
	}
	default IArrayElement element(IExpressionView ... views) {
		return element(IExpressionView.toList(views));
	}

	IRecordFieldExpression field(IVariableDeclaration field);
	
	default boolean is(IVariableDeclaration variable) {
		return getVariable().equals(variable);
	}
}