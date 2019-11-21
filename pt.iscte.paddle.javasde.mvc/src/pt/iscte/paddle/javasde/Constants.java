package pt.iscte.paddle.javasde;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

public interface Constants {
	int TAB = 20;
	String FONT_FACE = "Monospace";
	Color COLOR_KW = Display.getDefault().getSystemColor(SWT.COLOR_DARK_MAGENTA);
	Color COLOR_PH = Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
	Color COLOR_BACK = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
	Color COLOR_ERROR = Display.getDefault().getSystemColor(SWT.COLOR_RED);
	Color COLOR_BACKGROUND = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
	Color COLOR_ADDLABEL = new Color(Display.getDefault(), 245, 245, 245);
	Color COLOR_HIGHLIGHT = new Color(Display.getDefault(), 0, 0, 200);
	int ARRAY_DIMS = 3;
	List<String> BINARY_OPERATORS = Arrays.asList("+", "-", "*", "/ ", "%", "==", "!=", "<", "<=", ">", ">=", "&", "&&", "|", "||", "^");
	Supplier<List<String>> BINARY_OPERATORS_SUPPLIER = () -> BINARY_OPERATORS;
	Supplier<List<String>> EMPTY_TOKEN_SUPPLIER = () -> Collections.emptyList();
	int FONT_SIZE = 24;
	int MENU_KEY = SWT.SPACE;
	Font FONT_TINY = new Font(null, FONT_FACE, 10, SWT.NONE);
	Font FONT = new Font(null, FONT_FACE, FONT_SIZE, SWT.NONE);
	Font FONT_KW = new Font(null, FONT_FACE, FONT_SIZE, SWT.BOLD);
	Font FONT_PH = new Font(null, FONT_FACE, FONT_SIZE, SWT.NONE);
	Color FONT_COLOR = Display.getDefault().getSystemColor(SWT.COLOR_BLACK);
	
	List<String> PRIMITIVE_TYPES = Arrays.asList("int", "double", "boolean", "char");
	Supplier<List<String>> PRIMITIVE_TYPES_SUPPLIER = () -> PRIMITIVE_TYPES;
	List<String> PRIMITIVE_TYPES_VOID = Arrays.asList("int", "double", "boolean", "char", "void");
	Supplier<List<String>> PRIMITIVE_TYPES_VOID_SUPPLIER = () -> PRIMITIVE_TYPES_VOID;
	List<String> UNARY_OPERATORS = Arrays.asList("!", "-", "(int)");
	Supplier<List<String>> UNARY_OPERATORS_SUPPLIER = () -> UNARY_OPERATORS;
	RowLayout ROW_LAYOUT_H_ZERO = create(SWT.HORIZONTAL);
	RowLayout ROW_LAYOUT_V_ZERO = create(SWT.VERTICAL);
	GridData ALIGN_TOP = new GridData(SWT.LEFT, SWT.TOP, false, false);
	String FOR_FLAG = "FOR";
	
	static RowLayout create(int style) {
		RowLayout layout = new RowLayout(style);
		layout.marginLeft = 0;
		layout.marginRight = 0;
		layout.marginTop = 0;
		layout.marginBottom = 0;
		layout.spacing = 2;
		return layout;
	}
	
	
	static boolean isKeyword(String token) {
		return token.matches("class|static|final|return|new|void|if|else|while|for|break|continue|int|double|boolean|char|true|false|null");
	}
	
	static void setFont(Text control, boolean init) {
		if (Constants.isKeyword(control.getText())) {
			control.setFont(FONT_KW);
			control.setForeground(COLOR_KW);
		} else {
			control.setFont(init ? FONT_PH : FONT);
			control.setForeground(init ? COLOR_PH : FONT_COLOR);
		}
	}

}
