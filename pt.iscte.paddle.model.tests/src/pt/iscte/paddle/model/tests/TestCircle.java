package pt.iscte.paddle.model.tests;

import static pt.iscte.paddle.model.IOperator.MUL;
import static pt.iscte.paddle.model.IType.DOUBLE;

import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IConstant;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.IVariable;

public class TestCircle extends BaseTest {

	IConstant PI = module.addConstant(DOUBLE, DOUBLE.literal(3.14159265359));
	IProcedure circleArea = module.addProcedure(DOUBLE);
	IVariable r = circleArea.addParameter(DOUBLE);
	IBlock body = circleArea.getBody();
	IReturn ret = body.addReturn(MUL.on(MUL.on(PI, r), r));
	
	@Case("3")
	public void test(IExecutionData data) {
		equal(28.274333, data.getReturnValue());
	}
	
}
