package pt.iscte.paddle.javasde;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


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
		area.addControlListener(ControlListener.controlResizedAdapter(
				e -> { 
					scroll.setMinSize(area.computeSize(SWT.DEFAULT, SWT.DEFAULT));
					scroll.requestLayout();}));

		ClassWidget classWidget = new ClassWidget(area, "Name", new UiMode());

		Button button = new Button(area, SWT.PUSH);
		button.setText("code");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StringBuffer buffer = new StringBuffer();
				classWidget.toCode(buffer);
				System.out.println(buffer);
			}
		});

		Button hide = new Button(area, SWT.PUSH);
		hide.setText("hide/show +");
		hide.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ClassWidget.hideAddLabels();
			}
		});
		
//		Text text = new Text(area, SWT.MULTI);
//		text.setText("??");
//		text.setFont(EditorWidget.FONT);
//		text.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));

//		classWidget.addControlListener(ControlListener.controlResizedAdapter(e -> {
//			StringBuffer buffer = new StringBuffer();
//			classWidget.toCode(buffer);
//			text.setText(buffer.toString()); 
//			text.requestLayout();
//		}));
		
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
}