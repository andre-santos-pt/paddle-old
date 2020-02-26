package pt.iscte.paddle.model.impl;

import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.commands.IModifyCommand;

public class PropertyModifyCommand<E extends IProgramElement> implements IModifyCommand<E> {

	private final E element;
	private final Object key;
	private final Object newValue;
	private Object oldValue;
	
	public PropertyModifyCommand(E element, Object key, Object newValue) {
		this.element = element;
		this.key = key;
		this.newValue = newValue;
	}
	
	@Override
	public void execute() {
		oldValue = element.getProperty(key);
		element.setProperty(key, newValue);
	}

	@Override
	public void undo() {
		element.setProperty(key, oldValue);
	}

	@Override
	public E getElement() {
		return element;
	}

	@Override
	public IProgramElement getSubElement() {
		return null;
	}

}
