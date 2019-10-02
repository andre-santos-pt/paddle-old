import java.util.Arrays;
import java.util.Collections;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class MethodWidget extends EditorWidget {
	
	private Id rettype;
	private Id id;
	private SequenceWidget sequence;

	public MethodWidget(ClassWidget parent, String name) {
		super(parent);

		RowLayout layout = new RowLayout(SWT.VERTICAL);
		layout.marginLeft = 40;
		setLayout(layout);
		
		EditorWidget header = new EditorWidget(this);
		header.setLayout(ROW_LAYOUT_ZERO);

		new Token(header, "static");
		rettype = ClassWidget.createId(header, "int", () -> Arrays.asList("int", "double"));
		rettype.setFont(Token.FONT);

		id = ClassWidget.createId(header, "func", () -> Collections.emptyList());

		new Token(header, "(");
		ParamList params = new ParamList(header);
		new Token(header, ")");
		new Token(header, "{");

		sequence = new SequenceWidget(this);

		new Token(this, "}");
	}

	private class ParamList extends EditorWidget {
		public ParamList(Composite parent) {
			super(parent);
			setLayout(ROW_LAYOUT_ZERO);
//			setLayoutData(new RowData(20, 20));
			
//			Label add = new Label(this, SWT.NONE);
//			add.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
//			add.setText("+");
			Label add = ClassWidget.createAddLabel(this);
			
			Menu menu = new Menu(add);
			MenuItem addParam = new MenuItem(menu, SWT.NONE);
			addParam.setText("parameter");
			addParam.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if(ParamList.this.getChildren().length != 0)
						new Token(ParamList.this, ",");
					new ArgWidget();
					ParamList.this.getParent().pack();
					ParamList.this.getParent().getParent().layout();
				}
			});
			add.setMenu(menu);
		}
		
		private class ArgWidget extends EditorWidget {
			public ArgWidget() {
				super(ParamList.this);
				setLayout(new RowLayout(SWT.HORIZONTAL));
				Id type = ClassWidget.createId(this, "int", () -> Arrays.asList("int", "double"));
				Id var = ClassWidget.createId(this, "p", () -> Collections.emptyList());
			}
		}
	}

	public void toCode(StringBuffer buffer) {
		buffer.append("static ").append(rettype.toString()).append(" ").append(id.toString())
		.append("(")
		.append(") {");
		
		buffer.append("}\n");
		
	}

	public SequenceWidget getSequence() {
		return sequence;
	}
	
	

}
