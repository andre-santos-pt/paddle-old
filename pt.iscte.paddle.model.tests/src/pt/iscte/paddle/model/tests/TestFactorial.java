package pt.iscte.paddle.model.tests;

import static org.junit.Assert.assertFalse;
import static pt.iscte.paddle.model.IOperator.EQUAL;
import static pt.iscte.paddle.model.IOperator.MUL;
import static pt.iscte.paddle.model.IOperator.SUB;
import static pt.iscte.paddle.model.IType.INT;

import org.junit.Test;

import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IBinaryExpression;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProcedureCallExpression;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.cfg.IBranchNode;
import pt.iscte.paddle.model.cfg.IControlFlowGraph;
import pt.iscte.paddle.model.cfg.IStatementNode;

public class TestFactorial extends BaseTest {	
	IProcedure factorial = module.addProcedure(INT);
	IVariableDeclaration n = factorial.addParameter(INT);
	IBinaryExpression guard = EQUAL.on(n, INT.literal(0));
	ISelection sel = factorial.getBody().addSelectionWithAlternative(guard);
	IReturn return1 = sel.addReturn(INT.literal(1));
	IProcedureCallExpression recCall = factorial.expression(SUB.on(n, INT.literal(1)));
	IBinaryExpression retExp = MUL.on(n, recCall);
	IBlock elseBlock = sel.getAlternativeBlock();
	IReturn return2 = elseBlock.addReturn(retExp);

	@Test
	public void test() {
		assertFalse(factorial.isConstantTime());
	}

	@Case("0")
	public void testBaseCase(IExecutionData data) {
		equal(1, data.getReturnValue());
		System.out.println(factorial.isRecursive());
	}

	@Case("5")
	public void testRecursiveCase(IExecutionData data) {
		equal(120, data.getReturnValue());
	}

	@Override
	protected IControlFlowGraph cfg() {
		IControlFlowGraph cfg = IControlFlowGraph.create(factorial);

		IBranchNode ifGuard = cfg.newBranch(guard);
		cfg.getEntryNode().setNext(ifGuard);

		IStatementNode firstReturn = cfg.newStatement(return1);
		ifGuard.setBranch(firstReturn);
		firstReturn.setNext(cfg.getExitNode());

		IStatementNode secondReturn = cfg.newStatement(return2);
		ifGuard.setNext(secondReturn);
		secondReturn.setNext(cfg.getExitNode());

		return cfg;

	}
}
