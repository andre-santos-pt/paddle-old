package pt.iscte.paddle.model.pythonparser;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class ErrorListener extends BaseErrorListener {

	List<RecognitionException> exceptions;

	public ErrorListener() {
		super();
		exceptions = new ArrayList<>();
	}

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		exceptions.add(e);
	}

	public boolean hasErrors() {
		return !exceptions.isEmpty();
	}

	public RecognitionException[] getExceptions() {
		return exceptions.toArray(new RecognitionException[0]);
	}

	public void checkForErrors() throws ParseException {
		if (hasErrors()) {
			throw new ParseException(String.format("Parsing grammar failed, %d errors detected", exceptions.size()),
					getExceptions());
		}
	}
}
