import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import pt.iscte.paddle.javasde.ClassWidget;
import pt.iscte.paddle.javasde.UiMode;
import pt.iscte.paddle.model.IModule;
 
public class SWTTabFolder
{
	public static void main (String [] args) {
		Display display = new Display ();
		Shell shell = new Shell(display);
		shell.setText("/User/Andre/ex1");
 
		shell.setLayout(new FillLayout());
 
	    TabFolder folder = new TabFolder(shell, SWT.NONE);
	    
	    //Tab 1
	    TabItem tab1 = new TabItem(folder, SWT.NONE);
	    tab1.setText("Main.java");
	    
	    ScrolledComposite scroll = new ScrolledComposite(folder, SWT.H_SCROLL | SWT.V_SCROLL);
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

		UiMode mode = new UiMode();
		
		IModule module = IModule.create();
		module.setId("TestClass");

		ClassWidget c = new ClassWidget(area, module, mode);
		
	    
	    tab1.setControl(scroll);	    
 
	    //Tab 2
	    TabItem tab2 = new TabItem(folder, SWT.NONE);
	    tab2.setText("Util.java");
	    
	    Group group = new Group(folder, SWT.NONE);
	    group.setText("Group in Tab 2");
	    
	    Label label = new Label(group, SWT.BORDER);
	    label.setText("Label in Tab 2");
	    label.setBounds(10, 100, 100, 100);
	    
	    Text text = new Text(group, SWT.NONE);
	    text.setText("Text in Tab 2");
	    text.setBounds(10, 200, 100, 100);
	    
	    tab2.setControl(group);
	    
		shell.open();
 
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}
 
}