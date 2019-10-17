package pt.iscte.paddle.javasde;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class ControlWidget extends StatementWidget implements Selectable {
	
	public ControlWidget(SequenceWidget parent, boolean whileLoop) {
		super(parent);
		RowLayout layout = new RowLayout(SWT.VERTICAL);
		layout.spacing = 2;
		setLayout(layout);
		EditorWidget header = new EditorWidget(this);
		header.setLayout(new RowLayout(SWT.HORIZONTAL));
		new Token(header, whileLoop ? "while" : "if", "(");
		new ExpressionWidget(header);
		new Token(header, ")", "{");
		
		new SequenceWidget(this);
		
		new Token(this, "}");
	}


	
}
