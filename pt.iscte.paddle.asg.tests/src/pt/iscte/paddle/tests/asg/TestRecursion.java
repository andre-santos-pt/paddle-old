package pt.iscte.paddle.tests.asg;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IFactory;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IOperator;
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
		IFactory factory = IFactory.INSTANCE;
		IModule program = factory.createModule("Test");
		program.loadBuildInProcedures(TestBuiltinProcedures.TestProcedures.class);
		IProcedure proc = program.addProcedure("fact", IDataType.INT);
		IVariable nParam = proc.addParameter("n", IDataType.INT);
		IBinaryExpression guard = factory.binaryExpression(IOperator.EQUAL, nParam.expression(), factory.literal(0));
		ISelectionWithAlternative sel = proc.getBody().addSelectionWithAlternative(guard);
		sel.addReturnStatement(factory.literal(1));
		IProcedureCallExpression recCall = proc.callExpression(factory.binaryExpression(IOperator.SUB, nParam.expression(), factory.literal(1)));
		IBinaryExpression retExp = factory.binaryExpression(IOperator.MUL, nParam.expression(), recCall);
		sel.getAlternativeBlock().addReturnStatement(retExp);
		System.out.println(program);
		IProgramState state = IMachine.create(program);
		IExecutionData data = state.execute(proc, 5);
		assertEquals(new BigDecimal(120), data.getReturnValue().getValue());
	}
}
