package pt.iscte.paddle.asg;

import java.util.List;

public interface IProcedureCallExpression extends IExpression {
	IProcedureDeclaration getProcedure();
	List<IExpression> getArguments();
}
