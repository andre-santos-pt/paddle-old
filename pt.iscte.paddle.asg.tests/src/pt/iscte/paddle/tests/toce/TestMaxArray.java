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

public class TestMaxArray extends BaseTest {

	IProcedure max = module.addProcedure(INT);
	IVariable array = max.addParameter(INT.array().reference());
	IBlock body = max.getBody();
	IVariable m = body.addVariable(INT);
	IVariableAssignment mAss = body.addAssignment(m, array.element(INT.literal(0)));
	IVariable i = body.addVariable(INT);
	IVariableAssignment iAss = body.addAssignment(i, INT.literal(1));
	ILoop loop = body.addLoop(SMALLER.on(i, array.length()));
	ISelection ifstat = loop.addSelection(GREATER.on(array.element(i), m));
	IVariableAssignment mAss_ = ifstat.addAssignment(m, array.element(i));
	IVariableAssignment iInc = loop.addIncrement(i);
	IReturn ret = body.addReturn(m);
}
