package pt.iscte.paddle.asg.future;

import pt.iscte.paddle.model.IStatement;
import pt.iscte.paddle.model.IVariable;

public interface IFree extends IStatement {
	IVariable getVariable();
}
