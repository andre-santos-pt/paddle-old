package pt.iscte.paddle.roles;

import pt.iscte.paddle.model.IBinaryExpression;
import pt.iscte.paddle.model.IBinaryOperator;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IVariable;
import pt.iscte.paddle.model.IVariableAssignment;

public interface IGatherer extends IVariableRole {
	
	default String getName() {
		return "Gatherer";
	}
	
	IVariable getSource();
	IBinaryOperator getOperator();
	
	class Visitor implements IBlock.IVisitor {
		final IVariable var;
		boolean allSameAcc = true;
		IBinaryOperator operator = null;
		boolean first = true;
		
		Visitor(IVariable var) {
			this.var = var;
		}
		
		@Override
		public boolean visit(IVariableAssignment assignment) {
			if(assignment.getVariable().equals(var)) {
				if(first)
					first = false; // FIXME
				else {
					IBinaryOperator op = getAccumulationOperator(assignment);
					if(op == null || operator != null && op != operator)
						allSameAcc = false;
					else
						operator = op;
				}
			}
			return true;
		}
	}

	static boolean isGatherer(IVariable var) {
		Visitor v = new Visitor(var);
		((IProcedure) var.getParent()).getBody().accept(v);
		return v.allSameAcc && v.operator != null;
	}

	static IVariableRole createGatherer(IVariable var) {
		assert isGatherer(var);
		Visitor v = new Visitor(var);
		((IProcedure) var.getParent()).getBody().accept(v);
		return new IGatherer() {
			@Override
			public IVariable getSource() {
				return null;
			}
			
			@Override
			public IBinaryOperator getOperator() {
				return v.operator;
			}
			
			@Override
			public String toString() {
				return getName() + "(" + getOperator() + ")";
			}
		};
	}
	
	static IBinaryOperator getAccumulationOperator(IVariableAssignment var) {
		IExpression expression = var.getExpression();
		if(expression instanceof IBinaryExpression) {
			IBinaryExpression e = (IBinaryExpression) expression;
			IExpression left = e.getLeftOperand();
			IExpression right = e.getRightOperand();
			if(e.getOperator().isArithmetic() && 
				(
				left instanceof IVariable && ((IVariable) left).equals(var.getVariable()) ||
				right instanceof IVariable && ((IVariable) right).equals(var.getVariable()))
				)
				return e.getOperator();
		}
		return null;
	}
	
	
}
