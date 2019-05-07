package pt.iscte.paddle.tests.asg;

import static pt.iscte.paddle.asg.IOperator.EQUAL;
import static pt.iscte.paddle.asg.IOperator.MUL;
import static pt.iscte.paddle.asg.IOperator.SUB;
import static pt.iscte.paddle.asg.IType.INT;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProcedureCall;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.ISelection;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IExecutionData;

public class TestRecursion extends BaseTest {	
	IProcedure fact = module.addProcedure(INT);
	IVariable n = fact.addParameter(INT);
	IBinaryExpression guard = EQUAL.on(n, INT.literal(0));
	ISelection sel = fact.getBody().addSelectionWithAlternative(guard);
	IReturn return1 = sel.addReturn(INT.literal(1));
	IProcedureCall recCall = fact.call(SUB.on(n, INT.literal(1)));
	IBinaryExpression retExp = MUL.on(n, recCall);
	IBlock elseBlock = sel.getAlternativeBlock();
	IReturn return2 = elseBlock.addReturn(retExp);
	
	@Case("5")
	public void test(IExecutionData data) {
		equal(120, data.getReturnValue());
	}
}
