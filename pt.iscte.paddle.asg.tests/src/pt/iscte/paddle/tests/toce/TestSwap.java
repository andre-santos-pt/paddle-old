package pt.iscte.paddle.tests.toce;
import static org.junit.Assert.assertEquals;
import static pt.iscte.paddle.asg.IType.INT;
import static pt.iscte.paddle.asg.IType.VOID;

import pt.iscte.paddle.asg.IArrayElementAssignment;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILiteral;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestSwap extends BaseTest {

	IProcedure swap = module.addProcedure(VOID);
	IVariable array = swap.addParameter(INT.array().reference());
	IVariable i = swap.addParameter(INT);
	IVariable j = swap.addParameter(INT);
	
	IBlock body = swap.getBody();
	IVariable t = body.addVariable(INT, array.dereference().element(i));
	IArrayElementAssignment ass = body.addArrayElementAssignment(array.dereference(), array.dereference().element(j), i);
	IArrayElementAssignment ass0 = body.addArrayElementAssignment(array.dereference(), t, j);
	
	private IVariable a;
	
	private int[] integers = {-2, 0, 1, 4, 5, 8, 10, 11, 20, 23};
	private ILiteral[] literals = literalIntArray(integers);
	private int iParam = 3;
	private int jParam = 4;
	
	protected IProcedure main() {
		IProcedure test = module.addProcedure(VOID);
		IBlock body = test.getBody();
		a = body.addVariable(INT.array().reference(), INT.array().heapAllocation(INT.literal(literals.length)));
		
		for(int i = 0; i < literals.length; i++)
			body.addArrayElementAssignment(a, literals[i], INT.literal(i));

		body.addCall(swap, a, INT.literal(iParam), INT.literal(jParam));
		return test;
	}
	
	

	@Case
	public void test(IExecutionData data) {
		IArray array = (IArray) data.getVariableValue(a);
		System.out.println(data.getVariableValue(a));
		for(int i = 0; i < integers.length; i++)
			if(i != iParam && i != jParam)
				assertEquals(new Integer(integers[i]), new Integer(array.getElement(i).toString()));
		
		assertEquals(new Integer(integers[iParam]), new Integer(array.getElement(jParam).toString()));
		assertEquals(new Integer(integers[jParam]), new Integer(array.getElement(iParam).toString()));
	}
}
