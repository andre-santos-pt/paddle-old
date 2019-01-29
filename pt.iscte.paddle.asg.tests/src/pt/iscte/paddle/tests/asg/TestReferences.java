package pt.iscte.paddle.tests.asg;

import org.junit.Test;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IFactory;
import pt.iscte.paddle.asg.ILiteral;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IStructType;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.machine.IMachine;
import pt.iscte.paddle.machine.IProgramState;
import static pt.iscte.paddle.asg.ILiteral.*;
import static pt.iscte.paddle.asg.IDataType.*;

public class TestReferences {

	@Test
	public void test() {
		IModule program = IModule.create("Struct");
		
		IProcedure proc = program.addProcedure("test", VOID);
		IBlock body = proc.getBody();
		IVariable a = body.addVariable("a", INT.reference());
		IVariable b = body.addVariable("b", INT.reference());
		
		a.addTargetAssignment(literal(5));
		b.addAssignment(a);
		
		b.addTargetAssignment(literal(7));

		System.out.println(program);
		IProgramState state = IMachine.create(program);
		IExecutionData data = state.execute(proc);
		System.out.println(data.getVariableValue("a"));
		System.out.println(data.getVariableValue("b"));
	}
		
}
