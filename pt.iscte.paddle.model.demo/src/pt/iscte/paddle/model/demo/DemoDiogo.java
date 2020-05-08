package pt.iscte.paddle.model.demo;
import static pt.iscte.paddle.model.IOperator.ADD;
import static pt.iscte.paddle.model.IOperator.SMALLER;
import static pt.iscte.paddle.model.IOperator.SMALLER_EQ;
import static pt.iscte.paddle.model.IType.INT;

import pt.iscte.paddle.interpreter.ArrayIndexError;
import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.interpreter.IMachine;
import pt.iscte.paddle.interpreter.IProgramState;
import pt.iscte.paddle.interpreter.IProgramState.IListener;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;

public class DemoDiogo {

	public static void main(String[] args) throws ExecutionError {

		// instantiate model from file
		IModule module = IModule.create();
		IProcedure procedure = module.addProcedure(INT);
		procedure.setId("sumAll");

		IVariableDeclaration n = procedure.addParameter(INT);
		n.setId("n");

		IBlock body = procedure.getBody();

		IVariableDeclaration i1 = body.addVariable(INT, INT.literal(0));
		i1.setId("i1");

		IVariableDeclaration array = body.addVariable(INT.array().reference());
		array.setId("array");
		body.addAssignment(array, INT.array().heapAllocation(n));

		ILoop loop1 = body.addLoop(SMALLER.on(i1, n));
		loop1.addArrayElementAssignment(array, ADD.on(i1, INT.literal(1)), i1);
		loop1.addIncrement(i1);

		IVariableDeclaration i2 = body.addVariable(INT, INT.literal(0));
		i2.setId("i2");

		IVariableDeclaration sum = body.addVariable(INT, INT.literal(0));
		sum.setId("sum");

		ILoop loop = body.addLoop(SMALLER_EQ.on(i2, n));
		loop.addAssignment(sum, ADD.on(sum, array.element(i2)));
		loop.addIncrement(i2);

		body.addReturn(sum);

		System.err.println(procedure);
		IProgramState state = IMachine.create(module);
		try {
			IExecutionData data = state.execute(procedure, 5);  
		}
		catch(ArrayIndexError e) {
			System.out.println(e.getTarget());
			System.out.println(e.getIndexExpression());
			System.out.println(e.getIndexDimension());
		}


	}
}