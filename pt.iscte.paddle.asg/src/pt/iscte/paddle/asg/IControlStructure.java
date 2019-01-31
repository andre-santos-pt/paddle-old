package pt.iscte.paddle.asg;

public interface IControlStructure extends IBlockChild, IStatementContainer {
	IBlock getParent();
//	IBlock getBlock();
	IExpression getGuard(); 
}
