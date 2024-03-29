package pt.iscte.paddle.model.demo;
import static pt.iscte.paddle.model.IOperator.ADD;
import static pt.iscte.paddle.model.IOperator.DIFFERENT;
import static pt.iscte.paddle.model.IOperator.GREATER;
import static pt.iscte.paddle.model.IOperator.SMALLER;
import static pt.iscte.paddle.model.IType.DOUBLE;
import static pt.iscte.paddle.model.IType.INT;

import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.IVariableAssignment;

public class Examples {
	
	static IProcedure createNaturalsFunction(IModule module) {
		IProcedure naturals = module.addProcedure(INT.array().reference());								// static int[] naturals(
		IVariableDeclaration n = naturals.addParameter(INT);														// int n)						
		IBlock body = naturals.getBody();																// {
		IVariableDeclaration array = body.addVariable(INT.array().reference());									// 		int[] array;
		body.addAssignment(array, INT.array().heapAllocation(n.expression()));							// 		array = new int[n];
		IVariableDeclaration i = body.addVariable(INT, INT.literal(0));											// 		int i; i = 0;
		ILoop loop = body.addLoop(SMALLER.on(i, n));													// 		while(i < n) {
		loop.addArrayElementAssignment(array.expression(), ADD.on(i, INT.literal(1)), i.expression());	// 			array[i] = i + 1;
		loop.addAssignment(i, ADD.on(i, INT.literal(1)));												//			i = i + 1;
																										//		}
		body.addReturn(array);																			//		return array;
																										// }
		
		// optional ids
		naturals.setId("naturals");
		n.setId("n");
		array.setId("array");
		i.setId("i");
				
		return naturals;
	}

	static IProcedure createArrayMaxFunction(IModule module) {
		IProcedure max = module.addProcedure(INT);
		IVariableDeclaration array = max.addParameter(INT.array());
		IBlock body = max.getBody();
		IVariableDeclaration m = body.addVariable(INT);
		IVariableAssignment mAss = body.addAssignment(m, array.element(INT.literal(0)));
		IVariableDeclaration i = body.addVariable(INT);
		IVariableAssignment iAss = body.addAssignment(i, INT.literal(1));
		ILoop loop = body.addLoop(SMALLER.on(i, array.length()));
		ISelection ifstat = loop.addSelection(GREATER.on(array.element(i), m));
		IVariableAssignment mAss_ = ifstat.addAssignment(m, array.element(i));
		IVariableAssignment iInc = loop.addIncrement(i);
		IReturn ret = body.addReturn(m);
		
		max.setId("max");
		array.setId("array");
		m.setId("m");
		i.setId("i");
		
		return max;
	}
	

	static IProcedure createArraySumFunction(IModule module) {
		IProcedure summation = module.addProcedure(DOUBLE);
		IVariableDeclaration array = summation.addParameter(DOUBLE.array().reference());
		IBlock sumBody = summation.getBody();
		IVariableDeclaration sum = sumBody.addVariable(DOUBLE, DOUBLE.literal(0.0));
		IVariableDeclaration i = sumBody.addVariable(INT, INT.literal(0));
		ILoop loop = sumBody.addLoop(DIFFERENT.on(i, array.dereference().getTarget().length()));
		IVariableAssignment ass1 = loop.addAssignment(sum, ADD.on(sum, array.dereference().getTarget().element(i)));
		IVariableAssignment ass2 = loop.addIncrement(i);
		IReturn ret = sumBody.addReturn(sum);
		
		summation.setId("summation");
		array.setId("array");
		sum.setId("sum");
		i.setId("i");
		
		return summation;
	}
}