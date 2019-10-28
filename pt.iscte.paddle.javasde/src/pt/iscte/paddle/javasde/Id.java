package pt.iscte.paddle.javasde;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

public class Id extends EditorWidget {

	private final String initialId;
	private boolean menuMode = false;
	private final Text text;
	private Label arrayPart;
	private Menu popupMenu;

	private SelectionListener[] typeListeners;
	private SelectionListener[] arrayListeners;

	private List<String> provider;

	Id(EditorWidget parent, String id, Supplier<List<String>> idProvider) {
		super(parent);
		initialId = id;
		text = new Text(this, SWT.NONE);
		text.setText(id);
		provider = idProvider.get();
		setFont(text, true);
		text.addVerifyListener(e -> e.doit = menuMode || provider.isEmpty() && (isValidCharacter(e.character) || e.character == SWT.BS || e.character == SWT.DEL));

		text.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				if(text.getText().isBlank()) {
					menuMode = true;
					text.setText(initialId);
					menuMode = false;
					setFont(text, true); 
					text.requestLayout();
				}
			}
			public void focusGained(FocusEvent e) {
				text.selectAll();
			}
		});

		text.addModifyListener(e -> { 
			setFont(text, false); 
			text.requestLayout();
		});

		if(!provider.isEmpty()) {
			addMenu(provider);
			addKeyListeners();
		}
	}

	private void addMenu(List<String> provider) {
		popupMenu = new Menu(text);
		MenuItem[] items = new MenuItem[provider.size()];
		typeListeners = new SelectionListener[provider.size()];
		for(int i = 0; i < provider.size(); i++) {
			MenuItem it = new MenuItem(popupMenu, SWT.CHECK);
			items[i] = it;
			items[i].setText(provider.get(i));
			items[i].setSelection(provider.get(i).equals(initialId));
			typeListeners[i] = new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					for(MenuItem m : items)
						m.setSelection(m == it);
					menuMode = true;
					text.setText(it.getText());
					menuMode = false;
					setFont(text, false);
					text.requestLayout();
				}
			};
			items[i].addSelectionListener(typeListeners[i]);
		}
		setMenu(popupMenu);
	}

	void addArrayPart() {
		arrayPart = new Label(this, SWT.NONE);
		arrayPart.setText("");
		arrayPart.setFont(FONT);
		new MenuItem(popupMenu, SWT.SEPARATOR);
		MenuItem[] items = new MenuItem[ARRAY_DIMS+1];
		arrayListeners = new SelectionListener[ARRAY_DIMS+1];
		for(int i = 0; i < items.length; i++) {
			MenuItem m = new MenuItem(popupMenu, SWT.CHECK);
			items[i] = m;
			m.setText(i == 0 ? "single variable" : i + "-dimensional array");
			m.setSelection(i == 0);
			m.setData(i);
			arrayListeners[i] = new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					for(MenuItem it : items)
						it.setSelection(it == m);

					String dims = "";
					for(int j = (Integer) m.getData(); j > 0; j--)
						dims += "[ ]";
					arrayPart.setText(dims.isEmpty() ? " " : dims);
					arrayPart.requestLayout();
				}
			};
			m.addSelectionListener(arrayListeners[i]);
		}
	}

	private void addKeyListeners() {
		text.addKeyListener(KeyListener.keyPressedAdapter(e -> {
			if(e.keyCode == MENU_KEY && popupMenu != null) {
				popup(popupMenu, text);
			}
			else if(e.keyCode == SWT.ARROW_DOWN) {
				int index = provider.indexOf(text.getText());
				if(index != -1) {
					index = (index+1)%provider.size();
					typeListeners[index].widgetSelected(null);
				}
				else if(provider.size() > 0)
					typeListeners[0].widgetSelected(null);
			}
			else if(e.keyCode == SWT.ARROW_UP) {
				int index = provider.indexOf(text.getText());
				if(index != -1) {
					index--;
					if(index == -1)
						index = provider.size()-1;
					typeListeners[index].widgetSelected(null);
				}
				else if(provider.size() > 0)
					typeListeners[0].widgetSelected(null);
			}
			else if(arrayPart != null && e.character >= '0' && e.character <= '0' + ARRAY_DIMS) {
				arrayListeners[e.character - '0'].widgetSelected(null);
			}
		}));
	}

	public void setToolTip(String text) {
		this.text.setToolTipText(text);
	}

	@Override
	public boolean setFocus() {
		text.setFocus();
		return true;
	}

	public static boolean isValidCharacter(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
	}

	public static boolean isValid(String s) {
		return s.matches("[a-zA-Z_]+");
	}

	public void setMenu(Menu menu) {
		text.setMenu(menu);
	}

	@Override
	public String toString() {
		return text.getText();
	}
}
