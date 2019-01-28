package pt.iscte.paddle.tests.asg;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IFactory;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IOperator;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.ISelectionWithAlternative;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.machine.IMachine;
import pt.iscte.paddle.machine.IProgramState;

public class Test2DArrays {

	@Test
	public void testIdMatrix() {
		IFactory factory = IFactory.INSTANCE;
		IModule program = factory.createModule("Arrays2D");
		
		IProcedure idFunc = program.addProcedure("idMatrix", factory.arrayType(IDataType.INT, 2));
		IVariable nParam = idFunc.addParameter("n", IDataType.INT);
		
		IBlock body = idFunc.getBody();
		IVariable idVar = body.addVariable("id", factory.arrayType(IDataType.INT, 2));
		idVar.addAssignment(factory.arrayAllocation(IDataType.INT, 2, nParam.expression(), nParam.expression()));
		IVariable iVar = body.addVariable("i", IDataType.INT);
		IExpression e = factory.binaryExpression(IOperator.DIFFERENT, iVar.expression(), nParam.expression());
		ILoop loop = body.addLoop(e);
		loop.arrayElementAssignment(idVar, factory.literal(1), iVar.expression(), iVar.expression());
		loop.addAssignment(iVar, factory.binaryExpression(IOperator.ADD, iVar.expression(), factory.literal(1)));
		
		body.addReturnStatement(idVar.expression());

		System.out.println(program);
		final int N = 4;
		final BigDecimal ZERO = new BigDecimal(0);
		final BigDecimal ONE = new BigDecimal(1);
		
		IProgramState state = IMachine.create(program);
		IExecutionData data = state.execute(idFunc, N);
		IArray returnValue = (IArray) data.getReturnValue();
		assertEquals(N, returnValue.getLength());
		for(int i = 0; i < N; i++) {
			IArray line = (IArray) returnValue.getElement(i);
			assertEquals(N, line.getLength());
			for(int j = 0; j < N; j++)
				assertEquals(i == j ? ONE : ZERO, line.getElement(j).getValue());
		}
	}


	@Test
	public void testNatMatrix() {
		IFactory factory = IFactory.INSTANCE;
		IModule program = factory.createModule("NatMatrix");
		
		IProcedure natFunc = program.addProcedure("natMatrix", factory.arrayType(IDataType.INT, 2));
		IVariable linesParam = natFunc.addParameter("lines", IDataType.INT);
		IVariable colsParam = natFunc.addParameter("cols", IDataType.INT);
		
		IBlock body = natFunc.getBody();
		
		IVariable mVar = body.addVariable("m", factory.arrayType(IDataType.INT, 2));
		mVar.addAssignment(factory.arrayAllocation(IDataType.INT, 2, linesParam.expression(), colsParam.expression()));
		
		IVariable iVar = body.addVariable("i", IDataType.INT);
		iVar.addAssignment(factory.literal(0));
		
		IVariable jVar = body.addVariable("j", IDataType.INT);
		IVariable nVar = body.addVariable("n", IDataType.INT);
		nVar.addAssignment(factory.literal(1));

		IExpression outerGuard = factory.binaryExpression(IOperator.DIFFERENT, iVar.expression(), linesParam.expression());
		ILoop outerLoop = body.addLoop(outerGuard);
		outerLoop.addAssignment(jVar, factory.literal(0));
		IExpression innerGuard = factory.binaryExpression(IOperator.DIFFERENT, jVar.expression(), colsParam.expression());
		ILoop innerLoop = outerLoop.addLoop(innerGuard);
		innerLoop.arrayElementAssignment(mVar, nVar.expression(), iVar.expression(), jVar.expression());
		innerLoop.addAssignment(jVar, factory.binaryExpression(IOperator.ADD, jVar.expression(), factory.literal(1)));
		innerLoop.addAssignment(nVar, factory.binaryExpression(IOperator.ADD, nVar.expression(), factory.literal(1)));
		
		outerLoop.addAssignment(iVar, factory.binaryExpression(IOperator.ADD, iVar.expression(), factory.literal(1)));
		
		body.addReturnStatement(mVar.expression());
		
		final int L = 2;
		final int C = 4;
		IProgramState state = IMachine.create(program);
		System.out.println(program);
		IExecutionData data = state.execute(natFunc, L, C);
		IArray returnValue = (IArray) data.getReturnValue();
		assertEquals(L, returnValue.getLength());
		int n = 1;
		for(int i = 0; i < L; i++) {
			IArray line = (IArray) returnValue.getElement(i);
			assertEquals(C, line.getLength());
			for(int j = 0; j < C; j++)
				assertEquals(new BigDecimal(n++), line.getElement(j).getValue());
		}
		
		data.printResult();
	}
	
	@Test
	public void testContainsNinMatrix() {
		IFactory factory =IFactory.INSTANCE;
		IModule program = factory.createModule("ContainsInMatrix");
		
		IProcedure findFunc = program.addProcedure("contains", IDataType.BOOLEAN);
		IVariable mParam = (IVariable) findFunc.addParameter("m", factory.arrayType(IDataType.INT, 2));
		IVariable nParam = findFunc.addParameter("n", IDataType.INT);
		IBlock body = findFunc.getBody();
		IVariable iVar = body.addVariable("i", IDataType.INT);
		iVar.addAssignment(factory.literal(0));
		
		IVariable jVar = body.addVariable("j", IDataType.INT);
		IExpression outerGuard = factory.binaryExpression(IOperator.DIFFERENT, iVar.expression(), mParam.lengthExpression());
		ILoop outerLoop = body.addLoop(outerGuard);
		outerLoop.addAssignment(jVar, factory.literal(0));
		IExpression innerGuard = factory.binaryExpression(IOperator.DIFFERENT, jVar.expression(), mParam.lengthExpression(iVar.expression()) );
		ILoop innerLoop = outerLoop.addLoop(innerGuard);
		ISelectionWithAlternative ifEq = innerLoop.addSelectionWithAlternative(factory.binaryExpression(IOperator.EQUAL, mParam.elementExpression(iVar.expression(), jVar.expression()), nParam.expression()));
		ifEq.addReturnStatement(factory.literal(true));
		innerLoop.addIncrement(jVar);
		outerLoop.addIncrement(iVar);
		
		body.addReturnStatement(factory.literal(false));
		
		
		
		
		IProcedure main = program.addProcedure("main", IDataType.BOOLEAN);
		IBlock mainBody = main.getBody();
		IVariable array = mainBody.addVariable("test", factory.arrayType(IDataType.INT, 2));
		array.addAssignment(factory.arrayAllocation(IDataType.INT, 2, factory.literal(3)));
		array.elementAssignment(factory.arrayAllocation(IDataType.INT, 1, factory.literal(0)), factory.literal(0));
		array.elementAssignment(factory.arrayAllocation(IDataType.INT, 1, factory.literal(2)), factory.literal(1));
		array.elementAssignment(factory.arrayAllocation(IDataType.INT, 1, factory.literal(4)), factory.literal(2));
		
		array.elementAssignment(factory.literal(5), factory.literal(2), factory.literal(2));
		
		IVariable var = mainBody.addVariable("contains", IDataType.BOOLEAN);
		var.addAssignment(findFunc.callExpression(array.expression(), factory.literal(5)));
		mainBody.addReturnStatement(var.expression());
		
		
		System.out.println(program);
		IProgramState state = IMachine.create(program);
		IExecutionData data = state.execute(main);
		System.out.println(data.getReturnValue());
	}
}
