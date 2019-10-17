import java.io.File;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.interpreter.IMachine;
import pt.iscte.paddle.interpreter.IProgramState;
import pt.iscte.paddle.interpreter.IProgramState.IListener;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.javali.translator.Translator;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.IVariableAssignment;

public class DemoListener {

	public static void main(String[] args) throws ExecutionError {

		// instantiate model from file
		Translator translator = new Translator(new File("naturals.javali").getAbsolutePath());
		IModule module = translator.createProgram();
		IProcedure nats = module.getProcedures().iterator().next(); // first

		System.out.println(module);

		IProgramState state = IMachine.create(module);

		// Tracer ----
		state.addListener(new IListener() {
			public void step(IProgramElement statement) {
				if(statement instanceof IVariableAssignment) {
					IVariableAssignment a = (IVariableAssignment) statement;
					if(a.getTarget().getId().equals("i"))
						System.out.println(state.getCallStack().getTopFrame().getVariableStore(a.getTarget()));
				}
			}
		});

		System.out.println("Modifications of variable i:");

		IExecutionData data = state.execute(nats, 5);  // naturals(5)
		IValue ret = data.getReturnValue();

		System.out.println();
		System.out.println("RESULT: " + ret);

	}
}