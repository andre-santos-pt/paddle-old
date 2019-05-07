import static pt.iscte.paddle.asg.IOperator.ADD;
import static pt.iscte.paddle.asg.IOperator.GREATER;
import static pt.iscte.paddle.asg.IOperator.SMALLER;
import static pt.iscte.paddle.asg.IType.INT;

import java.util.ArrayList;
import java.util.List;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.ISelection;
import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.machine.IMachine;
import pt.iscte.paddle.machine.IProgramState;
import pt.iscte.paddle.machine.IValue;

public class Test {

	public static void main(String[] args) throws ExecutionError {
		IModule program = IModule.create();

		IProcedure max = program.addProcedure(INT);  max.setId("max");
		
		IVariable array = max.addParameter(INT.array()); array.setId("array");
		
		IVariable bound = max.addParameter(INT); bound.setId("bound");
		IBlock body = max.getBody();
		IVariable m = body.addVariable(INT); m.setId("m");
		body.addAssignment(m, array.element(INT.literal(0)));
		IVariable i = body.addVariable(INT); i.setId("i");
		body.addAssignment(i, INT.literal(1));
		ILoop loop = body.addLoop(SMALLER.on(i, bound));
		ISelection selection = loop.addSelection(GREATER.on(array.element(i), bound));
		selection.addAssignment(m, array.element(i));
		loop.addAssignment(i, ADD.on(i, INT.literal(1)));
		body.addReturn(m);
		
		
		IProcedure main = program.addProcedure(IType.DOUBLE);  main.setId("main");
		IBlock body2 = main.getBody();
		IVariable a = main.getBody().addVariable(INT.array());
		
		System.out.println(max);
		
		class ConstantChecker implements IBlock.IVisitor {
			List<IVariable> constants = new ArrayList<>(max.getVariables());
			
			public boolean visit(IVariableAssignment assignment) {
				constants.remove(assignment.getVariable());
				return false;
			}
		};
		ConstantChecker constantChecker = new ConstantChecker();
		max.getBody().accept(constantChecker);
		
		System.out.println(constantChecker.constants);
		
		IProgramState state = IMachine.create(program);
		
		
		
		IExecutionData data = state.execute(max, new int[] {2,4,3}, 3);
		IValue result = data.getReturnValue();
		System.out.println(result);
		
		//		List<ISemanticProblem> problems = program.validateSematics();
//		if(problems.isEmpty())
//			state.execute(main);
//		else
//			for (ISemanticProblem p : program.validateSematics()) {
//				System.err.println(p);
//			}


	}

}
