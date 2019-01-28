package pt.iscte.paddle.javali2asg;

public class IdLocation implements ISourceLocation  {
	private final int offset;
	private final int length;

	public IdLocation(int offset, int length) {
		this.offset = offset;
		this.length = length;
	}

	@Override
	public String toString() {
		return "(" + offset + "->" + (offset+length) + ")"; 
	}
	
	public int getStartChar() {
		return offset;
	}
	public int getEndChar() {
		return offset + length;
	}
}
