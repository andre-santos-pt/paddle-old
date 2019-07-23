package pt.iscte.paddle.asg.future;

import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IVariable;

// TODO ideia
interface IParameter extends IVariable {
	default IExpression getPrecondition() {
		return null; 
	}
}
