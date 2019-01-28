package pt.iscte.paddle.tests.asg;


import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IFactory;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IStructType;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.machine.IMachine;
import pt.iscte.paddle.machine.IProgramState;

public class TestStruct {

	@Test
	public void test() {
		IFactory factory = IFactory.INSTANCE;
		IModule program = factory.createModule("Struct");
		
		IStructType pointType = program.addStruct("Point");
		pointType.addMemberVariable("x", IDataType.INT);
		pointType.addMemberVariable("y", IDataType.INT);
		
		IProcedure moveProc = program.addProcedure("move", IDataType.VOID);
		IVariable pParam = moveProc.addParameter("p", pointType);
		
		pParam.addMemberAssignment("x", factory.literal(7));
		
		IProcedure main = program.addProcedure("main", IDataType.INT);
		IBlock body = main.getBody();
		IVariable pVar = body.addVariable("pp", pointType);
		pVar.addAssignment(pointType.allocationExpression());
		
		body.addProcedureCall(moveProc, pVar.expression());
		
		body.addReturnStatement(pVar.memberExpression("x"));
		
		
		System.out.println(program);
		IProgramState state = IMachine.create(program);
		IExecutionData data = state.execute(main);
		
		assertEquals(new BigDecimal(7), data.getReturnValue().getValue());
	}
}
