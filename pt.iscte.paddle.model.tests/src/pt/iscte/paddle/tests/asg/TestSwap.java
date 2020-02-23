package pt.iscte.paddle.tests.asg;
import static pt.iscte.paddle.model.IType.INT;
import static pt.iscte.paddle.model.IType.VOID;

import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IArrayElementAssignment;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProcedureCall;
import pt.iscte.paddle.model.IVariable;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.tests.BaseTest;

public class TestSwap extends BaseTest {

	IProcedure swap = module.addProcedure(VOID);
	IVariable v = swap.addParameter(INT.array().reference());
	IVariable i = swap.addParameter(INT);
	IVariable j = swap.addParameter(INT);
	
	IBlock body = swap.getBody();
	IVariable t = body.addVariable(INT, v.dereference().element(i));
	IArrayElementAssignment ass = body.addArrayElementAssignment(v.dereference(), v.dereference().element(j), i);
	IArrayElementAssignment ass0 = body.addArrayElementAssignment(v.dereference(), t, j);
	
	IProcedure main = module.addProcedure(VOID);
	IBlock mBody = main.getBody();
	IVariable array = mBody.addVariable(INT.array());
	IVariableAssignment ass1 = mBody.addAssignment(array, INT.array().stackAllocation(INT.literal(3)));

	IArrayElementAssignment ass2 = mBody.addArrayElementAssignment(array, INT.literal(5), INT.literal(0));
	IArrayElementAssignment ass3 = mBody.addArrayElementAssignment(array, INT.literal(7), INT.literal(1));
	IArrayElementAssignment ass4 = mBody.addArrayElementAssignment(array, INT.literal(9), INT.literal(2));
	IProcedureCall call = mBody.addCall(swap, array.address(), INT.literal(0), INT.literal(2));
	
	@Case
	public void testSwap(IExecutionData data) {
		IArray array = (IArray) data.getVariableValue(this.array);
		System.out.println(array);
		equal(9, array.getElement(0));
		equal(5, array.getElement(2));
	}
}
