package pt.iscte.paddle.asg;

public interface IProgramElement {
	String ID = "ID";
	
	void setProperty(String key, Object value);	
	
	Object getProperty(String key);
	
	default <T> void setProperty(Class<T> key, T value) {
		setProperty(key.getName(), value);
	}
	
	default <T> T getProperty(Class<T> key) {
		return key.cast(getProperty(key.getName()));
	}
	
	default void setFlag(String key) {
		setProperty(key, Boolean.TRUE);
	}
	
	default void setProperty(Object key) {
		setProperty(key.getClass().getName(), key);
	}
	
	default boolean is(String key) {
		return Boolean.TRUE.equals(getProperty(key));
	}
	
	default String getId() {
		return (String) getProperty(ID);
	}
	
	default void setId(String id) {
		setProperty(ID, id);
	}
}
