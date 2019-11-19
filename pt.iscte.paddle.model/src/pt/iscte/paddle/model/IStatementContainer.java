package pt.iscte.paddle.model;

import java.util.Arrays;
import java.util.List;

public interface IStatementContainer {
	
	IBlock getBlock();
	
	
	default boolean isEmpty() {
		return getBlock().isEmpty();
	}
	
	
	default IBlock addBlock(IProgramElement parent) {
		return addBlock(parent, getBlock().getSize());
	}
	
	default IBlock addBlock(IProgramElement parent, int index) {
		return getBlock().addBlock(parent, index);
	}
	
	

	default IVariable addVariable(IType type) {
		return addVariable(type, getBlock().getSize());
	}
	
	default IVariable addVariable(IType type, int index) {
		return getBlock().addVariable(type, index);
	}

	default IVariable addVariable(IType type, IExpression initialization) {
		return addVariable(type, initialization, getBlock().getSize());
	}
	
	default IVariable addVariable(IType type, IExpression initialization, int index) {
		IVariable var = addVariable(type, index);
		addAssignment(var, initialization, index + 1);
		return var;
	}
	
	default IVariableAssignment addAssignment(IVariable var, IExpression exp) {
		return addAssignment(var, exp, getBlock().getSize());
	}

	default IVariableAssignment addAssignment(IVariable var, IExpression exp, int index) {
		return getBlock().addAssignment(var, exp, index);
	}

	
	
	default IVariableAssignment addIncrement(IVariable var) {
		return addIncrement(var, getBlock().getSize());
	}
	
	default IVariableAssignment addIncrement(IVariable var, int index) {
		assert var.getType() == IType.INT;
		return addAssignment(var, IOperator.ADD.on(var, IType.INT.literal(1)), index);
	}
	
	default IVariableAssignment addDecrement(IVariable var) {
		return addDecrement(var, getBlock().getSize());
	}

	default IVariableAssignment addDecrement(IVariable var, int index) {
		assert var.getType() == IType.INT;
		return addAssignment(var, IOperator.SUB.on(var, IType.INT.literal(1)), index);
	}
	
	
	
	default IArrayElementAssignment addArrayElementAssignment(IExpression target, IExpression exp, List<IExpression> indexes) {
		return addArrayElementAssignment(target, exp, getBlock().getSize(), indexes);
	}

	default IArrayElementAssignment addArrayElementAssignment(IExpression target, IExpression exp, int index, List<IExpression> indexes) {
		return getBlock().addArrayElementAssignment(target, exp, index, indexes);
	}
	
	default IArrayElementAssignment addArrayElementAssignment(IExpression target, IExpression exp, IExpression ... indexes) {
		return addArrayElementAssignment(target, exp, getBlock().getSize(), indexes);
	}
	
	default IArrayElementAssignment addArrayElementAssignment(IExpression target, IExpression exp, int index, IExpression ... indexes) {
		return addArrayElementAssignment(target, exp, index, Arrays.asList(indexes));
	}

	
	
	default IRecordFieldAssignment addRecordFieldAssignment(IRecordFieldExpression target, IExpression exp) {
		return addRecordFieldAssignment(target, exp, getBlock().getSize());
	}
	
	default IRecordFieldAssignment addRecordFieldAssignment(IRecordFieldExpression target, IExpression exp, int index) {
		return getBlock().addRecordFieldAssignment(target, exp, index);
	}
	
	
	
	default ISelection addSelection(IExpression guard) {
		return addSelection(guard, getBlock().getSize());
	}
	
	default ISelection addSelection(IExpression guard, int index) {
		return getBlock().addSelection(guard, index);
	}

	default ISelection addSelectionWithAlternative(IExpression guard) {
		return addSelectionWithAlternative(guard, getBlock().getSize());
	}
	
	default ISelection addSelectionWithAlternative(IExpression guard, int index) {
		return getBlock().addSelectionWithAlternative(guard, index);
	}

	
	
	
	default ILoop addLoop(IExpression guard) {
		return addLoop(guard, getBlock().getSize());
	}
	
	default ILoop addLoop(IExpression guard, int index) {
		return getBlock().addLoop(guard, index);
	}

	
	
	default IReturn addReturn() {
		return addReturn(getBlock().getSize());
	}
	
	default IReturn addReturn(int index) {
		return getBlock().addReturn(index);
	}
	
	default IReturn addReturn(IExpression expression) {
		return addReturn(expression, getBlock().getSize());
	}
	
	default IReturn addReturn(IExpression expression, int index) {
		return getBlock().addReturn(expression, index);
	}
	
	
	
	default IBreak addBreak() {
		return addBreak(getBlock().getSize());
	}
	
	default IBreak addBreak(int index) {
		return getBlock().addBreak(index);
	}

	
	
	default IContinue addContinue() {
		return addContinue(getBlock().getSize());
	}
	
	default IContinue addContinue(int index) {
		return getBlock().addContinue(index);
	}
	
	default IProcedureCall addCall(IProcedure procedure, List<IExpression> args) {
		return addCall(procedure, getBlock().getSize(), args);
	}
	
	default IProcedureCall addCall(IProcedure procedure, int index, List<IExpression> args) {
		return getBlock().addCall(procedure, index, args);
	}
	
	default IProcedureCall addCall(IProcedure procedure, IExpression ... args) {
		return addCall(procedure, getBlock().getSize(), args);
	}
	
	default IProcedureCall addCall(IProcedure procedure, int index, IExpression ... args) {
		return addCall(procedure, index, Arrays.asList(args));
	}

}
