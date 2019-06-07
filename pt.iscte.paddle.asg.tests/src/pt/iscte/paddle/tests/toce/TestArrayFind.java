package pt.iscte.paddle.tests.toce;
import static pt.iscte.paddle.asg.IOperator.AND;
import static pt.iscte.paddle.asg.IOperator.EQUAL;
import static pt.iscte.paddle.asg.IOperator.NOT;
import static pt.iscte.paddle.asg.IOperator.SMALLER;
import static pt.iscte.paddle.asg.IType.BOOLEAN;
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

public class TestArrayFind extends BaseTest {

	IProcedure exists = module.addProcedure(BOOLEAN);
	IVariable array = exists.addParameter(INT.array().reference());
	IVariable e = exists.addParameter(INT);
	IBlock body = exists.getBody();
	IVariable found = body.addVariable(BOOLEAN);
	IVariableAssignment foundAss = body.addAssignment(found, BOOLEAN.literal(false));
	IVariable i = body.addVariable(INT);
	IVariableAssignment iAss = body.addAssignment(i, INT.literal(0));
	ILoop loop = body.addLoop(AND.on(NOT.on(found), SMALLER.on(i, array.length())));
	ISelection ifstat = loop.addSelection(EQUAL.on(array.element(i), e));
	IVariableAssignment foundAss_ = ifstat.addAssignment(found, BOOLEAN.literal(true));
	IVariableAssignment iInc = loop.addIncrement(i);
	IReturn ret = body.addReturn(found);
	
	
	protected IProcedure main() {
		IProcedure test = module.addProcedure(BOOLEAN);
		IVariable e = test.addParameter(INT);
		IBlock body = test.getBody();
		IVariable a = body.addVariable(INT.array().reference(), INT.array().heapAllocation(INT.literal(10)));
		body.addArrayElementAssignment(a, INT.literal(3), INT.literal(0));
		body.addArrayElementAssignment(a, INT.literal(1), INT.literal(1));
		body.addArrayElementAssignment(a, INT.literal(4), INT.literal(3));
		body.addArrayElementAssignment(a, INT.literal(-2), INT.literal(4));
		body.addArrayElementAssignment(a, INT.literal(30), INT.literal(5));
		body.addArrayElementAssignment(a, INT.literal(10), INT.literal(6));
		body.addArrayElementAssignment(a, INT.literal(4), INT.literal(7));
		body.addArrayElementAssignment(a, INT.literal(-2), INT.literal(8));
		body.addReturn(exists.call(a, e));
		return test;
	}
	
	@Case("4")
	public void testTrue(IExecutionData data) {
		isTrue(data.getReturnValue());
	}
	
	@Case("3")
	public void testTrueFirst(IExecutionData data) {
		isTrue(data.getReturnValue());
	}
	
	@Case("-2")
	public void testTrueLast(IExecutionData data) {
		isTrue(data.getReturnValue());
	}
	
	@Case("5")
	public void testFalse(IExecutionData data) {
		isFalse(data.getReturnValue());
	}
}
