package pt.iscte.paddle.asg;

public interface IProgramElement {
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
}
