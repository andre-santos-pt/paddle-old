package pt.iscte.paddle.tests.toce;
import static pt.iscte.paddle.asg.IOperator.SMALLER;
import static pt.iscte.paddle.asg.IType.INT;

import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestMin extends BaseTest {
	IProcedure min = getModule().addProcedure(INT);
	IVariable a = min.addParameter(INT);
	IVariable b = min.addParameter(INT);
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
