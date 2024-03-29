package pt.iscte.paddle.model;

import java.util.ArrayList;
import java.util.List;

public interface IArrayElementAssignment extends IStatement {
	
	IArrayAccess getArrayAccess();
	IExpression getExpression();
	IBlock getParent();
	
	default int getDimensions() {
		return getArrayAccess().getIndexes().size();
	}
	@Override
	default List<IExpression> getExpressionParts() {
		List<IExpression> list = new ArrayList<IExpression>(getArrayAccess().getIndexes());
		list.add(getExpression());
		return list;
	}
	
	default boolean isSame(IProgramElement s) {
		return s instanceof IArrayElementAssignment &&
			getArrayAccess().isSame(((IArrayElementAssignment) s).getArrayAccess()) &&
			IExpression.areSame(getExpressionParts(), ((IStatement) s).getExpressionParts());
	}
}
