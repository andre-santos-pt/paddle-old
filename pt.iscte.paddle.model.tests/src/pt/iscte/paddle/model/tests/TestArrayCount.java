package pt.iscte.paddle.model.tests;
import static org.junit.Assert.assertEquals;
import static pt.iscte.paddle.model.IOperator.EQUAL;
import static pt.iscte.paddle.model.IOperator.SMALLER;
import static pt.iscte.paddle.model.IType.INT;

import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.ILiteral;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.cfg.IBranchNode;
import pt.iscte.paddle.model.cfg.IControlFlowGraph;
import pt.iscte.paddle.model.cfg.IStatementNode;

public class TestArrayCount extends BaseTest {

	IProcedure count = module.addProcedure(INT);
	IVariableDeclaration array = count.addParameter(INT.array().reference());
	IVariableDeclaration e = count.addParameter(INT);
	IBlock body = count.getBody();
	IVariableDeclaration c = body.addVariable(INT);
	IVariableAssignment cAss = body.addAssignment(c, INT.literal(0));
	IVariableDeclaration i = body.addVariable(INT);
	IVariableAssignment iAss = body.addAssignment(i, INT.literal(0));
	ILoop loop = body.addLoop(SMALLER.on(i, array.length()));
	ISelection ifstat = loop.addSelection(EQUAL.on(array.element(i), e));
	IVariableAssignment cAss_ = ifstat.addIncrement(c);
	IVariableAssignment iInc = loop.addIncrement(i);
	IReturn ret = body.addReturn(c);

	private IVariableDeclaration a;

	private int element = 3;
	private int[] integers = {-2, element, 1, 4, 5, element, 10, 11, 20, element};
	private ILiteral[] literals = literalIntArray(integers);

	protected IProcedure main() {
		IProcedure test = module.addProcedure(INT);
		IBlock body = test.getBody();
		a = body.addVariable(INT.array().reference(), INT.array().heapAllocation(INT.literal(literals.length)));

		for(int i = 0; i < literals.length; i++)
			body.addArrayElementAssignment(a, literals[i], INT.literal(i));

		body.addReturn(count.expression(a, INT.literal(element)));
		return test;
	}
	
	@Override
	protected IControlFlowGraph cfg() {
		IControlFlowGraph cfg = IControlFlowGraph.create(count);
		
		IStatementNode s_cAss = cfg.newStatement(cAss);
		cfg.getEntryNode().setNext(s_cAss);

		IStatementNode s_iAss = cfg.newStatement(iAss);
		s_cAss.setNext(s_iAss);

		IBranchNode b_loop = cfg.newBranch(loop.getGuard());
		s_iAss.setNext(b_loop);

		IBranchNode b_ifstat = cfg.newBranch(ifstat.getGuard());
		b_loop.setBranch(b_ifstat);

		IStatementNode s_cAss_ = cfg.newStatement(cAss_);
		b_ifstat.setBranch(s_cAss_);

		IStatementNode s_iInc = cfg.newStatement(iInc);
		s_cAss_.setNext(s_iInc);
		b_ifstat.setNext(s_iInc);

		s_iInc.setNext(b_loop);

		IStatementNode s_ret = cfg.newStatement(ret);
		b_loop.setNext(s_ret);

		s_ret.setNext(cfg.getExitNode());
		return cfg;
	}


	@Case
	public void test(IExecutionData data) {
		equal(element, data.getReturnValue());
		IArray array = (IArray) data.getVariableValue(a);
		for(int i = 0; i < integers.length; i++)
			assertEquals(new Integer(integers[i]), new Integer(array.getElement(i).toString()));
	}
}
