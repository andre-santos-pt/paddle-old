package pt.iscte.paddle.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.iscte.paddle.model.IProgramElement;

public class ProgramElement implements IProgramElement {
	
	private Map<Object, Object> properties = Collections.emptyMap();
	private List<IPropertyListener> listeners = Collections.emptyList();
	
	ProgramElement(String ... flags) {
		for(String f : flags)
			setFlag(f);
	}
	
	@Override
	public Object getProperty(Object key) {
		return properties.get(key);
	}
	
	@Override
	public void setProperty(Object key, Object value) {
		if(properties.isEmpty())
			properties = new HashMap<>(5);
		
		Object old = properties.put(key, value);
		listeners.forEach(l -> l.propertyChanged(key, value, old));
	}
	
	@Override
	public void addPropertyListener(IPropertyListener listener) {
		if(listeners.isEmpty())
			listeners = new ArrayList<>(3);
		
		listeners.add(listener);
	}
	
}
