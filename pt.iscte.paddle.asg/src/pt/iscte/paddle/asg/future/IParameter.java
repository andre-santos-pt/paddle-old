package pt.iscte.paddle.asg.future;

import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IVariable;

// TODO ideia
interface IParameter extends IVariable {
	default IExpression getPrecondition() {
		return null; 
	}
}
