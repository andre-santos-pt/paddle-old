package pt.iscte.paddle.model.tests;
import pt.iscte.paddle.model.IArrayElementAssignment;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.IExecutionData;
import static pt.iscte.paddle.model.IOperator.GREATER;
import static pt.iscte.paddle.model.IType.INT;
import static pt.iscte.paddle.model.IType.VOID;

public class TestReturnVoid extends BaseTest {
	IProcedure procedure = getModule().addProcedure(VOID);
	IVariableDeclaration p = procedure.addParameter(INT.array().reference());
	IVariableDeclaration i = procedure.addParameter(INT);
	IBlock body = procedure.getBody();
	ISelection selection = body.addSelection(GREATER.on(p.element(i), INT.literal(0)));
	IReturn ret1 = selection.getBlock().addReturn();
	IArrayElementAssignment a = body.addArrayElementAssignment(p, INT.literal(-1), i);	

	private IVariableDeclaration array;
	
	protected IProcedure main() {
		IProcedure test = module.addProcedure(VOID);
		IBlock body = test.getBody();
		array = body.addVariable(INT.array().reference(), INT.array().heapAllocation(INT.literal(2)));
		body.addArrayElementAssignment(array,  INT.literal(10), INT.literal(0));
		body.addArrayElementAssignment(array,  INT.literal(0), INT.literal(1));
		body.addCall(procedure, array, INT.literal(0));
		body.addCall(procedure, array, INT.literal(1));
		return test;
	}

	@Case
	public void testOutput(IExecutionData data) {
		IArray a = (IArray) data.getVariableValue(array);
		equal(10, a.getElement(0));
		equal(-1, a.getElement(1));
	}
}
