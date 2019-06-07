package pt.iscte.paddle.tests.toce;
import static pt.iscte.paddle.asg.IOperator.GREATER;
import static pt.iscte.paddle.asg.IType.INT;

import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.ISelection;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IExecutionData;
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
