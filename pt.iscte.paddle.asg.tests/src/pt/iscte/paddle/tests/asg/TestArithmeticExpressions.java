package pt.iscte.paddle.tests.asg;

import static org.junit.Assert.assertEquals;
import static pt.iscte.paddle.asg.IOperator.ADD;
import static pt.iscte.paddle.asg.IOperator.MUL;
import static pt.iscte.paddle.asg.IType.DOUBLE;
import static pt.iscte.paddle.asg.IType.INT;
import static pt.iscte.paddle.asg.IType.VOID;

import java.math.BigDecimal;

import org.junit.Test;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.ILiteral;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.IExpressionEvaluator;
import pt.iscte.paddle.machine.IMachine;
import pt.iscte.paddle.machine.IProgramState;
import pt.iscte.paddle.machine.IValue;


// TODO more arithmetic cases
public class TestArithmeticExpressions extends BaseTest {

	final int EXP = 0;
	final int TYPE = 1;
	final int RES = 2;

	ILiteral L1 = INT.literal(1);
	ILiteral L3 = INT.literal(3);
	ILiteral L6 = INT.literal(6);

	ILiteral L3_3 = INT.literal(3.3);
	ILiteral L6_4 = INT.literal(6.4);

	IBinaryExpression L3_ADD_L6 = ADD.on(L3, L6);

	@Test
	public void testAddCases() throws ExecutionError {
		test(L3_ADD_L6, IType.INT, 9);
		test(ADD.on(L3_ADD_L6, L1), INT, 10);
		test(ADD.on(L1, L3_ADD_L6), INT, 10);
		test(ADD.on(L3_3, L6), DOUBLE, 9.3);
		test(ADD.on(L6, L3_3), DOUBLE, 9.3);
		test(ADD.on(L3_3, L6_4), DOUBLE, 9.7);
	}

	@Test
	public void testProdCases() throws ExecutionError {
		test(MUL.on(L1, L3), INT, 3);
		test(MUL.on(L3, L6), INT, 18);
		test(MUL.on(L3, L3_3), DOUBLE, 9.9);
	}

	private void test(IExpression expression, IType type, Number result) throws ExecutionError {
		IProcedure mockProcedure = module.addProcedure(VOID);
		IProgramState mockState = IMachine.create(module);
		mockState.setupExecution(mockProcedure);
		IExpressionEvaluator eval = mockState.createExpressionEvaluator(expression);
		IValue value = eval.evaluate();
		String text = expression + " = " + value;
		assertEquals(type, value.getType());
		assertEquals(new BigDecimal(result.toString()), value.getValue());
		System.out.println(text);
	}
}
