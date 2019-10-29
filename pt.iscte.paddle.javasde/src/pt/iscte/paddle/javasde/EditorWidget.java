package pt.iscte.paddle.javasde;
import static java.lang.System.lineSeparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;

public class EditorWidget extends Composite {

	static final String FONT_FACE = "Monospace";
	static final int FONT_SIZE = 24;

	static final int TAB = 30;

	static final int MENU_KEY = SWT.SPACE;

	static final int ARRAY_DIMS = 3;

	static final Font FONT_TINY = new Font(null, FONT_FACE, 10, SWT.NONE);

	static final Font FONT = new Font(null, FONT_FACE, FONT_SIZE, SWT.NONE);

	static final Font FONT_KW = new Font(null, FONT_FACE, FONT_SIZE, SWT.BOLD);

	static final Font FONT_PH = new Font(null, FONT_FACE, FONT_SIZE, SWT.NONE);

	static final Color FONT_COLOR = Display.getDefault().getSystemColor(SWT.COLOR_BLACK);
	static final Color COLOR_KW = Display.getDefault().getSystemColor(SWT.COLOR_DARK_MAGENTA);
	static final Color COLOR_PH = Display.getDefault().getSystemColor(SWT.COLOR_GRAY);

	static final Color COLOR_BACK = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);

	static final Color COLOR_ERROR = Display.getDefault().getSystemColor(SWT.COLOR_RED);

	static final Color COLOR_BACKGROUND = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);

	static final Color COLOR_ADDLABEL = new Color(Display.getDefault(), 245, 245, 245);
	static final Color COLOR_HIGHLIGHT = new Color(Display.getDefault(), 0, 0, 200);



	static final List<String> PRIMITIVE_TYPES = Arrays.asList("int", "double", "boolean", "char");
	static final Supplier<List<String>> PRIMITIVE_TYPES_SUPPLIER = () -> PRIMITIVE_TYPES;

	static final List<String> PRIMITIVE_TYPES_VOID = Arrays.asList("int", "double", "boolean", "char", "void");
	static final Supplier<List<String>> PRIMITIVE_TYPES_VOID_SUPPLIER = () -> PRIMITIVE_TYPES_VOID;


	static final List<String> UNARY_OPERATORS = Arrays.asList("!", "-", "(int)");
	static final Supplier<List<String>> UNARY_OPERATORS_SUPPLIER = () -> UNARY_OPERATORS;

	static final List<String> BINARY_OPERATORS = Arrays.asList("+", "-", "*", "/ ", "%", "==", "!=", "<", "<=", ">", ">=", "&", "&&", "|", "||", "^");
	static final Supplier<List<String>> BINARY_OPERATORS_SUPPLIER = () -> BINARY_OPERATORS;

	
	static boolean isKeyword(String token) {
		return token.matches("class|static|return|new|void|if|while|int|double|boolean|char|true|false|null");
	}

	static EditorWidget selected = null;

	static final Supplier<List<String>> EMPTY_TOKEN_SUPPLIER = () -> Collections.emptyList();

	static final RowLayout ROW_LAYOUT_H_ZERO;
	static final RowLayout ROW_LAYOUT_V_ZERO;


	static {
		ROW_LAYOUT_H_ZERO = new RowLayout(SWT.HORIZONTAL);
		ROW_LAYOUT_H_ZERO.marginLeft = 0;
		ROW_LAYOUT_H_ZERO.marginRight = 0;
		ROW_LAYOUT_H_ZERO.marginTop = 0;
		ROW_LAYOUT_H_ZERO.marginBottom = 0;
		ROW_LAYOUT_H_ZERO.spacing = 2;

		ROW_LAYOUT_V_ZERO = new RowLayout(SWT.VERTICAL);
		ROW_LAYOUT_V_ZERO.marginLeft = 0;
		ROW_LAYOUT_V_ZERO.marginRight = 0;
		ROW_LAYOUT_V_ZERO.marginTop = 0;
		ROW_LAYOUT_V_ZERO.marginBottom = 0;
		ROW_LAYOUT_V_ZERO.spacing = 2;
	}

	final UiMode mode;

	private EditorWidget root;

	public EditorWidget(EditorWidget parent) {
		this(parent, parent.mode);
	}

	public EditorWidget(Composite parent, UiMode mode) {
		super(parent, SWT.NONE);
		this.mode = mode;
		setLayout(ROW_LAYOUT_H_ZERO);
		setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));

		EditorWidget e = this;
		while(!(e instanceof ClassWidget))
			e = (EditorWidget)e.getParent();
		root = (EditorWidget) e;
	}

	Text createAddLabel(Composite parent) {
		return root.createAddLabel(parent);
	}

	Text createAddLabel(Composite parent, String token) {
		return root.createAddLabel(parent, token);
	}

	Id createId(EditorWidget parent, String id) {
		return root.createId(parent, id);
	}

	Id createId(EditorWidget parent, String id, Supplier<List<String>> idProvider) {
		return root.createId(parent, id, idProvider);
	}

	


	static void setFont(Text control, boolean init) {
		if (EditorWidget.isKeyword(control.getText())) {
			control.setFont(FONT_KW);
			control.setForeground(COLOR_KW);
		} else {
			control.setFont(init ? FONT_PH : FONT);
			control.setForeground(init ? COLOR_PH : FONT_COLOR);
		}
	}

	void popup(Menu menu, Control control) {
		menu.setLocation(control.toDisplay(0, 40));
	}

	
	void setSibling(Control sibling) {
		sibling.addFocusListener(new FocusListener() {
			Color prev;
			public void focusLost(FocusEvent e) {
				setBackground(prev);
			}
			public void focusGained(FocusEvent e) {
				prev = getBackground();
				setBackground(COLOR_HIGHLIGHT);
			}
		});
		addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				sibling.setBackground(COLOR_ADDLABEL);
			}
			public void focusGained(FocusEvent e) {
				sibling.setBackground(COLOR_HIGHLIGHT);
			}
		});
	}
	
	public void toCode(StringBuffer buffer, int level) {
		while(level-- > 0)
			buffer.append("\t");

		toCode(buffer);
		buffer.append(lineSeparator());
	}

	// to override
	public void toCode(StringBuffer buffer) {
		buffer.append("#" + this.getClass().getSimpleName() + "#");
	}


}
