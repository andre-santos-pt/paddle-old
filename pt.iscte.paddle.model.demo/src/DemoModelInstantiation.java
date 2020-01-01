import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.interpreter.IMachine;
import pt.iscte.paddle.interpreter.IProgramState;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IModel2CodeTranslator;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;

public class DemoModelInstantiation {
	
	
	public static void main(String[] args) {
		
		// instantiate model manually
		IModule module = IModule.create();
		IProcedure naturals = Examples.createNaturalsFunction(module);

		// prints model as code (without ids if the above setId calls are not executed)
		String src = module.translate(new IModel2CodeTranslator.Java());
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