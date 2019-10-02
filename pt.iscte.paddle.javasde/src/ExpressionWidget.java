import java.util.Collections;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class ExpressionWidget extends EditorWidget {
	boolean test = false;
	
	public ExpressionWidget(Composite parent) {
		super(parent);
		setLayout(ROW_LAYOUT_ZERO);
		
		Id id = ClassWidget.createId(this, "exp", () -> Collections.emptyList());
		
		Menu menu = new Menu(id);
		MenuItem item = new MenuItem(menu, SWT.NONE);
		item.setText("binary expression");
		item.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Composite parent2 = ExpressionWidget.this.getParent();
				BinaryExpressionWidget exp = new BinaryExpressionWidget(parent2, "==");
				exp.moveBelow(ExpressionWidget.this);
				ExpressionWidget.this.dispose();
			}
		});
		id.setMenu(menu);
	}

}
