package pt.iscte.paddle.javasde;

public abstract class StatementWidget extends EditorWidget {
	
	public StatementWidget(EditorWidget parent) {
		super(parent, parent.mode);	
		setLayout(ROW_LAYOUT_H_ZERO);
	}
	
	public EditorWidget initControl() {
		return this;
	}
	
		
}
