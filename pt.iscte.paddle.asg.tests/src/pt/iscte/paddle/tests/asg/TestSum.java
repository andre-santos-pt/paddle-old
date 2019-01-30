package pt.iscte.paddle.tests.asg;
import static org.junit.Assert.assertEquals;
import static pt.iscte.paddle.asg.IDataType.INT;
import static pt.iscte.paddle.asg.ILiteral.literal;
import static pt.iscte.paddle.asg.IOperator.ADD;
import static pt.iscte.paddle.asg.IOperator.DIFFERENT;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;

import pt.iscte.paddle.asg.IArrayAllocation;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IModule;
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
		program = IModule.create("Sum");
		
		sumArrayProc = program.addProcedure("sum", INT);
		IVariable v = sumArrayProc.addParameter("v", INT.array().reference());
		
		IVariable s = sumArrayProc.getBody().addVariable("s", INT, literal(0));
		IVariable i = sumArrayProc.getBody().addVariable("i", INT, literal(0));
		
		ILoop loop = sumArrayProc.getBody().addLoop(DIFFERENT.on(i, v.valueOf().arrayLength()));
		loop.addAssignment(s, ADD.on(s, v.valueOf().arrayElement(i)));
		loop.addIncrement(i);

//		v.valueOf().addArrayAssignment(literal(-1), literal(0));
		
		sumArrayProc.getBody().addReturn(s);
		
		main = program.addProcedure("main", INT);
		
		IBlock body = main.getBody();
		IArrayAllocation allocation = INT.array().allocation(literal(3));
		IVariable test = body.addVariable("test", INT.array(), allocation);
		body.addArrayElementAssignment(test, literal(5), literal(0));
		body.addArrayElementAssignment(test, literal(7), literal(1));
		body.addArrayElementAssignment(test, literal(9), literal(2));
		
		
//		IArrayVariableDeclaration arrayT = main.getBody().addArrayDeclaration("t", factory.arrayType(IDataType.INT, 1));
//		arrayT.addAssignment(array.expression());
		
		main.getBody().addVariable("s", INT, sumArrayProc.call(test.address()));
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
