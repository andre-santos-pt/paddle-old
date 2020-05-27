package pt.iscte.paddle.model;

public interface IBlockElement extends IProgramElement {
	
	IProgramElement getParent();
	
//	void moveTo(IBlock block); // TODO move
	
	default IProcedure getOwnerProcedure() {
		IProgramElement p = getParent();
		while(!(p instanceof IProcedure))
			p = ((IBlockElement) p).getParent();
		
		return (IProcedure) p;
	}
	
	default void remove() {
		IProgramElement parent = getParent();
		if(parent instanceof IStatementContainer)
			((IStatementContainer) parent).removeElement(this);
		else if(parent instanceof IRecordType)
			((IRecordType) parent).removeField((IVariableDeclaration) this);
			
	}
}
