package pt.iscte.paddle.model.tests;
import static pt.iscte.paddle.model.IOperator.GREATER;
import static pt.iscte.paddle.model.IType.INT;

import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IVariable;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestMax extends BaseTest {
	IProcedure max = getModule().addProcedure(INT);
	IVariable a = max.addParameter(INT);
	IVariable b = max.addParameter(INT);
	ISelection ifstat = max.getBody().addSelectionWithAlternative(GREATER.on(a, b));
	IReturn ra = ifstat.addReturn(a);
	IReturn rb = ifstat.getAlternativeBlock().addReturn(b);
	
	@Case({"10", "8"})
	public void testFirst(IExecutionData data) {
		equal(10, data.getReturnValue());
	}
	
	@Case({"-1", "8"})
	public void testSecond(IExecutionData data) {
		equal(8, data.getReturnValue());
	}
}
