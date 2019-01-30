package pt.iscte.paddle.asg.semantics;

import java.util.List;

import pt.iscte.paddle.asg.IArrayElementAssignment;
import pt.iscte.paddle.asg.IArrayLengthExpression;
import pt.iscte.paddle.asg.IArrayType;
import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IControlStructure;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProcedureCallExpression;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.ISelection;
import pt.iscte.paddle.asg.IStructMemberAssignment;
import pt.iscte.paddle.asg.IUnaryExpression;
import pt.iscte.paddle.asg.IUnaryOperator;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;

public class Types extends Rule {

	@Override
	public boolean visit(IUnaryExpression exp) {
		IDataType expType = exp.getOperand().getType();
		IUnaryOperator operator = exp.getOperator();
		if(!operator.isValidFor(expType))
			addProblem("operator " + operator.getSymbol() + " cannot be used with " + expType, exp);
		return true;
	}
	
	@Override
	public boolean visit(IBinaryExpression exp) {
		IDataType left = exp.getLeftOperand().getType();
		IDataType right = exp.getRightOperand().getType();
		if(!exp.getOperator().isValidFor(left, right))
			addProblem("operator " + exp.getOperator().getSymbol() + " cannot be used with " + left + " and " + right, exp);
		return true;
	}
	
	@Override
	public boolean visit(IReturn returnStatement) {
		IProcedure procedure = returnStatement.getParent().getProcedure();
		IDataType expType = returnStatement.getExpression().getType();
		IDataType procReturnType = procedure.getReturnType();
		if(!procReturnType.isCompatible(expType))
			addProblem("incompatible return at " + procedure.shortSignature() +
					"; expected " + procReturnType + ", found " + expType, procReturnType , expType);
		return true;
	}
	
	@Override
	public boolean visit(IProcedureCallExpression exp) {
		List<IVariable> parameters = exp.getProcedure().getParameters();
		List<IExpression> arguments = exp.getArguments();
		if(parameters.size() != arguments.size()) {
			addProblem(ISemanticProblem.create("wrong number of arguments, given that " + exp.getProcedure().longSignature() + " has " + parameters.size() + " parameters.", exp.getProcedure(), exp));
			return true;
		}
		for (int i = 0; i < parameters.size(); i++) {
			if(!parameters.get(i).getType().isCompatible(arguments.get(i).getType()))
				addProblem(ISemanticProblem.create("incompatible argument", parameters.get(i), arguments.get(i)));
		}
		return true;
	}

	@Override
	public boolean visit(ISelection selection) {
		checkGuard(selection);
		return true;
	}

	@Override
	public boolean visit(ILoop loop) {
		checkGuard(loop);
		return true;
	}
	
	private void checkGuard(IControlStructure control) {
		IExpression guard = control.getGuard();
		if(!guard.getType().equals(IDataType.BOOLEAN))
			addProblem("guard must be a boolean expression, not " + guard.getType(), guard);
	}
	
	@Override
	public boolean visit(IVariableAssignment assignment) {
		checkAssignment(assignment.getVariable(), assignment.getExpression());
		return true;
	}
	
	private void checkAssignment(IVariable var, IExpression exp) {
		if(!var.getType().isCompatible(exp.getType()))
			addProblem("incompatible assignment: " + exp.getType() + " cannot be assigned to " + var.getType(),
					var, exp);	
	}
	
	private void checkType(IDataType type, IExpression exp) {
		if(!type.isCompatible(exp.getType()))
			addProblem("incompatible assignment: " + exp.getType() + " cannot be assigned to " + type,
					type, exp);	
	}
	@Override
	public boolean visit(IArrayElementAssignment assignment) {
		IVariable variable = assignment.getVariable();
		IDataType type = variable.getType();
		if(!(type instanceof IArrayType)) {
			addProblem("the type of variable " + variable + " is not an array type", assignment);
			return true;
		}
		
		//TODO int type on index
		
		IArrayType arrayType = (IArrayType) type;
		IDataType componentType = arrayType.getComponentTypeAt(assignment.getIndexes().size());
		IDataType expType = assignment.getExpression().getType();
		if(!componentType.isCompatible(expType))
			addProblem("incompatible assignment: " + expType + " cannot be assigned to " + componentType,
					arrayType, expType);	
		return true;
	}
	
	@Override
	public boolean visit(IStructMemberAssignment assignment) {
		checkAssignment(assignment.getVariable(), assignment.getExpression());
		return true;
	}
	
	@Override
	public boolean visit(IArrayLengthExpression exp) {
		// TODO Auto-generated method stub
		return super.visit(exp);
	}
}
