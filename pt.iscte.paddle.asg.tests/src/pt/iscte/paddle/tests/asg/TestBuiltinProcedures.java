package pt.iscte.paddle.tests.asg;

import java.util.Scanner;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.interpreter.ExecutionError.Type;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.IType;

// TODO read system.in
public class TestBuiltinProcedures extends BaseTest {

	public static class TestProcedures {
		public static int max(int a, int b) {
			return a > b ? a : b;
		}
		
		public static void print(int i) {
			System.out.println(i);
		}
		
		public static String readString() {
			Scanner scanner = new Scanner(System.in);
			String line = scanner.nextLine();
			scanner.close();
			return line;
		}
		
		public static int readInt() {
			Scanner scanner = new Scanner(System.in);
			String line = scanner.nextLine();
			scanner.close();
			return Integer.parseInt(line);
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
