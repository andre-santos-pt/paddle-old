package pt.iscte.paddle.javasde;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

public class ArrayElementExpression extends EditorWidget {

	private Id id;
	private ExpressionWidget expression;
	private Text placeHolder;
	
	public ArrayElementExpression(EditorWidget parent, String varId) {
		super(parent, parent.mode);
		setLayout(Constants.ROW_LAYOUT_H_ZERO);
		id = createId(this, varId);
		new Token(this, "[");
		expression = new ExpressionWidget(this, varId);
		new Token(this, "]");
		placeHolder = new Text(this, SWT.NONE);
		placeHolder.setText("");
		placeHolder.setFont(Constants.FONT);
		placeHolder.setEditable(false);
		
		
	}

	@Override
	public boolean setFocus() {
		id.setFocus();
		return true;
	}
	

	public void focusExpression() {
		id.setForeground(Constants.FONT_COLOR);
		id.requestLayout();
		expression.setFocus();
	}
}
