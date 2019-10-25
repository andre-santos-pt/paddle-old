package pt.iscte.paddle.javasde;

public class CallWidget extends StatementWidget {
	private Id id;
	
	public CallWidget(EditorWidget parent, String id, boolean statement) {
		super(parent);
		setLayout(ROW_LAYOUT_H_ZERO);
		
		this.id = ClassWidget.createId(this, id);
		new Token(this, "(");
		// TODO call arguments
		new Token(this, ")");
		if(statement)
			new Token(this, ";");
	}
	
	@Override
	public void toCode(StringBuffer buffer) {
		buffer.append(id).append("()"); // TODO call arguments
	}
	
}
