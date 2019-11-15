package pt.iscte.paddle.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import pt.iscte.paddle.model.IListenable;

public class ListenableProgramElement<L> extends ProgramElement implements IListenable<L> {

	private List<L> listeners;
	
	public void addListener(L listener) {
		if(listeners == null)
			listeners = new ArrayList<>(3);
		listeners.add(listener);
	}
	
	public void removeListener(L listener) {
		if(listeners != null)
			listeners.remove(listener);
	}
	
	public Iterator<L> getListeners() {
		return listeners == null ? Collections.emptyIterator() : listeners.iterator();
	}
}
