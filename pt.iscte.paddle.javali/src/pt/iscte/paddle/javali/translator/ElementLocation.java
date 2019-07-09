package pt.iscte.paddle.javali.translator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;


public class ElementLocation implements ISourceLocation {
	public enum Part {
		WHOLE,
		ID,
		EXPRESSION;
	}
	
	private final String source;
	private final int offset;
	private final int length;
	private final int line;

	public ElementLocation(EObject node) {
		this(NodeModelUtils.getNode(node));
	}
	
	public ElementLocation(ICompositeNode node) {
		this.source = node.getText();
		this.offset = node.getOffset();
		this.length = node.getLength();
		this.line = node.getStartLine();
	}
	
//	public ElementLocation(String source, int offset, int length, int line) {
//		this.source = source;
//		this.offset = offset;
//		this.length = length;
//		this.line = line;
//	}

	@Override
	public String toString() {
		return "line " + line + " (" + offset + "->" + (offset+length) + ")"; 
	}
	
	public int getStartChar() {
		return offset;
	}
	
	public int getEndChar() {
		return offset + length;
	}
	
	@Override
	public int getLine() {
		return line;
	}

	
//	@Override
//	public String getSourceCode() {
//		return source;
//	}
//	
//	@Override
//	public int getOffset() {
//		return offset;
//	}
//
//	@Override
//	public int getLength() {
//		return length;
//	}

}

