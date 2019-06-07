package pt.iscte.paddle.ui;

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;

public class TestHover implements ITextHover {

	@Override
	public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion) {
		// TODO Auto-generated method stub
		return "??? " + hoverRegion;
	}

	@Override
	public IRegion getHoverRegion(ITextViewer textViewer, int offset) {
		System.out.println(offset);
		return new Region(offset-10, 20);
	}

}
