package pt.iscte.paddle.asg;

// TODO ideia
interface IParameter extends IVariable {
	default IExpression getPrecondition() {
		return null; 
	}
}
