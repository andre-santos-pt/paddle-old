package pt.iscte.paddle.tests.toce;

import static pt.iscte.paddle.asg.IOperator.DIFFERENT;
import static pt.iscte.paddle.asg.IOperator.MUL;
import static pt.iscte.paddle.asg.IType.INT;
import static pt.iscte.paddle.asg.IType.VOID;

import pt.iscte.paddle.asg.IArrayElementAssignment;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestMatrixTranspose extends BaseTest {
	IProcedure transpose = module.addProcedure(INT.array2D()); // TODO .reference()
	IVariable matrix = transpose.addParameter(INT.array2D().reference()); 
	IBlock body = transpose.getBody();
	IVariable t = body.addVariable(INT.array2D(), INT.array2D().allocation(matrix.length(INT.literal(0)), matrix.length()));
	IVariable i = body.addVariable(INT, INT.literal(0));
	ILoop outLoop = body.addLoop(DIFFERENT.on(i, t.length()));
	IVariable j = outLoop.addVariable(INT, INT.literal(0));
	ILoop inLoop = outLoop.addLoop(DIFFERENT.on(j, t.length(i)));
	IArrayElementAssignment ass = inLoop.addArrayElementAssignment(t, matrix.element(j, i), i, j);
	IVariableAssignment jInc = inLoop.addIncrement(j);
	IVariableAssignment iInc = outLoop.addIncrement(i);
	IReturn ret = body.addReturn(t);
}
