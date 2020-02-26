package pt.iscte.paddle.model.tests;
import static pt.iscte.paddle.model.IOperator.ADD;
import static pt.iscte.paddle.model.IOperator.SUB;
import static pt.iscte.paddle.model.IType.INT;
import static pt.iscte.paddle.model.IType.VOID;

import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IArrayElementAssignment;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IRecordFieldAssignment;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;


public class TestStack extends BaseTest {

	IRecordType IntStack = module.addRecordType();
	IVariableDeclaration elements = IntStack.addField(INT.array().reference());
	IVariableDeclaration next = IntStack.addField(INT);
	
	IProcedure init = module.addProcedure(VOID);
	IVariableDeclaration stack = init.addParameter(IntStack.reference());
	IVariableDeclaration size = init.addParameter(INT);
	IBlock initBody = init.getBody();
	IRecordFieldAssignment eAss = initBody.addRecordFieldAssignment(stack.field(elements), INT.array().heapAllocation(size));
	IRecordFieldAssignment nextAss = initBody.addRecordFieldAssignment(stack.field(next), INT.literal(0));
	
	IProcedure push = module.addProcedure(VOID);
	IVariableDeclaration stack_ = push.addParameter(IntStack.reference());
	IVariableDeclaration e = push.addParameter(INT);
	IBlock pushBody = push.getBody();
	IArrayElementAssignment eAss__ = pushBody.addArrayElementAssignment(stack_.field(elements), e, stack_.field(next));
	IRecordFieldAssignment nextInc = pushBody.addRecordFieldAssignment(stack_.field(next), ADD.on(stack_.field(next), INT.literal(1)));
	
	IProcedure pop = module.addProcedure(INT);
	IVariableDeclaration stack__ = pop.addParameter(IntStack.reference());
	IBlock popBody = pop.getBody();
	IVariableDeclaration t = popBody.addVariable(INT);
	IVariableAssignment tAss = popBody.addAssignment(t, stack__.field(elements).element(SUB.on(stack__.field(next), INT.literal(1))));
	IRecordFieldAssignment nextDec = popBody.addRecordFieldAssignment(stack__.field(next), SUB.on(stack__.field(next), INT.literal(1)));
	IReturn popRet = popBody.addReturn(t);
	
	
	private IVariableDeclaration first;
	private IVariableDeclaration second;
	private IVariableDeclaration third;
	
	
	protected IProcedure main() {
		IProcedure test = module.addProcedure(INT);
		IBlock body = test.getBody();
		IVariableDeclaration stack = body.addVariable(IntStack.reference());
		
		body.addAssignment(stack, IntStack.heapAllocation());
		body.addCall(init, stack, INT.literal(10));
		body.addCall(push, stack, INT.literal(3));
		body.addCall(push, stack, INT.literal(5));
		body.addCall(push, stack, INT.literal(7));
		
		first = body.addVariable(INT, pop.call(stack));
		second = body.addVariable(INT, pop.call(stack));
		third = body.addVariable(INT, pop.call(stack));
		
		return test;
	}
	
	@Case
	public void test(IExecutionData data) {
		equal(7, data.getVariableValue(first));
		equal(5, data.getVariableValue(second));
		equal(3, data.getVariableValue(third));
	}
}
