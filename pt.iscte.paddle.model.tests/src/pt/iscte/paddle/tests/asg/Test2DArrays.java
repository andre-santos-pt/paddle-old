package pt.iscte.paddle.tests.asg;

import static org.junit.Assert.assertEquals;
import static pt.iscte.paddle.model.IOperator.ADD;
import static pt.iscte.paddle.model.IOperator.DIFFERENT;
import static pt.iscte.paddle.model.IOperator.EQUAL;
import static pt.iscte.paddle.model.IType.BOOLEAN;
import static pt.iscte.paddle.model.IType.INT;

import java.math.BigDecimal;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IArrayAllocation;
import pt.iscte.paddle.model.IArrayElementAssignment;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.tests.BaseTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	Test2DArrays.IdMatrix.class,
	Test2DArrays.NatMatrix.class,
	Test2DArrays.ContainsNinMatrix.class
})

// TODO Test 3D
public class Test2DArrays extends BaseTest {

	public static class IdMatrix extends BaseTest {
		IProcedure idMatrix = module.addProcedure(INT.array2D());
		IVariableDeclaration n = idMatrix.addParameter(INT);		
		IBlock body = idMatrix.getBody();
		IVariableDeclaration id = body.addVariable(INT.array2D());
		IVariableAssignment assignment = body.addAssignment(id, INT.array2D().stackAllocation(n, n));
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

	public static class NatMatrix extends BaseTest {
		IProcedure naturalsMatrix = module.addProcedure(INT.array2D());
		IVariableDeclaration lines = naturalsMatrix.addParameter(INT);
		IVariableDeclaration cols = naturalsMatrix.addParameter(INT);
		
		IBlock body = naturalsMatrix.getBody();
		
		IArrayAllocation allocation = INT.array2D().stackAllocation(lines, cols);
		IVariableDeclaration m = body.addVariable(INT.array2D(), allocation);
		IVariableDeclaration i = body.addVariable(INT, INT.literal(0));
	
		IVariableDeclaration n = body.addVariable(INT, INT.literal(1));
		
		IExpression outerGuard = DIFFERENT.on(i, lines);
		ILoop outLoop = body.addLoop(outerGuard);
		IVariableDeclaration j = outLoop.addVariable(INT);
		IVariableAssignment ass1 = outLoop.addAssignment(j, INT.literal(0));
		IExpression innerGuard = DIFFERENT.on(j, cols);
		ILoop inLoop = outLoop.addLoop(innerGuard);
		IArrayElementAssignment ass2 = inLoop.addArrayElementAssignment(m, n, i, j);
		IVariableAssignment ass3 = inLoop.addAssignment(j, ADD.on(j, INT.literal(1)));
		IVariableAssignment ass4 = inLoop.addAssignment(n, ADD.on(n, INT.literal(1)));
		
		IVariableAssignment ass5 = outLoop.addAssignment(i, ADD.on(i, INT.literal(1)));
		IReturn ret = body.addReturn(m);
		
		@Case({"2","4"})
		public void testNatMatrix(IExecutionData data) {
			final int L = 2;
			final int C = 4;
			IArray returnValue = (IArray) data.getReturnValue();
			assertEquals(L, returnValue.getLength());
			int n = 1;
			for(int i = 0; i < L; i++) {
				IArray line = (IArray) returnValue.getElement(i);
				assertEquals(C, line.getLength());
				for(int j = 0; j < C; j++)
					assertEquals(new BigDecimal(n++), line.getElement(j).getValue());
			}
		}
	}

	public static class ContainsNinMatrix extends BaseTest {
		IProcedure contains = module.addProcedure(BOOLEAN);
		IVariableDeclaration matrix = (IVariableDeclaration) contains.addParameter(INT.array2D());
		IVariableDeclaration n = contains.addParameter(INT);
		IBlock body = contains.getBody();
		IVariableDeclaration i = body.addVariable(INT, INT.literal(0));
		IExpression outerGuard = DIFFERENT.on(i, matrix.length());
		ILoop outerLoop = body.addLoop(outerGuard);
		IVariableDeclaration j = outerLoop.addVariable(INT);
		IVariableAssignment ass1 = outerLoop.addAssignment(j, INT.literal(0));
		IExpression innerGuard = DIFFERENT.on(j, matrix.length(i) );
		ILoop innerLoop = outerLoop.addLoop(innerGuard);
		ISelection ifEq = innerLoop.addSelection(EQUAL.on(matrix.element(i, j), n));
		IReturn ret1 = ifEq.addReturn(BOOLEAN.literal(true));
		IVariableAssignment inc1 = innerLoop.addIncrement(j);
		IVariableAssignment inc2 = outerLoop.addIncrement(i);
		IReturn ret2 = body.addReturn(BOOLEAN.literal(false));
		
		
		IProcedure main = module.addProcedure(BOOLEAN);
		IBlock mainBody = main.getBody();
		IVariableDeclaration array = mainBody.addVariable(INT.array2D());
		IVariableAssignment ass2 = mainBody.addAssignment(array, INT.array2D().stackAllocation(INT.literal(3)));
		IArrayElementAssignment ass3 = mainBody.addArrayElementAssignment(array, INT.array().stackAllocation(INT.literal(0)), INT.literal(0));
		IArrayElementAssignment ass4 = mainBody.addArrayElementAssignment(array, INT.array().stackAllocation(INT.literal(2)), INT.literal(1));
		IArrayElementAssignment ass5 = mainBody.addArrayElementAssignment(array, INT.array().stackAllocation(INT.literal(4)), INT.literal(2));
		IArrayElementAssignment ass6 = mainBody.addArrayElementAssignment(array, INT.literal(5), INT.literal(2), INT.literal(2));
		IVariableDeclaration var = mainBody.addVariable(BOOLEAN);
		IVariableAssignment ass7 = mainBody.addAssignment(var, contains.expression(array, INT.literal(5)));
		IReturn ret3 = mainBody.addReturn(var);
		
		@Case
		public void test(IExecutionData data) {
			isTrue(data.getReturnValue());
		}
	}
}
