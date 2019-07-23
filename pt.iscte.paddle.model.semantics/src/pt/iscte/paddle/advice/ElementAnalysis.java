package pt.iscte.paddle.advice;
import java.util.Collection;

import pt.iscte.paddle.model.IProgramElement;

public interface ElementAnalysis<T extends IProgramElement> {

	Collection<IAnalsysItem> perform(T e);
}
