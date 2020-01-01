import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IVariable;
import pt.iscte.paddle.roles.IGatherer;
import pt.iscte.paddle.roles.IVariableRole;

public class DemoGatherer {

	public static void main(String[] args) {
		IModule module = IModule.create();
		IProcedure sum = Examples.createArraySumFunction(module);

		for (IVariable var : sum.getVariables()) {
			if(IGatherer.isGatherer(var)) {
				IVariableRole g = IGatherer.createGatherer(var);
				System.out.println(var + ": " + g);
			}
		}
	}
}
