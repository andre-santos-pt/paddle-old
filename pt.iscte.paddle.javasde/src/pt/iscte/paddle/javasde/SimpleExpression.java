package pt.iscte.paddle.javasde;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;

public class SimpleExpression extends EditorWidget {

	private Text text;
	private Class<?> literalType;

	public SimpleExpression(EditorWidget parent, String literal) {
		super(parent, parent.mode);
		setLayout(ROW_LAYOUT_H_ZERO);
		setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		text = new Text(this, SWT.NONE);
		text.setText(literal);
		setFont(text, true);
		text.addVerifyListener(e -> e.doit = validCharacter(e.character) || e.character == '.' && text.getText().indexOf('.') == -1);
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				try {
					Integer.parseInt(text.getText());
					literalType = Integer.class;
					text.setToolTipText("integer");
				}
				catch(NumberFormatException ex) {
					try {
						Double.parseDouble(text.getText());
						literalType = Double.class;
					}
					catch(NumberFormatException ex2) {
						if(text.getText().matches("'.'"))
							literalType = Character.class;
						else if(text.getText().matches("true|false"))
							literalType = Boolean.class;
						else if(Id.isValid(text.getText()))
							literalType = null;
						else {
							text.setForeground(COLOR_ERROR);
							text.setFont(FONT_PH);
							text.setToolTipText("invalid literal");
							text.requestLayout();
							literalType = null;
							return;
						}
					}
				}

				text.setForeground(FONT_COLOR);
				text.setFont(FONT);
				text.requestLayout();
			}
		});
		text.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				text.selectAll();
			}
		});
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.character == '!') {
					UnaryExpressionWidget w = new UnaryExpressionWidget((EditorWidget) getParent(), "!", text.getText());
					w.setFocus();
					dispose();
					w.requestLayout();
				}
				else if(e.character == '+') {
					BinaryExpressionWidget w = new BinaryExpressionWidget((EditorWidget) getParent(), "+");
					w.setLeft(text.getText());
					w.focusRight();
					dispose();
					w.requestLayout();
				}
				else if(e.character == '[') {
					ArrayElementExpression w = new ArrayElementExpression((EditorWidget) getParent(), text.getText());
					w.focusExpression();
					dispose();
					w.requestLayout();
				}
				else if(e.character == '.' && text.getText().matches("[a-zA-Z_]+")) {
					FieldExpression w = new FieldExpression((EditorWidget) getParent(), text.getText());
					w.focusExpression();
					dispose();
					w.requestLayout();
				}
			}
		});
		text.setMenu(new Menu(text));
	}

	private boolean validCharacter(char c) {
		return Id.isValidCharacter(c) || c >= '0' && c <= '9';
	}

	@Override
	public void setMenu(Menu menu) {
		text.setMenu(menu);
	}

	public void set(String expression) {
		text.setText(expression);
	}

	public void setForeground(Color color) {
		text.setForeground(color);
	}
	
	@Override
	public void toCode(StringBuffer buffer) {
		buffer.append(text.getText());
	}
	
	@Override
	public String toString() {
		return text.getText();
	}
}
