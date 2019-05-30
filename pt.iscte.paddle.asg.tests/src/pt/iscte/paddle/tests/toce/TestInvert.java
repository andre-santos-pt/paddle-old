package pt.iscte.paddle.tests.toce;
import static pt.iscte.paddle.asg.IType.*;
import static pt.iscte.paddle.asg.IOperator.*;

import pt.iscte.paddle.asg.IArrayElementAssignment;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProcedureCall;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestInvert extends BaseTest {
	private static IProcedure swap = new TestSwap().swap;
	static {
		swap.setId("swap");
	}
	
	IProcedure invert = module.addProcedure(VOID);
	IVariable array = invert.addParameter(INT.array().reference());
	
	IBlock body = invert.getBody();
	IVariable i = body.addVariable(INT, INT.literal(0));
	ILoop loop = body.addLoop(SMALLER.on(i, DIV.on(array.length(), INT.literal(2))));
	IProcedureCall swapCall = loop.addCall(swap, array, i, SUB.on(SUB.on(array.length(), INT.literal(1)), i));
	IVariableAssignment iInc = loop.addIncrement(i);
	
}
