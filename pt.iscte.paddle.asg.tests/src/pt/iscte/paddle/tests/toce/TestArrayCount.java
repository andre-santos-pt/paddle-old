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

public class TestArrayCount extends BaseTest {

	IProcedure count = module.addProcedure(INT);
	IVariable array = count.addParameter(INT.array().reference());
	IVariable e = count.addParameter(INT);
	IBlock body = count.getBody();
	IVariable c = body.addVariable(INT);
	IVariableAssignment cAss = body.addAssignment(c, INT.literal(0));
	IVariable i = body.addVariable(INT);
	IVariableAssignment iAss = body.addAssignment(i, INT.literal(0));
	ILoop loop = body.addLoop(SMALLER.on(i, array.length()));
	ISelection ifstat = loop.addSelection(EQUAL.on(array.element(i), e));
	IVariableAssignment cAss_ = ifstat.addIncrement(c);
	IVariableAssignment iInc = loop.addIncrement(i);
	IReturn ret = body.addReturn(c);
}
