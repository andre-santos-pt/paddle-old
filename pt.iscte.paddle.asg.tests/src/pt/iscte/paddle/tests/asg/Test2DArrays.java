package pt.iscte.paddle.tests.asg;

import static org.junit.Assert.assertEquals;
import static pt.iscte.paddle.asg.IDataType.BOOLEAN;
import static pt.iscte.paddle.asg.IDataType.INT;
import static pt.iscte.paddle.asg.ILiteral.literal;
import static pt.iscte.paddle.asg.IOperator.ADD;
import static pt.iscte.paddle.asg.IOperator.DIFFERENT;
import static pt.iscte.paddle.asg.IOperator.EQUAL;

import java.math.BigDecimal;

import org.junit.Test;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IModule;
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
		IModule program = IModule.create("Arrays2D");
		
		IProcedure idFunc = program.addProcedure("idMatrix", INT.array2D());
		IVariable nParam = idFunc.addParameter("n", INT);
		
		IBlock body = idFunc.getBody();
		IVariable idVar = body.addVariable("id", INT.array2D());
		idVar.addAssignment(INT.array2D().allocation(nParam, nParam));
		IVariable iVar = body.addVariable("i", INT);
		IExpression e = DIFFERENT.on(iVar, nParam);
		ILoop loop = body.addLoop(e);
		loop.addArrayElementAssignment(idVar, literal(1), iVar, iVar);
		loop.addAssignment(iVar, ADD.on(iVar, literal(1)));
		
		body.addReturn(idVar);

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
		IModule program = IModule.create("NatMatrix");
		
		IProcedure natFunc = program.addProcedure("natMatrix", INT.array2D());
		IVariable linesParam = natFunc.addParameter("lines", INT);
		IVariable colsParam = natFunc.addParameter("cols", INT);
		
		IBlock body = natFunc.getBody();
		
		IVariable mVar = body.addVariable("m", INT.array2D());
		mVar.addAssignment(INT.array2D().allocation(linesParam, colsParam));
		
		IVariable iVar = body.addVariable("i", INT);
		iVar.addAssignment(literal(0));
		
		IVariable jVar = body.addVariable("j", INT);
		IVariable nVar = body.addVariable("n", INT);
		nVar.addAssignment(literal(1));

		IExpression outerGuard = DIFFERENT.on(iVar, linesParam);
		ILoop outLoop = body.addLoop(outerGuard);
		outLoop.addAssignment(jVar, literal(0));
		IExpression innerGuard = DIFFERENT.on(jVar, colsParam);
		ILoop inLoop = outLoop.addLoop(innerGuard);
		inLoop.addArrayElementAssignment(mVar, nVar, iVar, jVar);
		inLoop.addAssignment(jVar, ADD.on(jVar, literal(1)));
		inLoop.addAssignment(nVar, ADD.on(nVar, literal(1)));
		
		outLoop.addAssignment(iVar, ADD.on(iVar, literal(1)));
		
		body.addReturn(mVar);
		
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
		IModule program = IModule.create("ContainsInMatrix");
		
		IProcedure findFunc = program.addProcedure("contains", BOOLEAN);
		IVariable mParam = (IVariable) findFunc.addParameter("m", INT.array2D());
		IVariable nParam = findFunc.addParameter("n", INT);
		IBlock body = findFunc.getBody();
		IVariable iVar = body.addVariable("i", INT);
		iVar.addAssignment(literal(0));
		
		IVariable jVar = body.addVariable("j", INT);
		IExpression outerGuard = DIFFERENT.on(iVar, mParam.arrayLength());
		ILoop outerLoop = body.addLoop(outerGuard);
		outerLoop.addAssignment(jVar, literal(0));
		IExpression innerGuard = DIFFERENT.on(jVar, mParam.arrayLength(iVar) );
		ILoop innerLoop = outerLoop.addLoop(innerGuard);
		ISelectionWithAlternative ifEq = innerLoop.addSelectionWithAlternative(EQUAL.on(mParam.arrayElement(iVar, jVar), nParam));
		ifEq.addReturn(literal(true));
		innerLoop.addIncrement(jVar);
		outerLoop.addIncrement(iVar);
		
		body.addReturn(literal(false));
		
		
		IProcedure main = program.addProcedure("main", BOOLEAN);
		IBlock mainBody = main.getBody();
		IVariable array = mainBody.addVariable("test", INT.array2D());
		array.addAssignment(INT.array2D().allocation(literal(3)));
		array.addArrayAssignment(INT.array2D().allocation(literal(0)), literal(0));
		array.addArrayAssignment(INT.array2D().allocation(literal(2)), literal(1));
		array.addArrayAssignment(INT.array2D().allocation(literal(4)), literal(2));
		
		array.addArrayAssignment(literal(5), literal(2), literal(2));
		
		IVariable var = mainBody.addVariable("contains", BOOLEAN);
		var.addAssignment(findFunc.call(array, literal(5)));
		mainBody.addReturn(var);
		
		
		System.out.println(program);
		IProgramState state = IMachine.create(program);
		IExecutionData data = state.execute(main);
		System.out.println(data.getReturnValue());
	}
}
