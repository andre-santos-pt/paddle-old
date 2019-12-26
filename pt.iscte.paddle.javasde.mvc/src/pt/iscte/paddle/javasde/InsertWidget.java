package pt.iscte.paddle.javasde;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

public class InsertWidget implements TextWidget {

	final Text text;
	private boolean edit;

	public InsertWidget(Composite parent, boolean editable) {
		text = createAddLabel(parent, editable);
	}

	private Text createAddLabel(Composite parent, boolean editable) {
		Text text = new Text(parent, SWT.SINGLE);
		text.setText(" ");
		text.setForeground(Constants.FONT_COLOR);
		text.setEditable(true);
		text.setFont(Constants.FONT);
		text.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				e.doit = editable && 
						(
								edit || Constants.isLetter(e.character) || Constants.isNumber(e.character) || 
								e.character == '.' && text.getText().matches("[0-9]*") ||
								e.character == '=' || 
								e.character == Constants.DEL_KEY || e.character == SWT.CR ||
								e.character == '/' || (e.character == ' ' && text.getText().startsWith("//"))
								);
			}
		});
		text.addModifyListener(Constants.MODIFY_PACK);
		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				text.selectAll();
			}
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
				int index = SequenceWidget.findModelIndex(text);
				for(Action a : actions)
					if(a.isEnabled(e.character, text.getText(), index,  text.getCaretPosition(), text.getSelectionCount())) {
						a.run(e.character, text.getText(), index, text.getCaretPosition(), text.getSelectionCount());
						return;
					}
			
				Menu menu = text.getMenu();
				if(menu != null) {
					if (e.keyCode == Constants.MENU_KEY && !text.getText().startsWith("//"))
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
		Constants.addArrowKeys(text, TextWidget.create(text));
//		text.addFocusListener(Constants.ADD_HIDE);
		return text;
	}

	public static abstract class Action {
		final CharSequence text;
		final char accelerator;
		public Action(CharSequence text, char accelerator) {
			this.text = text;
			this.accelerator = accelerator;
		}
		public boolean isEnabled(char c, String text, int index, int caret, int selection) {
			return true;
		}
		public abstract void run(char c, String text, int index, int caret, int selection);
	}
	
	private List<Action> actions = new ArrayList<>();
	
	void addAction(Action a) {
		assert a != null;
		actions.add(a);
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

	public void requestLayout() {
		text.requestLayout();
	}

	public void addFocusListener(FocusListener listener) {
		text.addFocusListener(listener);
	}

	public void addKeyListener(KeyAdapter listener) {
		text.addKeyListener(listener);
	}
}
