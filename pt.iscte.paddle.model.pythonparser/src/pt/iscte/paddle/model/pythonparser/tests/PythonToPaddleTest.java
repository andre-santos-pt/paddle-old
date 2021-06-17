package pt.iscte.paddle.model.pythonparser.tests;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import junit.framework.TestCase;
import pt.iscte.paddle.model.IModel2CodeTranslator;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IModel2CodeTranslator.Java;
import pt.iscte.paddle.model.pythonparser.ParseException;
import pt.iscte.paddle.model.pythonparser.PythonToPaddle;

public class PythonToPaddleTest extends TestCase {
	
	public static String LOOSE_EXPRESSION = "a = 587 / 1\nprint(a)\n";
	public static String INVALID_FUNCTION = "def inv(a, b):\nreturn a + b";
	public static String SUM_FUNCTION = "def sum(a, b):\n  return a + b";
	public static String NO_ARGUMENTS = "def p():\n  print(\"hello\")\n\n";
	public static String ERASTOTHENES_PATH = "src/pt/iscte/paddle/model/pythonparser/tests/erastothenes.py";

	public void testEmpty() throws ParseException {
		PythonToPaddle.parseFromString("");
	}

	public void testLooseExpression() throws ParseException {
		PythonToPaddle.parseFromString(LOOSE_EXPRESSION);
	}
	
	public void testInvalidFunction() {
		assertThrows(ParseException.class, () -> {
			PythonToPaddle.parseFromString(INVALID_FUNCTION);
		});
	}

	public void testSumFunction() throws ParseException {
		IModule mod = PythonToPaddle.parseFromString(SUM_FUNCTION);
		System.out.println(mod.translate(new IModel2CodeTranslator.Java()));
	}
	
	public void testNoArguments() throws ParseException {
		IModule mod = PythonToPaddle.parseFromString(NO_ARGUMENTS);
		System.out.println(mod.translate(new IModel2CodeTranslator.Java()));
	}

	public void testErastothenesFunction() throws IOException, ParseException {
		IModule mod = PythonToPaddle.parseFromFileName(ERASTOTHENES_PATH);
		System.out.println(mod.translate(new IModel2CodeTranslator.Java()));
	}

}
