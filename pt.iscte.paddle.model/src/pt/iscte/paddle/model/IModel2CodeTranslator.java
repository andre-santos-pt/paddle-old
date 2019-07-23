package pt.iscte.paddle.model;

public interface IModel2CodeTranslator {
	
	String type(IType t);
	
	String header(IModule m);
	String close(IModule m);
	
	String declaration(IConstant c);
	String declaration(IRecordType t);
	String header(IProcedure p);
	String close(IProcedure p);
	
	String statements(IBlock b);
	
	String statement(IBlockElement e);
	String declaration(IVariable v);
	String assignment(IVariableAssignment a);
	
	String expression(IExpression e);
	
	String operator(IOperator o);
}
