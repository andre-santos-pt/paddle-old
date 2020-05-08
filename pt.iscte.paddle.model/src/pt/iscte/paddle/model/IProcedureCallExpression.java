package pt.iscte.paddle.model;

public interface IProcedureCallExpression extends IProcedureCall, ICompositeExpression, ITargetExpression {

	@Override
	default boolean includes(IVariableDeclaration variable) {
		for(IExpression arg : getArguments())
			if(arg.includes(variable))
				return true;
		
		return false;
	}
}
