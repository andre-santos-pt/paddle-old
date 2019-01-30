package pt.iscte.paddle.asg;

import java.util.List;


public interface IProcedureCall extends IStatement, ICompositeExpression {
	IProcedureDeclaration getProcedure();
	List<IExpression> getArguments();

	default boolean isOperation() {
		return false;
	}
}
