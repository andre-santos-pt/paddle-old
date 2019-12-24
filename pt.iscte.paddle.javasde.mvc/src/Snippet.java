import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Snippet {

	public static void main(String[] args) {

		Display display = new Display ();
		final Shell shell = new Shell (display);
		shell.setLayout(new FillLayout());

		Text text1 = new Text(shell, SWT.SINGLE | SWT.BORDER);
		Text text2 = new Text(shell, SWT.SINGLE | SWT.BORDER);

		Color gray = text1.getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY);


		shell.open ();

		Color origBG = text2.getBackground();
		FocusListener focusListener = new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				((Text)e.widget).setBackground(origBG);
			}

			@Override
			public void focusGained(FocusEvent e) {
				((Text)e.widget).setBackground(gray);
			}
		};
		text1.addFocusListener(focusListener);
		text2.addFocusListener(focusListener);

		Executors.newScheduledThreadPool(1).schedule(() -> display.syncExec(()->text2.setFocus()), 3, TimeUnit.SECONDS);

		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		gray.dispose();

		display.dispose ();
	}

}