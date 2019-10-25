package pt.iscte.paddle.javasde;
import static java.lang.System.lineSeparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

public class EditorWidget extends Composite {

	static final String FONT_FACE = "Arial";
	static final int FONT_SIZE = 24;
	
	static final int TAB = 40;
	
	static final Font FONT = new Font(null, FONT_FACE, FONT_SIZE, SWT.NONE);

	static final Font FONT_KW = new Font(null, FONT_FACE, FONT_SIZE, SWT.BOLD);

	static final Font FONT_PH = new Font(null, FONT_FACE, FONT_SIZE, SWT.ITALIC);
	
	static final Color FONT_COLOR = Display.getDefault().getSystemColor(SWT.COLOR_BLACK);
	static final Color COLOR_KW = Display.getDefault().getSystemColor(SWT.COLOR_DARK_MAGENTA);
	static final Color COLOR_PH = Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
	
	static final Color COLOR_BACK = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
	
	static final Color COLOR_ERROR = Display.getDefault().getSystemColor(SWT.COLOR_RED);
	
	static final List<String> PRIMITIVE_TYPES = Arrays.asList("int", "double", "boolean", "char");
	
	static final Supplier<List<String>> PRIMITIVE_TYPES_SUPPLIER = () -> PRIMITIVE_TYPES;
	
	static final List<String> UNARY_OPERATORS = Arrays.asList("!", "-", "(int)");
	static final Supplier<List<String>> UNARY_OPERATORS_SUPPLIER = () -> UNARY_OPERATORS;
	
	static final List<String> BINARY_OPERATORS = Arrays.asList("+", "-", "*", "/ ", "%", "==", "!=", "<", "<=", ">", ">=", "&", "&\\&", "|", "||", "^");
	static final Supplier<List<String>> BINARY_OPERATORS_SUPPLIER = () -> BINARY_OPERATORS;
	
	
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
	
	public EditorWidget(EditorWidget parent) {
		this(parent, parent.mode);
	}
	
	public EditorWidget(Composite parent, UiMode mode) {
		super(parent, SWT.NONE);
		this.mode = mode;
		setLayout(ROW_LAYOUT_H_ZERO);
		setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));

		if(this instanceof Selectable) {
			addMouseListener(new MouseAdapter() {
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
		}
	}

	
	static boolean isKeyword(String token) {
		return token.matches("class|static|return|new|void|if|while|int|double|boolean|char");
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
	
	public void toCode(StringBuffer buffer, int level) {
		while(level-- > 0)
			buffer.append("\t");
		
		toCode(buffer);
		buffer.append(lineSeparator());
	}
	
	public void toCode(StringBuffer buffer) {
		buffer.append("#" + this.getClass().getSimpleName() + "#");
	}
}
