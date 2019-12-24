package pt.iscte.paddle.javasde;

import org.eclipse.swt.events.KeyListener;

public interface Expression {

	Expression copyTo(EditorWidget parent);
	
	void dispose();
	boolean setFocus();
	void requestLayout();
	void setData(Object data);

	void addKeyListener(KeyListener listener);
}
