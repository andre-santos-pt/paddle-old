package pt.iscte.paddle.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import pt.iscte.paddle.model.IProgramElement;

public class ProgramElement implements IProgramElement {
	
	private Map<Object, Object> properties = Collections.emptyMap();
	private List<IPropertyListener> listeners = Collections.emptyList();
	
	public ProgramElement(String ... flags) {
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
		
//		if(value == null && properties.containsKey(key))
//			properties.remove(key);
		
		Object old = properties.put(key, value);
		
		listeners.forEach(l -> l.propertyChanged(key, value, old));
	}
	
	@Override
	public void addPropertyListener(IPropertyListener listener) {
		if(listeners.isEmpty())
			listeners = new ArrayList<>(3);
		
		listeners.add(listener);
	}
	
	@Override
	public Set<String> getFlags() {
		Set<String> flags = new HashSet<String>();
		for(Entry e : properties.entrySet())
			if(e.getValue().equals(Boolean.TRUE))
				flags.add(e.getKey().toString());
		return flags;
	}
	
}
