package pt.iscte.paddle.model.tests;
import static pt.iscte.paddle.model.IOperator.SMALLER;
import static pt.iscte.paddle.model.IType.INT;

import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.IVariableDeclaration;

public class TestMin extends BaseTest {
	IProcedure min = getModule().addProcedure(INT);
	IVariableDeclaration a = min.addParameter(INT);
	IVariableDeclaration b = min.addParameter(INT);
	IReturn ret = min.getBody().addReturn(SMALLER.on(a, b).conditional(a, b));
	
	@Case({"-2", "3"})
	public void testFirst(IExecutionData data) {
		equal(-2, data.getReturnValue());
	}
	
	@Case({"2", "1"})
	public void testSecond(IExecutionData data) {
		equal(1, data.getReturnValue());
	}
}
