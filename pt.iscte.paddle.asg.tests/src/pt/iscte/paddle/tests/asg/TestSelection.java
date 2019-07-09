package pt.iscte.paddle.tests.asg;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pt.iscte.paddle.model.IOperator.GREATER;
import static pt.iscte.paddle.model.IType.INT;

import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IBinaryExpression;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IOperator;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IVariable;

// TODO if if
public class TestSelection extends BaseTest {
	
	IProcedure max = module.addProcedure(INT);
	IVariable a = max.addParameter(INT);
	IVariable b = max.addParameter(INT);
	IBinaryExpression guard = GREATER.on(a, b);
	ISelection ifElse = max.getBody().addSelectionWithAlternative(guard);
	IReturn ret = ifElse.addReturn(a);
	IBlock elseBlock = ifElse.getAlternativeBlock();
	IReturn retElse = elseBlock.addReturn(b);
	

	@Case({"2", "-1"})
	public void caseFirst(IExecutionData data) {
		assertTrue(data.getReturnValue().toString().equals("2"));
	}
	
	@Case({"2", "4"})
	public void caseSecond(IExecutionData data) {
		assertTrue(data.getReturnValue().toString().equals("4"));
	}
	
	public void commonAsserts(IExecutionData data) {
		assertEquals(0, data.getTotalAssignments());
		assertEquals(1, data.getOperationCount(IOperator.OperationType.RELATIONAL));
		assertEquals(1, data.getCallStackDepth());
	}
}
