package pt.iscte.paddle.javasde;

import java.util.function.Function;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class BinaryExpressionWidget extends EditorWidget implements Expression {
	private ExpressionWidget left;
	private ExpressionWidget right;
	private Token op;
	private boolean brackets;
	
	private RowData data = new RowData();

	public BinaryExpressionWidget(EditorWidget parent, String operator) {
		this(parent, 
				e -> new SimpleExpressionWidget(e, "", false),
				e -> new SimpleExpressionWidget(e, "", false),
				operator);
	}
	
	public BinaryExpressionWidget(EditorWidget parent, Function<EditorWidget, Expression> left, String operator) {
		this(parent, 
				left,
				e -> new SimpleExpressionWidget(e, "", false),
				operator);
	}
	
	public BinaryExpressionWidget(EditorWidget parent, Function<EditorWidget, Expression> left, Function<EditorWidget, Expression> right, String operator) {
		super(parent);
		brackets = false;
		setLayout(Constants.ROW_LAYOUT_H_ZERO);
		data.exclude = !brackets;
		
		FixedToken lBracket = new FixedToken(this, "(");
		lBracket.setLayoutData(data);
	
		this.left = new ExpressionWidget(this, left);
		this.op = new Token(this, operator, Constants.ARITHMETIC_OPERATORS, Constants.RELATIONAL_OPERATORS, Constants.LOGICAL_OPERATORS);
		this.right = new ExpressionWidget(this, right);
		
		FixedToken rBracket = new FixedToken(this, ")");
		rBracket.setLayoutData(data);
		
		Menu menu = op.getMenu();
		new MenuItem(menu, SWT.SEPARATOR);
		
		MenuItem brack = new MenuItem(menu, SWT.NONE);
		brack.setText("( ... )");
		brack.setAccelerator('(');
		brack.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				data.exclude = brackets;
				brackets = !brackets;
				lBracket.setVisible(brackets);
				rBracket.setVisible(brackets);
				BinaryExpressionWidget.this.requestLayout();
			}
		});
		
		MenuItem simple = new MenuItem(menu, SWT.NONE);
		simple.setText("delete");
		simple.setAccelerator(Constants.DEL_KEY);
		simple.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				deleteOperator();
			}
		});
		
		// problema com tabs
//		op.addKeyListener(new KeyAdapter() {
//			public void keyPressed(KeyEvent e) {
//				if(e.keyCode == Constants.DEL_KEY)
//					deleteOperator();
//			}
//		});
		
		this.right.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == Constants.DEL_KEY)
					deleteOperator();
			}
		});
	}
	
	@Override
	public boolean setFocus() {
		left.setFocus();
		return true;
	}

	void setLeft(String expression) {
		left.set(expression);
	}

	public void focusRight() {
		left.setForeground(Constants.FONT_COLOR);
		right.setFocus();
		requestLayout();
	}

	@Override
	public void toCode(StringBuffer buffer) {
		if(brackets) buffer.append("(");
		left.toCode(buffer);
		buffer.append(" " + op + " ");
		right.toCode(buffer);
		if(brackets) buffer.append(")");
	}

	private void deleteOperator() {
		ExpressionWidget parent = (ExpressionWidget) getParent();
		if(left.expression instanceof SimpleExpressionWidget)
			parent.expression = new SimpleExpressionWidget(parent, left.expression.toString(), false); // TODO propagate
		else if(left.expression instanceof BinaryExpressionWidget)
			parent.expression = new BinaryExpressionWidget(parent, ((BinaryExpressionWidget) left.expression).op.toString());
		dispose();
		parent.expression.requestLayout();
		parent.expression.setFocus();
	}

	@Override
	public Expression copyTo(EditorWidget parent) {
		return new BinaryExpressionWidget(parent, p -> ((Expression) left.expression), op.getText());
	}


}
