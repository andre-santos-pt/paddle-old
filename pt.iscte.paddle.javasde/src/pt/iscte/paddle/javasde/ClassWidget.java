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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;


public class ClassWidget extends EditorWidget {

	private Id id;
	private Text closeBlock;
	private UiMode mode;

	public ClassWidget(Composite parent, String name, UiMode mode) {
		super(parent, mode);
		this.mode = mode;
		GridLayout layout = new GridLayout(1, true);
		layout.verticalSpacing = 10;
		setLayout(layout);

		Token openBlock = null;
		if(!mode.staticClass) {
			EditorWidget header = new EditorWidget(this, mode);
			header.setLayout(ROW_LAYOUT_H_ZERO);
			new Token(header, "class");
			id = createId(header, name);
			openBlock = new Token(header, "{");
		}

		Menu menu = createMenu(mode);

		closeBlock = createAddLabel(this, mode.staticClass ? " " : "}");
		if(!mode.staticClass)
			openBlock.setSibling(closeBlock);

		closeBlock.setMenu(menu);

//		if(!mode.staticClass)
//			new Token(this, "}");		
	}

	public UiMode getMode() {
		return mode;
	}

	private Menu createMenu(UiMode mode) {
		Menu menu = new Menu(this);
		MenuItem item = new MenuItem(menu, SWT.NONE);
		item.setText("function");
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MethodWidget m = new MethodWidget(ClassWidget.this, "func", "type");
				m.moveAbove(closeBlock);
				m.requestLayout();
				m.setFocus();
			}
		});
		MenuItem item2 = new MenuItem(menu, SWT.NONE);
		item2.setText("procedure");
		item2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MethodWidget m = new MethodWidget(ClassWidget.this, "proc", "void");
				m.moveAbove(closeBlock);
				m.requestLayout();
				m.setFocus();
			}
		});
		return menu;
	}

	public void toCode(StringBuffer buffer) {
		buffer.append("public class ").append(id.toString()).append(" {").append(lineSeparator());
		for(Control c : getChildren())
			if(c instanceof MethodWidget)
				((MethodWidget) c).toCode(buffer);

		buffer.append("}").append(lineSeparator()).append(lineSeparator());
	}

	private List<Control> addLabels = new ArrayList<>();
	private boolean editMode = true;

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
		return createAddLabel(parent, " ");
	}

	Text createAddLabel(Composite parent, String token) {
		Text label = new Text(parent, SWT.NONE);
		label.setForeground(FONT_COLOR);
		label.setBackground(COLOR_ADDLABEL);
		label.setEditable(false);
		label.setFont(FONT);
		label.setText(token);
		label.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				Menu menu = label.getMenu();
				if(e.keyCode == MENU_KEY && menu != null) {
					menu.setLocation(label.toDisplay(0, 20));
					menu.setVisible(true);
					menu.setDefaultItem(menu.getItem(0));
				}
				else if(e.keyCode == SWT.ARROW_UP) {
					Composite p = label.getParent();
					Control[] children = p.getChildren();
					for (int i = 2; i < children.length; i++) {
						if(children[i] == label) {
							children[i-2].setFocus();
							return;
						}
					}
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
			l.setBackground(editMode ? COLOR_ADDLABEL : COLOR_BACKGROUND);
			//			l.setVisible(editMode);
		}
		idWidgets.removeIf(isDisposed);
		//		for(Id id : idWidgets)
		//			id.setEditable(!editMode);
	}

	public MethodWidget createMethod(String name, String returnType) {
		MethodWidget m = new MethodWidget(this, name, returnType);
		m.moveAbove(closeBlock);
		return m;
	}

}
