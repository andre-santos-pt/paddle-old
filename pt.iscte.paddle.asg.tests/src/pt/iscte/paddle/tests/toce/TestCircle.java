package pt.iscte.paddle.tests.toce;

import static pt.iscte.paddle.asg.IOperator.MUL;
import static pt.iscte.paddle.asg.IType.DOUBLE;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IConstant;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestCircle extends BaseTest {

	IConstant PI = module.addConstant(DOUBLE, DOUBLE.literal(3.14159265359));
	IProcedure circleArea = module.addProcedure(DOUBLE);
	IVariable r = circleArea.addParameter(DOUBLE);
	IBlock body = circleArea.getBody();
	IReturn ret = body.addReturn(MUL.on(MUL.on(PI, r), r));
	
	
}
