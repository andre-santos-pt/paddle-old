package pt.iscte.paddle.javasde;

import static java.lang.System.lineSeparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;


public class ClassWidget extends EditorWidget {

	private String name;
	private Id id;
	private Label addLabel;

	public ClassWidget(Composite parent, String name, UiMode mode) {
		super(parent, mode);
		this.name = name;
		GridLayout layout = new GridLayout(1, true);
		layout.verticalSpacing = 10;
		setLayout(layout);

		if(!mode.staticClass) {
			EditorWidget header = new EditorWidget(this, mode);
			header.setLayout(ROW_LAYOUT_H_ZERO);
			new Token(header, "class");
			id = ClassWidget.createId(header, name);
			new Token(header, "{");
		}

		MethodWidget methodWidget = new MethodWidget(this, "method", "int", mode);

		Menu menu = createMenu(mode);
		
		addLabel = createAddLabel(this);
		addLabel.setMenu(menu);

		if(!mode.staticClass)
			new Token(this, "}");		
	}

	private Menu createMenu(UiMode mode) {
		Menu menu = new Menu(this);
		MenuItem item = new MenuItem(menu, SWT.NONE);
		item.setText("function");
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MethodWidget m = new MethodWidget(ClassWidget.this, "func", "type", mode);
				m.moveAbove(addLabel);
				m.requestLayout();
				m.setFocus();
			}
		});
		MenuItem item2 = new MenuItem(menu, SWT.NONE);
		item2.setText("procedure");
		item2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MethodWidget m = new MethodWidget(ClassWidget.this, "proc", "void", mode);
				m.moveAbove(addLabel);
				m.requestLayout();
				m.setFocus();
			}
		});
		return menu;
	}

	public void toCode(StringBuffer buffer) {
		buffer.append("public class ").append(name).append(" {").append(lineSeparator());
		for(Control c : getChildren())
			if(c instanceof MethodWidget)
				((MethodWidget) c).toCode(buffer);

		buffer.append("}").append(lineSeparator()).append(lineSeparator());
	}

	private static List<Label> addLabels = new ArrayList<>();
	private static boolean editMode = true;

	private static List<Id> idWidgets = new ArrayList<>();


	static Color WHITE = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
	static Color GRAY = new Color(Display.getDefault(), 240, 240, 240);

	private static final Supplier<List<String>> EMPTY_SUPPLIER = () -> Collections.emptyList();
	
	static Id createId(EditorWidget parent, String id) {
		return createId(parent, id, EMPTY_SUPPLIER);
	}
	
	static Id createId(EditorWidget parent, String id, Supplier<List<String>> idProvider) {
		Id w = new Id(parent, id, idProvider);
		idWidgets.add(w);
		return w;
	}

	static Label createAddLabel(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setForeground(editMode ? GRAY : WHITE);
		label.setFont(FONT);
		label.setText("+");
		addLabels.add(label);
		return label;
	}


	private static final Predicate<Control> isDisposed = c -> c.isDisposed();
	public static void hideAddLabels() {
		
		// dispose
		editMode = !editMode;
		addLabels.removeIf(isDisposed);
		for(Label l : addLabels) {
			l.setForeground(editMode ? WHITE : GRAY);
			//			l.setVisible(editMode);
		}
		idWidgets.removeIf(isDisposed);
		for(Id id : idWidgets)
			id.setEditable(!editMode);
	}
}
