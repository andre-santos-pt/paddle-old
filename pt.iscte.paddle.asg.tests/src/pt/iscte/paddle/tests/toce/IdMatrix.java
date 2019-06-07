package pt.iscte.paddle.tests.toce;

import static org.junit.Assert.assertEquals;

import pt.iscte.paddle.asg.IArrayElementAssignment;
import pt.iscte.paddle.asg.IBlock;
import static pt.iscte.paddle.asg.IOperator.*;
import static pt.iscte.paddle.asg.IType.*;

import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.tests.asg.BaseTest;

public class IdMatrix extends BaseTest {
	IProcedure idMatrix = module.addProcedure(INT.array2D());
	IVariable n = idMatrix.addParameter(INT);		
	IBlock body = idMatrix.getBody();
	IVariable id = body.addVariable(INT.array2D());
	IVariableAssignment assignment = body.addAssignment(id, INT.array2D().stackAllocation(n, n));
	IVariable i = body.addVariable(INT);
	IVariableAssignment iInit = body.addAssignment(i, INT.literal(0));
	IExpression e = DIFFERENT.on(i, n);
	ILoop loop = body.addLoop(e);
	IArrayElementAssignment ass2 = loop.addArrayElementAssignment(id, INT.literal(1), i, i);
	IVariableAssignment ass3 = loop.addAssignment(i, ADD.on(i, INT.literal(1)));
	IReturn ret = body.addReturn(id);

	@Case("4")
	public void test(IExecutionData data) {
		final int N = 4;
		IArray returnValue = (IArray) data.getReturnValue();
		assertEquals(N, returnValue.getLength());
		for(int i = 0; i < N; i++) {
			IArray line = (IArray) returnValue.getElement(i);
			assertEquals(N, line.getLength());
			for(int j = 0; j < N; j++)
				equal(i == j ? 1 : 0, line.getElement(j));
		}
	}
}