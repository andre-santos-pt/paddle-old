package pt.iscte.paddle.tests.asg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

public class TestRandom {

	public static class RandomProc {
		public static double random() {
			return Math.random();
		}
	}
	
	@Test
	public void testRandom() {
		IFactory factory = IFactory.INSTANCE;
		IModule program = factory.createModule("Random");
		program.loadBuildInProcedures(RandomProc.class);
		IProcedure proc = program.addProcedure("randomDouble", IDataType.DOUBLE);
		proc.getBody().addReturnStatement(program.resolveProcedure("random").callExpression());
		IProgramState state = IMachine.create(program);
		IExecutionData data = state.execute(proc);
//		assertEquals(2, data.getTotalProcedureCalls());
	}
	
	@Test 
	public void testRandomInt() {
		IFactory factory = IFactory.INSTANCE;
		IModule program = factory.createModule("Random");
		program.loadBuildInProcedures(RandomProc.class);
		IProcedure proc = program.addProcedure("randomInt", IDataType.DOUBLE);
		IVariable minParam = proc.addParameter("min", IDataType.INT);
		IVariable maxParam = proc.addParameter("max", IDataType.INT);
		IVariable rVar = proc.getBody().addVariable("r", IDataType.DOUBLE);
		rVar.addAssignment(program.resolveProcedure("random").callExpression());
		IBinaryExpression d = factory.binaryExpression(IOperator.SUB, maxParam.expression(), minParam.expression());
		IBinaryExpression d1 = factory.binaryExpression(IOperator.ADD, d, factory.literal(1));
		IBinaryExpression m = factory.binaryExpression(IOperator.MUL, rVar.expression(), d1);
		IUnaryExpression t = factory.unaryExpression(IOperator.TRUNCATE, m);
		IBinaryExpression e = factory.binaryExpression(IOperator.ADD, minParam.expression(), t);
		proc.getBody().addReturnStatement(e);
		
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
