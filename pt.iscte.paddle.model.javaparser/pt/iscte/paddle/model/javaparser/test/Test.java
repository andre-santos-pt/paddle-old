package pt.iscte.paddle.model.javaparser.test;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.javaparser.Java2Paddle;
import pt.iscte.paddle.model.javaparser.Javac;
import pt.iscte.paddle.model.javaparser.Paddle2Java;
import pt.iscte.paddle.model.validation.AsgSemanticChecks;
import pt.iscte.paddle.model.validation.ISemanticProblem;
import pt.iscte.paddle.model.validation.SemanticChecker;

public class Test {

	public static void main(String[] args) throws IOException {
		File projectsRoot = new File("/Users/andresantos/Desktop/Trabalhos254");

		File outputDir = new File("/Users/andresantos/EclipseWS/workspace-paddle/trabalhos2paddle/src");

		File outputDirBis = new File("/Users/andresantos/EclipseWS/workspace-paddle/trabalhos4paddle/src");

		List<File> projsCompilationOk = new ArrayList<>();
		List<IModule> paddleParseOk = new ArrayList<>();		
		List<Integer> exceptions = new ArrayList<Integer>();
		List<Integer> recompileFail = new ArrayList<>();
		List<Integer> reparseFail = new ArrayList<>();

		long time = System.currentTimeMillis();
		final int FIRST = 1;
		final int LAST = 254;
		for (int n = FIRST; n <= LAST; n++) {
			File proj = new File(projectsRoot, ""+n);
			String projId = "Project" + proj.getName();

			Java2Paddle p = new Java2Paddle(proj, projId);
			addBuiltins(p);

			if(p.checkSyntax()) {
				projsCompilationOk.add(proj);
				try {
					IModule m = p.parse();
					SemanticChecker checker = new SemanticChecker(new AsgSemanticChecks());
					List<ISemanticProblem> problems = checker.check(m);
					System.err.println("semantics: " + problems);
					System.err.println("unsupported: " + p.getUnsupported().size());

					String code = new Paddle2Java(projId).translate(m);

					File fileOut = new File(outputDir, projId + ".java");
					PrintWriter w = new PrintWriter(fileOut);
					w.write(code);
					w.close();

					if(Javac.compile(m, new File(outputDir.getParentFile(), "bin"))) {
						paddleParseOk.add(m);	

						Java2Paddle p2 = new Java2Paddle(fileOut, projId);
						addBuiltins(p2);
						try {
							String code2 = new Paddle2Java().translate(p2.parse());
							File fileOut2 = new File(outputDirBis, projId + ".java");
							PrintWriter w2 = new PrintWriter(fileOut2);
							w2.write(code2);
							w2.close();
						}
						catch(Exception e) {
							reparseFail.add(n);
							e.printStackTrace();
						}
						
						if(!Javac.compile(m, new File(outputDirBis.getParentFile(), "bin")))
							reparseFail.add(n);	
							
						//						Path copied = Paths.get(new File(binDest, projId + ".java").getAbsolutePath());
						//						Path originalPath = Paths.get(f.getAbsolutePath());
						//						Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
					}
					else
						recompileFail.add(n);
				}
				catch(Exception e) {
					exceptions.add(n);
					e.printStackTrace();
				}
			}
		}
		System.out.println("projs ok: " + projsCompilationOk.size());
		System.out.println("paddle parse: " + paddleParseOk.size());

		System.out.println("exceptions: " + exceptions.size() + " -- " + exceptions);
		System.out.println("recompile fail: " + recompileFail.size() + " -- " + recompileFail);
		System.out.println("reparse fail: " + reparseFail.size() + " -- " + reparseFail);

		System.out.println("time " + (System.currentTimeMillis() - time));
	}





	static void addBuiltins(Java2Paddle j2p) {
		
		j2p.loadBuiltInProcedures(Object.class);
		j2p.loadBuiltInProcedures(String.class);
		j2p.loadBuiltInProcedures(Math.class);
		j2p.loadBuiltInProcedures(ImageUtil.class);
		j2p.loadBuiltInProcedures(IllegalArgumentException.class);
		j2p.loadBuiltInProcedures(IllegalStateException.class);
		j2p.loadBuiltInProcedures(NullPointerException.class);
		
		j2p.loadBuiltInProcedures(PrintStream.class);
		IRecordType system = j2p.addBuiltInRecordType("System");
		IRecordType out = j2p.addBuiltInRecordType("PrintStream");
		out.setNamespace("System");
		system.addField(out, "out");
		
	}

}


