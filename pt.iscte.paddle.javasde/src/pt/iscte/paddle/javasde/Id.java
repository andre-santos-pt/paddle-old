package pt.iscte.paddle.javasde;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

public class Id extends EditorWidget {
	private boolean menuMode = false;
	private Text text;
	private Label arrayPart;
	//	private boolean array = false;
	private Menu popupMenu;

	//	private static class IdVerifier implements VerifyKeyListener {
	//		@Override
	//		public void verifyKey(VerifyEvent event) {
	//			public void verifyText(VerifyEvent e) {
	//				if(!test && !validIdCharacter(e.character))
	//					e.doit = false;
	//			}
	//		}
	//	}

	Id(EditorWidget parent, String id, Supplier<List<String>> idProvider) {
		super(parent);
		text = new Text(this, SWT.NONE);
		text.setText(id);
		List<String> provider = idProvider.get();
		setFont(text, true);
		text.addVerifyListener(e -> e.doit = menuMode || provider.isEmpty() && (isValidCharacter(e.character) || e.character == SWT.BS || e.character == SWT.DEL));
		text.addFocusListener(FocusListener.focusGainedAdapter(e -> text.selectAll()));
		text.addModifyListener(e -> { 
			setFont(text, false); 
			text.setBackground(text.getText().isBlank() ? COLOR_ERROR : COLOR_BACK); 
			text.requestLayout();
			text.setFocus();
		});
		
		addKeyListeners(provider);

		popupMenu = new Menu(text);

		for(String s : provider) {
			MenuItem item = new MenuItem(popupMenu, SWT.NONE);
			item.setText(s);
			item.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					menuMode = true;
					text.setText(item.getText());
					setFont(text, false);
					menuMode = false;
				}
			});
		}

		setMenu(popupMenu);
	}

	private void addKeyListeners(List<String> provider) {
		text.addKeyListener(KeyListener.keyPressedAdapter(e -> {
			if(e.keyCode == SWT.ARROW_DOWN) {
				menuMode = true;
				int index = provider.indexOf(text.getText());
				if(index != -1) {
					index = (index+1)%provider.size();
					text.setText(provider.get(index));
				}
				else if(provider.size() > 0)
					text.setText(provider.get(0));
				menuMode = false;
			}
			else if(e.keyCode == SWT.ARROW_UP) {
				menuMode = true;
				int index = provider.indexOf(text.getText());
				if(index != -1) {
					index--;
					if(index == -1)
						index = provider.size()-1;
					text.setText(provider.get(index));
				}
				else if(provider.size() > 0)
					text.setText(provider.get(provider.size()-1));
				menuMode = false;
			}
		}));
	}

	void addArrayPart() {
		arrayPart = new Label(this, SWT.NONE);
		arrayPart.setText(" ");
		arrayPart.setFont(FONT);
		Menu menu = new Menu(arrayPart);
		MenuItem[] items = new MenuItem[4];
		for(int i = 0; i < items.length; i++) {
			MenuItem m = new MenuItem(menu, SWT.CHECK);
			items[i] = m;
			m.setText(i == 0 ? "" : i + "-dimensional array");
			m.setData(i);
			m.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					for(MenuItem it : items)
						if(it != m) 
							it.setSelection(false);

					String dims = "";
					for(int j = (Integer) m.getData(); j > 0; j--)
						dims += "[ ]";
					arrayPart.setText(dims.isEmpty() ? " " : dims);
					arrayPart.requestLayout();
				}
			});
		}
		arrayPart.setMenu(menu);
	}

	//	void addArrayMenu() {
	//		new MenuItem(popupMenu, SWT.SEPARATOR);
	//		
	//		MenuItem arrayItem = new MenuItem(popupMenu, SWT.CHECK);
	//		arrayItem.setText("array");
	//		arrayItem.addSelectionListener(new SelectionAdapter() {
	//			public void widgetSelected(SelectionEvent e) {
	//				menuMode = true;
	//				array = arrayItem.getSelection();
	//				text.setText(text.getText());
	//				arrayPart.setText(array ? "[ ]" : "");
	//				setFont(text, false);
	//				menuMode = false;
	//			}
	//		});
	//	}

	public void setToolTip(String text) {
		this.text.setToolTipText(text);
	}

	@Override
	public boolean setFocus() {
		text.setFocus();
		return true;
	}

	static boolean isValidCharacter(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
	}

	static boolean isValid(String s) {
		return s.matches("[a-zA-Z_]+");
	}

	public void setMenu(Menu menu) {
		text.setMenu(menu);
	}

	@Override
	public String toString() {
		return text.getText();
	}

	public void setEditable(boolean editable) {
		if(editable)
			text.setBackground(ClassWidget.GRAY);
		else
			text.setBackground(ClassWidget.WHITE);
	}
}
