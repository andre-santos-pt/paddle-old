package pt.iscte.paddle.model.pythonparser;

import org.antlr.v4.runtime.RecognitionException;

public class ParseException extends Exception {
	
	private static final long serialVersionUID = 8555677350472205245L;
	RecognitionException[] children;
	
	public ParseException(String message) {
		super(message);
		this.children = null;
	}

	public ParseException(String message, RecognitionException[] children) {
		super(message, children[0]);
		this.children = children;
	}

	public RecognitionException[] getChildren() {
		return this.children;
	}
}
