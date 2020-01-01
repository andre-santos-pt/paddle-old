import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.model.IBlock.IVisitor;
import pt.iscte.paddle.model.IModel2CodeTranslator;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IVariableAssignment;

public class DemoVisitor {

	public static void main(String[] args) throws ExecutionError {

		// instantiate model from file
		IModule module = IModule.create();
		
		IProcedure nats = Examples.createNaturalsFunction(module);
		
		// prints model as code
		String src = module.translate(new IModel2CodeTranslator.Java());
		System.out.println(src);

		// Model visitor
		System.out.println("Assignments to variable i:");
		nats.accept(new IVisitor() {
			public boolean visit(IVariableAssignment assignment) {
				if (assignment.getTarget().getId().equals("i"))
					System.out.println(assignment);
				return true;
			}
		});
	}
}