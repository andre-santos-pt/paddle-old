package pt.iscte.paddle.tests.asg;

import static pt.iscte.paddle.asg.IDataType.INT;
import static pt.iscte.paddle.asg.IDataType.VOID;
import static pt.iscte.paddle.asg.ILiteral.literal;

import org.junit.Test;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.machine.IMachine;
import pt.iscte.paddle.machine.IProgramState;

public class TestReferences {

	@Test
	public void test() {
		IModule program = IModule.create("Struct");
		
		IProcedure proc = program.addProcedure("test", VOID);
		IBlock body = proc.getBody();
		IVariable a = body.addVariable("a", INT.reference());
		IVariable b = body.addVariable("b", INT.reference());
		
		body.addAssignment(a.valueOf(), literal(5));
		body.addAssignment(b, a);
		
		body.addAssignment(b, literal(7));

		System.out.println(program);
		IProgramState state = IMachine.create(program);
		IExecutionData data = state.execute(proc);
		System.out.println(data.getVariableValue("a"));
		System.out.println(data.getVariableValue("b"));
	}
		
}
