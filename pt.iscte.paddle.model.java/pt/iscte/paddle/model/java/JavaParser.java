package pt.iscte.paddle.model.java;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import pt.iscte.paddle.model.IModule;

public class JavaParser {
	
	private final String filename;
	private final String source;
	
	public JavaParser(File file) {
		source = readSource(file);
		filename = file.getName().substring(0, file.getName().lastIndexOf('.'));
	}
	
	public JavaParser(StringBuffer buffer) {
		source = buffer.toString();
		filename = "undefined";
	}
	
	public IModule parse() {
		return IModule.create();
	}
	
	public boolean hasParseProblems() {
		return false;
	}
	
	private static String readSource(File file) {
		StringBuilder src = new StringBuilder();
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine())
				src.append(scanner.nextLine()).append('\n');
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return src.toString();
	}
	
}

