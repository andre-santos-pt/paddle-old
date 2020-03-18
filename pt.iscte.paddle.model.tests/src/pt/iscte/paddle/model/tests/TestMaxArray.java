package pt.iscte.paddle.model.tests;
import static pt.iscte.paddle.model.IOperator.GREATER;
import static pt.iscte.paddle.model.IOperator.SMALLER;
import static pt.iscte.paddle.model.IType.INT;

import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.cfg.IBranchNode;
import pt.iscte.paddle.model.cfg.IControlFlowGraph;
import pt.iscte.paddle.model.cfg.IStatementNode;

public class TestMaxArray extends BaseTest {

	IProcedure max = module.addProcedure(INT);
	IVariableDeclaration array = max.addParameter(INT.array().reference());
	IBlock body = max.getBody();
	IVariableDeclaration m = body.addVariable(INT);
	IVariableAssignment mAss = body.addAssignment(m, array.element(INT.literal(0)));
	IVariableDeclaration i = body.addVariable(INT);
	IVariableAssignment iAss = body.addAssignment(i, INT.literal(1));
	ILoop loop = body.addLoop(SMALLER.on(i, array.length()));
	ISelection ifstat = loop.addSelection(GREATER.on(array.element(i), m));
	IVariableAssignment mAss_ = ifstat.addAssignment(m, array.element(i));
	IVariableAssignment iInc = loop.addIncrement(i);
	IReturn ret = body.addReturn(m);
	
	
	protected IProcedure main() {
		IProcedure test = module.addProcedure(INT);
		IBlock body = test.getBody();
		IVariableDeclaration a = body.addVariable(INT.array().reference(), INT.array().heapAllocation(INT.literal(5)));
		IVariableDeclaration zero = body.addVariable(INT, INT.literal(0));
		body.addArrayElementAssignment(a, INT.literal(3), zero);
		body.addArrayElementAssignment(a, INT.literal(1), INT.literal(1));
		body.addArrayElementAssignment(a, INT.literal(4), INT.literal(3));
		body.addArrayElementAssignment(a, INT.literal(-2), INT.literal(4));
		body.addReturn(max.expression(a));
		return test;
	}
	
	@Case
	public void test(IExecutionData data) {
		equal(4, data.getReturnValue());
	}
	
	@Override
	protected IControlFlowGraph cfg() {
		IControlFlowGraph cfg = IControlFlowGraph.create(max);
		
		IStatementNode s_mAss = cfg.newStatement(mAss);
		cfg.getEntryNode().setNext(s_mAss);

		IStatementNode s_iAss = cfg.newStatement(iAss);
		s_mAss.setNext(s_iAss);

		IBranchNode b_loop = cfg.newBranch(loop.getGuard());
		s_iAss.setNext(b_loop);
		
		IBranchNode b_if = cfg.newBranch(ifstat.getGuard());
		b_loop.setBranch(b_if);
		
		IStatementNode s_mAss_ = cfg.newStatement(mAss_);
		b_if.setBranch(s_mAss_);
		
		IStatementNode s_iInc = cfg.newStatement(iInc);
		b_if.setNext(s_iInc);
		s_mAss_.setNext(s_iInc);
		
		s_iInc.setNext(b_loop);
		
		IStatementNode s_ret = cfg.newStatement(ret);
		b_loop.setNext(s_ret);

		s_ret.setNext(cfg.getExitNode());
		
		return cfg;
	}
}
