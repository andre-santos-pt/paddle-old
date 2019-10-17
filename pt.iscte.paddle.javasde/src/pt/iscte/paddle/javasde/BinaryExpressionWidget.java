package pt.iscte.paddle.javasde;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;

public class BinaryExpressionWidget extends EditorWidget {
	boolean test = false;
	
	public BinaryExpressionWidget(Composite parent, String operator) {
		super(parent);
		setLayout(ROW_LAYOUT_ZERO);
		
		ExpressionWidget left = new ExpressionWidget(this);
	
		Token op = new Token(this, operator);

		ExpressionWidget right = new ExpressionWidget(this);
				
		setToolTipText("Identifier\nCan only contain letters and underscores (_)");
	}

}
