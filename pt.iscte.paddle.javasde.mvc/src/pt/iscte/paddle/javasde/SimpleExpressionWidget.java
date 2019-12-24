package pt.iscte.paddle.javasde;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;

public class SimpleExpressionWidget extends Canvas implements TextWidget, Expression {

	private Text text;
	private Class<?> literalType;
	final boolean assign;
	
	public SimpleExpressionWidget(EditorWidget parent, String literal, boolean assign) {
		super(parent, SWT.NONE);
		this.assign = assign;
		setLayout(Constants.ROW_LAYOUT_H_ZERO);
		
		text = new Text(this, SWT.NONE);
		text.setText(literal);
		text.setBackground(Constants.COLOR_BACKGROUND);
		Constants.setFont(text, true);
		text.addVerifyListener(e -> e.doit = 
				validCharacter(e.character) || 
				e.character == '.' && text.getText().indexOf('.') == -1 || 
				e.character == '-' && text.getText().indexOf('-') == -1 ||
				e.character == SWT.BS);

		text.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				text.selectAll();
			}
			@Override
			public void focusLost(FocusEvent e) {
				Constants.setFont(text, false);
				text.setBackground(Constants.COLOR_BACKGROUND);
				text.setToolTipText("");
				try {
					Integer.parseInt(text.getText());
					literalType = Integer.class;
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
							text.setBackground(Constants.COLOR_ERROR);
							text.setToolTipText("invalid expression");
							text.requestLayout();
							literalType = null;
						}
					}
				}
				text.requestLayout();
			}
		});
		
		Constants.addArrowKeys(text, this);
		
		addTransformationKeyListener(assign);
		text.addModifyListener(Constants.MODIFY_PACK);
		text.setMenu(new Menu(text));
	}

	private void addTransformationKeyListener(boolean assign) {
		text.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				String match = null;
				EditorWidget w = null;
				if(!assign && text.getCaretPosition() == 0 && (match = match(e.character, Constants.UNARY_OPERATORS)) != null) {
					w = new UnaryExpressionWidget((EditorWidget) getParent(), match, text.getText());
					w.setFocus();
				}
				else if(!assign && text.getCaretPosition() == text.getText().length() && (match = match(e.character, Constants.BINARY_OPERATORS)) != null) {
					BinaryExpressionWidget b = new BinaryExpressionWidget((EditorWidget) getParent(), match);
					b.setLeft(text.getText());
					b.focusRight();
					w = b;
				}
				else if(!assign && e.character == SWT.SPACE && text.getText().equals("new")) {
					ArrayAllocationExpression a = new ArrayAllocationExpression((EditorWidget) getParent());
					a.setFocus();
					w = a;
				}
				else if(!assign && e.character == '(' && Id.isValid(text.getText())) {
					CallWidget c = new CallWidget((EditorWidget) getParent(), text.getText(), false);
					c.focusArgument();
					w = c;
				}
				else if(e.character == '[') {
					ArrayElementExpression a = new ArrayElementExpression((EditorWidget) getParent(), text.getText(), "expression");
					a.addExpressionInserts();
					a.focusExpression();
					w = a;
				}
				else if(e.character == '.' && Id.isValid(text.getText())) {
					FieldExpression f = new FieldExpression((EditorWidget) getParent(), text.getText());
					f.focusExpression();
					w = f;
				}
				
				if(w != null) {
					dispose();
					w.requestLayout();
				}
			}
		});
	}

	@Override
	public void setData(Object data) {
		text.setData(data);
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

	@Override
	public String toString() {
		return text.getText();
	}

	@Override
	public Text getWidget() {
		return text;
	}
	
	@Override
	public Expression copyTo(EditorWidget parent) {
		return new SimpleExpressionWidget(parent, text.getText(), assign);
	}
	
	@Override
	public void addKeyListener(KeyListener listener) {
		text.addKeyListener(listener);
	}
	
//	@Override
//	public void dispose() {
//		super.dispose();
//	}
	
//	@Override
//	public boolean setFocus() {
//		return text.setFocus();
//	}
}
