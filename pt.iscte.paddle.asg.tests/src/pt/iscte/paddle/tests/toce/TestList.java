package pt.iscte.paddle.tests.toce;
import static pt.iscte.paddle.asg.IOperator.*;
import static pt.iscte.paddle.asg.IType.*;

import pt.iscte.paddle.asg.IArrayElementAssignment;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILiteral;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IRecordFieldAssignment;
import pt.iscte.paddle.asg.IRecordType;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.ISelection;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.machine.IValue;
import pt.iscte.paddle.tests.asg.BaseTest;


public class TestList extends BaseTest {

//	void m() {
		IRecordType Node = module.addRecordType();
		IVariable element = Node.addMemberVariable(INT);
		IVariable next = Node.addMemberVariable(Node); // TODO .reference()

		IRecordType IntList = module.addRecordType();
		IVariable head = IntList.addMemberVariable(Node);
		IVariable tail = IntList.addMemberVariable(Node);

		IProcedure init = module.addProcedure(VOID);
		IVariable list = init.addParameter(IntList);
		IBlock initBody = init.getBody();
		IRecordFieldAssignment hAss = initBody.addRecordMemberAssignment(list, head, ILiteral.NULL);
		IRecordFieldAssignment tAss = initBody.addRecordMemberAssignment(list, tail, ILiteral.NULL);
		
		
		IProcedure add = module.addProcedure(VOID);
		IVariable list_ = add.addParameter(IntList.reference());
		IVariable e = add.addParameter(INT);
		IBlock addBody = add.getBody();
		IVariable n = addBody.addVariable(Node, Node.allocationExpression());
		IRecordFieldAssignment nAss = addBody.addRecordMemberAssignment(n, element, e);
		ISelection checkEmpty = addBody.addSelectionWithAlternative(EQUAL.on(list_.fieldVariable(head), ILiteral.NULL));
		IRecordFieldAssignment hAss_ = checkEmpty.getBlock().addRecordMemberAssignment(list_, head, n);
		IRecordFieldAssignment tAss_ = checkEmpty.getBlock().addRecordMemberAssignment(list_, tail, n);
		
		IBlock elseBlock = checkEmpty.getAlternativeBlock();
		IRecordFieldAssignment tNextAss = elseBlock.addRecordMemberAssignment(list_, tail.fieldVariable(next), n);
		IRecordFieldAssignment tAss__ = elseBlock.addRecordMemberAssignment(list_, tail, n);

		
		IProcedure exists = module.addProcedure(BOOLEAN);
		IVariable list__ = exists.addParameter(IntList.reference());
		IVariable e_ = exists.addParameter(INT);
		IBlock existsBody = exists.getBody();
		IVariable n_ = existsBody.addVariable(Node, list__.field(head));  // TODO .reference()
		ILoop findLoop = existsBody.addLoop(DIFFERENT.on(n_, ILiteral.NULL));
		ISelection iffind = findLoop.addSelection(EQUAL.on(n_.field(element), e_));
		IReturn retTrue = iffind.getBlock().addReturn(BOOLEAN.literal(true));
		IVariableAssignment moveNext = findLoop.addAssignment(n_, n_.field(next));
		IReturn retFalse = existsBody.addReturn(BOOLEAN.literal(false));
//	}
}
