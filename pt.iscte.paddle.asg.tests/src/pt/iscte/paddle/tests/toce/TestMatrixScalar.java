package pt.iscte.paddle.tests.toce;

import static pt.iscte.paddle.asg.IOperator.DIFFERENT;
import static pt.iscte.paddle.asg.IOperator.MUL;
import static pt.iscte.paddle.asg.IType.BOOLEAN;
import static pt.iscte.paddle.asg.IType.INT;
import static pt.iscte.paddle.asg.IType.VOID;

import pt.iscte.paddle.asg.IArrayElementAssignment;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.machine.IValue;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestMatrixScalar extends BaseTest {
	IProcedure scale = module.addProcedure(VOID);
	IVariable matrix = scale.addParameter(INT.array2D());
	IVariable s = scale.addParameter(INT);
	IBlock body = scale.getBody();
	IVariable i = body.addVariable(INT, INT.literal(0));
	ILoop outLoop = body.addLoop(DIFFERENT.on(i, matrix.length()));
	IVariable j = outLoop.addVariable(INT, INT.literal(0));
	ILoop inLoop = outLoop.addLoop(DIFFERENT.on(j, matrix.length(i)));
	IArrayElementAssignment ass = inLoop.addArrayElementAssignment(matrix, MUL.on(matrix.element(i, j), s), i, j);
	IVariableAssignment jInc = inLoop.addIncrement(j);
	IVariableAssignment iInc = outLoop.addIncrement(i);
	
	private IVariable m;
	
	public IProcedure main() {
		IProcedure main = module.addProcedure(VOID);
		IBlock mainBody = main.getBody();
		m = mainBody.addVariable(INT.array2D());
		mainBody.addAssignment(m, INT.array2D().stackAllocation(INT.literal(3)));
		mainBody.addArrayElementAssignment(m, INT.array().stackAllocation(INT.literal(0)), INT.literal(0));
		mainBody.addArrayElementAssignment(m, INT.array().stackAllocation(INT.literal(2)), INT.literal(1));
		mainBody.addArrayElementAssignment(m, INT.array().stackAllocation(INT.literal(4)), INT.literal(2));
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
