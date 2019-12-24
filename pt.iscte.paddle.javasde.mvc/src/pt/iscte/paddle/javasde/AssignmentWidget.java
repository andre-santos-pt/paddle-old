package pt.iscte.paddle.javasde;

import java.util.function.Function;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Control;

public class AssignmentWidget extends EditorWidget {

	private final ExpressionWidget id;
	private  ExpressionWidget expression;


	AssignmentWidget(EditorWidget parent, String id, String expression, boolean statement, boolean array) {
		super(parent);
//		this.id = createId(this, id);
		this.id = new ExpressionWidget(this, id); // TODO variants
		new FixedToken(this, "=");
		Function<EditorWidget, Control> f = 
				e -> array ? new ArrayElementExpression(this, id) : new SimpleExpressionWidget(e, id, false);
		this.expression = new ExpressionWidget(this, "expression");
		if(statement)
			new FixedToken(this, ";");
		
//		this.id.addKeyListener(delListener);
//		this.expression.addKeyListener(delListener);
		
	}
	
	
	// PROblema
	private KeyListener delListener = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			if(e.keyCode == Constants.DEL_KEY && ((TextWidget) ((Control) e.widget).getParent()).isAtBeginning())
				dispose();
			
			// TODO selection
		}	
	};
	
	public boolean setFocus() {
		return id.setFocus();
	}
	
	public void focusExpression() {
		expression.setFocus();
	}
	
	@Override
	public void toCode(StringBuffer buffer) {
		buffer.append(id).append(" = ");
		expression.toCode(buffer);
	}

}
