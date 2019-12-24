package pt.iscte.paddle.javasde;
import java.util.function.Function;

import org.eclipse.swt.events.KeyListener;

public class ExpressionWidget extends EditorWidget implements Expression {
	Expression expression;

	public ExpressionWidget(EditorWidget parent, String literal) {
		this(parent, w -> new SimpleExpressionWidget(w, literal, false));
	}

	public ExpressionWidget(EditorWidget parent, Function<EditorWidget, Expression> sup) {
		super(parent);
		setLayout(Constants.ROW_LAYOUT_H_ZERO);
		expression = sup.apply(this);
	}

	public void set(String expression) {
		this.expression.dispose();
		this.expression = new SimpleExpressionWidget(this, expression, false);
		this.expression.requestLayout();
	}
	
	@Override
	public void setData(Object data) {
		expression.setData(data);
	}

	@Override
	public boolean setFocus() {
		return expression.setFocus();
	}
	
//	@Override
//	public Text getWidget() {
//		return ((TextWidget) expression).getWidget();
//	}
	
	@Override
	public void addKeyListener(KeyListener listener) {
		expression.addKeyListener(listener);
	}
	
//	@Override
//	public void addFocusListener(FocusListener listener) {
//		expression.addFocusListener(listener);
//	}

//	public String getText() {
//		return ((TextWidget) expression).getText();
//	}

@Override
public Expression copyTo(EditorWidget parent) {
	return expression.copyTo(parent);
}	
}
