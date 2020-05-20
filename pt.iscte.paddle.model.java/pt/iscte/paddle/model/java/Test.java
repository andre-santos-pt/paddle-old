package pt.iscte.paddle.model.java;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import pt.iscte.paddle.model.IModule;

public class Test {

	public static void main(String[] args) throws IOException {
		//		Charset latin = Charset.forName("ISO-8859-1");
		//		Charset utf8 = Charset.forName("UTF-8");
		//		Charset win = Charset.forName("windows-1252");

		//		String path = "/Users/andresantos/git/paddle-ui/pt.iscte.paddle.javardise.tests/src-gen";
		File root = new File("/Users/andresantos/Desktop/Trabalhos254/4");
//		File root = new File("/Users/andresantos/EclipseWS/workspace-paddle/student/src");
		List<File> compilationOk = new ArrayList<>();
		List<IModule> parseOk = new ArrayList<>();
		List<String> errorProjs = new ArrayList<>();
		
		for (int n = 2; n <= 2; n++) {
			File proj = new File(root, ""+n);

			JavaParser p = new JavaParser(root, f -> f.getName().equals("ImageUtil.java"));
			if(p.check()) {
				compilationOk.add(proj);
//				 try {
					IModule m = p.parse();
					parseOk.add(m);
					System.out.println(m);
//				}
//				catch(Exception e) {
//					errorProjs.add(""+n);
//					e.printStackTrace();
//				}
			}
			//					success.add(proj);
			//			System.out.println(proj + " : " + (m == null ? "fail" : "ok"));
		}
		System.out.println("comp " + compilationOk.size());
		System.out.println("parse " + parseOk.size());
		System.out.println(errorProjs);
		//		if(m == null)
		//			System.err.println("file errors on " + path);
		//		System.out.println(m);
	}
}

//		SemanticChecker checker = new SemanticChecker(new AsgSemanticChecks());
//		List<ISemanticProblem> problems = checker.check(module);
//	    System.err.println(problems);


