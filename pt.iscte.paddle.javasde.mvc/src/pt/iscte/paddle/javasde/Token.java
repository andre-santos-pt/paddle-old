package pt.iscte.paddle.javasde;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

public class Token {
	private final Control control;
	private Map<Character, String> map;
	
	private static final List<String>[] EMPTY_ARRAY  = new List[0];
	
	public Token(EditorWidget parent, Keyword token) {
		this(parent, token.toString(), new List[] { Arrays.asList(token.toString()) });
	}
	
	public Token(EditorWidget parent, String token) {
		this(parent, token, EMPTY_ARRAY);
	}

	public Token(EditorWidget parent, String token, List<String> ... alternatives) {
		control = alternatives.length == 0 ? new Label(parent, SWT.NONE) : new Text(parent, SWT.NONE);
		if(control instanceof Label) {
			((Label) control).setText(token);
			
		}
		else {
			((Text) control).setText(token);
			((Text) control).setEditable(false);
		}

		if(Constants.isKeyword(token)) {
			control.setFont(Constants.FONT_KW);
			control.setForeground(Constants.COLOR_KW);
		}
		else
			control.setFont(token.equals(".") ? Constants.FONT_DOT : Constants.FONT);

		map = new HashMap<>();
		
		Menu menu = new Menu(control);
		String prev = null;
		for(List<String> set : alternatives) {
			for(String t : set) {
				MenuItem item = new MenuItem(menu, SWT.NONE);
				item.setText(t);
				if(prev != null && prev.charAt(0) != t.charAt(0)) {
					item.setAccelerator(t.charAt(0));
					map.put(t.charAt(0), t);
				}
				item.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						((Text) control).setText(item.getText());
						control.requestLayout();
					}
				});
				
				prev = t;
			}
			new MenuItem(menu, SWT.SEPARATOR);
		}

		control.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == Constants.MENU_KEY && menu != null) {
					menu.setLocation(control.toDisplay(0, 20));
					menu.setVisible(true);
				}
				else if(map.containsKey(e.character)) {
					((Text) control).setText(map.get(e.character));
					control.requestLayout();
					((Text) control).selectAll();
				}
				
			}
		});

		control.addFocusListener(FOCUS_LISTENER);
		control.setMenu(menu);
	}

	private static final FocusAdapter FOCUS_LISTENER = new FocusAdapter() {
		public void focusGained(FocusEvent e) {
			if(e.widget instanceof Text)
				((Text) e.widget).selectAll();
		}
	};

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

	public void setLayoutData(RowData data) {
		control.setLayoutData(data);
	}

	public void addKeyListener(KeyListener keyListener) {
		control.addKeyListener(keyListener);
	}

//	public void addClickToFocus() {
//		control.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseDown(MouseEvent e) {
//				control.setFocus();
//			}
//		});
//	}


}
