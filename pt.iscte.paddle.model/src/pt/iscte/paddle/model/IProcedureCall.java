package pt.iscte.paddle.model;

import java.util.List;


public interface IProcedureCall extends IStatement {
	IProcedureDeclaration getProcedure();
	List<IExpression> getArguments();

	default boolean isOperation() {
		return false;
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

}
