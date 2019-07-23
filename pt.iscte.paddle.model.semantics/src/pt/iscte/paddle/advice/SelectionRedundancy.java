package pt.iscte.paddle.advice;
import java.util.Collection;
import java.util.List;

import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IBlockElement;
import pt.iscte.paddle.model.ISelection;

public class SelectionRedundancy implements ElementAnalysis<ISelection> {

	@Override
	public Collection<IAnalsysItem> perform(ISelection e) {
		if(e.hasAlternativeBlock()) {
			IBlock a = e.getBlock();
			IBlock b = e.getAlternativeBlock();

			if(!a.isEmpty() && b != null && !b.isEmpty()) {
				List<IBlockElement> aSeq = a.getChildren();
				List<IBlockElement> bSeq = a.getChildren();
				int start = 0;
				while(start < Math.min(aSeq.size(), bSeq.size()) && aSeq.get(start).equals(bSeq.get(start)))
					start++;

				int endA = aSeq.size() - 1;
				int endB = bSeq.size() - 1;
				int end = 0;
				while(endA >= 0 && endB >= 0 && aSeq.get(endA).equals(bSeq.get(endB))) {
					end++;
					endA--;
					endB--;
				}
			}
		}
		return null;
	}

}
