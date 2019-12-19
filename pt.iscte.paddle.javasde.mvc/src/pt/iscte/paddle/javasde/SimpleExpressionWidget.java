package pt.iscte.paddle.javasde;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;

public class SimpleExpressionWidget extends Canvas {

	private Text text;
	private Class<?> literalType;
	final boolean assign;
	
	public SimpleExpressionWidget(EditorWidget parent, String literal, boolean assign) {
		super(parent, SWT.NONE);
		this.assign = assign;
		setLayout(Constants.ROW_LAYOUT_H_ZERO);
		setBackground(Constants.COLOR_BACKGROUND);
		text = new Text(this, SWT.NONE);
		text.setText(literal);
		Constants.setFont(text, true);
		text.addVerifyListener(e -> e.doit = validCharacter(e.character) || e.character == '.' && text.getText().indexOf('.') == -1 || e.character == SWT.BS);
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				text.setForeground(Constants.FONT_COLOR);
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
							text.setForeground(Constants.COLOR_ERROR);
							text.setToolTipText("invalid literal");
							text.requestLayout();
							literalType = null;
						}
					}
				}
				text.requestLayout();
			}
		});
		text.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				text.selectAll();
			}
			@Override
			public void focusLost(FocusEvent e) {
				Constants.setFont(text, false);
				text.requestLayout();
			}
		});
		
		text.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				String match = null;
				EditorWidget w = null;
				if(!assign && (match = match(e.character, Constants.UNARY_OPERATORS)) != null) {
					w = new UnaryExpressionWidget((EditorWidget) getParent(), match, text.getText());
					w.setFocus();
				}
				else if(!assign && (match = match(e.character, Constants.BINARY_OPERATORS)) != null) {
					BinaryExpressionWidget b = new BinaryExpressionWidget((EditorWidget) getParent(), match);
					b.setLeft(text.getText());
					b.focusRight();
					w = b;
				}
				else if(e.character == '[') {
					ArrayElementExpression a = new ArrayElementExpression((EditorWidget) getParent(), text.getText());
					a.focusExpression();
					w = a;
				}
				else if(e.character == '.' && text.getText().matches("[a-zA-Z_]+")) {
					FieldExpression f = new FieldExpression((EditorWidget) getParent(), text.getText());
					f.focusExpression();
					w = f;
				}
				else if(!assign && e.character == '(' && text.getText().matches("[a-zA-Z_]+")) {
					CallWidget c = new CallWidget((EditorWidget) getParent(), text.getText(), false);
					c.focusArgument();
					w = c;
				}
				
				if(w != null) {
					dispose();
					w.requestLayout();
				}
			}

		});
		text.setMenu(new Menu(text));
	}

	private String match(char character, List<String> operators) {
		for(String o : operators)
			if(o.charAt(0) == character)
				return o;
		return null;
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
	public String toString() {
		return text.getText();
	}
	
	@Override
	public void addFocusListener(FocusListener listener) {
		text.addFocusListener(listener);
	}
	
	@Override
	public void addKeyListener(KeyListener listener) {
		text.addKeyListener(listener);
	}

	public String getText() {
		return text.getText();
	}
}
