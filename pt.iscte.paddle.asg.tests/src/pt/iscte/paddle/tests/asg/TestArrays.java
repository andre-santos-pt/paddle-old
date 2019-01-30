package pt.iscte.paddle.tests.asg;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static pt.iscte.paddle.asg.IDataType.INT;
import static pt.iscte.paddle.asg.ILiteral.literal;
import static pt.iscte.paddle.asg.IOperator.ADD;
import static pt.iscte.paddle.asg.IOperator.SMALLER;

import java.math.BigDecimal;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.machine.IMachine;
import pt.iscte.paddle.machine.IProgramState;
import pt.iscte.paddle.machine.IValue;
import pt.iscte.paddle.tests.asg.TestArrays.Naturals;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	Naturals.class
})
public class TestArrays {

	public static class Naturals extends BaseTest {
		protected IProcedure defineProcedure(IModule module) {
			IProcedure natFunc = module.addProcedure("naturals", INT.array());
			IVariable n = natFunc.addParameter("n", INT);
			IBlock body = natFunc.getBody();
			IVariable v = body.addVariable("v", INT.array());
			body.addAssignment(v, INT.array().allocation(n));
			IVariable i = body.addVariable("i", INT, literal(0));
			ILoop loop = body.addLoop(SMALLER.on(i, n));
			loop.addArrayElementAssignment(v, ADD.on(i, literal(1)), i);
			loop.addAssignment(i, ADD.on(i, literal(1)));
			body.addReturn(v);
			return natFunc;
		}
		
		protected void commonAsserts(IExecutionData data) {
			IValue returnValue = data.getReturnValue();
			assertNotEquals(IValue.NULL, returnValue);
			assertTrue(returnValue instanceof IArray);
		}
		
		@Case("5")
		public void lengthFive(IExecutionData data) {
			IArray array = (IArray) data.getReturnValue();
			assertEquals(5, array.getLength());
			for(int x = 0; x < 5; x++)
				assertEqual(x+1, array.getElement(x));
		}
		
		@Case("0")
		public void zeroLength(IExecutionData data) {
			IArray array = (IArray) data.getReturnValue();
			assertEquals(0, array.getLength());
		}
	}

//	@Test
	public void testNaturals() {
		IModule program = IModule.create("Arrays");

		IProcedure natFunc = program.addProcedure("naturals", INT.array());
		IVariable n = natFunc.addParameter("n", INT);
		IBlock body = natFunc.getBody();
		IVariable v = body.addVariable("v", INT.array(), INT.array().allocation(n));
		IVariable i = body.addVariable("i", INT);
		ILoop loop = body.addLoop(SMALLER.on(i, n));
		loop.addArrayElementAssignment(v, ADD.on(i, literal(1)), i);
		loop.addAssignment(i, ADD.on(i, literal(1)));
		body.addReturn(v);

		System.out.println(program);

		IProgramState state = IMachine.create(program);
		IExecutionData data = state.execute(natFunc, "5");

		assertNotEquals(IValue.NULL, data.getReturnValue());

		IArray array = (IArray) data.getReturnValue();
		for(int x = 0; x < 5; x++)
			assertEquals(new BigDecimal(x+1), array.getElement(x).getValue());
	}

	public void testContainsReturn() {

	}

	public void testContainsBreak() {

	}

	public void testReplaceContinue() {

	}

	public void testMerge() {

	}
}
