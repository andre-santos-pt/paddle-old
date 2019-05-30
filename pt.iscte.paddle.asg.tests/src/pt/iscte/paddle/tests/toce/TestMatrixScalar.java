package pt.iscte.paddle.tests.toce;

import static pt.iscte.paddle.asg.IOperator.DIFFERENT;
import static pt.iscte.paddle.asg.IOperator.MUL;
import static pt.iscte.paddle.asg.IType.INT;
import static pt.iscte.paddle.asg.IType.VOID;

import pt.iscte.paddle.asg.IArrayElementAssignment;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
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
}
