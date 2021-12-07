package pt.iscte.paddle.model.tests;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IVariableDeclaration;
import static pt.iscte.paddle.model.IOperator.GREATER;
import static pt.iscte.paddle.model.IType.INT;

import pt.iscte.paddle.interpreter.IExecutionData;

public class TestReturn extends BaseTest {
	IProcedure procedure = getModule().addProcedure(INT);
	IVariableDeclaration p = procedure.addParameter(INT);
	IBlock body = procedure.getBody();
	ISelection selection = body.addSelection(GREATER.on(p, INT.literal(0)));
	IReturn ret1 = selection.getBlock().addReturn(INT.literal(1));
	IReturn ret2 = body.addReturn(INT.literal(0));
	
	@Case("10")
	public void testOne(IExecutionData data) {
		equal(1, data.getReturnValue());
	}
	
	@Case("0")
	public void testZero(IExecutionData data) {
		equal(0, data.getReturnValue());
	}
}
