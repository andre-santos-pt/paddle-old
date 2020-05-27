package pt.iscte.paddle.model.validation;

import java.util.List;

import pt.iscte.paddle.model.IArrayElementAssignment;
import pt.iscte.paddle.model.IArrayLength;
import pt.iscte.paddle.model.IArrayType;
import pt.iscte.paddle.model.IBinaryExpression;
import pt.iscte.paddle.model.IControlStructure;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProcedureCall;
import pt.iscte.paddle.model.IRecordFieldAssignment;
import pt.iscte.paddle.model.IReferenceType;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IUnaryExpression;
import pt.iscte.paddle.model.IUnaryOperator;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.IVariableDereference;

public class CheckTypes extends Rule {

	@Override
	public boolean visit(IUnaryExpression exp) {
		IType expType = exp.getOperand().getType();
		IUnaryOperator operator = exp.getOperator();
		if(!operator.isValidFor(expType))
			addProblem("operator " + operator.getSymbol() + " cannot be used with " + expType, exp);
		return true;
	}

	@Override
	public boolean visit(IBinaryExpression exp) {
		IExpression left = exp.getLeftOperand();
		IExpression right = exp.getRightOperand();
		if(!exp.getOperator().isValidFor(left, right))
			addProblem("operator " + exp.getOperator().getSymbol() + " cannot be used with " + left + " and " + right, exp);
		return true;
	}

	@Override
	public boolean visit(IReturn returnStatement) {
		IProcedure procedure = returnStatement.getParent().getProcedure();
		if(procedure.getReturnType().isVoid())  {
			if(!returnStatement.isError() && returnStatement.getExpression() != null)
				addProblem("void procedures cannot return values", procedure, returnStatement);
		}
		else {
			IExpression retExp = returnStatement.getExpression();
			if(retExp == null) {
				addProblem("procedure has to return a value of type " + procedure.getReturnType(), procedure, returnStatement);
			}
			else {
				IType expType = retExp.getType();
				IType procReturnType = procedure.getReturnType();
				if(!procReturnType.isCompatible(expType))
					addProblem("incompatible return at " + procedure.shortSignature() +
							"; expected " + procReturnType + ", found " + expType, procReturnType , expType);
			}
		}
		return true;
	}

	@Override
	public boolean visit(IProcedureCall exp) {
		if(exp.isBound()) {
			List<IVariableDeclaration> parameters = exp.getProcedure().getParameters();
			List<IExpression> arguments = exp.getArguments();
			if(parameters.size() != arguments.size()) {
				addProblem(ISemanticProblem.create("wrong number of arguments, given that " + exp.getProcedure().longSignature() + " has " + parameters.size() + " parameters.", exp.getProcedure(), exp));
				return true;
			}
			for (int i = 0; i < parameters.size(); i++) {
				if(!parameters.get(i).getType().isCompatible(arguments.get(i).getType()))
					addProblem(ISemanticProblem.create(exp + ", incompatible argument (" + i + ")", parameters.get(i).getType(), arguments.get(i).getType()));
			}
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
		if(!guard.getType().equals(IType.BOOLEAN))
			addProblem("guard must be a boolean expression, not " + guard.getType(), guard);
	}

	@Override
	public boolean visit(IVariableAssignment assignment) {
		checkAssignmentTypes(assignment.getTarget(), assignment.getExpression());

		if(!assignment.getOwnerProcedure().getVariables().contains(assignment.getTarget()))
			addProblem("unknown variable " + assignment.getTarget().getId(), assignment.getTarget());

		return true;
	}

	private void checkAssignmentTypes(IVariableDeclaration var, IExpression exp) {
		if(!var.getType().isCompatible(exp.getType()) && !exp.isNull())
			addProblem("incompatible assignment: " + exp.getType() + " cannot be assigned to " + var.getType(),
					var, exp);	
	}

	@Override
	public void visit(IVariableDereference exp) {
		if(!exp.getTarget().getType().isReference())
			addProblem("not a reference type", exp);
	}

	@Override
	public boolean visit(IArrayElementAssignment assignment) {
		IExpression variable = assignment.getTarget();
		IType type = variable.getType();
		if(type instanceof IReferenceType)
			type = ((IReferenceType) type).resolveTarget();

		if(!(type instanceof IArrayType)) {
			addProblem("the type of variable " + variable + " is not an array type", assignment);
			return true;
		}

		//		checkAssignment(assignment.getVariable(), assignment.getExpression());

		//TODO int type on index

		//		IArrayType arrayType = (IArrayType) type;
		//		IDataType componentType = arrayType.getComponentTypeAt(assignment.getIndexes().size());
		//		IDataType expType = assignment.getExpression().getType();
		//		if(!componentType.isCompatible(expType))
		//			addProblem("incompatible assignment: " + expType + " cannot be assigned to " + componentType,
		//					arrayType, expType);	
		return true;
	}

	@Override
	public boolean visit(IRecordFieldAssignment assignment) {
		checkAssignmentTypes(assignment.getField(), assignment.getExpression());
		return true;
	}

	@Override
	public boolean visit(IArrayLength exp) {
		return super.visit(exp);
	}
}
