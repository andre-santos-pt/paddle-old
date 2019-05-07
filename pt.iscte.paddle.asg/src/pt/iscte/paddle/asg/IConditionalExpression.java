package pt.iscte.paddle.asg;

// TODO
public interface IConditionalExpression extends ICompositeExpression {
	IExpression getConditional();
	IExpression getTrueExpression();
	IExpression getFalseExpression();
	
	@Override
	default IType getType() {
		return getTrueExpression().getType();
	}
}
