package pt.iscte.paddle.tests.toce;
import static org.junit.Assert.assertEquals;
import static pt.iscte.paddle.asg.IOperator.ADD;
import static pt.iscte.paddle.asg.IOperator.SMALLER;
import static pt.iscte.paddle.asg.IOperator.SUB;
import static pt.iscte.paddle.asg.IType.INT;
import static pt.iscte.paddle.asg.IType.VOID;

import java.util.Arrays;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILiteral;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProcedureCall;
import pt.iscte.paddle.asg.ISelection;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestSelectionSort extends BaseTest {

	private IProcedure swap = importProcedure(TestSwap.class, "swap");
	
	IProcedure sort = module.addProcedure(VOID);
	IVariable array = sort.addParameter(INT.array().reference());
	IBlock body = sort.getBody();
	IVariable i = body.addVariable(INT, INT.literal(0));

	ILoop outloop = body.addLoop(SMALLER.on(i, SUB.on(array.length(), INT.literal(1))));
	IVariable min = outloop.addVariable(INT, i);
	IVariable j = outloop.addVariable(INT, ADD.on(i, INT.literal(1)));

	ILoop inloop = outloop.addLoop(SMALLER.on(j, array.length()));
	ISelection ifstat = inloop.addSelection(SMALLER.on(array.element(j), array.element(min)));
	IVariableAssignment minAss = ifstat.getBlock().addAssignment(min, j);
	IVariableAssignment jInc = inloop.addIncrement(j);
	IProcedureCall swapCall = outloop.addCall(swap, array, i, min);
	IVariableAssignment iInc = outloop.addIncrement(i);
	
	private IVariable a;
	
	private int[] integers = {-2, 10, 1, 0, 5, 8, 120, 211, 20, 13};
	private ILiteral[] literals = literalIntArray(integers);
	
	protected IProcedure main() {
		IProcedure test = module.addProcedure(VOID);
		IBlock body = test.getBody();
		a = body.addVariable(INT.array().reference(), INT.array().heapAllocation(INT.literal(literals.length)));
		
		for(int i = 0; i < literals.length; i++)
			body.addArrayElementAssignment(a, literals[i], INT.literal(i));

		body.addCall(sort, a);
		return test;
	}
	
	

	@Case
	public void test(IExecutionData data) {
		int[] sorted = Arrays.copyOf(integers, integers.length);
		Arrays.sort(sorted);
		IArray array = (IArray) data.getVariableValue(a);
		for(int i = 0; i < integers.length; i++)
			assertEquals(new Integer(sorted[i]), new Integer(array.getElement(i).toString()));
		
	}
}
