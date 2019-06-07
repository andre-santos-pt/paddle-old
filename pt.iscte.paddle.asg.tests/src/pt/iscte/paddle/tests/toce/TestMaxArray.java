package pt.iscte.paddle.tests.toce;
import static pt.iscte.paddle.asg.IOperator.GREATER;
import static pt.iscte.paddle.asg.IOperator.SMALLER;
import static pt.iscte.paddle.asg.IType.DOUBLE;
import static pt.iscte.paddle.asg.IType.INT;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.ISelection;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestMaxArray extends BaseTest {

	IProcedure max = module.addProcedure(INT);
	IVariable array = max.addParameter(INT.array().reference());
	IBlock body = max.getBody();
	IVariable m = body.addVariable(INT);
	IVariableAssignment mAss = body.addAssignment(m, array.element(INT.literal(0)));
	IVariable i = body.addVariable(INT);
	IVariableAssignment iAss = body.addAssignment(i, INT.literal(1));
	ILoop loop = body.addLoop(SMALLER.on(i, array.length()));
	ISelection ifstat = loop.addSelection(GREATER.on(array.element(i), m));
	IVariableAssignment mAss_ = ifstat.addAssignment(m, array.element(i));
	IVariableAssignment iInc = loop.addIncrement(i);
	IReturn ret = body.addReturn(m);
	
	
	protected IProcedure main() {
		IProcedure test = module.addProcedure(INT);
		IBlock body = test.getBody();
		IVariable a = body.addVariable(INT.array().reference(), INT.array().heapAllocation(INT.literal(5)));
		IVariable zero = body.addVariable(INT, INT.literal(0));
		body.addArrayElementAssignment(a, INT.literal(3), zero);
		body.addArrayElementAssignment(a, INT.literal(1), INT.literal(1));
		body.addArrayElementAssignment(a, INT.literal(4), INT.literal(3));
		body.addArrayElementAssignment(a, INT.literal(-2), INT.literal(4));
		body.addReturn(max.call(a));
		return test;
	}
	
	@Case
	public void test(IExecutionData data) {
		equal(4, data.getReturnValue());
	}
	
}
