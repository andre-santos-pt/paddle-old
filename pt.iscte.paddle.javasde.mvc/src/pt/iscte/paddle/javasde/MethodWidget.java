package pt.iscte.paddle.javasde;

import static java.lang.System.lineSeparator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import pt.iscte.paddle.model.IProcedure;

public class MethodWidget extends EditorWidget implements StatementContainer {

	final IProcedure procedure;
	private Id retType;
	private EditorWidget id;
	private SequenceWidget body;
	private ParamList params;

	MethodWidget(SequenceWidget parent, IProcedure procedure) {
		super(parent, parent.getMode());
		this.procedure = procedure;
		
		setLayout(Constants.ROW_LAYOUT_V_ZERO);

		EditorWidget header = new EditorWidget(this, mode);
		header.setLayout(Constants.ROW_LAYOUT_H_ZERO);

		if (!mode.staticClass)
			new Token(header, "static");

		retType = createId(header, procedure.getReturnType().toString(), Constants.PRIMITIVE_TYPES_VOID_SUPPLIER);
		retType.addArrayPart();
		retType.setToolTip("Return type");
		
		String name = procedure.getId();
		if(name == null)
			name = "procedure";
		id = createId(header, name);

		new Token(header, "(");
		params = new ParamList(header);
		new Token(header, ")");
		Token openBody = new Token(header, "{");
		body = new SequenceWidget(this, Constants.TAB);
		
		body.addStatementCommands(procedure.getBody());
		body.addBlockListener(procedure.getBody());
//		closeBody = createAddLabel(this, "}");	
//		Control closeBody = createAddLabel(this, "}");
//		closeBody.setMenu(body.createMenu(closeBody, false));
		Token closeBody = new Token(this, "}");
		new Label(this, SWT.NONE); // line
	}

	private class ParamList extends EditorWidget {
		public ParamList(Composite parent) {
			super(parent, MethodWidget.this.mode);
			setLayout(Constants.ROW_LAYOUT_H_ZERO);
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
				setLayout(Constants.ROW_LAYOUT_H_ZERO);
				if(comma)
					new Token(this, ",");
				type = createId(this, "int", Constants.PRIMITIVE_TYPES_SUPPLIER);
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

//	@Override
//	public SequenceWidget getBody() {
//		return body;
//	}
//	
//	@Override
//	public Control getTail() {
//		return closeBody;
//	}
	
	
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
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
		super.accept(visitor);
	}
}
