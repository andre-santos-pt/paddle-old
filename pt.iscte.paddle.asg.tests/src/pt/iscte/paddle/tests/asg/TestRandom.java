package pt.iscte.paddle.tests.asg;

import static org.junit.Assert.assertTrue;
import static pt.iscte.paddle.asg.IDataType.DOUBLE;
import static pt.iscte.paddle.asg.IDataType.INT;
import static pt.iscte.paddle.asg.ILiteral.literal;
import static pt.iscte.paddle.asg.IOperator.ADD;
import static pt.iscte.paddle.asg.IOperator.MUL;
import static pt.iscte.paddle.asg.IOperator.SUB;
import static pt.iscte.paddle.asg.IOperator.TRUNCATE;

import org.junit.Test;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProcedureCall;
import pt.iscte.paddle.asg.IUnaryExpression;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.machine.IMachine;
import pt.iscte.paddle.machine.IProgramState;

public class TestRandom {

	public static class RandomProc {
		public static double random() {
			return Math.random();
		}
	}
	
	@Test
	public void testRandom() {
		IModule program = IModule.create("Random");
		program.loadBuildInProcedures(RandomProc.class);
		IProcedure proc = program.addProcedure("randomDouble", DOUBLE);
		proc.getBody().addReturn(program.resolveProcedure("random").call());
		System.out.println(program);
		IProgramState state = IMachine.create(program);
		IExecutionData data = state.execute(proc);
		System.out.println(data.getReturnValue());
//		assertEquals(2, data.getTotalProcedureCalls());
	}
	
	@Test 
	public void testRandomInt() {
		IModule program = IModule.create("Random");
		program.loadBuildInProcedures(RandomProc.class);
		IProcedure proc = program.addProcedure("randomInt", INT);
		IVariable min = proc.addParameter("min", INT);
		IVariable max = proc.addParameter("max", INT);
		IProcedureCall call = program.resolveProcedure("random").call();
		IVariable r = proc.getBody().addVariable("r", DOUBLE, call);
		IBinaryExpression m = MUL.on(r, ADD.on(SUB.on(max, min), literal(1)));
		IUnaryExpression t = TRUNCATE.on(m);
		IBinaryExpression e = ADD.on(min, t);
		proc.getBody().addReturn(e);
		
		System.out.println(program);
		IProgramState state = IMachine.create(program);
		for(int i = 0; i < 10; i++) {
			IExecutionData data = state.execute(proc, "1", "10");
			Number output = (Number) data.getReturnValue().getValue(); 
			assertTrue(output.intValue() >= 1); 
			assertTrue(output.intValue() <= 10); 
		}
	}
	
}
