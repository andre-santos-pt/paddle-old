import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.model.IBlock.IVisitor;
import pt.iscte.paddle.model.IArrayElementAssignment;
import pt.iscte.paddle.model.IModel2CodeTranslator;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;

public class DemoVisitor {

	public static void main(String[] args) throws ExecutionError {

		// instantiate model from file
		IModule module = IModule.create();
		
		IProcedure nats = Examples.createNaturalsFunction(module);
		
		// prints model as code
		String src = module.translate(new IModel2CodeTranslator.Java());
		System.out.println(src);

		IVariableDeclaration iVar = nats.getVariable("i");
		// Model visitor
		System.out.println("Assignments to variable i:");
		nats.accept(new IVisitor() {
			public boolean visit(IVariableAssignment assignment) {
				if (assignment.getTarget().equals(iVar))
					System.out.println(assignment);
				
				if(assignment.getExpression().includes(iVar))
					System.out.println("expression including var i: " + assignment);
				return true;
			}
			
			@Override
			public boolean visit(IArrayElementAssignment assignment) {
				if(assignment.getExpression().includes(iVar))
					System.out.println("expression including var i: " + assignment);
				return true;
			}
			
		});
	}
}