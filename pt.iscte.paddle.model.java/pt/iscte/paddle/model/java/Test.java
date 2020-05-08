package pt.iscte.paddle.model.java;

import java.io.File;

import pt.iscte.paddle.model.IModule;

public class Test {

	public void test() {
		
		
	}
	public static void main(String[] args) {
		JavaParser p = new JavaParser(new File("Test.java"));
		p = null;
		IModule m = p.parse();
		System.out.println(m);
		
	}

}
