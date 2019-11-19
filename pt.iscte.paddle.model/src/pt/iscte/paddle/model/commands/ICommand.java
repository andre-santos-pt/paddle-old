package pt.iscte.paddle.model.commands;

public interface ICommand<E> {
	void execute();
	void undo();
	E getElement();
	
	default String toText() {
		return getClass().getSimpleName() + " " + getElement();
	}
}