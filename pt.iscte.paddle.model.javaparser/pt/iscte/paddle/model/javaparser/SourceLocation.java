package pt.iscte.paddle.model.javaparser;

import java.io.File;

public final class SourceLocation {
	final String path;
	final int line;
	
	public SourceLocation(File file, int line) {
		this.path = file.getAbsolutePath();
		this.line = line;
	}

	@Override
	public String toString() {
		return path + " : " + line;
	}
}
