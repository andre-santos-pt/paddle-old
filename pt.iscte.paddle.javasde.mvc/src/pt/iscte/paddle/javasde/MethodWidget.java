package pt.iscte.paddle.javasde;

import static java.lang.System.lineSeparator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowData;
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
		header.setLayout(Constants.ROW_LAYOUT_H);

		if (!mode.staticClass)
			new Token(header, Keyword.STATIC);

		retType = new Id(header, procedure.getReturnType().toString(), true, Constants.PRIMITIVE_TYPES_VOID_SUPPLIER);
		retType.setToolTip("Return type");

		String name = procedure.getId();
		if(name == null)
			name = "procedure";
		id = new Id(header, name, false);
		new FixedToken(header, "(");
		params = new ParamList(header);
		new FixedToken(header, ")");
		new FixedToken(header, "{");
		body = new SequenceWidget(this, Constants.TAB);
		body.addBlockListener(procedure.getBody());
		body.addActions(BlockAction.all(procedure.getBody()));
		new FixedToken(this, "}");
		new Label(this, SWT.NONE); // line
	}

	
	
	private class ParamList extends EditorWidget {
		private InsertWidget insertWidget;

		public ParamList(Composite parent) {
			super(parent, MethodWidget.this.mode);
			setLayout(Constants.ROW_LAYOUT_H_ZERO);
//			insertWidget = new InsertWidget(this, true);
//			insertWidget.addAction(new InsertWidget.Action("parameter",'0') {
//				@Override
//				boolean isEnabled(char c, String text, int index, int caret, int selection) {
//					return BlockAction.isType(text) && c == ' ';
//				}
//				
//				@Override
//				void run(char c, String text, int index, int caret, int selection) {
//					addParam(insertWidget.text, text, false);
//				}
//			});
			createInsert2();
		}
		
		private void createInsert2() {
			insertWidget = new InsertWidget(this, true);
			insertWidget.addAction(new InsertWidget.Action("parameter",'0') {
				@Override
				boolean isEnabled(char c, String text, int index, int caret, int selection) {
					return BlockAction.isType(text) && c == ' ';
				}
				
				@Override
				void run(char c, String text, int index, int caret, int selection) {
					addParam(insertWidget.text, text, false, false);
				}
			});
			insertWidget.addFocusListener(Constants.FOCUS_SELECTALL);
		}

		private InsertWidget createInsert() {
			InsertWidget w = new InsertWidget(this, true);
			w.setLayoutData(new RowData(10, SWT.DEFAULT));
			
			Menu menu = w.createMenu();

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
					addParam(w.text, Keyword.INT.toString(), true, false);
				}
			};
			addParam.addSelectionListener(paramListener);
			addParam.setData(paramListener);
			return w;
		}

		private class Param extends EditorWidget {
			private final Id type;
			private final Id var;
			private FixedToken comma;

			public Param(String type, boolean comma) {
				super(ParamList.this, ParamList.this.mode);
				setLayout(Constants.ROW_LAYOUT_H_DOT);
				if(comma)
					this.comma = new FixedToken(this, ",");
				this.type = new Id(this, type, true, Constants.PRIMITIVE_TYPES_SUPPLIER);
				this.type.setToolTip("Parameter type");
				var = new Id(this, "parameter", false);
				var.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						if(e.character == ',')
							addParam(Param.this, Keyword.INT.toString(), false, true);
						else if(e.keyCode == Constants.DEL_KEY && var.isAtBeginning()) {
							dispose();
							Control[] children = ParamList.this.getChildren();
							if(children.length == 0) {
//								insertWidget = new InsertWidget(ParamList.this, true);
//								insertWidget.requestLayout();
//								insertWidget.setFocus();
								createInsert2();
							}
							else {
								if(children.length == 1 && ((Param) children[0]).comma != null)
									((Param) children[0]).comma.dispose();

								((Param) children[children.length-1]).focusVariable();
							}
							ParamList.this.requestLayout();
						}
					}
				});
				var.setToolTip("Parameter name");
			}

			void focusVariable() {
				var.setFocus();
			}

			@Override
			public boolean setFocus() {
				type.setFocus();
				return true;
			}
		}

		private void addParam(Control control, String type, boolean above, boolean focusType) {
			if(insertWidget != null) {
				insertWidget.dispose();
				insertWidget = null;
			}
			boolean comma = ParamList.this.getChildren().length != 0;
			Param param = new Param(type, comma);
			param.requestLayout();
			if(focusType)
				param.setFocus();
			else
				param.focusVariable();
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
		id.setFocus();
		return true;
	}

	void focusReturnType() {
		retType.setFocus();
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
		super.accept(visitor);
	}
}
