package pt.iscte.paddle.tests.toce;

import static pt.iscte.paddle.asg.IOperator.ADD;
import static pt.iscte.paddle.asg.IOperator.SMALLER_EQ;
import static pt.iscte.paddle.asg.IType.INT;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestSumEven extends BaseTest {

	IProcedure sumEven = module.addProcedure(INT);
	IVariable from = sumEven.addParameter(INT);
	IVariable to = sumEven.addParameter(INT);
	IBlock body = sumEven.getBody();
	IVariable sum = body.addVariable(INT);
	IVariableAssignment sumAss = body.addAssignment(sum, INT.literal(0));
	IVariable i = body.addVariable(INT);
	IVariableAssignment iAss = body.addAssignment(i, from);
	ILoop loop = body.addLoop(SMALLER_EQ.on(i, to));
	IVariableAssignment sumAss_ = loop.addAssignment(sum, ADD.on(sum, i));
	IVariableAssignment iAss_ = loop.addAssignment(i, ADD.on(i, INT.literal(2)));
	IReturn ret = body.addReturn(sum);
}
