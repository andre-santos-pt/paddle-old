import org.eclipse.swt.widgets.Composite;

public abstract class StatementWidget extends EditorWidget {
	
	public StatementWidget(Composite parent) {
		super(parent);		
	}
	
	public EditorWidget initControl() {
		return this;
	}
	
//	@Override
//	public boolean setFocus() {
//		return initControl().setFocus();
//	}
		
}
