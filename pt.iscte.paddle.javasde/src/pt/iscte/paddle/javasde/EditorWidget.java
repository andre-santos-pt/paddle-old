package pt.iscte.paddle.javasde;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

public class EditorWidget extends Composite {

	static final Font FONT = new Font(null, "Monospace", 32, SWT.NONE);

	static final Font FONT_KW = new Font(null, "Monospace", 32, SWT.BOLD);

	static EditorWidget selected = null;
	
	static RowLayout ROW_LAYOUT_ZERO;
	
	static {
		ROW_LAYOUT_ZERO = new RowLayout(SWT.HORIZONTAL);
		ROW_LAYOUT_ZERO.marginLeft = 0;
		ROW_LAYOUT_ZERO.marginRight = 0;
		ROW_LAYOUT_ZERO.marginTop = 0;
		ROW_LAYOUT_ZERO.marginBottom = 0;
	}
	
	public EditorWidget(Composite parent) {
		super(parent, SWT.NONE);
		setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));

		if(this instanceof Selectable) {
//			MouseTrackAdapter adapter = new MouseTrackAdapter() {
//
//				@Override
//				public void mouseExit(MouseEvent e) {
//					setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
//				}
//
//				@Override
//				public void mouseEnter(MouseEvent e) {
//					setBackground(Display.getDefault().getSystemColor(SWT.COLOR_YELLOW));
//					System.out.println("ee");
//				}
//			};
//			addMouseTrackListener(adapter);
//			for (Control child : getChildren()) {
//				child.addMouseTrackListener(adapter);
//			}
			addMouseListener(new MouseListener() {
				
				@Override
				public void mouseUp(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseDown(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseDoubleClick(MouseEvent e) {
					if(selected == EditorWidget.this) {
						setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
						selected = null;
					}
					else {
						if(selected != null)
							selected.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
						selected = EditorWidget.this;
						selected.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));	
					}
				}
			});
			addKeyListener(new KeyListener() {
				
				@Override
				public void keyReleased(KeyEvent e) {
					
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					System.out.println(e);
					if(e.character == 'd')
						dispose();
				}
			});
		}
	}

	Composite getRootParent() {
		Composite parent = getParent();
		while(!(parent instanceof ClassWidget))
			parent = parent.getParent();
		return parent;
	}
	
	static boolean isToken(String token) {
		return token.matches("class|static|if|while|int|double");
	}
}
