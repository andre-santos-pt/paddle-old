package pt.iscte.paddle.javasde;


import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

import pt.iscte.paddle.model.IConstant;
import pt.iscte.paddle.model.ILiteral;
import pt.iscte.paddle.model.IProgramElement;

public class ConstantWidget extends EditorWidget {
	final IConstant constant;
	private Id type;
	private Id id;
	private SimpleExpression expression;
	
	public ConstantWidget(EditorWidget parent, IConstant constant) {
		super(parent);
		this.constant = constant;
		if(!getMode().staticClass)
			new Token(this, "static");
		
		new Token(this, "final");
		this.type = createId(this, constant.getType().getId(), Constants.PRIMITIVE_TYPES_SUPPLIER);
		
		String id = constant.getId();
		if(id == null)
			id = "CONSTANT";
		
		this.id = createId(this, id);
		this.id.setEditAction(() -> constant.setId(this.id.getText()));
		
		new Token(this, "=");
		this.expression = new SimpleExpression(this, constant.getValue().getStringValue());
		new Token(this, ";");
		
		
		expression.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				ILiteral val = ILiteral.matchValue(expression.getText());
				if(val == null)
					val = ILiteral.matchValue("0");
				
				constant.setValue(val);
			}
		});
		
		constant.addPropertyListener(new IProgramElement.IPropertyListener() {
			
			@Override
			public void propertyChanged(Object key, Object newValue, Object oldValue) {
				if(key.equals("ID"))
					ConstantWidget.this.id.set(oldValue == null ? "" : oldValue.toString());
				else if(key.equals("VALUE"))
					ConstantWidget.this.expression.set(newValue.toString());
			}
		});
	}
	
	@Override
	public String toString() {
		return "static final " + type + " " + id + " = " + expression + ";";
	}
	
	@Override
	public boolean setFocus() {
		type.setFocus();
		return true;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
		super.accept(visitor);
	}
}
