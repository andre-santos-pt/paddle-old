package pt.iscte.paddle.model.commands;

import pt.iscte.paddle.model.IProgramElement;

public interface ICommand<E extends IProgramElement> {
	void execute();
	void undo();
	E getElement();
	
	default String toText() {
		return getClass().getSimpleName() + " " + getElement();
	}
}