package pt.iscte.paddle.model.tests;

import static org.junit.Assert.*;
import static pt.iscte.paddle.model.IOperator.AND;
import static pt.iscte.paddle.model.IOperator.EQUAL;
import static pt.iscte.paddle.model.IOperator.NOT;
import static pt.iscte.paddle.model.IOperator.SMALLER;
import static pt.iscte.paddle.model.IType.BOOLEAN;
import static pt.iscte.paddle.model.IType.INT;

import org.junit.Test;

import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;

// TODO 
public class TestIsSame {

	@Test
	public void test() {
		IModule module = IModule.create();
		IProcedure exists2 = module.addProcedure(BOOLEAN);
		IVariableDeclaration array2 = exists2.addParameter(INT.array().reference());
		IVariableDeclaration e2 = exists2.addParameter(INT);
		IBlock body2 = exists2.getBody();
		IVariableDeclaration found2 = body2.addVariable(BOOLEAN);
		IVariableAssignment foundAss2 = body2.addAssignment(found2, BOOLEAN.literal(false));
		IVariableDeclaration i2 = body2.addVariable(INT);
		IVariableAssignment iAss2 = body2.addAssignment(i2, INT.literal(0));
		ILoop loop2 = body2.addLoop(AND.on(NOT.on(found2), SMALLER.on(i2, array2.length())));
		ISelection ifstat2 = loop2.addSelection(EQUAL.on(array2.element(i2), e2));
		IVariableAssignment foundAss_2 = ifstat2.addAssignment(found2, BOOLEAN.literal(true));
		IVariableAssignment iInc2 = loop2.addIncrement(i2);
		IReturn ret2 = body2.addReturn(found2);
		
		
	}

}
