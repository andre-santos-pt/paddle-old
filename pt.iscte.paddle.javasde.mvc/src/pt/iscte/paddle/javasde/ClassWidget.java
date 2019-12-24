package pt.iscte.paddle.javasde;

import static java.lang.System.lineSeparator;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
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
		super(parent, mode);
		this.module = module;
		GridLayout layout = new GridLayout(1, true);
		layout.verticalSpacing = 10;
		setLayout(new FillLayout());
		
		if (!mode.staticClass) {
			EditorWidget header = new EditorWidget(this, mode);
			header.setLayout(Constants.ROW_LAYOUT_H_ZERO);
			new Token(header, Keyword.CLASS);
			id = createId(header, module.getId());
			new FixedToken(header, "{");
		}

		body = new SequenceWidget(this, mode.staticClass ? 0 : Constants.TAB);
		body.addChildCommand("constant", 'c', (i, p) -> module.addConstant(IType.INT, ILiteral.matchValue("1")));
		body.addChildCommand("procedure", 'p', (i, p) -> module.addProcedure(IType.VOID));

		module.getConstants().forEach(c -> body.addElement(new ConstantWidget(body, c)));
		module.getProcedures().forEach(p -> body.addElement(new MethodWidget(body, p)));

		if (!mode.staticClass)
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

//	private List<Control> addLabels = new ArrayList<>();
//	private List<Id> idWidgets = new ArrayList<>();

	private static final Supplier<List<String>> EMPTY_SUPPLIER = () -> Collections.emptyList();

	Id createId(EditorWidget parent, String id) {
		return createId(parent, id, EMPTY_SUPPLIER);
	}

	Id createId(EditorWidget parent, String id, Supplier<List<String>> idProvider) {
		Id w = new Id(parent, id, false, idProvider);
//		idWidgets.add(w);
		return w;
	}

	Id createType(EditorWidget parent, String id, Supplier<List<String>> idProvider) {
		Id w = new Id(parent, id, true, idProvider);
//		idWidgets.add(w);
		return w;
	}
	
	InsertWidget addInsert(EditorWidget parent, boolean editable) {
		InsertWidget w = new InsertWidget(parent, editable);
//		addLabels.add(label);
		return w;
	}

	public void toCode(StringBuffer buffer) {
		buffer.append("public class ").append(id.toString()).append(" {").append(lineSeparator());
		for (Control c : getChildren())
			if (c instanceof MethodWidget)
				((MethodWidget) c).toCode(buffer);

		buffer.append("}").append(lineSeparator()).append(lineSeparator());
	}


}
