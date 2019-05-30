package pt.iscte.paddle.tests.toce;
import static pt.iscte.paddle.asg.IType.INT;
import static pt.iscte.paddle.asg.IType.VOID;

import pt.iscte.paddle.asg.IArrayElementAssignment;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestSwap extends BaseTest {

	IProcedure swap = module.addProcedure(VOID);
	IVariable array = swap.addParameter(INT.array().reference());
	IVariable i = swap.addParameter(INT);
	IVariable j = swap.addParameter(INT);
	
	IBlock body = swap.getBody();
	IVariable t = body.addVariable(INT, array.dereference().element(i));
	IArrayElementAssignment ass = body.addArrayElementAssignment(array.dereference(), array.dereference().element(j), i);
	IArrayElementAssignment ass0 = body.addArrayElementAssignment(array.dereference(), t, j);
	
}
