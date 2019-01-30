package pt.iscte.paddle.asg.future;

import pt.iscte.paddle.asg.IStatement;
import pt.iscte.paddle.asg.IVariable;

public interface IFree extends IStatement {
	IVariable getVariable();
}
