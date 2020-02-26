package pt.iscte.paddle.tests.asg;
import static org.junit.Assert.assertEquals;
import static pt.iscte.paddle.model.IOperator.ADD;
import static pt.iscte.paddle.model.IOperator.DIFFERENT;
import static pt.iscte.paddle.model.IType.INT;

import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IArrayAllocation;
import pt.iscte.paddle.model.IArrayElementAssignment;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.tests.BaseTest;

public class TestSum extends BaseTest {

	IProcedure sum = module.addProcedure(INT);
	IVariableDeclaration v = sum.addParameter(INT.array().reference());
	IBlock sumBody = sum.getBody();
	IVariableDeclaration s = sumBody.addVariable(INT, INT.literal(0));
	IVariableDeclaration i = sumBody.addVariable(INT, INT.literal(0));
	ILoop loop = sumBody.addLoop(DIFFERENT.on(i, v.dereference().length()));
	IVariableAssignment ass1 = loop.addAssignment(s, ADD.on(s, v.dereference().element(i)));
	IVariableAssignment ass2 = loop.addIncrement(i);
	IReturn ret = sumBody.addReturn(s);
	
	IProcedure main = module.addProcedure(INT);
	IBlock mainBody = main.getBody();
	IArrayAllocation allocation = INT.array().stackAllocation(INT.literal(3));
	IVariableDeclaration test = mainBody.addVariable(INT.array(), allocation);
	IArrayElementAssignment ass3 = mainBody.addArrayElementAssignment(test, INT.literal(5), INT.literal(0));
	IArrayElementAssignment ass4 = mainBody.addArrayElementAssignment(test, INT.literal(7), INT.literal(1));
	IArrayElementAssignment ass5 = mainBody.addArrayElementAssignment(test, INT.literal(9), INT.literal(2));
	IVariableDeclaration result = mainBody.addVariable(INT, sum.call(test.address()));
	IReturn mainRet = mainBody.addReturn(result);
	
	@Case
	public void test(IExecutionData data) {
		assertEquals(2, data.getCallStackDepth());
		equal(21, data.getVariableValue(result));
	}
}
