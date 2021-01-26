package pt.iscte.paddle.model.tests;
import static pt.iscte.paddle.model.IOperator.ADD;
import static pt.iscte.paddle.model.IOperator.DIFFERENT;
import static pt.iscte.paddle.model.IType.DOUBLE;
import static pt.iscte.paddle.model.IType.INT;

import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.cfg.IBranchNode;
import pt.iscte.paddle.model.cfg.IControlFlowGraph;
import pt.iscte.paddle.model.cfg.IStatementNode;

public class TestSum extends BaseTest {

	IProcedure summation = module.addProcedure(DOUBLE);
	IVariableDeclaration array = summation.addParameter(DOUBLE.array().reference());
	IBlock sumBody = summation.getBody();
	IVariableDeclaration sum = sumBody.addVariable(DOUBLE);
	IVariableAssignment sumAss = sumBody.addAssignment(sum, DOUBLE.literal(0.0));
	IVariableDeclaration i = sumBody.addVariable(INT);
	IVariableAssignment iAss = sumBody.addAssignment(i, INT.literal(0));
	ILoop loop = sumBody.addLoop(DIFFERENT.on(i, array.dereference().length()));
	IVariableAssignment ass1 = loop.addAssignment(sum, ADD.on(sum, array.dereference().element(i)));
	IVariableAssignment ass2 = loop.addIncrement(i);
	IReturn ret = sumBody.addReturn(sum);



	private IVariableDeclaration a;

	protected IProcedure main() {
		IProcedure test = module 	.addProcedure(DOUBLE);
		IBlock body = test.getBody();
		a = body.addVariable(DOUBLE.array().reference(), DOUBLE.array().heapAllocation(INT.literal(5)));
		body.addArrayElementAssignment(a, DOUBLE.literal(2.3), INT.literal(0));
		body.addArrayElementAssignment(a, DOUBLE.literal(3.1), INT.literal(1));
		body.addArrayElementAssignment(a, DOUBLE.literal(0.1), INT.literal(3));
		body.addArrayElementAssignment(a, DOUBLE.literal(10.0), INT.literal(4));

		IVariableDeclaration sum = body.addVariable(DOUBLE, summation.expression(a.address()));
		body.addReturn(sum);
		return test;
	}

	@Case
	public void test(IExecutionData data) {
		equal(15.5, data.getReturnValue());
		IArray a = (IArray) data.getVariableValue(this.a);
	}

	@Override
	protected IControlFlowGraph cfg() {
		IControlFlowGraph cfg = IControlFlowGraph.create(summation);

		IStatementNode s_sum = cfg.newStatement(sumAss);
		cfg.getEntryNode().setNext(s_sum);

		IStatementNode s_i = cfg.newStatement(iAss);
		s_sum.setNext(s_i);

		IBranchNode b_loop = cfg.newBranch(loop.getGuard());
		s_i.setNext(b_loop);

		IStatementNode s_ass1 = cfg.newStatement(ass1);
		b_loop.setBranch(s_ass1);

		IStatementNode s_ass2 = cfg.newStatement(ass2);
		s_ass1.setNext(s_ass2);

		s_ass2.setNext(b_loop);

		IStatementNode s_ret = cfg.newStatement(ret);
		b_loop.setNext(s_ret);

		s_ret.setNext(cfg.getExitNode());
		
		return cfg;
	}
}
