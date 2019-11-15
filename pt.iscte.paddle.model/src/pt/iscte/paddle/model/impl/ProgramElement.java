package pt.iscte.paddle.model.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import pt.iscte.paddle.model.IProgramElement;

public class ProgramElement implements IProgramElement {
	
	private Map<Object, Object> properties = Collections.emptyMap();
	
	@Override
	public Object getProperty(Object key) {
		return properties.get(key);
	}
	
	@Override
	public void setProperty(Object key, Object value) {
		if(properties.isEmpty())
			properties = new HashMap<>(5);
		
		properties.put(key, value);
	}
}
