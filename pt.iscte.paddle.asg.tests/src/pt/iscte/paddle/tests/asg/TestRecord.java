package pt.iscte.paddle.tests.asg;


import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProcedureCall;
import pt.iscte.paddle.asg.IRecordFieldAssignment;
import pt.iscte.paddle.asg.IRecordType;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.machine.IExecutionData;


public class TestRecord extends BaseTest {

	IRecordType Point = module.addRecordType();
	IVariable x = Point.addField(IType.INT);
	IVariable y = Point.addField(IType.INT);
	
	IProcedure move = module.addProcedure(IType.VOID);
	IVariable p = move.addParameter(Point.reference());
	IBlock moveBody = move.getBody();
	IRecordFieldAssignment ass = moveBody.addRecordMemberAssignment(p, x, IType.INT.literal(7));
	
	IProcedure main = module.addProcedure(IType.INT);
	IBlock mainBody = main.getBody();
	IVariable point = mainBody.addVariable(Point);
	IVariableAssignment ass2 = mainBody.addAssignment(point, Point.heapAllocation());
	IProcedureCall addCall = mainBody.addCall(move, point.address());
	IReturn addReturn = mainBody.addReturn(point.field(x));

	@Case
	public void test(IExecutionData data) {
		equal(7, data.getReturnValue());
	}
}
