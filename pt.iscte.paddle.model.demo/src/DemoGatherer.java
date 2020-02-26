import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.roles.IVariableRole;

public class DemoGatherer {

	public static void main(String[] args) {
		IModule module = IModule.create();
		IProcedure sum = Examples.createArraySumFunction(module);

		System.out.println(sum);
		
		for (IVariableDeclaration var : sum.getVariables()) {
			IVariableRole role = IVariableRole.match(var);
			System.out.println(var.getId() + ": " + role);
		}
	}
}
