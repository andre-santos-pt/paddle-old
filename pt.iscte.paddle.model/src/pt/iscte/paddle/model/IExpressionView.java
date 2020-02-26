package pt.iscte.paddle.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface IExpressionView {
	IExpression expression();
	
	static List<IExpression> toList(IExpressionView ... views) {
		return Arrays.asList(views).stream().map(e -> e.expression()).collect(Collectors.toList());
	}
}
