package pt.iscte.paddle.javasde;
import java.util.function.Supplier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class Token extends EditorWidget {

	public Token(Composite parent, String ... tokens) {
		super(parent);
		setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		
		setLayout(new FillLayout());
		for(String token : tokens) {
			Label label = new Label(this, SWT.NONE);
			label.setText(token);
			if(token.matches("class|static|return|if|while|int|double")) {
				label.setFont(FONT_KW);
				label.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_DARK_MAGENTA));
			}
			else
				label.setFont(FONT);
		}
	}
}
