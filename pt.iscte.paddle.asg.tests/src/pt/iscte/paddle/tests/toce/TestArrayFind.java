package pt.iscte.paddle.tests.toce;
import static pt.iscte.paddle.asg.IOperator.*;

import static pt.iscte.paddle.asg.IType.*;
import pt.iscte.paddle.asg.IArrayElement;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.ISelection;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestArrayFind extends BaseTest {

	IProcedure exists = module.addProcedure(BOOLEAN);
	IVariable array = exists.addParameter(INT.array().reference());
	IVariable e = exists.addParameter(INT);
	IBlock body = exists.getBody();
	IVariable found = body.addVariable(BOOLEAN);
	IVariableAssignment foundAss = body.addAssignment(found, BOOLEAN.literal(false));
	IVariable i = body.addVariable(INT);
	IVariableAssignment iAss = body.addAssignment(i, INT.literal(0));
	ILoop loop = body.addLoop(AND.on(NOT.on(found), SMALLER.on(i, array.length())));
	ISelection ifstat = loop.addSelection(EQUAL.on(array.element(i), e));
	IVariableAssignment foundAss_ = ifstat.addAssignment(found, BOOLEAN.literal(true));
	IVariableAssignment iInc = loop.addIncrement(i);
	IReturn ret = body.addReturn(found);
}
