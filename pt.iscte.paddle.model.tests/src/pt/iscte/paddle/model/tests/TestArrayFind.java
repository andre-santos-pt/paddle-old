package pt.iscte.paddle.model.tests;
import static pt.iscte.paddle.model.IOperator.AND;
import static pt.iscte.paddle.model.IOperator.EQUAL;
import static pt.iscte.paddle.model.IOperator.NOT;
import static pt.iscte.paddle.model.IOperator.SMALLER;
import static pt.iscte.paddle.model.IType.BOOLEAN;
import static pt.iscte.paddle.model.IType.INT;

import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;

public class TestArrayFind extends BaseTest {

	IProcedure exists = module.addProcedure(BOOLEAN);
	IVariableDeclaration array = exists.addParameter(INT.array().reference());
	IVariableDeclaration e = exists.addParameter(INT);
	IBlock body = exists.getBody();
	IVariableDeclaration found = body.addVariable(BOOLEAN);
	IVariableAssignment foundAss = body.addAssignment(found, BOOLEAN.literal(false));
	IVariableDeclaration i = body.addVariable(INT);
	IVariableAssignment iAss = body.addAssignment(i, INT.literal(0));
	ILoop loop = body.addLoop(AND.on(NOT.on(found), SMALLER.on(i, array.length())));
	ISelection ifstat = loop.addSelection(EQUAL.on(array.element(i), e));
	IVariableAssignment foundAss_ = ifstat.addAssignment(found, BOOLEAN.literal(true));
	IVariableAssignment iInc = loop.addIncrement(i);
	IReturn ret = body.addReturn(found);
	
	
	protected IProcedure main() {
		IProcedure test = module.addProcedure(BOOLEAN);
		IVariableDeclaration e = test.addParameter(INT);
		IBlock body = test.getBody();
		IVariableDeclaration a = body.addVariable(INT.array().reference(), INT.array().heapAllocation(INT.literal(10)));
		body.addArrayElementAssignment(a, INT.literal(3), INT.literal(0));
		body.addArrayElementAssignment(a, INT.literal(1), INT.literal(1));
		body.addArrayElementAssignment(a, INT.literal(4), INT.literal(3));
		body.addArrayElementAssignment(a, INT.literal(-2), INT.literal(4));
		body.addArrayElementAssignment(a, INT.literal(30), INT.literal(5));
		body.addArrayElementAssignment(a, INT.literal(10), INT.literal(6));
		body.addArrayElementAssignment(a, INT.literal(4), INT.literal(7));
		body.addArrayElementAssignment(a, INT.literal(-2), INT.literal(8));
		body.addReturn(exists.expression(a, e));
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
