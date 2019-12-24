package pt.iscte.paddle.javasde;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

public interface Constants {
	//	class Operator {
	//		final String token;
	//		final char accelerator;
	//		final String description;		
	//	}

	int TAB = 40;
	String FONT_FACE = "Monaco";
	Color COLOR_KW = Display.getDefault().getSystemColor(SWT.COLOR_DARK_MAGENTA);
	Color COLOR_PH = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY);
	Color COLOR_BACK = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
	Color COLOR_ERROR = new Color(Display.getDefault(), 255, 200, 200);
	Color COLOR_BACKGROUND = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
	Color COLOR_ADDLABEL = new Color(Display.getDefault(), 245, 245, 245);
	Color COLOR_HIGHLIGHT = new Color(Display.getDefault(), 0, 200, 200);
	Color COLOR_COMMENT = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GREEN);

	int ARRAY_DIMS = 3;
	List<String> BINARY_OPERATORS = Arrays.asList("+", "-", "*", "/ ", "%", "==", "!=", "<", "<=", ">", ">=", "&&", "||", "^");
	List<String> ARITHMETIC_OPERATORS = Arrays. asList("+", "-", "*", "/ ", "%");
	List<String> RELATIONAL_OPERATORS = Arrays. asList( "==", "!=", "<", "<=", ">", ">=");
	List<String> LOGICAL_OPERATORS = Arrays. asList("&&", "||", "^");
	//	Supplier<List<String>> BINARY_OPERATORS_SUPPLIER = () -> BINARY_OPERATORS;
	Supplier<List<String>> EMPTY_TOKEN_SUPPLIER = () -> Collections.emptyList();
	int FONT_SIZE = 20;
	int MENU_KEY = SWT.SPACE;
	int DEL_KEY = SWT.BS;
	Font FONT_TINY = new Font(null, FONT_FACE, 10, SWT.NONE);
	Font FONT = new Font(null, FONT_FACE, FONT_SIZE, SWT.NONE);
	Font FONT_DOT = new Font(null, "Arial", FONT_SIZE, SWT.BOLD);
	Font FONT_KW = new Font(null, FONT_FACE, FONT_SIZE, SWT.BOLD);
	Font FONT_PH = new Font(null, FONT_FACE, FONT_SIZE, SWT.NONE);
	Color FONT_COLOR = Display.getDefault().getSystemColor(SWT.COLOR_BLACK);


	List<String> PRIMITIVE_TYPES = Arrays.asList("boolean", "int", "char", "double");

	Supplier<List<String>> PRIMITIVE_TYPES_SUPPLIER = () -> PRIMITIVE_TYPES;
	List<String> PRIMITIVE_TYPES_VOID = Arrays.asList("void", "boolean", "int", "char","double");

	Supplier<List<String>> PRIMITIVE_TYPES_VOID_SUPPLIER = () -> PRIMITIVE_TYPES_VOID;
	List<String> UNARY_OPERATORS = Arrays.asList("!", "-", "+");  //"(int)", "(double)");
	//	Supplier<List<String>> UNARY_OPERATORS_SUPPLIER = () -> UNARY_OPERATORS;
	RowLayout ROW_LAYOUT_H_ZERO = create(SWT.HORIZONTAL, 0);
	RowLayout ROW_LAYOUT_H = create(SWT.HORIZONTAL, 3);
	RowLayout ROW_LAYOUT_H_DOT = create(SWT.HORIZONTAL, 0);
	RowLayout ROW_LAYOUT_V_ZERO = create(SWT.VERTICAL, 2);
	GridData ALIGN_TOP = new GridData(SWT.LEFT, SWT.TOP, false, false);
	String FOR_FLAG = Keyword.FOR.name();
	String ELSE_FLAG = Keyword.ELSE.name();

	int SINGLE_SPACE = 1;

	static RowLayout create(int style, int spacing) {
		RowLayout layout = new RowLayout(style);
		layout.marginLeft = 0;
		layout.marginRight = 0;
		layout.marginTop = 0;
		layout.marginBottom = 0;
		layout.spacing = spacing;
		return layout;
	}

	static boolean isLetter(char c) {
		return c >= 'a' && c <= 'z';
	}

	//	static boolean isKeyword(String token) {
	//		return token.matches("class|static|final|return|new|void|if|else|while|for|break|continue|byte|short|int|long|float|double|boolean|char|true|false|null");
	//	}

	static void setFont(Text control, boolean init) {
		if (Keyword.is(control.getText())) {
			control.setFont(FONT_KW);
			control.setForeground(COLOR_KW);
		} else {
			control.setFont(init ? FONT_PH : FONT);
			control.setForeground(init ? COLOR_PH : FONT_COLOR);
		}
	}

	GridData data = new GridData(SWT.LEFT, SWT.TOP, false, false);

	class GridDatas {
		final static GridData SHOW_GRID = new GridData(SWT.FILL, SWT.FILL, true, true);
		final static GridData HIDE_GRID = new GridData(SWT.LEFT, SWT.TOP, false, false);
		final static RowData SHOW_ROW = new RowData(SWT.DEFAULT, SWT.DEFAULT);
		final static RowData HIDE_ROW = new RowData(SWT.DEFAULT, SWT.DEFAULT);
		static {
			SHOW_GRID.exclude = false;
			HIDE_GRID.exclude = true;
			SHOW_ROW.exclude = false;
			HIDE_ROW.exclude = true;
		}
	}
	FocusListener ADD_HIDE = new FocusListener() {
		public void focusLost(FocusEvent e) {
			Control c = (Control) e.widget;
			c.setLayoutData(c.getParent().getLayout() instanceof GridLayout ? GridDatas.HIDE_GRID : GridDatas.HIDE_ROW);
			c.requestLayout();
		}

		public void focusGained(FocusEvent e) {
			Control c = (Control) e.widget;
			c.setLayoutData(c.getParent().getLayout() instanceof GridLayout ? GridDatas.SHOW_GRID : GridDatas.SHOW_ROW);
			c.requestLayout();
		}
	};

	KeyListener LISTENER_ARROW_KEYS = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			Control focusControl = Display.getDefault().getFocusControl();
			if(focusControl != null && focusControl.getData() instanceof TextWidget) {
				TextWidget w = (TextWidget) focusControl.getData();
				Text text = w.getWidget();

				if(e.keyCode == SWT.ARROW_RIGHT && (!text.getEditable() || text.getText().length() == text.getCaretPosition() && text.getSelectionCount() == 0)) {
					if(text.traverse(SWT.TRAVERSE_TAB_NEXT)) {
						w.setAtLeft();
					}
					e.doit = false;
				}
				else if(e.keyCode == SWT.ARROW_LEFT && (!text.getEditable() || text.getCaretPosition() == 0 && text.getSelectionCount() == 0)) {
					if(text.traverse(SWT.TRAVERSE_TAB_PREVIOUS))
						w.setAtRight();
					e.doit = false;
				}
				else if(e.keyCode == SWT.ARROW_UP) {
					moveCursorUp(w);
					e.doit = false;
				}
				else if(e.keyCode == SWT.ARROW_DOWN) {
					moveCursorDown(w);
					e.doit = false;
				}
			}
		}
	};

	static void addArrowKeys(Control control, TextWidget widget) {
		control.addKeyListener(LISTENER_ARROW_KEYS);
		control.setData(widget);
	}

	ModifyListener MODIFY_PACK = new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			((Control) e.widget).pack();
			((Control) e.widget).requestLayout();
		}
	};

	static void addFocusSelectAll(Text e) {
		e.addFocusListener(FOCUS_SELECTALL);
	}
	
	FocusListener FOCUS_SELECTALL = new FocusAdapter() {
		public void focusGained(FocusEvent e) {
			((Text) e.widget).selectAll();
		}
	};


	private static void moveCursorUp(TextWidget widget) {
		Control statement = widget.getStatement();
		SequenceWidget seq = (SequenceWidget) statement.getParent();
		seq.focusPrevious(statement);
	}

	private static void moveCursorDown(TextWidget widget) {
		Control statement = widget.getStatement();
		SequenceWidget seq = (SequenceWidget) statement.getParent();
		seq.focusNext(statement);
	}

	static String matchBinaryOperator(char character) {
		for(String o : BINARY_OPERATORS)
			if(o.charAt(0) == character)
				return o;
		return null;
	}
}
