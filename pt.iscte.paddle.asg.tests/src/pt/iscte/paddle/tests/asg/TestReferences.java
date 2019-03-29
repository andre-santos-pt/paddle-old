package pt.iscte.paddle.tests.asg;

import static pt.iscte.paddle.asg.IDataType.INT;
import static pt.iscte.paddle.asg.IDataType.VOID;
import static pt.iscte.paddle.asg.ILiteral.literal;

import org.junit.Test;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;

public class TestReferences extends BaseTest {

	IProcedure proc = module.addProcedure(VOID);
	IBlock body = proc.getBody();
	IVariable a = body.addVariable(INT.reference());
	IVariable b = body.addVariable(INT.reference());
	IVariableAssignment ass1 = body.addAssignment(a.valueOf(), literal(5));
	IVariableAssignment ass2 = body.addAssignment(b, a);
	IVariableAssignment ass3 = body.addAssignment(b, literal(7));
	
	
	
	public void test() {
		
		

	}
		
}
