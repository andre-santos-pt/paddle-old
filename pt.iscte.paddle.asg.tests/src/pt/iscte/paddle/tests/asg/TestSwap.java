package pt.iscte.paddle.tests.asg;
import static pt.iscte.paddle.asg.IType.INT;
import static pt.iscte.paddle.asg.IType.VOID;

import pt.iscte.paddle.asg.IArrayElementAssignment;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProcedureCall;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.IExecutionData;

public class TestSwap extends BaseTest {

	IProcedure swap = module.addProcedure(VOID);
	IVariable v = (IVariable) swap.addParameter(INT.array().reference());
	IVariable i = swap.addParameter(INT);
	IVariable j = swap.addParameter(INT);
	
	IBlock swapBody = swap.getBody();
	IVariable t = swapBody.addVariable(INT, v.value().element(i));
	IArrayElementAssignment ass = swapBody.addArrayElementAssignment(v.value(), v.value().element(j), i);
	IArrayElementAssignment ass0 = swapBody.addArrayElementAssignment(v.value(), t, j);
	
	IProcedure main = module.addProcedure(INT);
	IBlock body = main.getBody();
	IVariable array = body.addVariable(INT.array());
	IVariableAssignment ass1 = body.addAssignment(array, INT.array().allocation(INT.literal(3)));

	IArrayElementAssignment ass2 = body.addArrayElementAssignment(array, INT.literal(5), INT.literal(0));
	IArrayElementAssignment ass3 = body.addArrayElementAssignment(array, INT.literal(7), INT.literal(1));
	IArrayElementAssignment ass4 = body.addArrayElementAssignment(array, INT.literal(9), INT.literal(2));
	IProcedureCall call = body.addCall(swap, array.address(), INT.literal(0), INT.literal(2));
	
	@Case
	public void testSwap(IExecutionData data) {
		IArray array = (IArray) data.getVariableValue(this.array);
		System.out.println(array);
		equal(9, array.getElement(0));
		equal(5, array.getElement(2));
	}
}
