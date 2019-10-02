
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class ClassWidget extends EditorWidget {

	private Id id;
	private Label addLabel;
	
	public ClassWidget(Composite parent, String name) {
		super(parent);
//		RowLayout layout = new Gr(SWT.VERTICAL);
		setLayout(new GridLayout(1, true));

		EditorWidget header = new EditorWidget(this);
		header.setLayout(ROW_LAYOUT_ZERO);
		new Token(header, "class");
		id = ClassWidget.createId(header, "Name", () -> Collections.emptyList());
		new Token(header, "{");
		
		MethodWidget methodWidget = new MethodWidget(this, "method");
	
		new ControlWidget(methodWidget.getSequence(), false);
		new DeclarationWidget(methodWidget.getSequence());
		new AssignmentWidget(methodWidget.getSequence());
		
		Menu menu = new Menu(this);
		MenuItem item = new MenuItem(menu, SWT.NONE);
		item.setText("method");
		item.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MethodWidget m = new MethodWidget(ClassWidget.this, "func");
				m.moveAbove(addLabel);
				ClassWidget.this.getParent().layout();
			}
		});
		addLabel = createAddLabel(this);
		addLabel.setMenu(menu);
		
//		Label addLabel = new Label(this, SWT.BORDER);
//		addLabel.setText("+");
//		addLabel.addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
//			@Override
//			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
//				MethodWidget m = new MethodWidget(ClassWidget.this, "func");
//				m.moveAbove(addLabel);
//				ClassWidget.this.getParent().layout();
//			}
//		});
		new Token(this, "}");		
	}

	public void toCode(StringBuffer buffer) {
		buffer.append("class ").append(id).append(" {");
		for(Control c : getChildren())
			if(c instanceof MethodWidget)
				((MethodWidget) c).toCode(buffer);
		
		buffer.append("}\n");
	}
	
	private static List<Label> addLabels = new ArrayList<>();
	private static boolean editMode = true;
	
	private static List<Id> idWidgets = new ArrayList<>();
	
	private static Font font = new Font(Display.getDefault(), new FontData("Monospace",30,SWT.BOLD));
	
	static Color WHITE = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
	static Color GRAY = new Color(Display.getDefault(), 240, 240, 240);
	
	static Id createId(Composite parent, String id, Supplier<List<String>> idProvider) {
		Id w = new Id(parent, id, idProvider);
		idWidgets.add(w);
		return w;
	}
	
	static Label createAddLabel(EditorWidget parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setForeground(editMode ? GRAY : WHITE);
		label.setFont(font);
//		label.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false));
		label.setText("+");
		addLabels.add(label);
		return label;
	}
	
	
	static void hideAddLabels() {
		// dispose
		editMode = !editMode;
		for(Label l : addLabels) {
			l.setForeground(editMode ? WHITE : GRAY);
//			l.setVisible(editMode);
		}
		for(Id id : idWidgets)
			id.setEditable(!editMode);
	}
}
