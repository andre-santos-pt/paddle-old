package pt.iscte.paddle.javasde;

import static java.lang.System.lineSeparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;

import pt.iscte.paddle.model.IConstant;
import pt.iscte.paddle.model.ILiteral;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IType;


public class ClassWidget extends EditorWidget {

	private IModule module;
	private Id id;
	private SequenceWidget body;
	private UiMode mode;

	public ClassWidget(Composite parent, IModule module, UiMode mode) {
		super(parent, mode);
		this.module = module;
		this.mode = mode;
		GridLayout layout = new GridLayout(1, true);
		layout.verticalSpacing = 10;
		setLayout(layout);

		Token openBlock = null;
		if(!mode.staticClass) {
			EditorWidget header = new EditorWidget(this, mode);
			header.setLayout(Constants.ROW_LAYOUT_H_ZERO);
			new Token(header, "class");
			id = createId(header, module.getId());
			openBlock = new Token(header, "{");
		}

		body = new SequenceWidget(this, mode.staticClass ? 0 : Constants.TAB);
		body.addChildCommand("constant", 'c', i -> module.addConstant(IType.INT, ILiteral.matchValue("1")));
		body.addChildCommand("procedure", 'p', i -> module.addProcedure(IType.VOID));

		module.getConstants().forEach(c -> body.addElement(new ConstantWidget(body, c)));
		module.getProcedures().forEach(p -> body.addElement(new MethodWidget(body, p)));

		if(!mode.staticClass)
			new Token(this, "}");	

		module.addListener(new IModule.IListener() {
			public void constantAdded(IConstant constant) {
				body.addElement(new ConstantWidget(body, constant));
			}	

			public void constantDeleted(IConstant constant) {
				body.delete(e -> e instanceof ConstantWidget && ((ConstantWidget) e).constant == constant);
			}

			public void procedureAdded(IProcedure procedure) {
				body.addElement(new MethodWidget(body, procedure));
			}

			public void procedureDeleted(IProcedure procedure) {
				body.delete(e -> e instanceof MethodWidget && ((MethodWidget) e).procedure == procedure);
			}
		});
	}

	public UiMode getMode() {
		return mode;
	}

	public void toCode(StringBuffer buffer) {
		buffer.append("public class ").append(id.toString()).append(" {").append(lineSeparator());
		for(Control c : getChildren())
			if(c instanceof MethodWidget)
				((MethodWidget) c).toCode(buffer);

		buffer.append("}").append(lineSeparator()).append(lineSeparator());
	}

	private boolean editMode = true;

	private List<Control> addLabels = new ArrayList<>();
	private List<Id> idWidgets = new ArrayList<>();


	private static final Supplier<List<String>> EMPTY_SUPPLIER = () -> Collections.emptyList();


	Id createId(EditorWidget parent, String id) {
		return createId(parent, id, EMPTY_SUPPLIER);
	}

	Id createId(EditorWidget parent, String id, Supplier<List<String>> idProvider) {
		Id w = new Id(parent, id, idProvider);
		idWidgets.add(w);
		return w;
	}

	Text createAddLabel(Composite parent) {
		return createAddLabel(parent, "  ");
	}

	Text createAddLabel(Composite parent, String token) {
		Text label = new Text(parent, SWT.NONE);
		label.setForeground(Constants.FONT_COLOR);
		label.setBackground(Constants.COLOR_ADDLABEL);
		label.setEditable(false);
		label.setFont(Constants.FONT);
		label.setText(token);
		label.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				Menu menu = label.getMenu();
				if(e.keyCode == Constants.MENU_KEY && menu != null) {
					menu.setLocation(label.toDisplay(0, 20));
					menu.setVisible(true);
				}
				else if(e.keyCode == SWT.ARROW_UP) {
					Composite p = label.getParent();
					Control[] children = p.getChildren();
//					if(children[0] == label) {
//						Control[] pchildren = p.getParent().getChildren();
//						if(pchildren.length > 0)
//							pchildren[pchildren.length-2].setFocus();
//					}
//					else {
						for (int i = 2; i < children.length; i++) {
							if(children[i] == label) {
								children[i-2].setFocus();
								return;
							}
						}
//					}
				}
				else if(e.keyCode == SWT.ARROW_DOWN) {
					Composite p = label.getParent();
					Control[] children = p.getChildren();
					for (int i = 0; i < children.length - 2; i++) {
						if(children[i] == label) {
							children[i+2].setFocus();
							return;
						}
					}
				}
			}
		});
		addLabels.add(label);
		return label;
	}


	private static final Predicate<Control> isDisposed = c -> c.isDisposed();


	public void hideAddLabels() {
		// dispose
		editMode = !editMode;
		addLabels.removeIf(isDisposed);
		for(Control l : addLabels) {
			//			l.setForeground(editMode ? WHITE : FONT_COLOR);
			l.setBackground(editMode ? Constants.COLOR_ADDLABEL : Constants.COLOR_BACKGROUND);
			//			l.setVisible(editMode);
		}
		idWidgets.removeIf(isDisposed);
		//		for(Id id : idWidgets)
		//			id.setEditable(!editMode);
	}


}
