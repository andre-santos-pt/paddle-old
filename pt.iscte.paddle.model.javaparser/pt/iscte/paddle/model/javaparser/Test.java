package pt.iscte.paddle.model.javaparser;

import java.io.File;
import java.io.IOException;

import pt.iscte.paddle.model.IModule;

public class Test {

	public static void main(String[] args) throws IOException {
		
		File f = new File("/Users/andresantos/Desktop/Projectos/95805MarcioFernandes");
		IModule module = IModule.create();
		Java2Paddle j2p = new Java2Paddle(f, e -> e.getName().equals("ImageUtil.java"), module);
		j2p.parse();

	}

}
