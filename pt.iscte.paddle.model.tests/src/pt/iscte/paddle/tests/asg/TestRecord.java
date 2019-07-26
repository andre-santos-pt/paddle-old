package pt.iscte.paddle.tests.asg;


import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProcedureCall;
import pt.iscte.paddle.model.IRecordFieldAssignment;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariable;
import pt.iscte.paddle.model.IVariableAssignment;


public class TestRecord extends BaseTest {

	IRecordType Point = module.addRecordType();
	IVariable x = Point.addField(IType.INT);
	IVariable y = Point.addField(IType.INT);
	
	IProcedure move = module.addProcedure(IType.VOID);
	IVariable p = move.addParameter(Point.reference());
	IBlock moveBody = move.getBody();
	IRecordFieldAssignment ass = moveBody.addRecordFieldAssignment(p.field(x), IType.INT.literal(7));
	
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
