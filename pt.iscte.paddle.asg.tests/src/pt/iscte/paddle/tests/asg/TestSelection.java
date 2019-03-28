package pt.iscte.paddle.tests.asg;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pt.iscte.paddle.asg.IDataType.INT;
import static pt.iscte.paddle.asg.IOperator.GREATER;

import org.junit.BeforeClass;
import org.junit.Test;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IOperator;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.ISelection;
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
		program = IModule.create();
		maxFunc = program.addProcedure("max", INT);
		IVariable a = maxFunc.addParameter("a", INT);
		IVariable b = maxFunc.addParameter("b", INT);
		
		IBinaryExpression guard = GREATER.on(a, b);
		ISelection ifElse = maxFunc.getBody().addSelectionWithAlternative(guard);
		ifElse.addReturn(a);
		ifElse.getAlternativeBlock().addReturn(b);
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
