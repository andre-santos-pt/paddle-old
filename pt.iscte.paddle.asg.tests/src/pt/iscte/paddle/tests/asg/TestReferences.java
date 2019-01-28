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

public class TestReferences {

	@Test
	public void test() {
		IFactory factory = IFactory.INSTANCE;
		IModule program = factory.createModule("Struct");
		
		IProcedure proc = program.addProcedure("test", IDataType.VOID);
		IBlock body = proc.getBody();
		IVariable aVar = body.addVariable("a", IDataType.INT.referenceType());
		IVariable bVar = body.addVariable("b", IDataType.INT.referenceType());
		
		aVar.addTargetAssignment(factory.literal(5));
		bVar.addAssignment(aVar.expression());
		
		bVar.addTargetAssignment(factory.literal(7));
//		aVar.addAssignment(factory.nullLiteral());
		System.out.println(program);
		IProgramState state = IMachine.create(program);
		IExecutionData data = state.execute(proc);
		System.out.println(data.getVariableValue("a"));
		System.out.println(data.getVariableValue("b"));
	}
		
}
