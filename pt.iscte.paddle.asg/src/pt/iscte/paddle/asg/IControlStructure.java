package pt.iscte.paddle.asg;

public interface IControlStructure extends IBlock {
	IBlock getParent();
	IExpression getGuard(); 
}
