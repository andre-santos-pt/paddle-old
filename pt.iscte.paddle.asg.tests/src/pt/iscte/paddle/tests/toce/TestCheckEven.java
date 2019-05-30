package pt.iscte.paddle.tests.toce;
import static pt.iscte.paddle.asg.IOperator.*;

import static pt.iscte.paddle.asg.IType.*;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestCheckEven extends BaseTest {
	IProcedure isEven = getModule().addProcedure(BOOLEAN);
	IVariable n = isEven.addParameter(INT);
	IBinaryExpression e = EQUAL.on(MOD.on(n, INT.literal(2)), INT.literal(0));
	IBlock body = isEven.getBody();
	IReturn ret = body.addReturn(e);
}
