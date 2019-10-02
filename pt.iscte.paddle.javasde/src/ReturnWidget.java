import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;

public class ReturnWidget extends StatementWidget implements Selectable {
	boolean test = false;
	
	public ReturnWidget(Composite parent) {
		super(parent);
		setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Token ret = new Token(this, "return");
		
		new Token(this, ";");
	}

}
