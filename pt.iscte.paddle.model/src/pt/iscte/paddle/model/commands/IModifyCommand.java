package pt.iscte.paddle.model.commands;

import pt.iscte.paddle.model.IProgramElement;

public interface IModifyCommand<E extends IProgramElement> extends ICommand<E> {		
	IProgramElement getSubElement();
}