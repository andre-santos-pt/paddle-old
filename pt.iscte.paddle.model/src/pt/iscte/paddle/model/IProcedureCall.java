package pt.iscte.paddle.model;

import java.util.List;


public interface IProcedureCall extends IStatement, ICompositeExpression {
	IProcedureDeclaration getProcedure();
	List<IExpression> getArguments();

	default boolean isOperation() {
		return false;
	}
	
	@Override
	default boolean includes(IVariableDeclaration variable) {
		for(IExpression arg : getArguments())
			if(arg.includes(variable))
				return true;
		
		return false;
	}
	
	@Override
	default boolean isSame(IExpression e) {
		return e instanceof IProcedureCall &&
				getProcedure().equals(((IProcedureCall) e).getProcedure()) &&
				IExpression.areSame(getArguments(), ((IProcedureCall) e).getArguments());
	}
}
