package pt.iscte.paddle.model.javaparser;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import pt.iscte.paddle.model.IModule;

public class Javac {

	public static boolean compile(IModule module, File outputDir) throws IOException {
		Paddle2Java p2j = new Paddle2Java();
		String code = p2j.translate(module);
		return compile(module.getId(), code, outputDir);
	}
	
	
	public static boolean compile(String className, String src, File outputDir) throws IOException {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
		JavaStringObject stringObject = new JavaStringObject(className, src);
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(outputDir));
		fileManager.setLocation(StandardLocation.CLASS_PATH, Arrays.asList(outputDir));
		JavaCompiler.CompilationTask task = compiler.getTask(null,
				fileManager, diagnostics, null, null, Arrays.asList(stringObject));
		boolean call =  task.call();
		System.err.println(diagnostics.getDiagnostics());
		return call;
	}
	
	private static class JavaStringObject extends SimpleJavaFileObject {
	    private final StringBuffer source;

	    protected JavaStringObject(String name, String src) {
	    	this(name, new StringBuffer(src));
	    }
	    
	    protected JavaStringObject(String name, StringBuffer source) {
	        super(URI.create("string:///" + name.replaceAll("\\.", "/") +
	                Kind.SOURCE.extension), Kind.SOURCE);
	        this.source = source;
	    }

	    @Override
	    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
	        return source;
	    }
	}
}
