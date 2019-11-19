package pt.iscte.paddle.model.commands;

import pt.iscte.paddle.model.IProgramElement;

public interface IMoveCommand<E> extends ICommand<E> {		
	IProgramElement getLocation();
}