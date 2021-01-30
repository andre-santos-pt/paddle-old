package pt.iscte.paddle.model;

import java.util.Collections;
import java.util.List;

import pt.iscte.paddle.model.impl.ProcedureCall;
import pt.iscte.paddle.model.impl.UnboundProcedure;


public interface IProcedureCall extends IStatement {
	IProcedureDeclaration getProcedure();
	List<IExpression> getArguments();

	default boolean isOperation() {
		return false;
	}
	
	default boolean isBound() {
		return !(getProcedure() instanceof UnboundProcedure);
	}
	
	
	@Override
	default boolean isSame(IProgramElement e) {
		return e instanceof IProcedureCall &&
				getProcedure().equals(((IProcedureCall) e).getProcedure()) &&
				IExpression.areSame(getArguments(), ((IProcedureCall) e).getArguments());
	}

	
	static IProcedureCall unboundExpression(String id) {
		ProcedureCall call = new ProcedureCall(null, IProcedure.createUnbound(id), -1, Collections.emptyList());
		call.setId(id);
		return call;
	}
	
}
