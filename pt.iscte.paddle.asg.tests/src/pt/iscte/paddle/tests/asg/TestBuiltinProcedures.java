package pt.iscte.paddle.tests.asg;

import static pt.iscte.paddle.asg.ILiteral.literal;

import org.junit.Test;


import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ExecutionError.Type;
import pt.iscte.paddle.machine.IMachine;
import pt.iscte.paddle.machine.IProgramState;

public class TestBuiltinProcedures {

	public static class TestProcedures {
		public static int max(int a, int b) {
			return a > b ? a : b;
		}
		
		public static void print(int i) {
			System.out.println(i);
		}
		
		public static void _assert(boolean condition) throws ExecutionError {
			if(!condition)
				throw new ExecutionError(Type.ASSERTION, null, "Assertion failed");
		}
	}
	
	@Test
	public void test() {
		IModule program = IModule.create("Test");
		program.loadBuildInProcedures(TestBuiltinProcedures.TestProcedures.class);
		IProcedure proc = program.addProcedure("test", IDataType.VOID);
		IProcedure max = program.resolveProcedure("max", IDataType.INT, IDataType.INT);
		proc.getBody().addCall(max, literal(4), literal(6));
		System.out.println(program);
		IProgramState state = IMachine.create(program);
		state.execute(proc);
	}
}
