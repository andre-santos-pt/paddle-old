package pt.iscte.paddle.tests.asg;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;

import pt.iscte.paddle.asg.IDataType;
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
		IFactory factory = IFactory.INSTANCE;
		program = factory.createModule("Swap");
		
		swapProc = program.addProcedure("swap", IDataType.VOID);
		IVariable vParam = (IVariable) swapProc.addParameter("v", factory.arrayType(IDataType.INT, 1));
		IVariable iParam = swapProc.addParameter("i", IDataType.INT);
		IVariable jParam = swapProc.addParameter("j", IDataType.INT);
		
		IVariable tVar = swapProc.getBody().addVariable("t", IDataType.INT);
		tVar.addAssignment(vParam.elementExpression(iParam.expression()));
		vParam.elementAssignment(vParam.elementExpression(jParam.expression()), iParam.expression());
		vParam.elementAssignment(tVar.expression(), jParam.expression());
		
		iParam.addAssignment(factory.literal(4));
		
		main = program.addProcedure("main", IDataType.VOID);
		IVariable array = main.getBody().addVariable("test", factory.arrayType(IDataType.INT, 1));
		array.addAssignment(factory.arrayAllocation(IDataType.INT, 1, factory.literal(3)));
		array.elementAssignment(factory.literal(5), factory.literal(0));
		array.elementAssignment(factory.literal(7), factory.literal(1));
		array.elementAssignment(factory.literal(9), factory.literal(2));
		
		IVariable iVar = main.getBody().addVariable("i", IDataType.INT);
		iVar.addAssignment(factory.literal(0));
		
		main.getBody().addProcedureCall(swapProc, array.expression(), iVar.expression(), factory.literal(2));
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
