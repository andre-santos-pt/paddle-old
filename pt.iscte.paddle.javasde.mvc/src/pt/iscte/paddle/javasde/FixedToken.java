package pt.iscte.paddle.javasde;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public class FixedToken {
	private final Label label;
	
	public FixedToken(EditorWidget parent, Keyword keyword) {
		this(parent, keyword.toString());
	}
	
	public FixedToken(EditorWidget parent, String token) {
		label = new Label(parent, SWT.NONE);
		label.setText(token);

		if(Keyword.is(token)) {
			label.setFont(Constants.FONT_KW);
			label.setForeground(Constants.COLOR_KW);
		}
		else
			label.setFont(token.equals(".") ? Constants.FONT_DOT : Constants.FONT);
	}

	public void moveBelow(Control control) {
		control.moveBelow(control);
		control.requestLayout();
	}

	public void setVisible(boolean visible) {
		label.setVisible(visible);
	}

	public String toString() {
		return label.getText();
	}
	
	public void setLayoutData(RowData data) {
		label.setLayoutData(data);
	}

	public void addKeyListener(KeyListener keyListener) {
		label.addKeyListener(keyListener);
	}

	public void dispose() {
		label.dispose();
	}

	public void setFocus() {
		label.setFocus();
	}
}
