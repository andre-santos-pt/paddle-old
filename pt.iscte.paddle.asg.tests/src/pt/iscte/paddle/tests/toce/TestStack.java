package pt.iscte.paddle.tests.toce;
import static pt.iscte.paddle.asg.IOperator.ADD;
import static pt.iscte.paddle.asg.IOperator.SUB;
import static pt.iscte.paddle.asg.IType.INT;
import static pt.iscte.paddle.asg.IType.VOID;

import pt.iscte.paddle.asg.IArrayElementAssignment;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IRecordFieldAssignment;
import pt.iscte.paddle.asg.IRecordType;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.tests.asg.BaseTest;


public class TestStack extends BaseTest {

	IRecordType IntStack = module.addRecordType();
	IVariable elements = IntStack.addField(INT.array());
	IVariable next = IntStack.addField(INT);
	
	
	IProcedure init = module.addProcedure(VOID);
	IVariable stack = init.addParameter(IntStack.reference());
	IVariable size = init.addParameter(INT);
	IBlock initBody = init.getBody();
	IRecordFieldAssignment eAss = initBody.addRecordMemberAssignment(stack, elements, INT.array().allocation(size));
	IRecordFieldAssignment nextAss = initBody.addRecordMemberAssignment(stack, next, INT.literal(0));
	
	IProcedure push = module.addProcedure(VOID);
	IVariable stack_ = push.addParameter(IntStack.reference());
	IVariable e = push.addParameter(INT);
	IBlock pushBody = push.getBody();
	IArrayElementAssignment eAss__ = pushBody.addArrayElementAssignment(stack_.fieldVariable(elements), e, stack_.field(next));
	IRecordFieldAssignment nextInc = pushBody.addRecordMemberAssignment(stack_, next, ADD.on(stack_.field(next), INT.literal(1)));
	
	IProcedure pop = module.addProcedure(INT);
	IVariable stack__ = pop.addParameter(IntStack.reference());
	IBlock popBody = pop.getBody();
	IVariable t = popBody.addVariable(INT);
	IVariableAssignment tAss = popBody.addAssignment(t, stack_.fieldVariable(elements).element(SUB.on(stack_.field(next), INT.literal(1))));
	IRecordFieldAssignment nextDec = popBody.addRecordMemberAssignment(stack_, next, SUB.on(stack_.field(next), INT.literal(1)));
	IReturn popRet = popBody.addReturn(t);
}
