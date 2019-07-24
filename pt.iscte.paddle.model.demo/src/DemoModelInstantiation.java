import static pt.iscte.paddle.model.IOperator.ADD;
import static pt.iscte.paddle.model.IOperator.SMALLER;
import static pt.iscte.paddle.model.IType.INT;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.interpreter.IMachine;
import pt.iscte.paddle.interpreter.IProgramState;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.javali.translator.Model2Java;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IVariable;

public class DemoModelInstantiation {
	public static void main(String[] args) {
		
		// instantiate model manually
		IModule module = IModule.create();									// class DemoModule {
		IProcedure naturals = module.addProcedure(INT.array().reference());	// static int[] naturals(
		IVariable n = naturals.addParameter(INT);							// int n)						
		IBlock body = naturals.getBody();									// {
		IVariable array = body.addVariable(INT.array().reference());		// 		int[] array;
		body.addAssignment(array, INT.array().heapAllocation(n));			// 		array = new int[n];
		IVariable i = body.addVariable(INT, INT.literal(0));				// 		int i; i = 0;
		ILoop loop = body.addLoop(SMALLER.on(i, n));						// 		while(i < n) {
		loop.addArrayElementAssignment(array, ADD.on(i, INT.literal(1)), i);// 			array[i] = i + 1;
		loop.addAssignment(i, ADD.on(i, INT.literal(1)));					//			i = i + 1;
																			//		}
		body.addReturn(array);												//		return array;
																			// }

		// optional ids
		module.setId("DemoModule");
		naturals.setId("naturals");
		n.setId("n");
		array.setId("array");
		i.setId("i");

		// prints model as code (without ids if the above setId calls are not executed)
		String src = module.translate(new Model2Java());
		System.out.println(src);

		IProgramState state = IMachine.create(module);
		try {
			// execute a procedure call naturals(10)
			IExecutionData data = state.execute(naturals, 10);
			// obtain result
			IValue ret = data.getReturnValue();
			System.out.println(ret);
		} 
		catch (ExecutionError e) {
			// when the program executed led to a runtime error
			e.printStackTrace();
		}
	}
}