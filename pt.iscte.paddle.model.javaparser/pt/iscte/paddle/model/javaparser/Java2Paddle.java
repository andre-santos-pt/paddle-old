package pt.iscte.paddle.model.javaparser;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.function.Predicate;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.javaparser.antlr.JavaLexer;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.CompilationUnitContext;

// TODO API for text
public class Java2Paddle {

	private final String id;
	private final File[] javaFiles;
	boolean errors;
	private ParserAux aux;
	
	public Java2Paddle(File file) {
		this(file, f -> false);
	}

	public Java2Paddle(File file, Predicate<File> exclude) {
		id = file.getName();
		javaFiles = file.isFile() ? new File[] {file} : file.listFiles(f -> f.getName().endsWith(".java") && !exclude.test(f));
	}

	public boolean check() {
		class Listener extends BaseErrorListener {
			int errCount = 0;
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
					String msg, RecognitionException e) {
				errCount++;
			}
		};
		Listener l = new Listener();
		for(File f : javaFiles) {
			CharStream s;
			try {
//				String charset = UniversalDetector.detectCharset(f);
				s = CharStreams.fromFileName(f.getAbsolutePath(), Charset.forName("UTF-8"));
				
				JavaLexer lexer = new JavaLexer(s);
				JavaParser p = new JavaParser(new CommonTokenStream(lexer));
//				p.getInterpreter().setPredictionMode(PredictionMode.SLL);
				p.addErrorListener(l);
				CompilationUnitContext cu = p.compilationUnit();
			} 
			catch (IOException e) {
				return false;
			} finally {
			}
		}
		return l.errCount == 0;
	}

	public IModule parse() throws IOException {
		IModule module = IModule.create(id);
		addBuiltins(module);

		aux = new ParserAux(module);

		Map<IRecordType, File> types = new HashMap<>();

		for(File f : javaFiles) {
			String namespace = f.getName().substring(0, f.getName().lastIndexOf('.'));
			IRecordType t = module.addRecordType(namespace);
			types.put(t, f);
		}

		for(Entry<IRecordType, File> e : types.entrySet()) {
//			String charset = UniversalDetector.detectCharset(e.getValue());
			CharStream s = CharStreams.fromFileName(e.getValue().getAbsolutePath(), Charset.forName("UTF-8"));
			JavaLexer lexer = new JavaLexer(s);
			JavaParser p = new JavaParser(new CommonTokenStream(lexer));
			PreParserListener l = new PreParserListener(module, e.getKey(), e.getValue(), aux);
			ParseTreeWalker w = new ParseTreeWalker();
			w.walk(l, p.compilationUnit());
		}

		for(Entry<IRecordType, File> e : types.entrySet()) {
//			String charset = UniversalDetector.detectCharset(e.getValue());
			System.out.println("parsing " + e.getKey().getId() + "  " + e.getValue());
			CharStream s = CharStreams.fromFileName(e.getValue().getAbsolutePath(), Charset.forName("UTF-8"));
			JavaLexer lexer = new JavaLexer(s);
			JavaParser p = new JavaParser(new CommonTokenStream(lexer));
			ParserListener l = new ParserListener(module, e.getKey(), e.getValue(), aux);
			ParseTreeWalker w = new ParseTreeWalker();
			w.walk(l, p.compilationUnit());
			System.out.println("done " + e.getKey().getId() + "\n blocks: " + l.blockStack + "\n exp stack: " + l.expStack);
		}
		

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

	public List<Object> getUnsupported() {
		return aux.unsupported;
	}

}

