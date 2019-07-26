package pt.iscte.paddle.model;

import java.util.Arrays;
import java.util.List;

public interface IStatementContainer {
	
	IBlock getBlock();
	
	default boolean isEmpty() {
		return getBlock().isEmpty();
	}
	
	default IBlock addBlock() {
		return getBlock().addBlock();
	}

	default IVariable addVariable(IType type) {
		return getBlock().addVariable(type);
	}

	default IVariable addVariable(IType type, IExpression initialization) {
		IVariable var = addVariable(type);
		addAssignment(var, initialization);
		return var;
	}

	default IVariableAssignment addAssignment(IVariable var, IExpression exp) {
		return getBlock().addAssignment(var, exp);
	}

	default IVariableAssignment addIncrement(IVariable var) {
		assert var.getType() == IType.INT;
		return addAssignment(var, IOperator.ADD.on(var, IType.INT.literal(1)));
	}

	default IVariableAssignment addDecrement(IVariable var) {
		assert var.getType() == IType.INT;
		return addAssignment(var, IOperator.SUB.on(var, IType.INT.literal(1)));
	}

	default IArrayElementAssignment addArrayElementAssignment(IExpression target, IExpression exp, List<IExpression> indexes) {
		return getBlock().addArrayElementAssignment(target, exp, indexes);
	}
	
	default IArrayElementAssignment addArrayElementAssignment(IExpression target, IExpression exp, IExpression ... indexes) {
		return addArrayElementAssignment(target, exp, Arrays.asList(indexes));
	}

	default IRecordFieldAssignment addRecordFieldAssignment(IRecordFieldExpression target, IExpression exp) {
		return getBlock().addRecordFieldAssignment(target, exp);
	}
	
	default ISelection addSelection(IExpression guard) {
		return getBlock().addSelection(guard);
	}

	default ISelection addSelectionWithAlternative(IExpression guard) {
		return getBlock().addSelectionWithAlternative(guard);
	}

	default ILoop addLoop(IExpression guard) {
		return getBlock().addLoop(guard);
	}

	
	default IReturn addReturn() {
		return getBlock().addReturn();
	}
	
	default IReturn addReturn(IExpression expression) {
		return getBlock().addReturn(expression);
	}
	
	default IBreak addBreak() {
		return getBlock().addBreak();
	}

	default IContinue addContinue() {
		return getBlock().addContinue();
	}

	default IProcedureCall addCall(IProcedure procedure, List<IExpression> args) {
		return getBlock().addCall(procedure, args);
	}
	
	default IProcedureCall addCall(IProcedure procedure, IExpression ... args) {
		return addCall(procedure, Arrays.asList(args));
	}

}
