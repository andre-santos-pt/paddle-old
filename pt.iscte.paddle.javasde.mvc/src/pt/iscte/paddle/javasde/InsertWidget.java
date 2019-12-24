package pt.iscte.paddle.javasde;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

public class InsertWidget implements TextWidget {

	final Text text;
	private boolean edit;

	public InsertWidget(EditorWidget parent, boolean editable) {
		text = createAddLabel(parent, editable);
	}

	private Text createAddLabel(EditorWidget parent, boolean editable) {
		Text text = new Text(parent, SWT.SINGLE);
		text.setForeground(Constants.FONT_COLOR);
		text.setEditable(true);
		text.setFont(Constants.FONT);
		text.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				e.doit = editable && 
						(
								edit || Constants.isLetter(e.character) || e.character == '=' || 
								e.character == Constants.DEL_KEY || e.character == SWT.CR ||
								e.character == '/' || (e.character == ' ' && text.getText().startsWith("//"))
								);
			}
		});
		text.addModifyListener(Constants.MODIFY_PACK);
		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				boolean comment = text.getText().startsWith("//");
				if(!comment) {
					edit = true;
					text.setText("");
					edit = false;
				}
			}
		});

		text.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				Menu menu = text.getMenu();
				if(menu != null) {
					if(e.character == '=' && text.getText().length() > 0)
						runAction(menu, 'a', e.widget, text.getText());

					else if((e.character == '(' || e.character == SWT.SPACE) && Keyword.IF.match(text))
						runAction(menu, Keyword.IF.getAccelerator(), e.widget, null);

					else if((e.character == '{' || e.character == SWT.SPACE) && Keyword.ELSE.match(text))
						runAction(menu, Keyword.ELSE.getAccelerator(), e.widget, null);

					else if((e.character == '(' || e.character == SWT.SPACE) && text.getText().equals("while"))
						runAction(menu, 'w', e.widget, null);

					else if((e.character == '(' || e.character == SWT.SPACE) && Keyword.FOR.match(text))
						runAction(menu, 'f', e.widget, null);

					else if(e.character == '(' && !Keyword.is(text.getText()) && text.getText().length() > 0 && text.getCaretPosition() == text.getText().length())
						runAction(menu, 'p', e.widget, text.getText());

					else if(e.character == SWT.SPACE && text.getText().equals("return"))
						runAction(menu, 'r', e.widget, null);

					else if(e.character == SWT.SPACE && isType(text.getText()))
						runAction(menu, 'v', e.widget, text.getText());

					else if(e.character == '[' && isType(text.getText()))
						runAction(menu, 'v', e.widget, text.getText() + "[");

					else if(e.character == '[' && text.getText().length() > 0)
						runAction(menu, 'a', e.widget, text.getText());

					else if((e.character == ';' || e.character == SWT.SPACE) && Keyword.BREAK.match(text))
						runAction(menu, Keyword.BREAK.getAccelerator(), e.widget, null);

					else if((e.character == ';' || e.character == SWT.SPACE) && Keyword.CONTINUE.match(text))
						runAction(menu, Keyword.CONTINUE.getAccelerator(), e.widget, null);

					else if (e.keyCode == Constants.MENU_KEY && !text.getText().startsWith("//"))
						showMenu(text, menu);

					else if (e.keyCode == Constants.DEL_KEY && text.getText().isEmpty())
						deleteAction(menu);

					else if(e.keyCode == SWT.CR && text.getText().startsWith("//")) {
						InsertWidget w = new InsertWidget(parent, editable);
						w.setFocus();
						w.text.requestLayout();
					} 
				}
			}

			// TODO other types
			private boolean isType(String text) {
				return Constants.PRIMITIVE_TYPES.contains(text);
			}

			private void runAction(Menu menu, char accelarator, Widget widget, String param) {
				for (MenuItem menuItem : menu.getItems()) {
					if(menuItem.isEnabled() && menuItem.getAccelerator() == accelarator) {
						edit = true;
						text.setText("");
						edit = false;
						((SequenceWidget.MenuCommand) menuItem.getData()).run(param);
						//text.setText(spaces(spaces));
						if(!editable) {
							Control control = (Control) widget;
							control.setMenu(null); // to not dispose menu
							control.dispose();
						}
						break;
					}
				}
			}
		});

		text.addKeyListener(Constants.LISTENER_ARROW_KEYS);
		text.setData(this);
		return text;
	}


	private void showMenu(Text label, Menu menu) {
		int c = 0;
		MenuItem item = null;
		for (MenuItem menuItem : menu.getItems()) {
			if (menuItem.isEnabled() && menuItem.getStyle() != SWT.SEPARATOR
					&& menuItem.getAccelerator() != Constants.DEL_KEY
					&& (menuItem.getData() instanceof SelectionListener
							|| menuItem.getData() instanceof SequenceWidget.MenuCommand)) {
				item = menuItem;
				c++;
			}
		}
		if (c == 1) {
			((SelectionListener) item.getData()).widgetSelected(null);
		} 
		else {
			menu.setLocation(label.toDisplay(0, 20));
			menu.setVisible(true);
		}
	}

	private void deleteAction(Menu menu) {
		for (MenuItem menuItem : menu.getItems()) {
			if (menuItem.isEnabled() && menuItem.getAccelerator() == Constants.DEL_KEY) {
				((SelectionListener) menuItem.getData()).widgetSelected(null);
				break;
			}
		}
	}


	public void setFocus() {
		text.setFocus();
	}

	public void setLayoutData(Object data) {
		text.setLayoutData(data);
	}

	public void setMenu(Menu menu) {
		text.setMenu(menu);
	}

	public Menu createMenu() {
		Menu menu = new Menu(text);
		text.setMenu(menu);
		return menu;
	}

	public void dispose() {
		text.dispose();
	}

	@Override
	public Text getWidget() {
		return text;
	}
}
