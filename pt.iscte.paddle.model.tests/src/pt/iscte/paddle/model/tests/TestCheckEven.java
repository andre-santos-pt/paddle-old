package pt.iscte.paddle.model.tests;
import static pt.iscte.paddle.model.IOperator.EQUAL;
import static pt.iscte.paddle.model.IOperator.MOD;
import static pt.iscte.paddle.model.IType.BOOLEAN;
import static pt.iscte.paddle.model.IType.INT;

import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IBinaryExpression;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.IVariable;

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
