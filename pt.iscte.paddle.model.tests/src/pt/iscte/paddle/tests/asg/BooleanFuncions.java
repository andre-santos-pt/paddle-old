package pt.iscte.paddle.tests.asg;
import static org.junit.Assert.assertTrue;
import static pt.iscte.paddle.model.IOperator.DIFFERENT;
import static pt.iscte.paddle.model.IOperator.EQUAL;
import static pt.iscte.paddle.model.IOperator.MOD;
import static pt.iscte.paddle.model.IOperator.NOT;
import static pt.iscte.paddle.model.IType.BOOLEAN;
import static pt.iscte.paddle.model.IType.INT;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IBinaryExpression;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.IUnaryExpression;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.tests.BaseTest;
import pt.iscte.paddle.tests.asg.BooleanFuncions.IsEven;
import pt.iscte.paddle.tests.asg.BooleanFuncions.IsOdd;
import pt.iscte.paddle.tests.asg.BooleanFuncions.IsOddNotEven;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	IsEven.class,
	IsOdd.class,
	IsOddNotEven.class
})
public class BooleanFuncions {

	public static abstract class BooleanFunctions extends BaseTest {
		@Override
		protected void commonAsserts(IExecutionData data) {
			assertTrue(data.getReturnValue().getType().isBoolean());
		}
	}
	
	public static class IsEven extends BooleanFunctions {
		IProcedure isEven = module.addProcedure(BOOLEAN);
		IVariableDeclaration n = isEven.addParameter(INT);
		IBinaryExpression e = EQUAL.on(MOD.on(n, INT.literal(2)), INT.literal(0));
		IBlock body = isEven.getBody();
		IReturn ret = body.addReturn(e);
		
		@Case("6")
		public void caseTrue(IExecutionData data) {
			assertTrue(data.getReturnValue().isTrue());
		}
		
		@Case("7")
		public void caseFalse(IExecutionData data) {
			assertTrue(data.getReturnValue().isFalse());
		}
	}
	

	public static class IsOdd extends BooleanFunctions {
		IProcedure isOdd = module.addProcedure(BOOLEAN);
		IVariableDeclaration n = isOdd.addParameter(INT);
		IBinaryExpression e = DIFFERENT.on(MOD.on(n, INT.literal(2)), INT.literal(0));
		IBlock body = isOdd.getBody();
		IReturn ret = body.addReturn(e);

		@Case("6")
		public void caseTrue(IExecutionData data) {
			assertTrue(data.getReturnValue().isFalse());
		}
		
		@Case("7")
		public void caseFalse(IExecutionData data) {
			assertTrue(data.getReturnValue().isTrue());
		}
	}
	
	public static class IsOddNotEven extends IsOdd {
		IProcedure isOddNotEven = module.addProcedure(BOOLEAN);
		IProcedure isEven = new IsEven().isEven;
		IVariableDeclaration n = isOddNotEven.addParameter(INT);
		IUnaryExpression e = NOT.on(new IsEven().isEven.expression(n));
		IBlock body = isOddNotEven.getBody();
		IReturn ret = body.addReturn(e);

	}
	
	
//	private static IProcedure createWithinInterval() {
//		IProcedure f = program.addProcedure("withinInterval", BOOLEAN);
//		IVariable n = f.addParameter("n", INT);
//		IVariable a = f.addParameter("a", INT);
//		IVariable b = f.addParameter("b", INT);
//		IBinaryExpression e = AND.on(GREATER_EQ.on(n, a), SMALLER_EQ.on(n, b));
//		f.getBody().addReturn(e);
//		return f;
//	}
//	
//	@Test
//	public void testWithinInterval() {
//		IProgramState state = IMachine.create(program);
//		IExecutionData dataTrue = state.execute(withinIntervalFunc, "6", "4", "8");
//		assertTrueValue(dataTrue.getReturnValue());
//		
//		IExecutionData dataFalse = state.execute(withinIntervalFunc, "6", "7", "10");
//		assertFalseValue(dataFalse.getReturnValue());
//	}
//	
//	private static IProcedure createOutsideInterval() {
//		IProcedure f = program.addProcedure("ousideInterval", BOOLEAN);
//		IVariable n = f.addParameter("n", INT);
//		IVariable a = f.addParameter("a", INT);
//		IVariable b = f.addParameter("b", INT);
//		IBinaryExpression e = OR.on(SMALLER.on(n, a), GREATER.on(n, b));
//		f.getBody().addReturn(e);
//		return f;
//	}
//	
//	@Test
//	public void testOutsideInterval() {
//		IProgramState state = IMachine.create(program);
//		IExecutionData dataTrue = state.execute(outsideIntervalFunc, "3", "4", "8");
//		assertTrueValue(dataTrue.getReturnValue());
//		
//		IExecutionData dataFalse = state.execute(outsideIntervalFunc, "7", "7", "10");
//		assertFalseValue(dataFalse.getReturnValue());
//	}
	
}
