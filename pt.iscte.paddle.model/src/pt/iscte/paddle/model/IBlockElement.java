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
}
