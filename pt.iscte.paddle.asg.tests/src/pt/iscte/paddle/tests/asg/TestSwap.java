package pt.iscte.paddle.tests.asg;
import static org.junit.Assert.assertEquals;
import static pt.iscte.paddle.asg.IDataType.INT;
import static pt.iscte.paddle.asg.IDataType.VOID;
import static pt.iscte.paddle.asg.ILiteral.literal;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.machine.IMachine;
import pt.iscte.paddle.machine.IProgramState;

public class TestSwap {

	private static IModule program;
	private static IProcedure swapProc;
	private static IProcedure main;

	@BeforeClass
	public static void setup() {
		program = IModule.create("Swap");
		
		swapProc = program.addProcedure("swap", VOID);
		IVariable v = (IVariable) swapProc.addParameter("v", INT.array());
		IVariable i = swapProc.addParameter("i", INT);
		IVariable j = swapProc.addParameter("j", INT);
		
		IVariable t = swapProc.getBody().addVariable("t", INT, v.arrayElement(i));
		swapProc.getBody().addArrayElementAssignment(v, v.arrayElement(j), i);
		swapProc.getBody().addArrayElementAssignment(v, t, j);
		
		main = program.addProcedure("main", VOID);
		IBlock body = main.getBody();
		IVariable array = body.addVariable("test", INT.array());
		body.addAssignment(array, INT.array().allocation(literal(3)));

		body.addArrayElementAssignment(array, literal(5), literal(0));
		body.addArrayElementAssignment(array, literal(7), literal(1));
		body.addArrayElementAssignment(array, literal(9), literal(2));
		
		IVariable iVar = main.getBody().addVariable("i", INT, literal(0));
		
		main.getBody().addCall(swapProc, array, iVar, literal(2));
	}

	@Test
	public void testSwap() {
		System.out.println(program);
		IProgramState state = IMachine.create(program);
		IExecutionData data = state.execute(main);
		IArray array = (IArray) data.getVariableValue("test");
		assertEquals(new BigDecimal(9), array.getElement(0).getValue());
		assertEquals(new BigDecimal(5), array.getElement(2).getValue());
		assertEquals(new BigDecimal(0), data.getVariableValue("i").getValue());
	}
}
