import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public class SDEditor {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    RowLayout layout = new RowLayout(SWT.VERTICAL);
    layout.wrap = false;
    layout.marginLeft = 10;
    shell.setLayout(layout);
    shell.setText("Hello SDE");
	shell.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
	
	ClassWidget classWidget = new ClassWidget(shell, "Test");
	
    Button button = new Button(shell, SWT.PUSH);
    button.setText("code");
    button.addSelectionListener(new SelectionAdapter() {
    	@Override
    	public void widgetSelected(SelectionEvent e) {
    		StringBuffer buffer = new StringBuffer();
    		classWidget.toCode(buffer);
    		System.out.println(buffer);
    	}
	});
    
    Button hide = new Button(shell, SWT.PUSH);
    hide.setText("hide/show +");
    hide.addSelectionListener(new SelectionAdapter() {
    	@Override
    	public void widgetSelected(SelectionEvent e) {
    		ClassWidget.hideAddLabels();
    	}
	});
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