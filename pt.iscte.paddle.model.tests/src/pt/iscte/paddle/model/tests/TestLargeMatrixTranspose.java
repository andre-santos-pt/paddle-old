package pt.iscte.paddle.model.tests;

import static org.junit.Assert.assertEquals;
import static pt.iscte.paddle.model.IOperator.DIFFERENT;
import static pt.iscte.paddle.model.IType.INT;

import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.interpreter.IReference;
import pt.iscte.paddle.model.IArrayElementAssignment;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;

// TODO large matrix
public class TestLargeMatrixTranspose extends BaseTest {
	IProcedure transpose = module.addProcedure(INT.array2D().reference());
	IVariableDeclaration matrix = transpose.addParameter(INT.array2D().reference()); 
	IBlock body = transpose.getBody();
	IVariableDeclaration t = body.addVariable(INT.array2D().reference(), INT.array2D().heapAllocation(matrix.length(INT.literal(0)), matrix.length()));
	IVariableDeclaration i = body.addVariable(INT, INT.literal(0));
	ILoop outLoop = body.addLoop(DIFFERENT.on(i, t.length()));
	IVariableDeclaration j = outLoop.addVariable(INT, INT.literal(0));
	ILoop inLoop = outLoop.addLoop(DIFFERENT.on(j, t.length(i)));
	IArrayElementAssignment ass = inLoop.addArrayElementAssignment(t, matrix.element(j, i), i, j);
	IVariableAssignment jInc = inLoop.addIncrement(j);
	IVariableAssignment iInc = outLoop.addIncrement(i);
	IReturn ret = body.addReturn(t);
	
	private IVariableDeclaration m;

	
	public IProcedure main() {
		IProcedure main = module.addProcedure(INT.array2D().reference());
		IBlock body = main.getBody();
		m = body.addVariable(INT.array2D());
		body.addAssignment(m, INT.array2D().stackAllocation(INT.literal(2), INT.literal(3)));
	
		body.addArrayElementAssignment(m, INT.literal(1), INT.literal(0), INT.literal(0));
		body.addArrayElementAssignment(m, INT.literal(2), INT.literal(0), INT.literal(1));
		body.addArrayElementAssignment(m, INT.literal(3), INT.literal(0), INT.literal(2));
		
		body.addArrayElementAssignment(m, INT.literal(4), INT.literal(1), INT.literal(0));
		body.addArrayElementAssignment(m, INT.literal(5), INT.literal(1), INT.literal(1));
		body.addArrayElementAssignment(m, INT.literal(6), INT.literal(1), INT.literal(2));
		
		body.addReturn(transpose.call(m.address()));
		return main;
	}
	
	@Case
	public void test(IExecutionData data) {
		IArray matrix = (IArray) ((IReference) data.getReturnValue()).getTarget();
		IArray r0 = (IArray) matrix.getElement(0);
		IArray r1 = (IArray) matrix.getElement(1);
		IArray r2 = (IArray) matrix.getElement(2);
		
		assertEquals(2, r0.getLength());
		assertEquals(2, r1.getLength());
		assertEquals(2, r2.getLength());
		
		equal(1, r0.getElement(0));
		equal(4, r0.getElement(1));
		
		equal(2, r1.getElement(0));
		equal(5, r1.getElement(1));
		
		equal(3, r2.getElement(0));
		equal(6, r2.getElement(1));
	}
}
