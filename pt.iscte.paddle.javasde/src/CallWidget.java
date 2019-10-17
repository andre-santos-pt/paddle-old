import java.util.Collections;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;

public class CallWidget extends StatementWidget {
	public CallWidget(Composite parent, boolean statement) {
		super(parent);
		setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Id id = ClassWidget.createId(this, "func", () -> Collections.emptyList());
		new Token(this, "(");
		new Token(this, ")");
		if(statement)
			new Token(this, ";");
	}
	
}
