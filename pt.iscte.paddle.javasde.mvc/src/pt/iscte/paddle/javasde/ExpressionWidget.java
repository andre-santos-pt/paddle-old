package pt.iscte.paddle.javasde;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

public class ExpressionWidget extends EditorWidget  {
	boolean test = false;
	Control expression;

	public ExpressionWidget(EditorWidget parent, String literal) {
		this(parent, w -> new SimpleExpressionWidget(w, literal, false));
	}

	public ExpressionWidget(EditorWidget parent, Function<EditorWidget, Control> sup) {
		super(parent);
		setLayout(Constants.ROW_LAYOUT_H_ZERO);
		expression = sup.apply(this);// new SimpleExpressionWidget(this, literal, false);
//		Constants.addArrowKeys(expression, (TextWidget) expression); 
		addMenu(expression);
	}

	public void set(String expression) {
		this.expression.dispose();
		this.expression = new SimpleExpressionWidget(this, expression, false);
//		Constants.addArrowKeys(this.expression, (TextWidget) this.expression); 
		this.expression.requestLayout();
	}
	
	@Override
	public void setData(Object data) {
		expression.setData(data);
	}
	
	private void addMenu(Control widget) {
		Menu menu = new Menu(widget);
		MenuItem item1 = new MenuItem(menu, SWT.NONE);
		item1.setText("unary expression");
		item1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String target = expression.toString();
				expression.dispose();
				expression = new UnaryExpressionWidget(ExpressionWidget.this, "!", target);
				addMenu(expression);
				expression.requestLayout();
				expression.setFocus();
			}
		});
		MenuItem item2 = new MenuItem(menu, SWT.NONE);
		item2.setText("binary expression");
		item2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				expression.dispose();
				expression = new BinaryExpressionWidget(ExpressionWidget.this, "+");
				addMenu(expression);
				expression.requestLayout();
				expression.setFocus();
			}
		});
		
		MenuItem item3 = new MenuItem(menu, SWT.NONE);
		item3.setText("function call");
		item3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				expression.dispose();
				expression = new CallWidget(ExpressionWidget.this, "func", false);
				addMenu(expression);
				expression.requestLayout();
				expression.setFocus();
			}
		});
		
		MenuItem item4 = new MenuItem(menu, SWT.NONE);
		item4.setText("array allocation");
		item4.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				expression.dispose();
				expression = new ArrayAllocationExpression(ExpressionWidget.this);
				addMenu(expression);
				expression.requestLayout();
				expression.setFocus();
			}
		});
		
		MenuItem item5 = new MenuItem(menu, SWT.NONE);
		item5.setText("array element");
		item5.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				expression.dispose();
				expression = new ArrayElementExpression(ExpressionWidget.this, "variable");
				addMenu(expression);
				expression.requestLayout();
				expression.setFocus();
			}
		});
		
		widget.setMenu(menu);
	}

//	@Override
//	public boolean setFocus() {
//		return expression.setFocus();
//	}
	
//	@Override
//	public Text getWidget() {
//		return ((TextWidget) expression).getWidget();
//	}
	
	@Override
	public void addKeyListener(KeyListener listener) {
		expression.addKeyListener(listener);
	}
	
	@Override
	public void addFocusListener(FocusListener listener) {
		expression.addFocusListener(listener);
	}	
}
