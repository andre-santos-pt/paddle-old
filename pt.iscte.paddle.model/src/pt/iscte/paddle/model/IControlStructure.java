package pt.iscte.paddle.model;

public interface IControlStructure extends IBlockElement, IStatementContainer {
	IBlock getParent();
	IExpression getGuard(); 
}
