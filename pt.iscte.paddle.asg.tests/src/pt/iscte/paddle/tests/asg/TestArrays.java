package pt.iscte.paddle.tests.asg;


import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IFactory;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IOperator;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.machine.IMachine;
import pt.iscte.paddle.machine.IProgramState;

public class TestArrays {

	@Test
	public void testNaturals() {
		IFactory factory = IFactory.INSTANCE;
		IModule program = factory.createModule("Arrays");
		
		IProcedure natFunc = program.addProcedure("naturals", factory.arrayType(IDataType.INT, 1));
		IVariable nParam = natFunc.addParameter("n", IDataType.INT);
		IBlock body = natFunc.getBody();
		IVariable vVar = body.addVariable("v", factory.arrayType(IDataType.INT, 1));
		vVar.addAssignment(factory.arrayAllocation(IDataType.INT, 1, nParam.expression()));
		
		IVariable iVar = body.addVariable("i", IDataType.INT);
		IExpression e = factory.binaryExpression(IOperator.SMALLER, iVar.expression(), nParam.expression());
		ILoop loop = body.addLoop(e);
		IBinaryExpression iPlus1 = factory.binaryExpression(IOperator.ADD, iVar.expression(), factory.literal(1));
		loop.arrayElementAssignment(vVar, iPlus1, iVar.expression());
		loop.addAssignment(iVar, factory.binaryExpression(IOperator.ADD, iVar.expression(), factory.literal(1)));
		body.addReturnStatement(vVar.expression());
		
		System.out.println(program);
		IProgramState state = IMachine.create(program);
		IExecutionData data = state.execute(natFunc, "5");
		IArray array = (IArray) data.getReturnValue();
		for(int i = 0; i < 5; i++)
			assertEquals(new BigDecimal(i+1), array.getElement(i).getValue());
	}
	
	public void testContainsReturn() {
		
	}
	
	public void testContainsBreak() {
		
	}

	public void testReplaceContinue() {
		
	}
	
	public void testMerge() {
		
	}
}
