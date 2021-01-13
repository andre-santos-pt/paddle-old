package pt.iscte.paddle.model.javaparser;

import java.io.File;

public final class SourceLocation {
	final String location;
	final int line;
	
	public SourceLocation(String location, int line) {
		this.location = location;
		this.line = line;
	}

	@Override
	public String toString() {
		return location + " : " + line;
	}
}
