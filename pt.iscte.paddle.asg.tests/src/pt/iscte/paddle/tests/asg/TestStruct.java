package pt.iscte.paddle.tests.asg;


import static org.junit.Assert.assertEquals;
import static pt.iscte.paddle.asg.ILiteral.literal;

import java.math.BigDecimal;

import org.junit.Test;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IDataType;
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
		IModule program = IModule.create("Struct");
		
		IStructType pointType = program.addStruct("Point");
		pointType.addMemberVariable("x", IDataType.INT);
		pointType.addMemberVariable("y", IDataType.INT);
		
		IProcedure moveProc = program.addProcedure("move", IDataType.VOID);
		IVariable pParam = moveProc.addParameter("p", pointType);
		
		moveProc.getBody().addStructMemberAssignment(pParam, "x", literal(7));
		
		IProcedure main = program.addProcedure("main", IDataType.INT);
		IBlock body = main.getBody();
		IVariable p = body.addVariable("pp", pointType);
		body.addAssignment(p, pointType.allocationExpression());
		
		body.addCall(moveProc, p);
		
		body.addReturn(p.member("x"));
		
		
		System.out.println(program);
		IProgramState state = IMachine.create(program);
		IExecutionData data = state.execute(main);
		
		assertEquals(new BigDecimal(7), data.getReturnValue().getValue());
	}
}
