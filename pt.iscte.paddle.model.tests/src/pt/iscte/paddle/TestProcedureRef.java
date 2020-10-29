package pt.iscte.paddle;

import static pt.iscte.paddle.model.IType.INT;

import java.util.Collections;

import pt.iscte.paddle.asg.future.IProcedureType;
import pt.iscte.paddle.interpreter.IMachine;
import pt.iscte.paddle.interpreter.IProgramState;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IVariableDeclaration;

public class TestProcedureRef {

	public static void main(String[] args) {
		IModule module = IModule.create();

		IProgramState state = IMachine.create(module);

		IProcedure p = module.addProcedure(INT);
		IProcedureType l = IProcedureType.create(INT, Collections.emptyList());
		IVariableDeclaration n = p.addParameter(l);
		
		p.getBody().addCall(l);
		System.out.println(p);
	}

}
