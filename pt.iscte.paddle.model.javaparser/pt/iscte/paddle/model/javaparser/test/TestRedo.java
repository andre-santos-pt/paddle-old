package pt.iscte.paddle.model.javaparser.test;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.javaparser.Java2Paddle;
import pt.iscte.paddle.model.validation.AsgSemanticChecks;
import pt.iscte.paddle.model.validation.ISemanticProblem;
import pt.iscte.paddle.model.validation.SemanticChecker;

public class TestRedo {

	public static void main(String[] args) throws IOException {
		File binDest = new File("/Users/andresantos/EclipseWS/workspace-paddle/testgenok/genbin");
		File redoDest = new File("/Users/andresantos/EclipseWS/workspace-paddle/testredo/src");
		
		List<File> compilationOk = new ArrayList<>();
		List<File> parseOk = new ArrayList<>();
		List<IModule> reparseFail = new ArrayList<>();
		List<File> errorProjs = new ArrayList<>();
		List<Integer> exceptions = new ArrayList<Integer>();
		long time = System.currentTimeMillis();
		
		for (File f : binDest.listFiles(f -> f.getName().endsWith(".java") && !f.getName().equals("ImageUtil.java"))) {
			IModule module = IModule.create(f.getName().substring(0, f.getName().indexOf('.')));
			addBuiltins(module);

			Java2Paddle p = new Java2Paddle(f, fi -> false, module);
			if(p.checkSyntax()) {
				compilationOk.add(f);
				try {
					IModule m = p.parse();
					SemanticChecker checker = new SemanticChecker(new AsgSemanticChecks());
					List<ISemanticProblem> problems = checker.check(m);
					//				    System.err.println("semantics: " + problems);
					System.err.println("unsupported: " + p.getUnsupported().size());
					if(p.getUnsupported().size() == 0) {
						parseOk.add(f);
						Path copied = Paths.get(new File(redoDest, f.getName()).getAbsolutePath());
						Path originalPath = Paths.get(f.getAbsolutePath());
						Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
					}
					else
						errorProjs.add(f);

//					System.out.println(m);
				}
				catch(Exception e) {
					
					errorProjs.add(f);
				}
			}
			//					success.add(proj);
			//			System.out.println(proj + " : " + (m == null ? "fail" : "ok"));
		}
		System.out.println("comp " + compilationOk.size());
		System.out.println("parse " + parseOk.size());
		System.out.println(errorProjs);
		System.out.println("nulls " + exceptions);
		System.out.println("reparse fail: " + reparseFail.size());
		System.out.println("time " + (System.currentTimeMillis() - time));
		//		if(m == null)
		//			System.err.println("file errors on " + path);
		//		System.out.println(m);
	}

	private static void addBuiltins(IModule module) {
		//		module.addRecordType(String.class.getSimpleName());

		IRecordType system = module.addRecordType("System");
		IRecordType out = module.addRecordType("PrintStream");
		out.setNamespace("System");
		system.addField(out, "out");

		try {
			module.loadBuiltInProcedures(String.class.getMethod("length"));
			module.loadBuiltInProcedures(String.class.getMethod("trim"));
			module.loadBuiltInProcedures(String.class.getMethod("charAt", int.class));
			module.loadBuiltInProcedures(String.class.getMethod("toCharArray"));
			module.loadBuiltInProcedures(String.class.getMethod("toUpperCase"));
			module.loadBuiltInProcedures(String.class.getMethod("isEmpty"));
			module.loadBuiltInProcedures(String.class.getMethod("equals", Object.class)); // ?
			module.loadBuiltInProcedures(String.class.getMethod("concat", String.class));
			module.loadBuiltInProcedures(String.class.getMethod("compareTo", String.class));
			module.loadBuiltInProcedures(String.class.getMethod("compareToIgnoreCase", String.class));
			module.loadBuiltInProcedures(String.class.getConstructor(String.class));

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


