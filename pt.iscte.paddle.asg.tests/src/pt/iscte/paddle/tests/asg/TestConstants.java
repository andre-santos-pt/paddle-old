package pt.iscte.paddle.tests.asg;

import static pt.iscte.paddle.asg.IDataType.DOUBLE;
import static pt.iscte.paddle.asg.ILiteral.literal;
import static pt.iscte.paddle.asg.IOperator.MUL;

import org.junit.BeforeClass;
import org.junit.Test;

import pt.iscte.paddle.asg.IConstant;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IMachine;
import pt.iscte.paddle.machine.IProgramState;

public class TestConstants {

	static IModule program;
	private static IConstant piConst;
	
	@BeforeClass
	public static void setup() {
		program = IModule.create("Constants");	
		piConst = program.addConstant("PI", DOUBLE, literal(3.14159265359));
	}
	
	@Test
	public void testArea() {
		
		IProcedure circleArea = program.addProcedure("circleArea", DOUBLE);
		IVariable r = circleArea.addParameter("r", DOUBLE);
		circleArea.getBody().addReturn(MUL.on(MUL.on(piConst, r), r));
		
		IProgramState state = IMachine.create(program);
		state.execute(circleArea, 3);
		
	}
	
	@Test
	public void testPerimeter() {
		
		IProcedure circlePerimeter = program.addProcedure("circlePerimeter", DOUBLE);
		IVariable r = circlePerimeter.addParameter("r", DOUBLE);
		circlePerimeter.getBody().addReturn(MUL.on(MUL.on(literal(2), piConst), r));
		IProgramState state = IMachine.create(program);
		state.execute(circlePerimeter, 3);

		System.out.println(program);
	}
}
