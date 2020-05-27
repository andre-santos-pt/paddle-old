package pt.iscte.paddle.model.javaparser.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.javaparser.Java2Paddle;
import pt.iscte.paddle.model.validation.AsgSemanticChecks;
import pt.iscte.paddle.model.validation.ISemanticProblem;
import pt.iscte.paddle.model.validation.SemanticChecker;

public class Test {

	public static void main(String[] args) throws IOException {
		//		Charset latin = Charset.forName("ISO-8859-1");
		//		Charset utf8 = Charset.forName("UTF-8");
		//		Charset win = Charset.forName("windows-1252");

		//		String path = "/Users/andresantos/git/paddle-ui/pt.iscte.paddle.javardise.tests/src-gen";
		File root = new File("/Users/andresantos/Desktop/Trabalhos254/");
//		File root = new File("/Users/andresantos/git/paddle-ui/pt.iscte.paddle.javardise.tests/src-gen");
		List<File> compilationOk = new ArrayList<>();
		List<IModule> parseOk = new ArrayList<>();
		List<Integer> errorProjs = new ArrayList<>();
		int unsupported = 0;
		List<Integer> nullPointers = new ArrayList<Integer>();
		long time = System.currentTimeMillis();
		for (int n = 1; n <= 254; n++) {
			File proj = new File(root, ""+n);

			Java2Paddle p = new Java2Paddle(proj, f -> f.getName().equals("ImageUtil.java"));
			if(p.checkSyntax()) {
				compilationOk.add(proj);
				 try {
					IModule m = p.parse();
					parseOk.add(m);
//					System.out.println(m);
//					SemanticChecker checker = new SemanticChecker(new AsgSemanticChecks());
//					List<ISemanticProblem> problems = checker.check(m);
//				    System.err.println("semantics: " + problems);
//				    System.err.println("unsupported: " + p.getUnsupported().size());
					if(p.getUnsupported().size() > 0)
						unsupported++;
				 }
				catch(Exception e) {
					errorProjs.add(n);
					if(e instanceof NullPointerException)
						nullPointers.add(n);
				}
			}
			//					success.add(proj);
			//			System.out.println(proj + " : " + (m == null ? "fail" : "ok"));
		}
		System.out.println("comp " + compilationOk.size());
		System.out.println("parse " + parseOk.size());
		System.out.println("unsupport " + unsupported);
		System.out.println(errorProjs);
		System.out.println("nulls " + nullPointers);
		System.out.println("time " + (System.currentTimeMillis() - time));
		//		if(m == null)
		//			System.err.println("file errors on " + path);
		//		System.out.println(m);
	}
}

//		SemanticChecker checker = new SemanticChecker(new AsgSemanticChecks());
//		List<ISemanticProblem> problems = checker.check(module);
//	    System.err.println(problems);


