package pt.iscte.paddle.model.commands;

import pt.iscte.paddle.model.IProgramElement;

public interface IAddCommand<E> extends ICommand<E> {		
	IProgramElement getParent();
}