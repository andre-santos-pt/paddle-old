package pt.iscte.paddle.tests.asg;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static pt.iscte.paddle.model.IOperator.ADD;
import static pt.iscte.paddle.model.IOperator.SMALLER;
import static pt.iscte.paddle.model.IType.INT;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IArrayElementAssignment;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.tests.BaseTest;
import pt.iscte.paddle.tests.asg.TestArrays.Naturals;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	Naturals.class
})
public class TestArrays {

	public static class Naturals extends BaseTest {
		IProcedure naturals = module.addProcedure(INT.array());
		IVariableDeclaration n = naturals.addParameter(INT);
		IBlock body = naturals.getBody();
		IVariableDeclaration v = body.addVariable(INT.array());
		IVariableAssignment ass1 = body.addAssignment(v, INT.array().heapAllocation(n));
		IVariableDeclaration i = body.addVariable(INT, INT.literal(0));
		ILoop loop = body.addLoop(SMALLER.on(i, n));
		IArrayElementAssignment ass2 = loop.addArrayElementAssignment(v, ADD.on(i, INT.literal(1)), i);
		IVariableAssignment ass3 = loop.addAssignment(i, ADD.on(i, INT.literal(1)));
		IReturn addReturn = body.addReturn(v);
		
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
				equal(x+1, array.getElement(x));
		}
		
		@Case("0")
		public void zeroLength(IExecutionData data) {
			IArray array = (IArray) data.getReturnValue();
			assertEquals(0, array.getLength());
		}
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
