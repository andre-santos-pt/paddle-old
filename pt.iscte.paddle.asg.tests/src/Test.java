import static pt.iscte.paddle.asg.ILiteral.literal;
import static pt.iscte.paddle.asg.IOperator.*;

import java.util.ArrayList;
import java.util.List;

import static pt.iscte.paddle.asg.IDataType.*;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.ISelection;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.asg.IBlock.IVisitor;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.IMachine;
import pt.iscte.paddle.machine.IProgramState;

public class Test {

	public static void main(String[] args) throws ExecutionError {
		IModule program = IModule.create();

		IProcedure max = program.addProcedure(INT.array());  max.setId("max");
		
		IVariable array = max.addParameter(INT.array()); array.setId("array");
		IVariable bound = max.addParameter(INT); bound.setId("bound");
		IBlock body = max.getBody();
		IVariable m = body.addVariable(INT); m.setId("m");
		body.addAssignment(m, array.arrayElement(literal(0)));
		IVariable i = body.addVariable(INT); i.setId("i");
		body.addAssignment(i, literal(1));
		ILoop loop = body.addLoop(SMALLER.on(i, bound));
		ISelection selection = loop.addSelection(GREATER.on(array.arrayElement(i), bound));
		selection.addAssignment(m, array.arrayElement(i));
		loop.addAssignment(i, ADD.on(i, literal(1)));
		body.addReturn(m);
		
		
		IProcedure main = program.addProcedure(IDataType.DOUBLE);  main.setId("main");
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

		
		state.execute(main);
		
		//		List<ISemanticProblem> problems = program.validateSematics();
//		if(problems.isEmpty())
//			state.execute(main);
//		else
//			for (ISemanticProblem p : program.validateSematics()) {
//				System.err.println(p);
//			}


	}

}
