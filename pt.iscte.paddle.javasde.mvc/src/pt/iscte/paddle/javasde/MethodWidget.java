package pt.iscte.paddle.javasde;

import static java.lang.System.lineSeparator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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
		Token closeBody = new Token(this, "}");
		
		new Label(this, SWT.NONE); // line
	}

	private class ParamList extends EditorWidget {
		private Control addLabel;

		public ParamList(Composite parent) {
			super(parent, MethodWidget.this.mode);
			setLayout(Constants.ROW_LAYOUT_H_ZERO);
			addLabel = createAddLabel(this, Constants.SINGLE_SPACE, false);
			
			Menu menu = new Menu(addLabel);
			MenuItem delete = new MenuItem(menu, SWT.NONE);
			delete.setText("delete");
			delete.setAccelerator(Constants.DEL_KEY);
			delete.setEnabled(false);
			SelectionListener deleteListener = new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					Control[] children = params.getChildren();
					children[children.length-2].dispose();
					delete.setEnabled(children.length > 2);
					requestLayout();
				}
			};
			delete.addSelectionListener(deleteListener);
			delete.setData(deleteListener);
			
			new MenuItem(menu, SWT.SEPARATOR);
			MenuItem addParam = new MenuItem(menu, SWT.NONE);
			addParam.setText("parameter");
			addParam.setAccelerator('p');
			SelectionListener paramListener = new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					delete.setEnabled(true);
					addParam(addLabel, true);
				}
			};
			addParam.addSelectionListener(paramListener);
			addParam.setData(paramListener);
			addLabel.setMenu(menu);
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
				var.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						if(e.character == ',' && var.isAtEnd())
							addParam(Param.this, false);
					}
				});
				var.setToolTip("Parameter name");
			}
			
			@Override
			public boolean setFocus() {
				type.setFocus();
				return true;
			}
		}

		private void addParam(Control control, boolean above) {
			boolean comma = ParamList.this.getChildren()[0] != addLabel;
			Param param = new Param(comma);
			if(above)
				param.moveAbove(control);
			else
				param.moveBelow(control);
			param.requestLayout();
			param.setFocus();
		}
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
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
		super.accept(visitor);
	}
}
