package pt.iscte.paddle.tests.asg;

import static org.junit.Assert.assertTrue;
import static pt.iscte.paddle.model.IOperator.ADD;
import static pt.iscte.paddle.model.IOperator.MUL;
import static pt.iscte.paddle.model.IOperator.SUB;
import static pt.iscte.paddle.model.IOperator.TRUNCATE;
import static pt.iscte.paddle.model.IType.DOUBLE;
import static pt.iscte.paddle.model.IType.INT;

import java.math.BigDecimal;

import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IBinaryExpression;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProcedureCall;
import pt.iscte.paddle.model.IProcedureCallExpression;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.IUnaryExpression;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.tests.BaseTest;

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
	IVariableDeclaration min = randomInt.addParameter(INT);
	IVariableDeclaration max = randomInt.addParameter(INT);
	IProcedure random = module.resolveProcedure("random");
	IProcedureCallExpression randomCall = random.expression();
	IVariableDeclaration r = randomInt.getBody().addVariable(DOUBLE, randomCall);
	IBinaryExpression m = MUL.on(r, ADD.on(SUB.on(max, min), INT.literal(1)));
	IUnaryExpression t = TRUNCATE.on(m);
	IBinaryExpression e = ADD.on(min, t);
	IBlock body = randomInt.getBody();
	IReturn return1 = body.addReturn(e);

	@Case({"1","10"})
	public void testRandomInt(IExecutionData data) {
		int ret = ((BigDecimal) data.getReturnValue().getValue()).intValue();
		assertTrue(ret >= 1); 
		assertTrue(ret <= 10); 
	}

}
