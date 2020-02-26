package pt.iscte.paddle.model.tests;
import static pt.iscte.paddle.model.IOperator.ADD;
import static pt.iscte.paddle.model.IOperator.GREATER;
import static pt.iscte.paddle.model.IOperator.SMALLER;
import static pt.iscte.paddle.model.IType.INT;

import pt.iscte.paddle.model.IArrayElement;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;

public class TestMaxBound extends BaseTest {
	IProcedure max = module.addProcedure(INT);
	IVariableDeclaration array = max.addParameter(INT.array().reference());
	IVariableDeclaration bound = max.addParameter(INT);
	IBlock body = max.getBody();

	IVariableDeclaration m = body.addVariable(INT);
	IVariableAssignment mAss = body.addAssignment(m, array.element(INT.literal(0)));
	IVariableDeclaration i = body.addVariable(INT);
	IVariableAssignment iAss = body.addAssignment(i, INT.literal(1));

	ILoop loop = body.addLoop(SMALLER.on(i, bound));
	IArrayElement e = array.element(i);
	ISelection ifstat = loop.addSelection(GREATER.on(e, m));
	IVariableAssignment mAss_ = ifstat.addAssignment(m, e);
	IVariableAssignment iAss_ = loop.addAssignment(i, ADD.on(i, INT.literal(1)));
	IReturn ret = body.addReturn(m);

}
