package pt.iscte.paddle.model.java;

import java.util.Arrays;
import java.util.List;

import pt.iscte.paddle.model.IProgramElement;

public enum Keyword implements CharSequence {

	CLASS,
	
	STATIC {
		public Keyword[] excludes() {
			return new Keyword[] {ABSTRACT};
		}
	},
	
	FINAL {
		public Keyword[] excludes() {
			return new Keyword[] {ABSTRACT};
		}
	},
	PUBLIC {
		public Keyword[] excludes() {
			return new Keyword[] {PRIVATE, PROTECTED};
		}
	},
	PRIVATE {
		public Keyword[] excludes() {
			return new Keyword[] {PUBLIC, PRIVATE};
		}
	},
	PROTECTED {
		public Keyword[] excludes() {
			return new Keyword[] {PRIVATE, PUBLIC};
		}
	},
	ABSTRACT {
		public Keyword[] excludes() {
			return new Keyword[] {STATIC, FINAL};
		}
	},
	
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
	
	NEW,
	
	SUPER,
	THIS,
	EXTENDS,
	IMPLEMENTS;

	private static final Keyword[] EMPTY = {};
	
	private static final List<Keyword> CLASS_MODIFIERS = Arrays.asList(PUBLIC, ABSTRACT, FINAL);
	
	private static final List<Keyword> FIELD_MODIFIERS = Arrays.asList(PUBLIC, PRIVATE, PROTECTED, STATIC, FINAL);

	private static final List<Keyword> METHOD_MODIFIERS = Arrays.asList(PUBLIC, PRIVATE, PROTECTED, STATIC, ABSTRACT, FINAL);
	
	private static final List<Keyword> ACCESS_MODIFIERS = Arrays.asList(PUBLIC, PROTECTED, PRIVATE);
	
	public Keyword[] excludes() {
		return EMPTY;
	}
	
	public static List<Keyword> classModifiers() {
			return CLASS_MODIFIERS;
	}
	
	public static List<Keyword> fieldModifiers() {
			return FIELD_MODIFIERS;
	}
	
	public static List<Keyword> methodModifiers() {
			return METHOD_MODIFIERS;
	}
	
	
	public static List<Keyword> constructorModifiers() {
			return ACCESS_MODIFIERS;
	}
	
	private static boolean isModifier(List<Keyword> list, String keyword) {
		return is(keyword) && list.contains(valueOf(keyword.toUpperCase()));
	}
	public static boolean isClassModifier(String keyword) {
		return isModifier(CLASS_MODIFIERS, keyword);
	}
	
	public static boolean isFieldModifier(String keyword) {
		return isModifier(FIELD_MODIFIERS, keyword);
	}
	
	public static boolean isMethodModifier(String keyword) {
		return isModifier(METHOD_MODIFIERS, keyword);
	}
	
	public static boolean isAccessModifier(String keyword) {
		return isModifier(ACCESS_MODIFIERS, keyword);
	}
	
	private static final String regex = String.join("|", keywords());
	
	private static final Keyword[] modifiers = {STATIC, FINAL, PUBLIC, PRIVATE, PROTECTED, ABSTRACT};
			
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
	
	public String keyword() {
		return name().toLowerCase();
	}

	public char getAccelerator() {
		return toString().charAt(0);
	}


	public boolean isEqual(String text) {
		return name().toLowerCase().equals(text);
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

	public static Keyword match(String last) {
		return Keyword.valueOf(last.toUpperCase());
	}

	public static Keyword[] match(List<String> tokens) {
		Keyword[] set = new Keyword[tokens.size()];
		for(int i = 0; i < tokens.size(); i++)
			set[i] = Keyword.valueOf(tokens.get(i).toUpperCase());
		return set;
	}

	public void toCode(StringBuffer buffer) {
		buffer.append(name().toLowerCase());
	}

	public void setFlag(IProgramElement e) {
		e.setFlag(keyword());
	}
	
	
}
