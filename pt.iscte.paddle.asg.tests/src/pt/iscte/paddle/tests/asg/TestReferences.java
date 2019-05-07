package pt.iscte.paddle.tests.asg;

import static pt.iscte.paddle.asg.IType.INT;
import static pt.iscte.paddle.asg.IType.VOID;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;

public class TestReferences extends BaseTest {

	IProcedure proc = module.addProcedure(VOID);
	IBlock body = proc.getBody();
	IVariable a = body.addVariable(INT.reference());
	IVariable b = body.addVariable(INT.reference());
	IVariableAssignment ass1 = body.addAssignment(a.value(), INT.literal(5));
	IVariableAssignment ass2 = body.addAssignment(b, a);
	IVariableAssignment ass3 = body.addAssignment(b, INT.literal(7));
	
	
	
	public void test() {
		// TODO
		

	}
		
}
