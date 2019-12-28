package pt.iscte.paddle.javasde;

import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.widgets.Text;

public enum Keyword implements CharSequence {

	CLASS,
	
	STATIC,
	FINAL,
	PUBLIC,
	PRIVATE,
	PROTECTED,
	ABSTRACT,
	
	BOOLEAN,
	BYTE,
	SHORT,
	INT,
	LONG,
	FLOAT,
	DOUBLE,
	CHAR,
	
	VOID,
	
	TRUE,
	FALSE,
	NULL,
	
	IF,
	ELSE,
	
	WHILE,
	FOR,
	RETURN,
	BREAK,
	CONTINUE,
	
	NEW;

	private static final String regex = String.join("|", keywords());
	
	private static final Keyword[] modifiers = {STATIC, FINAL, PUBLIC, PRIVATE, PROTECTED, ABSTRACT};
	static final int LONGEST = CONTINUE.name().length();
			
	private static String[] keywords() {
		Keyword[] keywords = values();
		String[] v = new String[keywords.length];
		for(int i = 0; i < v.length; i++)
			v[i] = keywords[i].name().toLowerCase();
		return v;
	}

	static List<Keyword> modifiers() {
		return Arrays.asList(modifiers);
	}
	
	static boolean is(String str) {
		return str.matches(regex);
	}
	
	@Override
	public String toString() {
		return name().toLowerCase();
	}

	public char getAccelerator() {
		return toString().charAt(0);
	}


	public boolean match(String text) {
		return name().toLowerCase().equals(text);
	}
		
	public boolean match(Text label) {
		return match(label.getText());
	}


	@Override
	public int length() {
		return name().length();
	}


	@Override
	public char charAt(int index) {
		return name().charAt(index);
	}


	@Override
	public CharSequence subSequence(int start, int end) {
		return name().substring(start, end);
	}
}
