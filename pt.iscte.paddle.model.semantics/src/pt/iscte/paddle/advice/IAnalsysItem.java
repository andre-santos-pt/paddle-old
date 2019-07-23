package pt.iscte.paddle.advice;
import java.util.List;

import pt.iscte.paddle.model.IProgramElement;

public interface IAnalsysItem {
	String getTitle();
	String getDescription();
	List<IProgramElement> getElements();
}
