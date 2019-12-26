package pt.iscte.paddle.javasde;
import static java.lang.System.lineSeparator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;

public class EditorWidget extends Composite {


	private ClassWidget root;

	public EditorWidget(Composite parent) {
		super(parent, SWT.NONE);
		setLayout(Constants.ROW_LAYOUT_H_ZERO);
		setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));

		Composite e = this;
		while(!(e instanceof ClassWidget))
			e = e.getParent();
		root = (ClassWidget) e;
	}
	
	// fragile
	MethodWidget getParentMethod() {
		EditorWidget e = this;
		while(!(e instanceof MethodWidget))
			e = (EditorWidget)e.getParent();
		
		return (MethodWidget) e;
	}
	

	void popup(Menu menu, Control control) {
		menu.setLocation(control.toDisplay(0, 40));
	}

	void addTokenKeyHandler(KeyListener listener) {
		
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
				sibling.setBackground(Constants.COLOR_INSERT);
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
