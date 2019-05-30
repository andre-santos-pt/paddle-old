package pt.iscte.paddle.tests.toce;
import static pt.iscte.paddle.asg.IOperator.ADD;
import static pt.iscte.paddle.asg.IOperator.DIFFERENT;
import static pt.iscte.paddle.asg.IType.*;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestSum extends BaseTest {

	IProcedure summation = module.addProcedure(DOUBLE);
	IVariable array = summation.addParameter(DOUBLE.array().reference());
	IBlock sumBody = summation.getBody();
	IVariable sum = sumBody.addVariable(DOUBLE, DOUBLE.literal(0.0));
	IVariable i = sumBody.addVariable(INT, INT.literal(0));
	ILoop loop = sumBody.addLoop(DIFFERENT.on(i, array.dereference().length()));
	IVariableAssignment ass1 = loop.addAssignment(sum, ADD.on(sum, array.dereference().element(i)));
	IVariableAssignment ass2 = loop.addIncrement(i);
	IReturn ret = sumBody.addReturn(sum);
}
