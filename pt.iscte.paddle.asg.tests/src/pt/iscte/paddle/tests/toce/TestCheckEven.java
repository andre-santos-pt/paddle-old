package pt.iscte.paddle.tests.toce;
import static pt.iscte.paddle.asg.IOperator.EQUAL;
import static pt.iscte.paddle.asg.IOperator.MOD;
import static pt.iscte.paddle.asg.IType.BOOLEAN;
import static pt.iscte.paddle.asg.IType.INT;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestCheckEven extends BaseTest {
	IProcedure isEven = getModule().addProcedure(BOOLEAN);
	IVariable n = isEven.addParameter(INT);
	IBinaryExpression e = EQUAL.on(MOD.on(n, INT.literal(2)), INT.literal(0));
	IBlock body = isEven.getBody();
	IReturn ret = body.addReturn(e);
	
	@Case("4")
	public void testTrue(IExecutionData data) {
		isTrue(data.getReturnValue());
	}
	
	@Case("5")
	public void testFalse(IExecutionData data) {
		isFalse(data.getReturnValue());
	}
}
