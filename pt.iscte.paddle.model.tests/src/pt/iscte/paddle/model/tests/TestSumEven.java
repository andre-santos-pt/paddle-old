package pt.iscte.paddle.model.tests;

import static pt.iscte.paddle.model.IOperator.ADD;
import static pt.iscte.paddle.model.IOperator.DIFFERENT;
import static pt.iscte.paddle.model.IOperator.MOD;
import static pt.iscte.paddle.model.IOperator.SMALLER_EQ;
import static pt.iscte.paddle.model.IType.INT;

import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IVariable;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestSumEven extends BaseTest {
	IProcedure sumEven = module.addProcedure(INT);
	IVariable from = sumEven.addParameter(INT);
	IVariable to = sumEven.addParameter(INT);
	IBlock body = sumEven.getBody();
	IVariable sum = body.addVariable(INT);
	IVariableAssignment sumAss = body.addAssignment(sum, INT.literal(0));
	IVariable i = body.addVariable(INT);
	IVariableAssignment iAss = body.addAssignment(i, from);
	ISelection ifNotEven = body.addSelection(DIFFERENT.on(MOD.on(i, INT.literal(2)), INT.literal(0)));
	IVariableAssignment iToEven = ifNotEven.getBlock().addIncrement(i);
	ILoop loop = body.addLoop(SMALLER_EQ.on(i, to));
	IVariableAssignment sumAss_ = loop.addAssignment(sum, ADD.on(sum, i));
	IVariableAssignment iAss_ = loop.addAssignment(i, ADD.on(i, INT.literal(2)));
	IReturn ret = body.addReturn(sum);
	
	@Case({"4","10"})
	public void testEvenToEven(IExecutionData data) {
		equal(28, data.getReturnValue());
	}
	
	@Case({"3","10"})
	public void testOddToEven(IExecutionData data) {
		equal(28, data.getReturnValue());
	}
	
	@Case({"4","9"})
	public void testEvenToOdd(IExecutionData data) {
		equal(18, data.getReturnValue());
	}
	
	@Case({"3","9"})
	public void testOddToOdd(IExecutionData data) {
		equal(18, data.getReturnValue());
	}
}
