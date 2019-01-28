package pt.iscte.paddle.tests.asg;

import org.junit.BeforeClass;
import org.junit.Test;

import pt.iscte.paddle.asg.IConstant;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IFactory;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IOperator;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IMachine;
import pt.iscte.paddle.machine.IProgramState;

public class TestConstants {

	static IFactory fact = IFactory.INSTANCE;
	static IModule program;
	private static IConstant piConst;
	
	@BeforeClass
	public static void setup() {
		program = fact.createModule("Constants");	
		piConst = program.addConstant("PI", IDataType.DOUBLE, fact.literal(3.14159265359));
	}
	
	@Test
	public void testArea() {
		
		IProcedure circleArea = program.addProcedure("circleArea", IDataType.DOUBLE);
		IVariable rParam = circleArea.addParameter("r", IDataType.DOUBLE);
		circleArea.getBody().addReturnStatement(fact.binaryExpression(IOperator.MUL, fact.binaryExpression(IOperator.MUL, piConst.expression(), rParam.expression()), rParam.expression()));
		
		IProgramState state = IMachine.create(program);
		state.execute(circleArea, 3);
		
	}
	
	@Test
	public void testPerimeter() {
		
		IProcedure circlePerimeter = program.addProcedure("circlePerimeter", IDataType.DOUBLE);
		IVariable rParam = circlePerimeter.addParameter("r", IDataType.DOUBLE);
		circlePerimeter.getBody().addReturnStatement(fact.binaryExpression(IOperator.MUL, fact.binaryExpression(IOperator.MUL, fact.literal(2), piConst.expression()), rParam.expression()));
		IProgramState state = IMachine.create(program);
		state.execute(circlePerimeter, 3);

		System.out.println(program);
	}
}
