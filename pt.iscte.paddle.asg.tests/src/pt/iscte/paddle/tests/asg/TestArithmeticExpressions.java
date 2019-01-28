package pt.iscte.paddle.tests.asg;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IFactory;
import pt.iscte.paddle.asg.ILiteral;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IOperator;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.IExpressionEvaluator;
import pt.iscte.paddle.machine.IMachine;
import pt.iscte.paddle.machine.IProgramState;
import pt.iscte.paddle.machine.IValue;

// TODO more arithmetic cases
public class TestArithmeticExpressions {

	IFactory factory = IFactory.INSTANCE;
	final int EXP = 0;
	final int TYPE = 1;
	final int RES = 2;

	ILiteral L1 = factory.literal(1);
	ILiteral L3 = factory.literal(3);
	ILiteral L6 = factory.literal(6);

	ILiteral L3_3 = factory.literal(3.3);
	ILiteral L6_4 = factory.literal(6.4);

	IBinaryExpression L3_ADD_L6 = factory.binaryExpression(IOperator.ADD, L3, L6);

	//	Object[][] ADD_CASES = {
	//			{L3_ADD_L6, IDataType.INT, 9},
	//			{factory.binaryExpression(IOperator.ADD, L3_ADD_L6, L1), IDataType.INT, 10}, 
	//			{factory.binaryExpression(IOperator.ADD, L1, L3_ADD_L6), IDataType.INT, 10}, 
	//			{factory.binaryExpression(IOperator.ADD, L3_3, L6), IDataType.DOUBLE, 9.3},
	//			{factory.binaryExpression(IOperator.ADD, L6, L3_3), IDataType.DOUBLE, 9.3},
	//			{factory.binaryExpression(IOperator.ADD, L3_3, L6_4), IDataType.DOUBLE, 9.7}
	//	};

	@Test
	public void testAddCases() throws ExecutionError {
		test(L3_ADD_L6, IDataType.INT, 9);
		test(factory.binaryExpression(IOperator.ADD, L3_ADD_L6, L1), IDataType.INT, 10);
		test(factory.binaryExpression(IOperator.ADD, L1, L3_ADD_L6), IDataType.INT, 10);
		test(factory.binaryExpression(IOperator.ADD, L3_3, L6), IDataType.DOUBLE, 9.3);
		test(factory.binaryExpression(IOperator.ADD, L6, L3_3), IDataType.DOUBLE, 9.3);
		test(factory.binaryExpression(IOperator.ADD, L3_3, L6_4), IDataType.DOUBLE, 9.7);
	}



	//	private void testCase(Object[] c) throws ExecutionError {
	//		IExpression exp = (IExpression) c[EXP];
	//		IValue value = exp.evaluate(mockFrame);
	//		String text = exp + " = " + value;
	//		assertEquals(c[TYPE], value.getType(), text);
	//		assertEquals(c[RES], value.getValue(), text);
	//		System.out.println(text);
	//	}



	//	Object[][] PROD_CASES = {
	//			{factory.binaryExpression(IOperator.PROD, L1, L3), IDataType.INT, 3},
	//			{factory.binaryExpression(IOperator.PROD, L3, L6), IDataType.INT, 18},
	//			{factory.binaryExpression(IOperator.PROD, L3, L3_3), IDataType.DOUBLE, 9.9}
	//	};

	@Test
	public void testProdCases() throws ExecutionError {
		test(factory.binaryExpression(IOperator.MUL, L1, L3), IDataType.INT, 3);
		test(factory.binaryExpression(IOperator.MUL, L3, L6), IDataType.INT, 18);
		test(factory.binaryExpression(IOperator.MUL, L3, L3_3), IDataType.DOUBLE, 9.9);
	}

	private void test(IExpression expression, IDataType type, Number result) throws ExecutionError {
		// TODO to setup
		IModule mockProgram = factory.createModule("Expressions");
		IProcedure mockProcedure = mockProgram.addProcedure("mock", IDataType.VOID);
		IProgramState mockState = IMachine.create(mockProgram);
		mockState.setupExecution(mockProcedure);
		IExpressionEvaluator eval = mockState.createExpressionEvaluator(expression);
		IValue value = eval.evaluate();
		String text = expression + " = " + value;
		assertEquals(type, value.getType());
		assertEquals(new BigDecimal(result.toString()), value.getValue());
		System.out.println(text);
	}
}
