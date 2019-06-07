package pt.iscte.paddle.tests.toce;
import static org.junit.Assert.assertEquals;
import static pt.iscte.paddle.asg.IOperator.EQUAL;
import static pt.iscte.paddle.asg.IOperator.SMALLER;
import static pt.iscte.paddle.asg.IType.INT;
import static pt.iscte.paddle.asg.IType.VOID;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILiteral;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.ISelection;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestArrayCount extends BaseTest {

	IProcedure count = module.addProcedure(INT);
	IVariable array = count.addParameter(INT.array().reference());
	IVariable e = count.addParameter(INT);
	IBlock body = count.getBody();
	IVariable c = body.addVariable(INT);
	IVariableAssignment cAss = body.addAssignment(c, INT.literal(0));
	IVariable i = body.addVariable(INT);
	IVariableAssignment iAss = body.addAssignment(i, INT.literal(0));
	ILoop loop = body.addLoop(SMALLER.on(i, array.length()));
	ISelection ifstat = loop.addSelection(EQUAL.on(array.element(i), e));
	IVariableAssignment cAss_ = ifstat.addIncrement(c);
	IVariableAssignment iInc = loop.addIncrement(i);
	IReturn ret = body.addReturn(c);

	private IVariable a;

	private int element = 3;
	private int[] integers = {-2, element, 1, 4, 5, element, 10, 11, 20, element};
	private ILiteral[] literals = literalIntArray(integers);

	protected IProcedure main() {
		IProcedure test = module.addProcedure(INT);
		IBlock body = test.getBody();
		a = body.addVariable(INT.array().reference(), INT.array().heapAllocation(INT.literal(literals.length)));

		for(int i = 0; i < literals.length; i++)
			body.addArrayElementAssignment(a, literals[i], INT.literal(i));

		body.addReturn(count.call(a, INT.literal(element)));
		return test;
	}



	@Case
	public void test(IExecutionData data) {
		equal(element, data.getReturnValue());
		IArray array = (IArray) data.getVariableValue(a);
		for(int i = 0; i < integers.length; i++)
			assertEquals(new Integer(integers[i]), new Integer(array.getElement(i).toString()));
	}
}
