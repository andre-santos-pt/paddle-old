package pt.iscte.paddle.javasde;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

public class Id extends EditorWidget {
	boolean test = false;
	private Text text;
	
	Id(Composite parent, String id, Supplier<List<String>> idProvider) {
		super(parent);
		setLayout(new FillLayout());
		text = new Text(this, SWT.NONE);
		text.setText(id);
		if (EditorWidget.isToken(id)) {
			text.setFont(FONT_KW);
			text.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		} else
			text.setFont(FONT);

		text.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				if(!test)
					if(!((e.character >= 'a' && e.character <= 'z') || (e.character >= 'A' && e.character <= 'Z') || e.character == '_'))
						e.doit = false;
			}
		});
		
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
//				text.getParent().pack();
				Composite rootParent = getRootParent();
				pack();
				getParent().pack();
				rootParent.getParent().layout();
//				getParent().getParent().pack();
//				getParent().getParent().layout();
			}
		});

		Menu popupMenu = new Menu(text);
		
		for(String s : idProvider.get()) {
			MenuItem item = new MenuItem(popupMenu, SWT.NONE);
			item.setText(s);
			item.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					test = true;
					text.setText(item.getText());
					test = false;
//					System.out.println(item.getText());
//					pack();
//					getParent().layout();
				}
			});
		}

		setMenu(popupMenu);
		
		text.setToolTipText("Identifier\nCan only contain letters and underscores (_)");
	}
	
	public void setMenu(Menu menu) {
		text.setMenu(menu);
	}
	
	@Override
	public String toString() {
		return text.getText();
	}

	public void setEditable(boolean editable) {
//		text.setEditable(!editable);
		if(editable)
			text.setBackground(ClassWidget.GRAY);
		else
			text.setBackground(ClassWidget.WHITE);
	}
}
