package pt.iscte.paddle;

import static pt.iscte.paddle.model.IOperator.ADD;
import static pt.iscte.paddle.model.IOperator.DIFFERENT;
import static pt.iscte.paddle.model.IType.DOUBLE;
import static pt.iscte.paddle.model.IType.INT;

import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.interpreter.IMachine;
import pt.iscte.paddle.interpreter.IProgramState;
import pt.iscte.paddle.interpreter.IReference;
import pt.iscte.paddle.interpreter.impl.Reference;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;

public class Teemu {

	public static void main(String[] args) {
		IModule module = IModule.create();
//		IProcedure summation = module.addProcedure(IType.DOUBLE);
		IProcedure summation = module.addProcedure(IType.UNBOUND);
		summation.setId("sum");
//		IVariableDeclaration array = summation.addParameter(DOUBLE.array().reference());
		IVariableDeclaration array = summation.addParameter(IType.UNBOUND);
		array.setId("array");
		IBlock sumBody = summation.getBody();
		IVariableDeclaration sum = sumBody.addVariable(DOUBLE);
		sum.setId("s");
		IVariableAssignment sumAss = sumBody.addAssignment(sum, DOUBLE.literal(0.0));
		IVariableDeclaration i = sumBody.addVariable(INT);
		i.setId("i");
		IVariableAssignment iAss = sumBody.addAssignment(i, INT.literal(0));
		ILoop loop = sumBody.addLoop(DIFFERENT.on(i, array.dereference().length()));
		IVariableAssignment ass1 = loop.addAssignment(sum, ADD.on(sum, array.dereference().element(i)));
		IVariableAssignment ass2 = loop.addIncrement(i);
		IReturn ret = sumBody.addReturn(sum);
		System.out.println(summation);
		
		IProgramState state = IMachine.create(module);
		
		IArray a = state.allocateArray(INT, 3);
		a.setElement(0, state.getValue("1"));
		a.setElement(1, state.getValue("2"));
		a.setElement(2, state.getValue("3"));
		IReference r = new Reference(a);
		
		
		IExecutionData data = state.execute(summation, r);
		System.out.println(data);
	}

}
