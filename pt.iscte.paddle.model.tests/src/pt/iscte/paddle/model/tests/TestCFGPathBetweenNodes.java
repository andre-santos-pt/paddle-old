package pt.iscte.paddle.model.tests;

import static org.junit.Assert.assertEquals;
import static pt.iscte.paddle.model.IOperator.GREATER;
import static pt.iscte.paddle.model.IOperator.SMALLER;
import static pt.iscte.paddle.model.IType.INT;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.cfg.IBranchNode;
import pt.iscte.paddle.model.cfg.IControlFlowGraph;
import pt.iscte.paddle.model.cfg.IControlFlowGraph.Path;
import pt.iscte.paddle.model.cfg.INode;
import pt.iscte.paddle.model.cfg.IStatementNode;

public class TestCFGPathBetweenNodes extends BaseTest{

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

	@Override
	protected IControlFlowGraph cfg() {
		IControlFlowGraph cfg = max.getCFG();
		return super.cfg();
	}

	@Test
	public void pathsBetween() {
		List<INode> path1 = new ArrayList<INode>();
		List<INode> path2 = new ArrayList<INode>();

		IControlFlowGraph cfg = IControlFlowGraph.create(max);

		IStatementNode s_mAss = cfg.newStatement(mAss);
		cfg.getEntryNode().setNext(s_mAss);
		path1.add(s_mAss);
		path2.add(s_mAss);

		IStatementNode s_iAss = cfg.newStatement(iAss);
		s_mAss.setNext(s_iAss);
		path1.add(s_iAss);
		path2.add(s_iAss);
		

		IBranchNode b_loop = cfg.newBranch(loop.getGuard());
		s_iAss.setNext(b_loop);
		path1.add(b_loop);
		path2.add(b_loop);

		IBranchNode b_if = cfg.newBranch(ifstat.getGuard());
		b_loop.setBranch(b_if);
		path1.add(b_if);
		path2.add(b_if);

		IStatementNode s_mAss_ = cfg.newStatement(mAss_);
		b_if.setBranch(s_mAss_);
		path2.add(s_mAss_);

		IStatementNode s_iInc = cfg.newStatement(iInc);
		b_if.setNext(s_iInc);
		s_mAss_.setNext(s_iInc);
		path1.add(s_iInc);
		path2.add(s_iInc);

		s_iInc.setNext(b_loop);

		IStatementNode s_ret = cfg.newStatement(ret);
		b_loop.setNext(s_ret);

		s_ret.setNext(cfg.getExitNode());

		IControlFlowGraph cfg2 = max.getCFG();
		
		List<Path> paths = cfg2.pathsBetweenNodes(s_mAss, s_iInc);
		
		assertEquals(path2, paths.get(0).getNodes());
		assertEquals(path1, paths.get(1).getNodes());
	}

}
