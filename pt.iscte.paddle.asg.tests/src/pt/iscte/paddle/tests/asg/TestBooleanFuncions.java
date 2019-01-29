package pt.iscte.paddle.tests.asg;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IFactory;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IOperator;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IUnaryExpression;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.machine.IMachine;
import pt.iscte.paddle.machine.IProgramState;
import pt.iscte.paddle.machine.IValue;

import static pt.iscte.paddle.asg.IDataType.*;
import static pt.iscte.paddle.asg.IOperator.*;
import static pt.iscte.paddle.asg.ILiteral.*;

public class TestBooleanFuncions {
	private static IModule program;
	private static IProcedure evenFunc;
	private static IProcedure oddFunc;
	private static IProcedure oddNotEvenFunc;
	
	private static IProcedure withinIntervalFunc;
	private static IProcedure outsideIntervalFunc;

	
	
	@BeforeClass
	public static void setup() {
		program = IModule.create("BooleanFunctions");
		evenFunc = createIsEven();
		oddFunc = createIsOdd();
		oddNotEvenFunc = createIsOddNotEven();
		withinIntervalFunc = createWithinInterval();
		outsideIntervalFunc = createOutsideInterval();
		System.out.println(program);
	}

	private static void assertTrueValue(IValue value) {
		assertTrue(value.getValue().equals(Boolean.TRUE));
	}
	
	private static void assertFalseValue(IValue value) {
		assertTrue(value.getValue().equals(Boolean.FALSE));
	}

	private static IProcedure createIsEven() {
		IProcedure f = program.addProcedure("isEven", BOOLEAN);
		IVariable n = f.addParameter("n", INT);
		
		IBinaryExpression e = EQUAL.on(MOD.on(n, literal(2)), literal(0));
		
		f.getBody().addReturn(e);
		return f;
	}
	
	@Test
	public void testIsEven() {
		IProgramState state = IMachine.create(program);
		IExecutionData dataTrue = state.execute(evenFunc, "6");
		assertTrueValue(dataTrue.getReturnValue());
		
		IExecutionData dataFalse = state.execute(evenFunc, "7");
		assertFalseValue(dataFalse.getReturnValue());
	}
	

	private static IProcedure createIsOdd() {
		IProcedure f = program.addProcedure("isOdd", BOOLEAN);
		IVariable n = f.addParameter("n", INT);
		IBinaryExpression e = DIFFERENT.on(MOD.on(n, literal(2)), literal(0));
		f.getBody().addReturn(e);
		return f;
	}
	
	private static IProcedure createIsOddNotEven() {
		IProcedure f = program.addProcedure("isOddNotEven", BOOLEAN);
		IVariable n = f.addParameter("n", INT);
		
		IUnaryExpression e = NOT.on(evenFunc.call(n));
		f.getBody().addReturn(e);
		return f;
	}
	
	@Test
	public void testIsOdd() {
		IProgramState state = IMachine.create(program);
		IExecutionData dataTrue = state.execute(oddFunc, "7");
		assertTrueValue(dataTrue.getReturnValue());
		
		IExecutionData dataFalse = state.execute(oddFunc, "6");
		assertFalseValue(dataFalse.getReturnValue());
	}
	
	@Test
	public void testIsOddNotEven() {
		IProgramState state = IMachine.create(program);
		IExecutionData dataTrue = state.execute(oddNotEvenFunc, "7");
		assertTrueValue(dataTrue.getReturnValue());
		
		IExecutionData dataFalse = state.execute(oddNotEvenFunc, "6");
		assertFalseValue(dataFalse.getReturnValue());
	}
	
	
	private static IProcedure createWithinInterval() {
		IProcedure f = program.addProcedure("withinInterval", BOOLEAN);
		IVariable n = f.addParameter("n", INT);
		IVariable a = f.addParameter("a", INT);
		IVariable b = f.addParameter("b", INT);
		IBinaryExpression e = AND.on(GREATER_EQ.on(n, a), SMALLER_EQ.on(n, b));
		f.getBody().addReturn(e);
		return f;
	}
	
	@Test
	public void testWithinInterval() {
		IProgramState state = IMachine.create(program);
		IExecutionData dataTrue = state.execute(withinIntervalFunc, "6", "4", "8");
		assertTrueValue(dataTrue.getReturnValue());
		
		IExecutionData dataFalse = state.execute(withinIntervalFunc, "6", "7", "10");
		assertFalseValue(dataFalse.getReturnValue());
	}
	
	private static IProcedure createOutsideInterval() {
		IProcedure f = program.addProcedure("ousideInterval", BOOLEAN);
		IVariable n = f.addParameter("n", INT);
		IVariable a = f.addParameter("a", INT);
		IVariable b = f.addParameter("b", INT);
		IBinaryExpression e = OR.on(SMALLER.on(n, a), GREATER.on(n, b));
		f.getBody().addReturn(e);
		return f;
	}
	
	@Test
	public void testOutsideInterval() {
		IProgramState state = IMachine.create(program);
		IExecutionData dataTrue = state.execute(outsideIntervalFunc, "3", "4", "8");
		assertTrueValue(dataTrue.getReturnValue());
		
		IExecutionData dataFalse = state.execute(outsideIntervalFunc, "7", "7", "10");
		assertFalseValue(dataFalse.getReturnValue());
	}
	
}
