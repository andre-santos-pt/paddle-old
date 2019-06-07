package pt.iscte.paddle.tests.toce;
import static pt.iscte.paddle.asg.IOperator.ADD;
import static pt.iscte.paddle.asg.IOperator.DIFFERENT;
import static pt.iscte.paddle.asg.IType.DOUBLE;
import static pt.iscte.paddle.asg.IType.INT;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestSumCopy extends BaseTest {

	IProcedure summation = module.addProcedure(DOUBLE);
	IVariable array = summation.addParameter(DOUBLE.array());
	IBlock sumBody = summation.getBody();
	IVariable sum = sumBody.addVariable(DOUBLE, DOUBLE.literal(0.0));
	IVariable i = sumBody.addVariable(INT, INT.literal(0));
	ILoop loop = sumBody.addLoop(DIFFERENT.on(i, array.dereference().length()));
	IVariableAssignment ass1 = loop.addAssignment(sum, ADD.on(sum, array.dereference().element(i)));
	IVariableAssignment ass0 = loop.addArrayElementAssignment(array, DOUBLE.literal(0.0), i);
	IVariableAssignment ass2 = loop.addIncrement(i);
	IReturn ret = sumBody.addReturn(sum);
	
	
	
	private IVariable a;

	protected IProcedure main() {
		IProcedure test = module.addProcedure(DOUBLE);
		IBlock body = test.getBody();
		a = body.addVariable(DOUBLE.array(), DOUBLE.array().stackAllocation(INT.literal(5)));
		body.addArrayElementAssignment(a, DOUBLE.literal(2.3), INT.literal(0));
		body.addArrayElementAssignment(a, DOUBLE.literal(3.1), INT.literal(1));
		body.addArrayElementAssignment(a, DOUBLE.literal(0.1), INT.literal(3));
		body.addArrayElementAssignment(a, DOUBLE.literal(10.0), INT.literal(4));
		
		IVariable sum = body.addVariable(DOUBLE, summation.call(a));
		body.addReturn(sum);
		return test;
	}
	
	@Case
	public void test(IExecutionData data) {
		equal(15.5, data.getReturnValue());
		IArray a = (IArray) data.getVariableValue(this.a);
		equal(2.3, a.getElement(0));
		equal(3.1, a.getElement(1));
		equal(0.0, a.getElement(2));
		equal(0.1, a.getElement(3));
		equal(10.0, a.getElement(4));	
	}
}
