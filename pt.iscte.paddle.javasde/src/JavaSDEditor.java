
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import pt.iscte.paddle.javasde.ClassWidget;
import pt.iscte.paddle.javasde.WhileWidget;
import pt.iscte.paddle.javasde.MethodWidget;
import pt.iscte.paddle.javasde.ReturnWidget;
import pt.iscte.paddle.javasde.UiMode;


public class JavaSDEditor {
	
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		RowLayout layout = new RowLayout(SWT.VERTICAL);
		layout.wrap = false;
		layout.marginLeft = 10;
		shell.setLayout(new GridLayout());
		shell.setText("Java Syntax-Directed Editor");
		shell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));

		ScrolledComposite scroll = new ScrolledComposite(shell, SWT.H_SCROLL | SWT.V_SCROLL);
		scroll.setLayout(new GridLayout(1, false));
		scroll.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Composite area = new Composite(scroll, SWT.NONE);

		GridLayout gridLayout = new GridLayout(1, false);
		area.setLayout(gridLayout);
		area.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));

		scroll.setContent(area);
		scroll.setMinSize(100, 100);
		scroll.setExpandHorizontal(true);
		scroll.setExpandVertical(true);
		area.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				scroll.setMinSize(area.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				scroll.requestLayout();
			}
		});
//		area.addControlListener(ControlListener.controlResizedAdapter(
//				e -> { 
//					scroll.setMinSize(area.computeSize(SWT.DEFAULT, SWT.DEFAULT));
//					scroll.requestLayout();}));

		UiMode mode = new UiMode();
		
		ClassWidget c = instantiateExample(area, mode);
		
		Button button = new Button(area, SWT.PUSH);
		button.setText("code");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StringBuffer buffer = new StringBuffer();
				c.toCode(buffer);
				System.out.println(buffer);
			}
		});

		Button hide = new Button(area, SWT.PUSH);
		hide.setText("hide/show +");
		hide.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				c.hideAddLabels();
			}
		});
		
		shell.setSize(600, 800);
		shell.open();

		// Set up the event loop.
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				// If no more entries in the event queue
				display.sleep();
			}
		}
		display.dispose();
	}

	private static ClassWidget instantiateExample(Composite area, UiMode mode) {
		ClassWidget c = new ClassWidget(area, "Name", mode);
		MethodWidget m = c.createMethod("f", "int");
		WhileWidget l = m.createLoop("true");
		l.createCall("proc");
		WhileWidget l2 = l.createLoop("false");
		ReturnWidget createReturn = l2.createReturn("true");
		return c;
	}
}