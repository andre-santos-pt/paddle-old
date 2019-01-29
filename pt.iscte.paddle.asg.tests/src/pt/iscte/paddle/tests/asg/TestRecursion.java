package pt.iscte.paddle.tests.asg;

import static org.junit.Assert.assertEquals;
import static pt.iscte.paddle.asg.IDataType.INT;
import static pt.iscte.paddle.asg.ILiteral.literal;
import static pt.iscte.paddle.asg.IOperator.EQUAL;
import static pt.iscte.paddle.asg.IOperator.MUL;
import static pt.iscte.paddle.asg.IOperator.SUB;

import java.math.BigDecimal;

import org.junit.Test;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProcedureCallExpression;
import pt.iscte.paddle.asg.ISelectionWithAlternative;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.machine.IMachine;
import pt.iscte.paddle.machine.IProgramState;

public class TestRecursion {
	@Test
	public void test() {
		IModule program = IModule.create("Test");
		program.loadBuildInProcedures(TestBuiltinProcedures.TestProcedures.class);
		IProcedure proc = program.addProcedure("fact", INT);
		IVariable nParam = proc.addParameter("n", INT);
		IBinaryExpression guard = EQUAL.on(nParam, literal(0));
		ISelectionWithAlternative sel = proc.getBody().addSelectionWithAlternative(guard);
		sel.addReturn(literal(1));
		IProcedureCallExpression recCall = proc.call(SUB.on(nParam, literal(1)));
		IBinaryExpression retExp = MUL.on(nParam, recCall);
		sel.getAlternativeBlock().addReturn(retExp);
		System.out.println(program);
		IProgramState state = IMachine.create(program);
		IExecutionData data = state.execute(proc, 5);
		assertEquals(new BigDecimal(120), data.getReturnValue().getValue());
	}
}
