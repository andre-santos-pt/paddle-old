/*
 * generated by Xtext 2.17.0
 */
package pt.iscte.paddle.ui;

import com.google.inject.Injector;
import org.eclipse.core.runtime.Platform;
import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;
import pt.iscte.paddle.javali.ui.internal.JavaliActivator;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class JavaliExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return Platform.getBundle(JavaliActivator.PLUGIN_ID);
	}
	
	@Override
	protected Injector getInjector() {
		JavaliActivator activator = JavaliActivator.getInstance();
		return activator != null ? activator.getInjector(JavaliActivator.PT_ISCTE_PADDLE_JAVALI) : null;
	}

}
