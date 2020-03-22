import static pt.iscte.paddle.model.IOperator.GREATER;
import static pt.iscte.paddle.model.IOperator.SMALLER;
import static pt.iscte.paddle.model.IType.INT;

import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.cfg.IBranchNode;
import pt.iscte.paddle.model.cfg.IControlFlowGraph;
import pt.iscte.paddle.model.cfg.INode;
import pt.iscte.paddle.model.cfg.IStatementNode;

public class DemoControlFlowGraph {

	public static void main(String[] args) {
		IModule module = IModule.create();
		IProcedure max = module.addProcedure(INT);
		IVariableDeclaration array = max.addParameter(INT.array());
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

		// dead code
		IReturn ret_dead = body.addReturn(i);

		max.setId("max");
		array.setId("array");
		m.setId("m");
		i.setId("i");
		System.out.println(max);

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

		// dead code
		IStatementNode s_ret_dead = cfg.newStatement(ret_dead);
		s_ret_dead.setNext(cfg.getExitNode());

		cfg.getNodes().forEach(n -> System.out.println(n));

		for(INode n : cfg.reachability())
			System.out.println(n.isEntry() || n.isExit() ? n : n.getElement());

		System.out.println("dead: " + cfg.deadNodes());	

		IControlFlowGraph cfg2 = IControlFlowGraph.create(max);

		IStatementNode s_mAss2 = cfg.newStatement(mAss);
		cfg2.getEntryNode().setNext(s_mAss2);

		IStatementNode s_iAss2 = cfg.newStatement(iAss);
		s_mAss2.setNext(s_iAss2);

		IBranchNode b_loop2 = cfg.newBranch(loop.getGuard());
		s_iAss2.setNext(b_loop);

		System.out.println(cfg.isEquivalentTo(cfg2));

		System.out.println("\n<-------- PATHS BETWEEN TWO NODES! --------->");
		// All paths between two nodes.
		System.out.println("source: " + s_mAss);
		System.out.println("destination: " + s_iInc);
		System.out.println("The paths are: \n");
		cfg.pathsBetweenNodes(s_mAss, s_iInc).forEach(path -> {
			System.out.println("<------ BEGINNING ------>");
			path.getNodes().forEach(node -> System.out.println(node));
			System.out.println("<------ END ------>\n");
		});	
	}
}
