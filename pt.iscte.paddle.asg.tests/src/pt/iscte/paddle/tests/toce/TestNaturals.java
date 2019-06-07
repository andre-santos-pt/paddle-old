package pt.iscte.paddle.tests.toce;



import static org.junit.Assert.assertEquals;
import static pt.iscte.paddle.asg.IOperator.ADD;
import static pt.iscte.paddle.asg.IOperator.SMALLER;
import static pt.iscte.paddle.asg.IType.INT;

import pt.iscte.paddle.asg.IArrayElementAssignment;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestNaturals extends BaseTest  {
	IProcedure naturals = module.addProcedure(INT.array());
	IVariable n = naturals.addParameter(INT);
	IBlock body = naturals.getBody();
	IVariable array = body.addVariable(INT.array());
	IVariableAssignment ass1 = body.addAssignment(array, INT.array().stackAllocation(n));
	IVariable i = body.addVariable(INT, INT.literal(0));
	ILoop loop = body.addLoop(SMALLER.on(i, n));
	IArrayElementAssignment ass2 = loop.addArrayElementAssignment(array, ADD.on(i, INT.literal(1)), i);
	IVariableAssignment ass3 = loop.addAssignment(i, ADD.on(i, INT.literal(1)));
	IReturn addReturn = body.addReturn(array);
	
	@Case("0")
	public void testEmpty(IExecutionData data) {
		IArray array = (IArray) data.getReturnValue();
		assertEquals(0, array.getLength());
	}
	
	@Case("5")
	public void lengthFive(IExecutionData data) {
		IArray array = (IArray) data.getReturnValue();
		assertEquals(5, array.getLength());
		for(int x = 0; x < 5; x++)
			equal(x+1, array.getElement(x));
	}
}
