package pt.iscte.paddle.tests.asg;

import static pt.iscte.paddle.model.IType.INT;
import static pt.iscte.paddle.model.IType.VOID;

import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.tests.BaseTest;

public class TestReferences extends BaseTest {

	IProcedure proc = module.addProcedure(VOID);
	IBlock body = proc.getBody();
	IVariableDeclaration a = body.addVariable(INT.reference());
	IVariableDeclaration b = body.addVariable(INT.reference());
	IVariableAssignment ass1 = body.addAssignment(a.dereference().getTarget().getVariable(), INT.literal(5));
	IVariableAssignment ass2 = body.addAssignment(b, a);
	IVariableAssignment ass3 = body.addAssignment(b, INT.literal(7));
	
	
	
	public void test() {
		// TODO
		

	}
		
}
