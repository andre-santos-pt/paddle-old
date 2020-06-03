package pt.iscte.paddle.model.javaparser;



import java.io.File;
import java.io.IOException;
import java.lang.reflect.Executable;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.mozilla.universalchardet.UniversalDetector;

import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.javaparser.antlr.JavaLexer;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.CompilationUnitContext;

// TODO API for text
public class Java2Paddle {

	private final File[] javaFiles;
	boolean errors;
	private ParserAux aux;

	private final IModule module;

	public Java2Paddle(File file) {
		this(file, file.getName().replace(".java", ""));
	}
	
	public Java2Paddle(File file, String moduleId) {
		this(file, f -> false, IModule.create(moduleId));
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
				String charset = detectCharset(f);
				if(Charset.isSupported(charset)) {
					s = CharStreams.fromFileName(f.getAbsolutePath(), Charset.forName(charset));
					JavaLexer lexer = new JavaLexer(s);
					JavaParser p = new JavaParser(new CommonTokenStream(lexer));
					p.addErrorListener(l);
					CompilationUnitContext cu = p.compilationUnit();
				}
				else {
					l.errCount++;
				}
			} 
			catch (IOException e) {
				return false;
			} 
		}
		return l.errCount == 0;
	}
	
	
	private String detectCharset(File f) throws IOException {
		String charset = UniversalDetector.detectCharset(f);
		if(charset == null || !Charset.isSupported(charset))
			charset = "UTF-8";
		
		return charset;
	}
	

	public IModule parse() throws IOException {
		aux = new ParserAux(module);

		Map<IRecordType, File> types = new HashMap<>();
		Map<File, Charset> charsets = new HashMap<>();
		
//		for(File f : javaFiles) {
//			IRecordType t = module.addRecordType();
//			types.put(t, f);
//		}

		for(File f : javaFiles) {
			Charset charset = Charset.forName(detectCharset(f));
			charsets.put(f, charset);
			
			CharStream s = CharStreams.fromFileName(f.getAbsolutePath(), charset);
			JavaLexer lexer = new JavaLexer(s);
			JavaParser p = new JavaParser(new CommonTokenStream(lexer));
			RecordParserListener l = new RecordParserListener(module, aux);
			ParseTreeWalker w = new ParseTreeWalker();
			w.walk(l, p.compilationUnit());
		}
		
		for(File f : javaFiles) {
			CharStream s = CharStreams.fromFileName(f.getAbsolutePath(), charsets.get(f));
			JavaLexer lexer = new JavaLexer(s);
			JavaParser p = new JavaParser(new CommonTokenStream(lexer));
			MemberParserListener l = new MemberParserListener(module, f, aux);
			ParseTreeWalker w = new ParseTreeWalker();
			w.walk(l, p.compilationUnit());
		}

		for(File f : javaFiles) {
			CharStream s = CharStreams.fromFileName(f.getAbsolutePath(), charsets.get(f));
			JavaLexer lexer = new JavaLexer(s);
			JavaParser p = new JavaParser(new CommonTokenStream(lexer));
			BodyListener l = new BodyListener(module, f, aux);
			ParseTreeWalker w = new ParseTreeWalker();
			w.walk(l, p.compilationUnit());
			System.out.println("parsing done " + f);
			if(!l.blockStack.isEmpty())
				System.err.println("block stack: " + l.blockStack);
			if(!l.expStack.isEmpty())
				System.err.println("expression stack: " + l.expStack);
		}

		return module;
	}


	public List<Object> getUnsupported() {
		return aux.unsupported;
	}

	public void loadBuiltInProcedures(Executable ...executables) {
		module.loadBuiltInProcedures(executables);
	}

	public void loadBuiltInProcedures(Class<?> clazz) {
		module.loadBuiltInProcedures(clazz);
	}

	public IRecordType addBuiltInRecordType(String id) {
		IRecordType t = module.addRecordType(id);
		t.setNamespace(id);
		t.setFlag(IRecordType.BUILTIN);
		return t;
	}
}

