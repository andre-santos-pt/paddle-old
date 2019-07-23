package pt.iscte.paddle.semantics.java;

import static pt.iscte.paddle.model.IOperator.ADD;
import static pt.iscte.paddle.model.IOperator.DIFFERENT;
import static pt.iscte.paddle.model.IType.INT;

import pt.iscte.paddle.model.IArrayElementAssignment;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.IVariable;
import pt.iscte.paddle.model.IVariableAssignment;

public class ModelToJava {

	public static StringBuffer getCode(IModule module) {
		StringBuffer code = new StringBuffer();
		code.append("public class " + module.getId() + " {\n");
		
		module.getProcedures().forEach(p -> {
			code.append("public static " + p);
		});
		
		code.append("}");
		return code;
	}
	
	public static void main(String[] args) {
		IModule module = IModule.create();
		IProcedure idMatrix = module.addProcedure(INT.array2D());
		IVariable n = idMatrix.addParameter(INT);		
		IBlock body = idMatrix.getBody();
		IVariable id = body.addVariable(INT.array2D());
		IVariableAssignment assignment = body.addAssignment(id, INT.array2D().heapAllocation(n, n));
		IVariable iVar = body.addVariable(INT);
		IExpression e = DIFFERENT.on(iVar, n);
		ILoop loop = body.addLoop(e);
		IArrayElementAssignment ass2 = loop.addArrayElementAssignment(id, INT.literal(1), iVar, iVar);
		IVariableAssignment ass3 = loop.addAssignment(iVar, ADD.on(iVar, INT.literal(1)));
		IReturn ret = body.addReturn(id);
		
		System.out.println(getCode(module));
	}
}
