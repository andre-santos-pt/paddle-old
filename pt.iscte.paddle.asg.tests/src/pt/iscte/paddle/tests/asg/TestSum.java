package pt.iscte.paddle.tests.asg;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;

import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IFactory;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IOperator;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.machine.IMachine;
import pt.iscte.paddle.machine.IProgramState;

public class TestSum {

	private static IModule program;
	private static IProcedure sumArrayProc;
	private static IProcedure main;

	@BeforeClass
	public static void setup() {
		IFactory factory = IFactory.INSTANCE;
		program = factory.createModule("Sum");
		
		sumArrayProc = program.addProcedure("sum", IDataType.INT);
		IVariable vParam = sumArrayProc.addParameter("v", factory.arrayType(IDataType.INT, 1).referenceType());
		
		IVariable sVar = sumArrayProc.getBody().addVariable("s", IDataType.INT);
		sVar.addAssignment(factory.literal(0));
		IVariable iVar = sumArrayProc.getBody().addVariable("i", IDataType.INT);
		iVar.addAssignment(factory.literal(0));
		
		ILoop loop = sumArrayProc.getBody().addLoop(factory.binaryExpression(IOperator.DIFFERENT, iVar.expression(), vParam.lengthExpression()));
		loop.addAssignment(sVar, factory.binaryExpression(IOperator.ADD, sVar.expression(), vParam.elementExpression(iVar.expression())));
		loop.addAssignment(iVar, factory.binaryExpression(IOperator.ADD, iVar.expression(), factory.literal(true)));
		
		vParam.elementAssignment(factory.literal(-1), factory.literal(0));
		
		sumArrayProc.getBody().addReturnStatement(sVar.expression());
		
		main = program.addProcedure("main", IDataType.INT);
		
		IVariable array = main.getBody().addVariable("test", factory.arrayType(IDataType.INT, 1));
		array.addAssignment(factory.arrayAllocation(IDataType.INT, 1, factory.literal(3)));
		array.elementAssignment(factory.literal(5), factory.literal(0));
		array.elementAssignment(factory.literal(7), factory.literal(1));
		array.elementAssignment(factory.literal(9), factory.literal(2));
		
//		IArrayVariableDeclaration arrayT = main.getBody().addArrayDeclaration("t", factory.arrayType(IDataType.INT, 1));
//		arrayT.addAssignment(array.expression());
		
		IVariable mVar = main.getBody().addVariable("s", IDataType.INT);
		mVar.addAssignment(sumArrayProc.callExpression(array.expressionAddress()));
	}

	@Test
	public void testSum() {
		System.out.println(program);
		IProgramState state = IMachine.create(program);
		IExecutionData data = state.execute(main);
		assertEquals(2, data.getCallStackDepth());
		assertEquals(new BigDecimal(21), data.getVariableValue("s").getValue());
		System.out.println(data.getVariableValue("test"));
		
//		assertTrue(sumArrayProc.getVariable("s").getRole() instanceof IGatherer); 
	}
	
	// TODO @Test average
}
