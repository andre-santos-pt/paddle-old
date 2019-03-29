package pt.iscte.paddle.tests.asg;

import static pt.iscte.paddle.asg.IDataType.DOUBLE;
import static pt.iscte.paddle.asg.ILiteral.literal;
import static pt.iscte.paddle.asg.IOperator.MUL;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IConstant;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IExecutionData;

public class TestConstants extends BaseTest {

	IConstant PI = module.addConstant(DOUBLE, literal(3.14159265359));
	IProcedure circleArea = module.addProcedure(DOUBLE);
	IVariable r = circleArea.addParameter(DOUBLE);
	IBlock body = circleArea.getBody();
	IReturn ret = body.addReturn(MUL.on(MUL.on(PI, r), r));
	
	@Case("3")
	public void testArea(IExecutionData data) {
		equal(28.27433388231, data.getReturnValue());
	}
	
}
