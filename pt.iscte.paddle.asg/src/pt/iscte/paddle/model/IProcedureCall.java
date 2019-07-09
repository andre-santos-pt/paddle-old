package pt.iscte.paddle.model;

import java.util.List;


public interface IProcedureCall extends IStatement, ICompositeExpression {
	IProcedureDeclaration getProcedure();
	List<IExpression> getArguments();

	default boolean isOperation() {
		return false;
	}
}
