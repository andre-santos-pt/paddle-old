package pt.iscte.paddle.model.tests;
import static pt.iscte.paddle.model.IOperator.DIFFERENT;
import static pt.iscte.paddle.model.IOperator.EQUAL;
import static pt.iscte.paddle.model.IType.BOOLEAN;
import static pt.iscte.paddle.model.IType.INT;
import static pt.iscte.paddle.model.IType.VOID;

import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.ILiteral;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IRecordFieldAssignment;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IVariable;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.tests.asg.BaseTest;


public class TestList extends BaseTest {

	IRecordType Node = module.addRecordType();
	IVariable element = Node.addField(INT);
	IVariable next = Node.addField(Node.reference());

	IRecordType IntList = module.addRecordType();
	IVariable head = IntList.addField(Node.reference());
	IVariable tail = IntList.addField(Node.reference());

	IProcedure init = module.addProcedure(VOID);
	IVariable list = init.addParameter(IntList.reference());
	IBlock initBody = init.getBody();
	IRecordFieldAssignment hAss = initBody.addRecordFieldAssignment(list.field(head), ILiteral.NULL);
	IRecordFieldAssignment tAss = initBody.addRecordFieldAssignment(list.field(tail), ILiteral.NULL);


	IProcedure add = module.addProcedure(VOID);
	IVariable list_ = add.addParameter(IntList.reference());
	IVariable e = add.addParameter(INT);
	IBlock addBody = add.getBody();
	IVariable n = addBody.addVariable(Node.reference(), Node.heapAllocation());
	IRecordFieldAssignment nAss = addBody.addRecordFieldAssignment(n.field(element), e);
	ISelection checkEmpty = addBody.addSelectionWithAlternative(EQUAL.on(list_.field(head), ILiteral.NULL));
	IRecordFieldAssignment hAss_ = checkEmpty.getBlock().addRecordFieldAssignment(list_.field(head), n);
	IRecordFieldAssignment tAss_ = checkEmpty.getBlock().addRecordFieldAssignment(list_.field(tail), n);

	IBlock elseBlock = checkEmpty.getAlternativeBlock();
	IRecordFieldAssignment tNextAss = elseBlock.addRecordFieldAssignment(list_.field(tail).field(next), n); // problem
	IRecordFieldAssignment tAss__ = elseBlock.addRecordFieldAssignment(list_.field(tail), n);


	IProcedure exists = module.addProcedure(BOOLEAN);
	IVariable list__ = exists.addParameter(IntList.reference());
	IVariable e_ = exists.addParameter(INT);
	IBlock existsBody = exists.getBody();
	IVariable n_ = existsBody.addVariable(Node.reference(), list__.field(head));
	ILoop findLoop = existsBody.addLoop(DIFFERENT.on(n_, ILiteral.NULL));
	ISelection iffind = findLoop.addSelection(EQUAL.on(n_.field(element), e_));
	IReturn retTrue = iffind.getBlock().addReturn(BOOLEAN.literal(true));
	IVariableAssignment moveNext = findLoop.addAssignment(n_, n_.field(next));
	IReturn retFalse = existsBody.addReturn(BOOLEAN.literal(false));
	
	
	private IVariable existsTrue;
	private IVariable existsFalse;
	
	protected IProcedure main() {
		IProcedure test = module.addProcedure(INT);
		IBlock body = test.getBody();
		IVariable list = body.addVariable(IntList.reference());
		
		body.addAssignment(list, IntList.heapAllocation());
		body.addCall(init, list);
		body.addCall(add, list, INT.literal(3));
		body.addCall(add, list, INT.literal(5));
		body.addCall(add, list, INT.literal(7));
		
		existsTrue = body.addVariable(BOOLEAN, exists.call(list, INT.literal(7)));
		existsFalse = body.addVariable(BOOLEAN, exists.call(list, INT.literal(6)));
		
		return test;
	}
	
	@Case
	public void test(IExecutionData data) {
		isTrue(data.getVariableValue(existsTrue));
		isFalse(data.getVariableValue(existsFalse));
	}
}
