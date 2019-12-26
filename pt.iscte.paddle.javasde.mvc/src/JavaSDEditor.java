
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import pt.iscte.paddle.javasde.ClassWidget;
import pt.iscte.paddle.javasde.InsertWidget;
import pt.iscte.paddle.javasde.Keyword;
import pt.iscte.paddle.javasde.SequenceWidget;
import pt.iscte.paddle.javasde.UiMode;
import pt.iscte.paddle.model.IModule;


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

//		GridLayout gridLayout = new GridLayout(1, false);
//		area.setLayout(gridLayout);
		area.setLayout(new FillLayout());
//		scroll.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_CYAN));

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
		
		

		SequenceWidget seq = new SequenceWidget(area, 0);
		seq.addAction(new InsertWidget.Action("class", 'c') {
			public boolean isEnabled(char c, String text, int index, int caret, int selection) {
				return c == SWT.SPACE && Keyword.CLASS.match(text);
			}
			
			public void run(char c, String text, int index, int caret, int selection) {
				IModule module = IModule.create();
				module.setId("TestClass");
				ClassWidget w = seq.addWidget(p -> new ClassWidget(p, module, mode));		
				w.setFocus();
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

//	private static ClassWidget instantiationExample(IModule module, Composite area, UiMode mode) {
//		IConstant pi = module.addConstant(DOUBLE, DOUBLE.literal(3.14));
//		pi.setId("PI");
//		
//		IProcedure max = module.addProcedure(INT);
//		IVariable array = max.addParameter(INT.array());
//		IBlock body = max.getBody();
//		IVariable m = body.addVariable(INT);
//		IVariableAssignment mAss = body.addAssignment(m, array.element(INT.literal(0)));
//		IVariable i = body.addVariable(INT);
//		IVariableAssignment iAss = body.addAssignment(i, INT.literal(1));
//		ILoop loop = body.addLoop(SMALLER.on(i, array.length()));
//		ISelection ifstat = loop.addSelection(GREATER.on(array.element(i), m));
//		IVariableAssignment mAss_ = ifstat.addAssignment(m, array.element(i));
//		IVariableAssignment iInc = loop.addIncrement(i);
//		IReturn ret = body.addReturn(m);
//		
//		max.setId("max");
//		array.setId("array");
//		m.setId("m");
//		i.setId("i");
		
		
		
//		l.createCall("proc");
//		WhileWidget l2 = l.createLoop("false");
//		ReturnWidget createReturn = l2.createReturn("true");
//		return c;
//	}
}