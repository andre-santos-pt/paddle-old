package pt.iscte.paddle.javasde;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

public class Token implements TextWidget {
	private final Text text;
	private Map<Character, String> map;
	
	private static final List<String>[] EMPTY_ARRAY  = new List[0];
	
	public Token(EditorWidget parent, Keyword token) {
		this(parent, token.toString());
	}
	
	public Token(EditorWidget parent, String token) {
		this(parent, token, EMPTY_ARRAY);
	}

	public Token(EditorWidget parent, String token, List<String> ... alternatives) {
		text = new Text(parent, SWT.NONE);
		text.setText(token);
		text.setEditable(false);

		if(Keyword.is(token)) {
			text.setFont(Constants.FONT_KW);
			text.setForeground(Constants.COLOR_KW);
		}
		else
			text.setFont(token.equals(".") ? Constants.FONT_DOT : Constants.FONT);

		map = new HashMap<>();
		
		Menu menu = new Menu(text);
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
						((Text) text).setText(item.getText());
						text.requestLayout();
					}
				});
				
				prev = t;
			}
			new MenuItem(menu, SWT.SEPARATOR);
		}

		text.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == Constants.MENU_KEY && menu != null) {
					menu.setLocation(text.toDisplay(0, 20));
					menu.setVisible(true);
				}
				else if(map.containsKey(e.character)) {
					text.setText(map.get(e.character));
					text.requestLayout();
					text.selectAll();
				}
				
			}
		});
		
		text.setMenu(menu);
		Constants.addArrowKeys(text, this);
		Constants.addFocusSelectAll(text);
	}

	private static final FocusAdapter FOCUS_LISTENER = new FocusAdapter() {
		public void focusGained(FocusEvent e) {
			((Text) e.widget).selectAll();
		}
	};

	
	@Override
	public Text getWidget() {
		return text;
	}

	public Menu getMenu() {
		return text.getMenu();
	}

	public void setMenu(Menu menu) {
		text.setMenu(menu);
	}

	public void moveBelow(Control control) {
		control.moveBelow(control);
		control.requestLayout();
	}

	public void setVisible(boolean visible) {
		text.setVisible(visible);
	}

	@Override
	public String toString() {
		return getText();
	}
	
	boolean isKeyword(String keyword) {
		return getText().equals(keyword);
	}

	public void setLayoutData(RowData data) {
		text.setLayoutData(data);
	}


	public void dispose() {
		text.dispose();
	}

	public boolean setFocus() {
		return text.setFocus();
	}
	
	public void addKeyListener(KeyListener listener) {
		text.addKeyListener(listener);
	}

}
