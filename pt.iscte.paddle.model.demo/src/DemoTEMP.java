import static pt.iscte.paddle.model.IOperator.ADD;
import static pt.iscte.paddle.model.IOperator.SMALLER;
import static pt.iscte.paddle.model.IType.INT;

import java.io.File;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.interpreter.IMachine;
import pt.iscte.paddle.interpreter.IProgramState;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.interpreter.IProgramState.IListener;
import pt.iscte.paddle.javali.translator.Translator;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.IVariable;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IBlock.IVisitor;

public class DemoTEMP {

	public static void main(String[] args) throws ExecutionError {

		// instantiate model from file
		Translator translator = new Translator(new File("TestFile.javali").getAbsolutePath());
		IModule module = translator.createProgram();
		IProcedure nats = module.getProcedures().iterator().next(); // first

		System.out.println(module);

		// Model visitor
		System.out.println("Assignments (source) to variable i:");
		nats.accept(new IVisitor() {
			public boolean visit(IVariableAssignment assignment) {
				if (assignment.getVariable().getId().equals("i"))
					System.out.println(assignment);
				return true;
			}
		});

		// Interpreter --------
		IProgramState state = IMachine.create(module);

		// Tracer ----
		state.addListener(new IListener() {
			public void step(IProgramElement statement) {
				if(statement instanceof IVariableAssignment) {
					IVariableAssignment a = (IVariableAssignment) statement;
					if(a.getVariable().getId().equals("i"))
						System.out.println(state.getCallStack().getTopFrame().getVariableStore(a.getVariable()));
				}
			}
		});

		System.out.println("Assignments (execution) to variable i:");

		
		IExecutionData data = state.execute(nats, 5);  // naturals(5)
		IValue ret = data.getReturnValue();

		System.out.println();
		System.out.println("RESULT: " + ret);

	}
}