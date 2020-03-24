package pt.iscte.paddle.model;

import java.util.Collections;
import java.util.List;

import pt.iscte.paddle.model.impl.ProcedureCall;


public interface IProcedureCall extends IStatement {
	IProcedureDeclaration getProcedure();
	List<IExpression> getArguments();

	default boolean isOperation() {
		return false;
	}
	
	default boolean isBound() {
		return getProcedure() != null;
	}
	
	
	@Override
	default boolean isSame(IProgramElement e) {
		return e instanceof IProcedureCall &&
				getProcedure().equals(((IProcedureCall) e).getProcedure()) &&
				IExpression.areSame(getArguments(), ((IProcedureCall) e).getArguments());
	}

	static String argsToString(IModel2CodeTranslator t, List<IExpression> arguments) {
		String args = "";
		for(IExpression e : arguments) {
			if(!args.isEmpty())
				args += ", ";
			args += t.expression(e);
		}
		return args;
	}
	
	static IProcedureCall unboundExpression(String id) {
		ProcedureCall call = new ProcedureCall(null, null, -1, Collections.emptyList());
		call.setId(id);
		return call;
	}
	
}
