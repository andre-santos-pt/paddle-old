package pt.iscte.paddle.model.demo;
import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.model.IBlock.IVisitor;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProcedureCall;
import pt.iscte.paddle.model.IProcedureCallExpression;
import pt.iscte.paddle.model.IType;

public class DemoVisitorProcedureCalls {

	public static void main(String[] args) throws ExecutionError {

		// instantiate model from file
		IModule module = IModule.create();
		
		IProcedure nats = Examples.createNaturalsFunction(module);
		
		IProcedure main = module.addProcedure(IType.VOID, p -> p.setId("main"));
		main.getBody().addCall(nats, IType.INT.literal(4));
		main.getBody().addVariable(IType.INT, nats.expression(IType.INT.literal(5)));
		
		// prints model as code

		main.accept(new IVisitor() {
			public boolean visit(IProcedureCall call) {
				System.out.println("statement: " + call);
				return true;
			}
			
			@Override
			public boolean visit(IProcedureCallExpression exp) {
				System.out.println("expression: " + exp);
				return true;
			}
		});
	}
}