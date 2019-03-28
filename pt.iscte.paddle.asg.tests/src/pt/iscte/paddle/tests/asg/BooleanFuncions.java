package pt.iscte.paddle.tests.asg;
import static org.junit.Assert.assertTrue;
import static pt.iscte.paddle.asg.IDataType.BOOLEAN;
import static pt.iscte.paddle.asg.IDataType.INT;
import static pt.iscte.paddle.asg.ILiteral.literal;
import static pt.iscte.paddle.asg.IOperator.DIFFERENT;
import static pt.iscte.paddle.asg.IOperator.EQUAL;
import static pt.iscte.paddle.asg.IOperator.MOD;
import static pt.iscte.paddle.asg.IOperator.NOT;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.IUnaryExpression;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IExecutionData;
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
		IVariable n = isEven.addParameter(INT);
		IBinaryExpression e = EQUAL.on(MOD.on(n, literal(2)), literal(0));
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
		IVariable n = isOdd.addParameter(INT);
		IBinaryExpression e = DIFFERENT.on(MOD.on(n, literal(2)), literal(0));
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
		IVariable n = isOddNotEven.addParameter(INT);
		IUnaryExpression e = NOT.on(new IsEven().isEven.call(n));
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
