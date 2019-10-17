
import java.util.Collections;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

public class AssignmentWidget extends StatementWidget implements Selectable {
	
	private Id id;

	public AssignmentWidget(Composite parent) {
		super(parent);
		RowLayout layout = new RowLayout(SWT.HORIZONTAL);
		layout.spacing = 2;
		setLayout(layout);
		id = ClassWidget.createId(this, "var", () -> Collections.emptyList());
		Token equal = new Token(this, "=");
		
		new ExpressionWidget(this);
		new Token(this, ";");
	
		
	}
	
	@Override
	public EditorWidget initControl() {
		return id;
	}
	
}
