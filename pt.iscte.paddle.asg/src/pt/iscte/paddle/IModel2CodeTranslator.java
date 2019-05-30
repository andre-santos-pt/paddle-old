package pt.iscte.paddle;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IBlockElement;
import pt.iscte.paddle.asg.IConstant;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IOperator;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IRecordType;
import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;

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
