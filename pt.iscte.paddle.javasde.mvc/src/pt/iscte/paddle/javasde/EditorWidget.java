package pt.iscte.paddle.javasde;
import static java.lang.System.lineSeparator;

import java.util.List;
import java.util.function.Supplier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;

public class EditorWidget extends Composite {

	final UiMode mode;

	private EditorWidget root;

	public EditorWidget(EditorWidget parent) {
		this(parent, parent.mode);
	}

	public EditorWidget(Composite parent, UiMode mode) {
		super(parent, SWT.NONE);
		this.mode = mode;
		setLayout(Constants.ROW_LAYOUT_H_ZERO);
		setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));

		EditorWidget e = this;
		while(!(e instanceof ClassWidget))
			e = (EditorWidget)e.getParent();
		root = (EditorWidget) e;
	}

	Text createAddLabel(Composite parent) {
		return root.createAddLabel(parent);
	}

	Text createAddLabel(Composite parent, String token) {
		return root.createAddLabel(parent, token);
	}

	Id createId(EditorWidget parent, String id) {
		return root.createId(parent, id);
	}

	Id createId(EditorWidget parent, String id, Supplier<List<String>> idProvider) {
		return root.createId(parent, id, idProvider);
	}

	
	public UiMode getMode() {
		return mode;
	}

	void popup(Menu menu, Control control) {
		menu.setLocation(control.toDisplay(0, 40));
	}

	
	void setSibling(Control sibling) {
		sibling.addFocusListener(new FocusListener() {
			Color prev;
			public void focusLost(FocusEvent e) {
				setBackground(prev);
			}
			public void focusGained(FocusEvent e) {
				prev = getBackground();
				setBackground(Constants.COLOR_HIGHLIGHT);
			}
		});
		addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				sibling.setBackground(Constants.COLOR_ADDLABEL);
			}
			public void focusGained(FocusEvent e) {
				sibling.setBackground(Constants.COLOR_HIGHLIGHT);
			}
		});
	}
	
	public void toCode(StringBuffer buffer, int level) {
		while(level-- > 0)
			buffer.append("\t");

		toCode(buffer);
		buffer.append(lineSeparator());
	}

	// to override
	public void toCode(StringBuffer buffer) {
		buffer.append("#" + this.getClass().getSimpleName() + "#");
	}

	public void accept(Visitor visitor) {
		for (Control c : getChildren()) {
//			if(c instanceof ConstantWidget)
//				visitor.visit(((ConstantWidget) c));
//			else if(c instanceof MethodWidget)
//				visitor.visit(((MethodWidget) c));
//			else if(c instanceof WhileWidget)
//				visitor.visit(((WhileWidget) c));
			
			if(c instanceof EditorWidget)
				((EditorWidget) c).accept(visitor);
		}
	}

}
