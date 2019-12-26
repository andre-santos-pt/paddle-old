package pt.iscte.paddle.javasde;

import static java.lang.System.lineSeparator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import pt.iscte.paddle.model.IConstant;
import pt.iscte.paddle.model.ILiteral;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IType;

public class ClassWidget extends EditorWidget {

	private IModule module;
	private Id id;
	private SequenceWidget body;

	public ClassWidget(Composite parent, IModule module, UiMode mode) {
		super(parent);
		this.module = module;
		GridLayout layout = new GridLayout(1, true);
		layout.verticalSpacing = 10;
		setLayout(layout);
		
		if (!UiMode.isStatic()) {
			EditorWidget header = new EditorWidget(this);
			header.setLayout(Constants.ROW_LAYOUT_H_ZERO);
			new Token(header, Keyword.CLASS);
			id = new Id(header, module.getId(), false);
			new FixedToken(header, "{");
		}

		body = new SequenceWidget(this, UiMode.isStatic() ? 0 : Constants.TAB);
		body.addChildCommand("constant", 'c', (i, p) -> module.addConstant(IType.INT, ILiteral.matchValue("1")));
		body.addChildCommand("procedure", 'p', (i, p) -> module.addProcedure(IType.VOID));

		module.getConstants().forEach(c -> body.addElement(new ConstantWidget(body, c)));
		module.getProcedures().forEach(p -> body.addElement(new MethodWidget(body, p)));

		if (!UiMode.isStatic())
			new FixedToken(this, "}");

		module.addListener(new IModule.IListener() {
			public void constantAdded(IConstant constant) {
				body.addElement(new ConstantWidget(body, constant));
			}

			public void constantDeleted(IConstant constant) {
				body.delete(e -> e instanceof ConstantWidget && ((ConstantWidget) e).constant == constant);
			}

			public void procedureAdded(IProcedure procedure) {
				MethodWidget m = new MethodWidget(body, procedure);
				body.addElement(m);
				m.focusReturnType();
			}

			public void procedureDeleted(IProcedure procedure) {
				body.delete(e -> e instanceof MethodWidget && ((MethodWidget) e).procedure == procedure);
			}
		});

		addUndoFilter();
	}

	public boolean setFocus() {
		return id.setFocus();
	}
	
	private void addUndoFilter() {
		Display.getDefault().addFilter(SWT.KeyDown, new Listener() {
			public void handleEvent(Event event) {
				if ((event.stateMask & SWT.MODIFIER_MASK) == SWT.CTRL && event.keyCode == 'z') {
					System.out.println("UNDO");
					module.undo();
				}
			}
		});
	}

	public void toCode(StringBuffer buffer) {
		buffer.append("public class ").append(id.toString()).append(" {").append(lineSeparator());
		for (Control c : getChildren())
			if (c instanceof MethodWidget)
				((MethodWidget) c).toCode(buffer);

		buffer.append("}").append(lineSeparator()).append(lineSeparator());
	}


}
