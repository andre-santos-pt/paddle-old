package pt.iscte.paddle.asg.semantics;

import java.util.List;

import pt.iscte.paddle.asg.IArrayElementAssignment;
import pt.iscte.paddle.asg.IArrayLengthExpression;
import pt.iscte.paddle.asg.IControlStructure;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedureCallExpression;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.ISelection;
import pt.iscte.paddle.asg.IStructMemberAssignment;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;

public class Typing extends Rule {

	@Override
	public boolean visit(IReturn returnStatement) {
		IDataType procReturnType = returnStatement.getParent().getProcedure().getReturnType();
		if(!procReturnType.isCompatible(returnStatement.getExpression().getType()))
			addProblem(ISemanticProblem.create("incompatible return", returnStatement.getExpression().getType(), procReturnType));
		return true;
	}
	
	
	@Override
	public boolean visit(IProcedureCallExpression exp) {
		List<IVariable> parameters = exp.getProcedure().getParameters();
		List<IExpression> arguments = exp.getArguments();
		if(parameters.size() != arguments.size()) {
			addProblem(ISemanticProblem.create("wrong number of arguments, given that " + exp.getProcedure().getId() + " has " + parameters.size() + " parameters.", exp));
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
			addProblem(ISemanticProblem.create("guard must be a boolean expression, not " + guard.getType(), guard));
	}
	
	@Override
	public boolean visit(IVariableAssignment assignment) {
		if(!assignment.getVariable().getType().isCompatible(assignment.getExpression().getType()))
			addProblem(ISemanticProblem.create("incompatible assignment: " + assignment.getVariable().getType() + " -- " + assignment.getExpression().getType(),
					assignment.getVariable(), assignment.getExpression()));
		return true;
	}
	
	
	@Override
	public boolean visit(IArrayLengthExpression exp) {
		// TODO Auto-generated method stub
		return super.visit(exp);
	}
	
	
	@Override
	public boolean visit(IArrayElementAssignment assignment) {
		// TODO Auto-generated method stub
		return super.visit(assignment);
	}
	
	@Override
	public boolean visit(IStructMemberAssignment assignment) {
		// TODO Auto-generated method stub
		return super.visit(assignment);
	}
	
}
