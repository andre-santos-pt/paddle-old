package pt.iscte.paddle.model.javaparser.test;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.javaparser.Java2Paddle;
import pt.iscte.paddle.model.javaparser.Paddle2Java;
import pt.iscte.paddle.model.validation.AsgSemanticChecks;
import pt.iscte.paddle.model.validation.ISemanticProblem;
import pt.iscte.paddle.model.validation.SemanticChecker;

public class Test {

	public static void main(String[] args) throws IOException {
		File genDest = new File("/Users/andresantos/EclipseWS/workspace-paddle/testgen/gen");
		
		File root = new File("/Users/andresantos/Desktop/Trabalhos254");
//		File root = new File("/Users/andresantos/git/paddle-ui/pt.iscte.paddle.javardise.tests/src-gen/TestMax.java");
		List<File> compilationOk = new ArrayList<>();
		List<IModule> parseOk = new ArrayList<>();
		List<Integer> errorProjs = new ArrayList<>();
		List<Integer> exceptions = new ArrayList<Integer>();
		long time = System.currentTimeMillis();
		final int FIRST = 1;
		final int LAST = 254;
		for (int n = FIRST; n <= LAST; n++) {
			File proj = new File(root, ""+n);
			String projId = "Project" + proj.getName();
			IModule module = IModule.create(root.isFile() ? "$" + root.getName().substring(0, root.getName().length()-5) : projId);
			addBuiltins(module);
			
			Java2Paddle p = new Java2Paddle(root.isFile() ? root : proj, f -> f.getName().equals("ImageUtil.java"), module);
			if(p.checkSyntax()) {
				compilationOk.add(proj);
				 try {
					IModule m = p.parse();
					SemanticChecker checker = new SemanticChecker(new AsgSemanticChecks());
//					List<ISemanticProblem> problems = checker.check(m);
//				    System.err.println("semantics: " + problems);
				    System.err.println("unsupported: " + p.getUnsupported().size());
//					if(p.getUnsupported().size() == 0)
						parseOk.add(m);	
					
					String code = new Paddle2Java().translate(m);
					PrintWriter w = new PrintWriter(new File(genDest, root.isFile() ? root.getName() : projId + ".java"));
					w.write(code);
					w.close();
					
					if(FIRST == LAST)
						System.out.println(code);
				 }
				catch(Exception e) {
					errorProjs.add(n);
					exceptions.add(n);
					if(FIRST == LAST)
						e.printStackTrace();
				}
			}
			//					success.add(proj);
			//			System.out.println(proj + " : " + (m == null ? "fail" : "ok"));
		}
		System.out.println("comp " + compilationOk.size());
		System.out.println("parse " + parseOk.size());
		System.out.println(errorProjs);
		System.out.println("nulls " + exceptions);
		System.out.println("time " + (System.currentTimeMillis() - time));
		//		if(m == null)
		//			System.err.println("file errors on " + path);
		//		System.out.println(m);
	}
	
	private static void addBuiltins(IModule module) {
//		module.addRecordType(String.class.getSimpleName());
		try {
			module.loadBuiltInProcedures(String.class.getMethod("length"));
			module.loadBuiltInProcedures(String.class.getMethod("trim"));
			module.loadBuiltInProcedures(String.class.getMethod("toCharArray"));
			module.loadBuiltInProcedures(String.class.getMethod("concat", String.class));
			module.loadBuiltInProcedures(String.class.getMethod("compareTo", String.class));
			
			module.loadBuiltInProcedures(IllegalArgumentException.class.getConstructors());
			module.loadBuiltInProcedures(IllegalStateException.class.getConstructors());
			module.loadBuiltInProcedures(NullPointerException.class.getConstructors());
			
			module.loadBuiltInProcedures(PrintStream.class.getMethod("println", String.class));
		
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		module.loadBuiltInProcedures(Math.class);
		module.loadBuiltInProcedures(ImageUtil.class);
				
//		module.getProcedures().forEach(p -> System.out.println(p.getNamespace() + " :: " + p));
	}
}


