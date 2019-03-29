package pt.iscte.paddle.tests.asg;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pt.iscte.paddle.asg.IDataType.INT;
import static pt.iscte.paddle.asg.IOperator.GREATER;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IOperator;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.ISelection;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IExecutionData;

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
