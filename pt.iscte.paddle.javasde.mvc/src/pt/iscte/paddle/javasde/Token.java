package pt.iscte.paddle.javasde;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

public class Token {
	private final Control label;
	private Token sibling;

	public Token(EditorWidget parent, String token) {
		this(parent, token, Constants.EMPTY_TOKEN_SUPPLIER);
	}

	public Token(EditorWidget parent, String token, Supplier<List<String>> alternatives) {
		//		super(parent, SWT.NONE);
		//		setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		//		setLayout(new FillLayout());
		List<String> list = alternatives.get();
		label = list.isEmpty() ? new Label(parent, SWT.NONE) : new Text(parent, SWT.NONE);
		if(label instanceof Label)
			((Label) label).setText(token);
		else {
			((Text) label).setText(token);
			((Text) label).setEditable(false);
		}

		if(Constants.isKeyword(token)) {
			label.setFont(Constants.FONT_KW);
			label.setForeground(Constants.COLOR_KW);
		}
		else
			label.setFont(Constants.FONT);

		Menu menu = new Menu(label);
		for(String t : alternatives.get()) {
			MenuItem item = new MenuItem(menu, SWT.NONE);
			item.setText(t);
			item.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					((Text) label).setText(item.getText());
					label.requestLayout();
				}
			});
		}

		label.setMenu(menu);
	}

	//	@Override
	//	public void toCode(StringBuffer buffer) {
	//		if(label instanceof Label)
	//			buffer.append(((Label) label).getText());
	//		else
	//			buffer.append(((Text) label).getText());
	//	}


	public Menu getMenu() {
		return label.getMenu();
	}

	public void setMenu(Menu menu) {
		label.setMenu(menu);
	}

	public void moveBelow(Control control) {
		label.moveBelow(control);
		label.requestLayout();
	}

	public void setVisible(boolean visible) {
		label.setVisible(visible);
	}


}
