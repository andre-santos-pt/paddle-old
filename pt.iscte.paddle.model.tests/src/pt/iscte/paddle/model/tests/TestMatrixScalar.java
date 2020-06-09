package pt.iscte.paddle.model.tests;

import static pt.iscte.paddle.model.IOperator.MUL;
import static pt.iscte.paddle.model.IType.INT;
import static pt.iscte.paddle.model.IType.VOID;

import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IArrayElementAssignment;
import pt.iscte.paddle.model.IBinaryOperator;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;

public class TestMatrixScalar extends BaseTest {
	IProcedure scale = module.addProcedure(VOID);
	IVariableDeclaration matrix = scale.addParameter(INT.array2D().reference());
	IVariableDeclaration s = scale.addParameter(INT);
	IBlock body = scale.getBody();
	IVariableDeclaration i = body.addVariable(INT, INT.literal(0));
	ILoop outLoop = body.addLoop(IBinaryOperator.DIFFERENT.on(i, matrix.length()));
	IVariableDeclaration j = outLoop.addVariable(INT, INT.literal(0));
	ILoop inLoop = outLoop.addLoop(IBinaryOperator.DIFFERENT.on(j, matrix.length(i)));
	IArrayElementAssignment ass = inLoop.addArrayElementAssignment(matrix, MUL.on(matrix.element(i, j), s), i, j);
	IVariableAssignment jInc = inLoop.addIncrement(j);
	IVariableAssignment iInc = outLoop.addIncrement(i);
	
	private IVariableDeclaration m;
	
	public IProcedure main() {
		IProcedure main = module.addProcedure(VOID);
		IBlock mainBody = main.getBody();
		m = mainBody.addVariable(INT.array2D().reference());
		mainBody.addAssignment(m, INT.array2D().heapAllocation(INT.literal(3)));
		mainBody.addArrayElementAssignment(m, INT.array().heapAllocation(INT.literal(0)), INT.literal(0));
		mainBody.addArrayElementAssignment(m, INT.array().heapAllocation(INT.literal(2)), INT.literal(1));
		mainBody.addArrayElementAssignment(m, INT.array().heapAllocation(INT.literal(4)), INT.literal(2));
		mainBody.addArrayElementAssignment(m, INT.literal(2), INT.literal(1), INT.literal(1));
		mainBody.addArrayElementAssignment(m, INT.literal(4), INT.literal(2), INT.literal(2));
		mainBody.addArrayElementAssignment(m, INT.literal(6), INT.literal(2), INT.literal(3));
		mainBody.addCall(scale, m, INT.literal(2));
		return main;
	}

	
	@Case
	public void test(IExecutionData data) {
		IArray matrix = (IArray) data.getVariableValue(m);
		IArray r1 = (IArray) matrix.getElement(1);
		IArray r2 = (IArray) matrix.getElement(2);
		
		equal(0, r1.getElement(0));
		equal(4, r1.getElement(1));
		
		equal(0, r2.getElement(0));
		equal(0, r2.getElement(1));
		equal(8, r2.getElement(2));
		equal(12, r2.getElement(3));
	}
	
	
}
