package pt.iscte.paddle.model;

public interface IProgramElement {
	interface IPropertyListener {
		void propertyChanged(Object key, Object newValue, Object oldValue);
	}

	default void addPropertyListener(IPropertyListener listener) {
		throw new UnsupportedOperationException();
	}
	
	String ID = "ID";
	
	
	void setProperty(Object key, Object value);	

	Object getProperty(Object key);
	
	
	default <T> void setProperty(Class<T> key, T value) {
		setProperty(key.getName(), value);
	}
	
	default <T> T getProperty(Class<T> key) {
		return key.cast(getProperty(key.getName()));
	}
	
	default void setFlag(String key) {
		setProperty(key, Boolean.TRUE);
	}
	
	default void unsetFlag(String key) {
		
	}
	
	default void setProperty(Object key) {
		setProperty(key.getClass().getName(), key);
	}
	
	default boolean is(String key) {
		return Boolean.TRUE.equals(getProperty(key));
	}
	
	default boolean not(String key) {
		return !Boolean.TRUE.equals(getProperty(key));
	}
	
	default String getId() {
		return (String) getProperty(ID);
	}
	
	default void setId(String id) {
		setProperty(ID, id);
	}
	
	
}
