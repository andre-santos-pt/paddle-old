package pt.iscte.paddle.asg.future;

import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IVariableDeclaration;

// TODO ideia
interface IParameter extends IVariableDeclaration {
	default IExpression getPrecondition() {
		return null; 
	}
}
