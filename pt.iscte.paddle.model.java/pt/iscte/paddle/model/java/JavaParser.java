package pt.iscte.paddle.model.java;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import pt.iscte.paddle.java.antlr.Java8Lexer;
import pt.iscte.paddle.java.antlr.Java8Parser;
import pt.iscte.paddle.model.IModule;

public class JavaParser {
	
	private final File file;
	
	public JavaParser(File file) {
		this.file = file;
	}
	
	public IModule parse() throws IOException {
		CharStream stream = CharStreams.fromFileName(file.getAbsolutePath(), Charset.forName("UTF-8"));
		Java8Lexer lexer = new Java8Lexer(stream);
		Java8Parser parser = new Java8Parser(new CommonTokenStream(lexer));
		String id = file.getName().substring(0, file.getName().lastIndexOf('.'));
		IModule m = IModule.create(id);
		Listener l = new Listener(m);
		ParseTreeWalker walker = new ParseTreeWalker();
	    walker.walk(l, parser.compilationUnit());
		return m;
	}
	
	public boolean hasParseProblems() {
		return false;
	}
	
	private static String readSource(File file) {
		StringBuilder src = new StringBuilder();
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine())
				src.append(scanner.nextLine()).append('\n');
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return src.toString();
	}
	
}

