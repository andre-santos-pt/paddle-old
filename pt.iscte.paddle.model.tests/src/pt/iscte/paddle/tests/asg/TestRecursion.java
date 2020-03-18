package pt.iscte.paddle.tests.asg;

import static pt.iscte.paddle.model.IOperator.EQUAL;
import static pt.iscte.paddle.model.IOperator.MUL;
import static pt.iscte.paddle.model.IOperator.SUB;
import static pt.iscte.paddle.model.IType.INT;

import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IBinaryExpression;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProcedureCall;
import pt.iscte.paddle.model.IProcedureCallExpression;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.tests.BaseTest;

public class TestRecursion extends BaseTest {	
	IProcedure fact = module.addProcedure(INT);
	IVariableDeclaration n = fact.addParameter(INT);
	IBinaryExpression guard = EQUAL.on(n, INT.literal(0));
	ISelection sel = fact.getBody().addSelectionWithAlternative(guard);
	IReturn return1 = sel.addReturn(INT.literal(1));
	IProcedureCallExpression recCall = fact.expression(SUB.on(n, INT.literal(1)));
	IBinaryExpression retExp = MUL.on(n, recCall);
	IBlock elseBlock = sel.getAlternativeBlock();
	IReturn return2 = elseBlock.addReturn(retExp);
	
	@Case("5")
	public void test(IExecutionData data) {
		equal(120, data.getReturnValue());
	}
}
