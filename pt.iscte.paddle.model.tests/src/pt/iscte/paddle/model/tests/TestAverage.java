package pt.iscte.paddle.model.tests;
import static pt.iscte.paddle.model.IOperator.DIV;
import static pt.iscte.paddle.model.IType.DOUBLE;
import static pt.iscte.paddle.model.IType.INT;

import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.IVariableDeclaration;

public class TestAverage extends BaseTest {
	private IProcedure sum = importProcedure(TestSum.class, "summation");
	
	IProcedure average = getModule().addProcedure(DOUBLE);
	IVariableDeclaration array = average.addParameter(DOUBLE.array().reference());
	IReturn ret_ = average.getBody().addReturn(DIV.on(sum.call(array), array.length()));

	private IVariableDeclaration a;
	
	protected IProcedure main() {
		IProcedure test = module.addProcedure(DOUBLE);
		IBlock body = test.getBody();
		a = body.addVariable(DOUBLE.array().reference(), DOUBLE.array().heapAllocation(INT.literal(5)));
		IVariableDeclaration zero = body.addVariable(INT, INT.literal(0));
		body.addArrayElementAssignment(a, DOUBLE.literal(2.3), zero);
		body.addArrayElementAssignment(a, DOUBLE.literal(3.1), INT.literal(1));
		body.addArrayElementAssignment(a, DOUBLE.literal(0.1), INT.literal(3));
		body.addArrayElementAssignment(a, DOUBLE.literal(10.0), INT.literal(4));
		
		IVariableDeclaration sum = body.addVariable(DOUBLE, average.call(a));
		body.addReturn(sum);
		return test;
	}
	
	@Case
	public void test(IExecutionData data) {
		equal(3.1, data.getReturnValue());
	}
}
