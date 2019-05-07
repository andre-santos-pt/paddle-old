package pt.iscte.paddle.tests.asg;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ExecutionError.Type;
import pt.iscte.paddle.machine.IExecutionData;

// TODO read system.in
public class TestBuiltinProcedures extends BaseTest {

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
	
	@Override
	protected Class<?>[] getBuiltins() {
		return new Class[] {TestProcedures.class};
	}
	
	IProcedure test = module.addProcedure(IType.INT);
	IProcedure max = module.resolveProcedure("max", IType.INT, IType.INT);
	IBlock body = test.getBody();
	IReturn ret = body.addReturn(body.addCall(max, IType.INT.literal(4), IType.INT.literal(6)));
	
	@Case
	public void test(IExecutionData data) {
		equal(6, data.getReturnValue());
	}
}
