package pt.iscte.paddle.model.java;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.function.Predicate;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import pt.iscte.paddle.java.antlr.Java8Lexer;
import pt.iscte.paddle.java.antlr.Java8Parser;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.validation.AsgSemanticChecks;
import pt.iscte.paddle.model.validation.ISemanticProblem;
import pt.iscte.paddle.model.validation.SemanticChecker;

public class JavaParser {
	
	private final File[] javaFiles;
	
	public JavaParser(File file) {
		this(file, f -> false);
	}
	
	public JavaParser(File file, Predicate<File> exclude) {
		javaFiles = file.isFile() ? new File[] {file} : file.listFiles(f -> f.getName().endsWith(".java") && !exclude.test(f));
	}
	
	public IModule parse() throws IOException {
		IModule module = IModule.create();
		addBuiltins(module);
		
		ParserAux aux = new ParserAux(module);
		
		Map<IRecordType, File> types = new HashMap<>();

		for(File f : javaFiles) {
			String namespace = f.getName().substring(0, f.getName().lastIndexOf('.'));
			IRecordType t = module.addRecordType(namespace);
			types.put(t, f);
		}
		
		for(Entry<IRecordType, File> e : types.entrySet()) {
			CharStream s = CharStreams.fromFileName(e.getValue().getAbsolutePath(), Charset.forName("ISO-8859-1"));
			Java8Lexer lexer = new Java8Lexer(s);
			Java8Parser p = new Java8Parser(new CommonTokenStream(lexer));
			PreParserListener l = new PreParserListener(module, e.getKey(), aux);
			ParseTreeWalker w = new ParseTreeWalker();
			w.walk(l, p.compilationUnit());
		}
		
		for(Entry<IRecordType, File> e : types.entrySet()) {
			System.out.println("parsing " + e.getKey().getId());
			CharStream s = CharStreams.fromFileName(e.getValue().getAbsolutePath(), Charset.forName("UTF-8"));
			Java8Lexer lexer = new Java8Lexer(s);
			Java8Parser p = new Java8Parser(new CommonTokenStream(lexer));
			ParserListener l = new ParserListener(module, e.getKey(), aux);
			ParseTreeWalker w = new ParseTreeWalker();
			w.walk(l, p.compilationUnit());
			System.out.println("done " + e.getKey().getId() + "\n blocks: " + l.blockStack + "\n exp stack: " + l.expressionStack);
		}
//		SemanticChecker checker = new SemanticChecker(new AsgSemanticChecks());
//		List<ISemanticProblem> problems = checker.check(module);
//	    System.err.println(problems);
		
		return module;
	}

	private void addBuiltins(IModule module) {
		module.addRecordType(String.class.getSimpleName());
		try {
			module.loadBuiltInProcedures(String.class.getMethod("length"));
			module.loadBuiltInProcedures(String.class.getMethod("trim"));
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		module.loadBuiltInProcedures(Math.class);
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

