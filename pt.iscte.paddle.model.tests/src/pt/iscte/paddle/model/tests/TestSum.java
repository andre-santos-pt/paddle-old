package pt.iscte.paddle.model.tests;
import static pt.iscte.paddle.model.IOperator.ADD;
import static pt.iscte.paddle.model.IOperator.DIFFERENT;
import static pt.iscte.paddle.model.IType.DOUBLE;
import static pt.iscte.paddle.model.IType.INT;

import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.IVariable;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestSum extends BaseTest {

	IProcedure summation = module.addProcedure(DOUBLE);
	IVariable array = summation.addParameter(DOUBLE.array().reference());
	IBlock sumBody = summation.getBody();
	IVariable sum = sumBody.addVariable(DOUBLE, DOUBLE.literal(0.0));
	IVariable i = sumBody.addVariable(INT, INT.literal(0));
	ILoop loop = sumBody.addLoop(DIFFERENT.on(i, array.dereference().length()));
	IVariableAssignment ass1 = loop.addAssignment(sum, ADD.on(sum, array.dereference().element(i)));
	IVariableAssignment ass2 = loop.addIncrement(i);
	IReturn ret = sumBody.addReturn(sum);
	
	
	
	private IVariable a;

	protected IProcedure main() {
		IProcedure test = module 	.addProcedure(DOUBLE);
		IBlock body = test.getBody();
		a = body.addVariable(DOUBLE.array(), DOUBLE.array().stackAllocation(INT.literal(5)));
		body.addArrayElementAssignment(a, DOUBLE.literal(2.3), INT.literal(0));
		body.addArrayElementAssignment(a, DOUBLE.literal(3.1), INT.literal(1));
		body.addArrayElementAssignment(a, DOUBLE.literal(0.1), INT.literal(3));
		body.addArrayElementAssignment(a, DOUBLE.literal(10.0), INT.literal(4));
		
		IVariable sum = body.addVariable(DOUBLE, summation.call(a.address()));
		body.addReturn(sum);
		return test;
	}
	
	@Case
	public void test(IExecutionData data) {
		equal(15.5, data.getReturnValue());
		IArray a = (IArray) data.getVariableValue(this.a);
	}
}
