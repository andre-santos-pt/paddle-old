package pt.iscte.paddle.tests.toce;
import static pt.iscte.paddle.asg.IOperator.ADD;
import static pt.iscte.paddle.asg.IOperator.SMALLER;
import static pt.iscte.paddle.asg.IOperator.SUB;
import static pt.iscte.paddle.asg.IType.INT;
import static pt.iscte.paddle.asg.IType.VOID;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProcedureCall;
import pt.iscte.paddle.asg.ISelection;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestSelectionSort extends BaseTest {

	private static IProcedure swap = importProcedure(TestSwap.class, "swap");
	
	IProcedure sort = module.addProcedure(VOID);
	IVariable array = sort.addParameter(INT.array().reference());
	IBlock body = sort.getBody();
	IVariable i = body.addVariable(INT, INT.literal(0));

	ILoop outloop = body.addLoop(SMALLER.on(i, SUB.on(array.length(), INT.literal(1))));
	IVariable min = outloop.addVariable(INT, i);
	IVariable j = outloop.addVariable(INT, ADD.on(i, INT.literal(1)));

	ILoop inloop = outloop.addLoop(SMALLER.on(j, array.length()));
	ISelection ifstat = inloop.addSelection(SMALLER.on(array.element(j), array.element(min)));
	IVariableAssignment minAss = ifstat.getBlock().addAssignment(min, j);
	IVariableAssignment jInc = inloop.addIncrement(j);
	IProcedureCall swapCall = outloop.addCall(swap, array, i, min);
	IVariableAssignment iInc = outloop.addIncrement(i);
}
