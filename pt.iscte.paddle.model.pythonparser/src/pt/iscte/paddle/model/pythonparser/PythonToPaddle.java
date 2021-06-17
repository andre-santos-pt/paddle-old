package pt.iscte.paddle.model.pythonparser;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.pythonparser.antlr.Python3Lexer;
import pt.iscte.paddle.model.pythonparser.antlr.Python3Parser;

public class PythonToPaddle {

	public static IModule parseFromFileName(String filename) throws IOException, ParseException {
		return new PythonToPaddle(CharStreams.fromFileName(filename)).parse();
	}

	public static IModule parseFromString(String source) throws ParseException {
		return new PythonToPaddle(CharStreams.fromString(source)).parse();
	}

	CharStream stream;
	IModule module;

	public PythonToPaddle(CharStream stream) {
		this.stream = stream;
		module = IModule.create();
	}

	public IModule parse() throws ParseException {
		Python3Lexer lexer = new Python3Lexer(stream);
		Python3Parser parser = new Python3Parser(new CommonTokenStream(lexer));
		
		ErrorListener errorListener = new ErrorListener();
		parser.addErrorListener(errorListener);
		
		ModelConstructor constructor = new ModelConstructor(module);
		DelayedFuncsTreeWalker walker = new DelayedFuncsTreeWalker();

		// single_input & eval_input are in grammar but not supported
		walker.walk(constructor, parser.file_input());
		//System.out.println(constructor.scopeStack.peekLast().toString());

		errorListener.checkForErrors();
		constructor.checkForErrors();

		return module;
	}

}
