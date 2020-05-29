package pt.iscte.paddle.model.tests;

import static org.junit.Assert.assertEquals;
import static pt.iscte.paddle.model.IOperator.ADD;
import static pt.iscte.paddle.model.IOperator.DIFFERENT;
import static pt.iscte.paddle.model.IType.INT;

import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IArrayElementAssignment;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;

public class TestIdMatrix extends BaseTest {
	IProcedure idMatrix = module.addProcedure(INT.array2D());
	IVariableDeclaration n = idMatrix.addParameter(INT);		
	IBlock body = idMatrix.getBody();
	IVariableDeclaration id = body.addVariable(INT.array2D());
	IVariableAssignment assignment = body.addAssignment(id, INT.array2D().heapAllocation(n, n));
	IVariableDeclaration i = body.addVariable(INT);
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