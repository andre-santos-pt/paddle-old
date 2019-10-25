package pt.iscte.paddle.javasde;

import static java.lang.System.lineSeparator;

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

	private Id retType;
	private Id id;
	private SequenceWidget sequence;
	private ParamList params;

	public MethodWidget(Composite parent, String name, String returnType, UiMode mode) {
		super(parent, mode);

		RowLayout layout = new RowLayout(SWT.VERTICAL);
		layout.marginLeft = mode.staticClass ? 10 : TAB;
		setLayout(layout);

		EditorWidget header = new EditorWidget(this, mode);
		header.setLayout(ROW_LAYOUT_H_ZERO);

		if (!mode.staticClass)
			new Token(header, "static");

		retType = ClassWidget.createId(header, returnType, PRIMITIVE_TYPES_SUPPLIER);
		retType.addArrayPart();
		retType.setFont(Token.FONT);

		id = ClassWidget.createId(header, name);

		new Token(header, "(");
		params = new ParamList(header);
		new Token(header, ")");
		new Token(header, "{");
		sequence = new SequenceWidget(this);
		new Token(this, "}");
	}

	private class ParamList extends EditorWidget {
		public ParamList(Composite parent) {
			super(parent, MethodWidget.this.mode);
			setLayout(ROW_LAYOUT_H_ZERO);
			Label add = ClassWidget.createAddLabel(this);
			Menu menu = new Menu(add);
			MenuItem addParam = new MenuItem(menu, SWT.NONE);
			addParam.setText("parameter");
			addParam.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					if (ParamList.this.getChildren()[0] != add) {
						Token comma = new Token(ParamList.this, ",");
						comma.moveBelow(add);
					}
					Param param = new Param();
					param.moveBelow(add);
					param.requestLayout();
					param.setFocus();
				}
			});
			add.setMenu(menu);
		}

		private class Param extends EditorWidget {
			private Id type;
			private Id var;

			public Param() {
				super(ParamList.this, ParamList.this.mode);
				setLayout(ROW_LAYOUT_H_ZERO);
				type = ClassWidget.createId(this, "type", PRIMITIVE_TYPES_SUPPLIER);
				type.setToolTip("Parameter type");
				var = ClassWidget.createId(this, "parameter");
				var.setToolTip("Parameter name");
			}
			
			@Override
			public boolean setFocus() {
				type.setFocus();
				return true;
			}
		}
	}

	public void toCode(StringBuffer buffer) {
		buffer.append("\tpublic static ").append(retType).append(" ").append(id.toString())
		.append("(...)") // TODO parameters to code
		.append(" {").append(lineSeparator());
		sequence.toCode(buffer, 2);
		buffer.append("\t}").append(lineSeparator());

	}

	public SequenceWidget getSequence() {
		return sequence;
	}

	@Override
	public boolean setFocus() {
		retType.setFocus();
		return true;
	}
}
