package pt.iscte.paddle.javasde;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public interface Configuration {

	default boolean staticMode() {
		return false;
	}
	
	default int fontSize() {
		return 20;
	}
	
	default Color fontColor() {
		return Display.getDefault().getSystemColor(SWT.COLOR_BLACK);
	}
	
	
}
