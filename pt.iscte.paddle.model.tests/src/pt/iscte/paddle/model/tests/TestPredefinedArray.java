package pt.iscte.paddle.model.tests;

import static pt.iscte.paddle.model.IType.DOUBLE;

import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.tests.BaseTest.Case;

public class TestPredefinedArray extends BaseTest {

	IProcedure createArray = module.addProcedure(DOUBLE.array());
	IBlock body = createArray.getBody();
	IVariableDeclaration v = body.addVariable(DOUBLE.array(), 
			DOUBLE.array().heapAllocationWith(DOUBLE.literal(2.2), DOUBLE.literal(2.4), DOUBLE.literal(2.6)));
	IReturn ret = body.addReturn(v);
	
	@Case
	public void test(IExecutionData data) {
		equal(2.2, ((IArray) data.getReturnValue()).getElement(0));
		equal(2.4, ((IArray) data.getReturnValue()).getElement(1));
		equal(2.6, ((IArray) data.getReturnValue()).getElement(2));
	}
}
