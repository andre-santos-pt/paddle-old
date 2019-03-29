package pt.iscte.paddle.tests.asg;

import static org.junit.Assert.assertTrue;
import static pt.iscte.paddle.asg.IDataType.DOUBLE;
import static pt.iscte.paddle.asg.IDataType.INT;
import static pt.iscte.paddle.asg.ILiteral.literal;
import static pt.iscte.paddle.asg.IOperator.ADD;
import static pt.iscte.paddle.asg.IOperator.MUL;
import static pt.iscte.paddle.asg.IOperator.SUB;
import static pt.iscte.paddle.asg.IOperator.TRUNCATE;

import java.math.BigDecimal;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProcedureCall;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.IUnaryExpression;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IExecutionData;

public class TestRandom extends BaseTest {

	public static class RandomProc {
		public static double random() {
			return Math.random();
		}
	}

	@Override
	protected Class<?>[] getBuiltins() {
		return new Class[] {RandomProc.class};
	}

	IProcedure randomInt = module.addProcedure(INT);
	IVariable min = randomInt.addParameter(INT);
	IVariable max = randomInt.addParameter(INT);
	IProcedureCall randomCall = module.resolveProcedure("random").call();
	IVariable r = randomInt.getBody().addVariable(DOUBLE, randomCall);
	IBinaryExpression m = MUL.on(r, ADD.on(SUB.on(max, min), literal(1)));
	IUnaryExpression t = TRUNCATE.on(m);
	IBinaryExpression e = ADD.on(min, t);
	IBlock body = randomInt.getBody();
	IReturn return1 = body.addReturn(e);

	@Case({"1","10"})
	public void testRandomInt(IExecutionData data) {
		int ret = ((BigDecimal) data.getReturnValue()).intValue();
		assertTrue(ret >= 1); 
		assertTrue(ret <= 10); 
	}

}
