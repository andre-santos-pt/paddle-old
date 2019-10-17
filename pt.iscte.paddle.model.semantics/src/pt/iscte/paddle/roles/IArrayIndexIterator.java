package pt.iscte.paddle.roles;

import java.util.List;

import pt.iscte.paddle.model.IVariable;

public interface IArrayIndexIterator extends IStepper {
	
	List<IVariable> getArrayVariables();
}
