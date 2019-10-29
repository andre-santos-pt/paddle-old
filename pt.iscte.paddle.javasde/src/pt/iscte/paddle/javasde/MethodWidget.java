package pt.iscte.paddle.javasde;

import static java.lang.System.lineSeparator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class MethodWidget extends EditorWidget implements StatementContainer {

	private Id retType;
	private EditorWidget id;
	private SequenceWidget body;
	private ParamList params;
	private Control closeBody;

	MethodWidget(ClassWidget parent, String name, String returnType) {
		super(parent, parent.getMode());

		RowLayout layout = new RowLayout(SWT.VERTICAL);
		layout.marginLeft = mode.staticClass ? 10 : TAB;
		setLayout(layout);

		EditorWidget header = new EditorWidget(this, mode);
		header.setLayout(ROW_LAYOUT_H_ZERO);

		if (!mode.staticClass)
			new Token(header, "static");

		retType = createId(header, returnType, PRIMITIVE_TYPES_VOID_SUPPLIER);
		retType.addArrayPart();
		retType.setFont(Token.FONT);

		id = createId(header, name);

		new Token(header, "(");
		params = new ParamList(header);
		new Token(header, ")");
		Token openBody = new Token(header, "{");
		body = new SequenceWidget(this);
		closeBody = createAddLabel(this, "}");	
		closeBody.setMenu(body.createMenu(closeBody, false));
		openBody.setSibling(closeBody);
	}

	private class ParamList extends EditorWidget {
		public ParamList(Composite parent) {
			super(parent, MethodWidget.this.mode);
			setLayout(ROW_LAYOUT_H_ZERO);
			Control add = createAddLabel(this);
			Menu menu = new Menu(add);
			
			MenuItem delete = new MenuItem(menu, SWT.NONE);
			delete.setText("delete");
			delete.setAccelerator('d');
			delete.setEnabled(false);
			delete.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					Control[] children = params.getChildren();
					children[children.length-2].dispose();
					delete.setEnabled(children.length > 2);
					requestLayout();
				}
			});
			new MenuItem(menu, SWT.SEPARATOR);
			MenuItem addParam = new MenuItem(menu, SWT.NONE);
			addParam.setText("parameter");
			addParam.setAccelerator('p');
			addParam.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					delete.setEnabled(true);
					boolean comma = ParamList.this.getChildren()[0] != add;
					Param param = new Param(comma);
					param.moveAbove(add);
					param.requestLayout();
					param.setFocus();
				}
			});
			
			add.setMenu(menu);
		}

		private class Param extends EditorWidget {
			private Id type;
			private Id var;

			public Param(boolean comma) {
				super(ParamList.this, ParamList.this.mode);
				setLayout(ROW_LAYOUT_H_ZERO);
				if(comma)
					new Token(this, ",");
				type = createId(this, "type", PRIMITIVE_TYPES_SUPPLIER);
				type.addArrayPart();
				type.setToolTip("Parameter type");
				var = createId(this, "parameter");
				var.setToolTip("Parameter name");
			}
			
			@Override
			public boolean setFocus() {
				type.setFocus();
				return true;
			}
		}
	}

	@Override
	public SequenceWidget getBody() {
		return body;
	}
	
	@Override
	public Control getTail() {
		return closeBody;
	}
	
	
	public void toCode(StringBuffer buffer) {
		buffer.append("\tpublic static ").append(retType).append(" ").append(id.toString())
		.append("(...)") // TODO parameters to code
		.append(" {").append(lineSeparator());
		body.toCode(buffer, 2);
		buffer.append("\t}").append(lineSeparator());

	}

	public SequenceWidget getSequence() {
		return body;
	}

	@Override
	public boolean setFocus() {
		retType.setFocus();
		return true;
	}
}
