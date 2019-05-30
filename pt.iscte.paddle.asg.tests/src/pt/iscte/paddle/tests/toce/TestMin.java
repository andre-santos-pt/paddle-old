package pt.iscte.paddle.tests.toce;
import static pt.iscte.paddle.asg.IOperator.*;

import static pt.iscte.paddle.asg.IType.*;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.ISelection;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestMin extends BaseTest {
	IProcedure min = getModule().addProcedure(INT);
	IVariable a = min.addParameter(INT);
	IVariable b = min.addParameter(INT);
	IReturn ret = min.getBody().addReturn(SMALLER.on(a, b).conditional(a, b));
}
