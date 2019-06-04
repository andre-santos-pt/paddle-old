package pt.iscte.paddle.tests.toce;
import static pt.iscte.paddle.asg.IOperator.DIV;
import static pt.iscte.paddle.asg.IOperator.SMALLER;
import static pt.iscte.paddle.asg.IOperator.SUB;
import static pt.iscte.paddle.asg.IType.INT;
import static pt.iscte.paddle.asg.IType.VOID;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProcedureCall;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestInvert extends BaseTest {
	private static IProcedure swap = importProcedure(TestSwap.class, "swap");
	
	IProcedure invert = module.addProcedure(VOID);
	IVariable array = invert.addParameter(INT.array().reference());
	
	IBlock body = invert.getBody();
	IVariable i = body.addVariable(INT, INT.literal(0));
	ILoop loop = body.addLoop(SMALLER.on(i, DIV.on(array.length(), INT.literal(2))));
	IProcedureCall swapCall = loop.addCall(swap, array, i, SUB.on(SUB.on(array.length(), INT.literal(1)), i));
	IVariableAssignment iInc = loop.addIncrement(i);
	
}
