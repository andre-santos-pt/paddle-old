package pt.iscte.paddle.model;

public interface INamespaceElement extends IProgramElement {

	String NAMESPACE = "NAMESPACE";
	
	default String getNamespace() {
		return (String) getProperty(NAMESPACE);
	}
	
	default void setNamespace(String namespace) {
		setProperty(NAMESPACE, namespace);
	}
	
	default boolean sameNamespace(INamespaceElement e) {
		return getNamespace().equals(e.getNamespace());
	}
}
