
import static pt.iscte.paddle.model.IOperator.GREATER;
import static pt.iscte.paddle.model.IOperator.SMALLER;
import static pt.iscte.paddle.model.IType.*;

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

import pt.iscte.paddle.javasde.CallWidget;
import pt.iscte.paddle.javasde.ClassWidget;
import pt.iscte.paddle.javasde.ConstantWidget;
import pt.iscte.paddle.javasde.ControlWidget;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IConstant;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IVariable;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.javasde.MethodWidget;
import pt.iscte.paddle.javasde.InstructionWidget;
import pt.iscte.paddle.javasde.UiMode;
import pt.iscte.paddle.javasde.Visitor;


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
		
		IModule module = IModule.create();
		module.setId("TestClass");

		ClassWidget c = instantiationExample(module, area, mode);
		
		Button modelCode = new Button(area, SWT.PUSH);
		modelCode.setText("model code");
		modelCode.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println(module);
			}
		});
		
		Button button = new Button(area, SWT.PUSH);
		button.setText("code");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				c.accept(new Visitor() {
					@Override
					public void visit(MethodWidget w) {
						System.out.println(w);
					}
					
					@Override
					public void visit(ControlWidget w) {
						System.out.println("while(...) {");
					}
					
					@Override
					public void visit(ConstantWidget w) {
						System.out.println(w);
					}
					
					@Override
					public void visit(CallWidget w) {
						System.out.println(w);
					}
				});
//				StringBuffer buffer = new StringBuffer();
//				c.toCode(buffer);
//				System.out.println(buffer);
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
		
		Button undo = new Button(area, SWT.PUSH);
		undo.setText("undo");
		undo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				module.undo();
			}
		});
		
		
		Button redo = new Button(area, SWT.PUSH);
		redo.setText("redo");
		redo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				module.redo();
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

	private static ClassWidget instantiationExample(IModule module, Composite area, UiMode mode) {
		IConstant pi = module.addConstant(DOUBLE, DOUBLE.literal(3.14));
		pi.setId("PI");
		
		IProcedure max = module.addProcedure(INT);
		IVariable array = max.addParameter(INT.array());
		IBlock body = max.getBody();
		IVariable m = body.addVariable(INT);
		IVariableAssignment mAss = body.addAssignment(m, array.element(INT.literal(0)));
		IVariable i = body.addVariable(INT);
		IVariableAssignment iAss = body.addAssignment(i, INT.literal(1));
		ILoop loop = body.addLoop(SMALLER.on(i, array.length()));
		ISelection ifstat = loop.addSelection(GREATER.on(array.element(i), m));
		IVariableAssignment mAss_ = ifstat.addAssignment(m, array.element(i));
		IVariableAssignment iInc = loop.addIncrement(i);
		IReturn ret = body.addReturn(m);
		
		max.setId("max");
		array.setId("array");
		m.setId("m");
		i.setId("i");
		
		ClassWidget c = new ClassWidget(area, module, mode);
		
//		l.createCall("proc");
//		WhileWidget l2 = l.createLoop("false");
//		ReturnWidget createReturn = l2.createReturn("true");
		return c;
	}
}