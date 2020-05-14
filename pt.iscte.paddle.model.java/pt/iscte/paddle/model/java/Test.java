package pt.iscte.paddle.model.java;

import java.io.File;
import java.io.IOException;

import pt.iscte.paddle.model.IModule;

public class Test {

	public void test() {
		
		
	}
	public static void main(String[] args) throws IOException {
//		String path = "/Users/andresantos/git/paddle-ui/pt.iscte.paddle.javardise.tests/src-gen/TestFactorial.java";
		String path = "/Users/andresantos/Desktop/Trabalhos/2/ProjetoIndividual.java";
		JavaParser p = new JavaParser(new File(path));
		IModule m = p.parse();
		System.out.println(m);
		
	}

}
