package pt.iscte.paddle.tests.asg;

import org.junit.Test;

import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IFactory;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.machine.IMachine;
import pt.iscte.paddle.machine.IProgramState;

public class TestBuiltinProcedures {

	public static class TestProcedures {
		public static int max(int a, int b) {
			return a > b ? a : b;
		}
	}
	
	@Test
	public void test() {
		IFactory factory = IFactory.INSTANCE;
		IModule program = factory.createModule("Test");
		program.loadBuildInProcedures(TestBuiltinProcedures.TestProcedures.class);
		IProcedure proc = program.addProcedure("test", IDataType.VOID);
		IProcedure max = program.resolveProcedure("max", IDataType.INT, IDataType.INT);
		proc.getBody().addProcedureCall(max, factory.literal(4), factory.literal(6));
		System.out.println(program);
		IProgramState state = IMachine.create(program);
		state.execute(proc);
	}
}
