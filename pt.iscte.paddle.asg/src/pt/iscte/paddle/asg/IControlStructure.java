package pt.iscte.paddle.asg;

public interface IControlStructure extends IBlockElement, IStatementContainer {
	IBlock getParent();
//	IBlock getBlock();
	IExpression getGuard(); 
}
