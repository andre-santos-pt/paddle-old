package pt.iscte.paddle.tests.toce;
import static org.junit.Assert.assertEquals;
import static pt.iscte.paddle.asg.IOperator.IDIV;
import static pt.iscte.paddle.asg.IOperator.SMALLER;
import static pt.iscte.paddle.asg.IOperator.SUB;
import static pt.iscte.paddle.asg.IType.INT;
import static pt.iscte.paddle.asg.IType.VOID;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILiteral;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProcedureCall;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestInvert extends BaseTest {
	private IProcedure swap = importProcedure(TestSwap.class, "swap");
	
	IProcedure invert = module.addProcedure(VOID);
	IVariable array = invert.addParameter(INT.array().reference());
	
	IBlock body = invert.getBody();
	IVariable i = body.addVariable(INT, INT.literal(0));
	ILoop loop = body.addLoop(SMALLER.on(i, IDIV.on(array.length(), INT.literal(2))));
	IProcedureCall swapCall = loop.addCall(swap, array, i, SUB.on(SUB.on(array.length(), INT.literal(1)), i));
	IVariableAssignment iInc = loop.addIncrement(i);
	
	private IVariable aEven;
	private IVariable aOdd;

	private int[] integers = {-2, 0, 1, 4, 5, 8, 10, 11, 20, 23};
	private ILiteral[] literals = literalIntArray(integers);
	
	protected IProcedure main() {
		IProcedure test = module.addProcedure(VOID);
		IBlock body = test.getBody();
		
		aEven = body.addVariable(INT.array().reference(), INT.array().heapAllocation(INT.literal(literals.length)));
		for(int i = 0; i < literals.length; i++)
			body.addArrayElementAssignment(aEven, literals[i], INT.literal(i));
		
		aOdd = body.addVariable(INT.array().reference(), INT.array().heapAllocation(INT.literal(literals.length-1)));
		for(int i = 0; i < literals.length-1; i++)
			body.addArrayElementAssignment(aOdd, literals[i], INT.literal(i));

		body.addCall(invert, aEven);
		body.addCall(invert, aOdd);
		return test;
	}
	
	

	@Case
	public void test(IExecutionData data) {
		IArray even = (IArray) data.getVariableValue(aEven);
		for(int i = 0; i < integers.length; i++)
			assertEquals(new Integer(integers[i]), new Integer(even.getElement(integers.length-1-i).toString()));
		
		IArray odd = (IArray) data.getVariableValue(aOdd);
		for(int i = 0; i < integers.length - 1; i++)
			assertEquals(new Integer(integers[i]), new Integer(odd.getElement(integers.length-2-i).toString()));
	}
	
}
