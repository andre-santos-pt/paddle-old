package pt.iscte.paddle.model;

public interface IRecordAllocation extends ISimpleExpression {
	
	IRecordType getRecordType();
	
	@Override
	default boolean includes(IVariableDeclaration variable) {
		return false;
	}
	
	@Override
	default boolean isSame(IProgramElement e) {
		return e instanceof IRecordAllocation &&
				getRecordType().isSame(((IRecordAllocation) e).getRecordType());
	}
}
