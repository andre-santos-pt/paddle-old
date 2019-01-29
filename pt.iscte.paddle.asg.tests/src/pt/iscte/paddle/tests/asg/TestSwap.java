package pt.iscte.paddle.tests.asg;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;

import static pt.iscte.paddle.asg.IDataType.*;
import static pt.iscte.paddle.asg.ILiteral.*;
import pt.iscte.paddle.asg.IFactory;
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
		
		IVariable t = swapProc.getBody().addVariable("t", INT);
		t.addAssignment(v.arrayElement(i));
		v.addArrayAssignment(v.arrayElement(j), i);
		v.addArrayAssignment(t, j);
		
		i.addAssignment(literal(4));
		
		main = program.addProcedure("main", VOID);
		IVariable array = main.getBody().addVariable("test", INT.array());
		array.addAssignment(INT.array().allocation(literal(3)));
		array.addArrayAssignment(literal(5), literal(0));
		array.addArrayAssignment(literal(7), literal(1));
		array.addArrayAssignment(literal(9), literal(2));
		
		IVariable iVar = main.getBody().addVariable("i", INT);
		iVar.addAssignment(literal(0));
		
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
