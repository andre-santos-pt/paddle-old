package pt.iscte.paddle.roles;

import pt.iscte.paddle.model.IBinaryExpression;
import pt.iscte.paddle.model.IBinaryOperator;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ILiteral;
import pt.iscte.paddle.model.IOperator;
import pt.iscte.paddle.model.IVariable;
import pt.iscte.paddle.model.IVariableAssignment;

public interface IGatherer extends IVariableRole {

	Operation getOperation();

	default String getName() {
		return "Gatherer";
	}

	enum Operation {
		ADD, SUB, MUL, DIV, MOD;
	}
	
	class Visitor implements IBlock.IVisitor {
		final IVariable var;
		boolean allSameAcc = true;
		Operation operator = null;
		boolean first = true;

		Visitor(IVariable var) {
			this.var = var;
		}

		@Override
		public boolean visit(IVariableAssignment assignment) {
			if(assignment.getTarget().equals(var)) {
				if(first)
					first = false;
				else {
					Operation op = getAccumulationOperator(assignment);
					if(op == null || operator != null && op != operator)
						allSameAcc = false;
					else
						operator = op;
				}
			}
			return false;
		}
	}

	static boolean isGatherer(IVariable var) {
		Visitor v = new Visitor(var);
		var.getProcedure().accept(v);
		return v.allSameAcc && v.operator != null;
	}

	static class Gatherer implements IGatherer {
		private final Operation operator;

		public Gatherer(Operation operator) {
			this.operator = operator;
		}

		@Override
		public Operation getOperation() {
			return operator;
		}

		public String toString() {
			return getName() + "(" + getOperation() + ")";
		}
	}

	static IVariableRole createGatherer(IVariable var) {
		assert isGatherer(var);
		Visitor v = new Visitor(var);
		var.getProcedure().accept(v);
		return new Gatherer(v.operator);
	}

	static Operation getAccumulationOperator(IVariableAssignment var) {
		IExpression expression = var.getExpression();
		if(expression instanceof IBinaryExpression) {
			IBinaryExpression e = (IBinaryExpression) expression;
			IExpression left = e.getLeftOperand();
			IExpression right = e.getRightOperand();
			if(e.getOperator().isArithmetic() && 
					(
							left instanceof IVariable && ((IVariable) left).equals(var.getTarget()) && !(right instanceof ILiteral) ||
							right instanceof IVariable && ((IVariable) right).equals(var.getTarget()) && !(left instanceof ILiteral))
					)
				return match(e.getOperator());
		}
		return null;
	}
	
	static Operation match(IBinaryOperator op) {
		if(op == IOperator.ADD)
			return Operation.ADD;
		if(op == IOperator.SUB)
			return Operation.SUB;
		if(op == IOperator.MUL)
			return Operation.MUL;
		if(op == IOperator.DIV || op == IOperator.IDIV)
			return Operation.DIV;
		return null;
	}
}
