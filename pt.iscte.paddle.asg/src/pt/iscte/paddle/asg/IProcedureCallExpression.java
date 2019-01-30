package pt.iscte.paddle.asg;

import java.util.List;

public interface IProcedureCallExpression extends ICompositeExpression {
	IProcedureDeclaration getProcedure();
	List<IExpression> getArguments();
}
