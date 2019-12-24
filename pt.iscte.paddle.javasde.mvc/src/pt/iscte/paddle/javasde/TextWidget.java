package pt.iscte.paddle.javasde;

import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public interface TextWidget {

	Text getWidget();
	
//	default void addFocusListener(FocusListener listener) {
//		getWidget().addFocusListener(listener);
//	}
//	
//	default void addKeyListener(KeyListener listener) {
//		getWidget().addKeyListener(listener);
//	}

//	default void setFocus() {
//		getWidget().setFocus();
//	}
	
	default String getText() {
		return getWidget().getText();
	}
	
	default Control getStatement() {
		Control c = getWidget();
		while(!(c.getParent() instanceof SequenceWidget))
			c = c.getParent();
		
		return c;
	}
	
	default boolean isAtBeginning() {
		return getWidget().getCaretPosition() == 0 && getWidget().getSelectionCount() == 0;
	}
	
	default boolean isAtEnd() {
		return getWidget().getCaretPosition() == getWidget().getText().length();
	}
	
	default void setAtLeft() {
		getWidget().setSelection(0, 0);
	}
	
	default void setAtRight() {
		getWidget().setSelection(getWidget().getText().length());
	}
	
	static TextWidget create(Text text) {
		return new TextWidget() {
			public Text getWidget() {
				return text;
			}
		};
	}
	
}

