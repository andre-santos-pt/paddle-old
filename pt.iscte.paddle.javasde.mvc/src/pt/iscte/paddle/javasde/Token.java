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
	private final Control control;
//	private Token sibling;

	public Token(EditorWidget parent, String token) {
		this(parent, token, Constants.EMPTY_TOKEN_SUPPLIER);
	}

	public Token(EditorWidget parent, String token, Supplier<List<String>> alternatives) {
		List<String> list = alternatives.get();
		control = list.isEmpty() ? new Label(parent, SWT.NONE) : new Text(parent, SWT.NONE);
		if(control instanceof Label)
			((Label) control).setText(token);
		else {
			((Text) control).setText(token);
			((Text) control).setEditable(false);
		}

		if(Constants.isKeyword(token)) {
			control.setFont(Constants.FONT_KW);
			control.setForeground(Constants.COLOR_KW);
		}
		else
			control.setFont(Constants.FONT);

		Menu menu = new Menu(control);
		for(String t : alternatives.get()) {
			MenuItem item = new MenuItem(menu, SWT.NONE);
			item.setText(t);
			item.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					((Text) control).setText(item.getText());
					control.requestLayout();
				}
			});
		}

		control.setMenu(menu);
	}

	//	@Override
	//	public void toCode(StringBuffer buffer) {
	//		if(label instanceof Label)
	//			buffer.append(((Label) label).getText());
	//		else
	//			buffer.append(((Text) label).getText());
	//	}


	public Menu getMenu() {
		return control.getMenu();
	}

	public void setMenu(Menu menu) {
		control.setMenu(menu);
	}

	public void moveBelow(Control control) {
		control.moveBelow(control);
		control.requestLayout();
	}

	public void setVisible(boolean visible) {
		control.setVisible(visible);
	}
	
	boolean isKeyword(String keyword) {
		return control instanceof Label && ((Label) control).getText().equals(keyword);
	}


}
