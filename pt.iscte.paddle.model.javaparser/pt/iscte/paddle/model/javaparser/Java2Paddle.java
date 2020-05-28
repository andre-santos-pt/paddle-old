package pt.iscte.paddle.model.javaparser;



import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
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

//	private final String id;
	private final File[] javaFiles;
	boolean errors;
	private ParserAux aux;
	
	private final IModule module;
	
	public Java2Paddle(File file) {
		this(file, f -> false, IModule.create(file.getName()));
	}

	public Java2Paddle(File file, Predicate<File> exclude, IModule module) {
		this.module = module;
		javaFiles = file.isFile() ? new File[] {file} : file.listFiles(f -> f.getName().endsWith(".java") && !exclude.test(f));
	}

	public boolean checkSyntax() {
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
				s = CharStreams.fromFileName(f.getAbsolutePath(), Charset.forName("UTF-8"));
				JavaLexer lexer = new JavaLexer(s);
				JavaParser p = new JavaParser(new CommonTokenStream(lexer));
				p.addErrorListener(l);
				CompilationUnitContext cu = p.compilationUnit();
			} 
			catch (IOException e) {
				return false;
			} 
		}
		return l.errCount == 0;
	}

	public IModule parse() throws IOException {
		aux = new ParserAux(module);

		Map<IRecordType, File> types = new HashMap<>();

		for(File f : javaFiles) {
			String namespace = f.getName().substring(0, f.getName().lastIndexOf('.'));
			IRecordType t = module.addRecordType(namespace);
			t.setNamespace(namespace);
			types.put(t, f);
		}

		for(Entry<IRecordType, File> e : types.entrySet()) {
			CharStream s = CharStreams.fromFileName(e.getValue().getAbsolutePath(), Charset.forName("UTF-8"));
			JavaLexer lexer = new JavaLexer(s);
			JavaParser p = new JavaParser(new CommonTokenStream(lexer));
			PreParserListener l = new PreParserListener(module, e.getKey(), e.getValue(), aux);
			ParseTreeWalker w = new ParseTreeWalker();
			w.walk(l, p.compilationUnit());
		}

		for(Entry<IRecordType, File> e : types.entrySet()) {
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

	

	public boolean hasParseProblems() {
		return false;
	}

	public List<Object> getUnsupported() {
		return aux.unsupported;
	}

}

