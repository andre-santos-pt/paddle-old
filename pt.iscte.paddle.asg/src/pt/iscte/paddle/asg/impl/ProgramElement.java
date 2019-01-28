package pt.iscte.paddle.asg.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import pt.iscte.paddle.asg.IProgramElement;

abstract class ProgramElement implements IProgramElement {
	
	private Map<String, Object> properties = Collections.emptyMap();
	
	@Override
	public Object getProperty(String key) {
		return properties.get(key);
	}
	
	@Override
	public void setProperty(String key, Object value) {
		if(properties.isEmpty())
			properties = new HashMap<>(5);
		
		properties.put(key, value);
	}
}
