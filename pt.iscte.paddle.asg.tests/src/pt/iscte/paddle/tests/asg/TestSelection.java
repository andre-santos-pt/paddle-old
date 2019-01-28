package pt.iscte.paddle.tests.asg;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IBinaryOperator;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IFactory;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IOperator;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.ISelectionWithAlternative;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.machine.IMachine;
import pt.iscte.paddle.machine.IProgramState;


// TODO if if
public class TestSelection {
	private static IModule program;
	private static IProcedure maxFunc;
	
	@BeforeClass
	public static void setup() {
		IFactory factory = IFactory.INSTANCE;
		program = factory.createModule("Selection");
		maxFunc = program.addProcedure("max", IDataType.INT);
		IVariable aParam = maxFunc.addParameter("a", IDataType.INT);
		IVariable bParam = maxFunc.addParameter("b", IDataType.INT);
		
		IBinaryExpression e = factory.binaryExpression(IBinaryOperator.GREATER, aParam.expression(), bParam.expression());
		ISelectionWithAlternative ifElse = maxFunc.getBody().addSelectionWithAlternative(e);
		ifElse.addReturnStatement(aParam.expression());
		IBlock elseblock = ifElse.getAlternativeBlock();
		elseblock.addReturnStatement(bParam.expression());
		System.out.println(program);
	}

	@Test
	public void testFirst() {
		IProgramState state = IMachine.create(program);
		IExecutionData data = state.execute(maxFunc, "2","-1");
		assertTrue(data.getReturnValue().toString().equals("2"));
//		commonAsserts(data);
	}
	
	@Test
	public void testSecond() {
		IProgramState state = IMachine.create(program);
		IExecutionData data = state.execute(maxFunc, "2","4");
		assertTrue(data.getReturnValue().toString().equals("4"));
//		commonAsserts(data);
	}

	// TODO repor
	private static void commonAsserts(IExecutionData data) {
		assertEquals(0, data.getTotalAssignments());
		assertEquals(1, data.getOperationCount(IOperator.OperationType.RELATIONAL));
		assertEquals(1, data.getCallStackDepth());
	}
}
