package pt.iscte.paddle.tests.asg;
import static org.junit.Assert.assertEquals;
import static pt.iscte.paddle.asg.IDataType.INT;
import static pt.iscte.paddle.asg.ILiteral.literal;
import static pt.iscte.paddle.asg.IOperator.ADD;
import static pt.iscte.paddle.asg.IOperator.DIFFERENT;

import pt.iscte.paddle.asg.IArrayAllocation;
import pt.iscte.paddle.asg.IArrayElementAssignment;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.machine.IExecutionData;

public class TestSum extends BaseTest {

	IProcedure sum = module.addProcedure(INT);
	IVariable v = sum.addParameter(INT.array().reference());
	IBlock sumBody = sum.getBody();
	IVariable s = sumBody.addVariable(INT, literal(0));
	IVariable i = sumBody.addVariable(INT, literal(0));
	ILoop loop = sumBody.addLoop(DIFFERENT.on(i, v.valueOf().arrayLength()));
	IVariableAssignment ass1 = loop.addAssignment(s, ADD.on(s, v.valueOf().arrayElement(i)));
	IVariableAssignment ass2 = loop.addIncrement(i);
	IReturn ret = sumBody.addReturn(s);
	
	IProcedure main = module.addProcedure(INT);
	IBlock mainBody = main.getBody();
	IArrayAllocation allocation = INT.array().allocation(literal(3));
	IVariable test = mainBody.addVariable(INT.array(), allocation);
	IArrayElementAssignment ass3 = mainBody.addArrayElementAssignment(test, literal(5), literal(0));
	IArrayElementAssignment ass4 = mainBody.addArrayElementAssignment(test, literal(7), literal(1));
	IArrayElementAssignment ass5 = mainBody.addArrayElementAssignment(test, literal(9), literal(2));
	IVariable result = mainBody.addVariable(INT, sum.call(test.address()));

	@Case
	public void test(IExecutionData data) {
		assertEquals(2, data.getCallStackDepth());
		equal(21, data.getVariableValue(result));
	}
}
