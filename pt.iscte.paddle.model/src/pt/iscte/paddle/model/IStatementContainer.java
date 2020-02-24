package pt.iscte.paddle.model;

import java.util.Arrays;
import java.util.List;

public interface IStatementContainer {
	
	IBlock getBlock();
	
	
	default boolean isEmpty() {
		return getBlock().isEmpty();
	}
	
	
	default IBlock addBlock() {
		return addBlockAt(getBlock().getSize());
	}
	
	default IBlock addBlockAt(int index, String ... flags) {
		return getBlock().addBlockAt(index, flags);
	}
	
	

	default IVariable addVariable(IType type, String ... flags) {
		return addVariableAt(type, getBlock().getSize(), flags);
	}
	
	default IVariable addVariableAt(IType type, int index, String ... flags) {
		return getBlock().addVariableAt(type, index, flags);
	}

	default IVariable addVariable(IType type, IExpression initialization, String ... flags) {
		return addVariableAt(type, initialization, getBlock().getSize(), flags);
	}
	
	default IVariable addVariableAt(IType type, IExpression initialization, int index, String ... flags) {
		IVariable var = addVariableAt(type, index, flags);
		addAssignmentAt(var, initialization, index + 1);
		return var;
	}
	
	default IVariable addVariableWithIdAt(IType type, String id, String ... flags) {
		return addVariableWithIdAt(type, id, getBlock().getSize(), flags);
	}
	
	default IVariable addVariableWithIdAt(IType type, String id, int index, String ... flags) {
		return getBlock().addVariableWithIdAt(type, id, index, flags);
	}
	
	
	default IVariableAssignment addAssignment(IVariable var, IExpression exp, String ... flags) {
		return addAssignmentAt(var, exp, getBlock().getSize(), flags);
	}

	default IVariableAssignment addAssignmentAt(IVariable var, IExpression exp, int index, String ... flags) {
		return getBlock().addAssignmentAt(var, exp, index, flags);
	}

	default IVariableAssignment addAssignmentAt(String var, IExpression exp, int index, String ... flags) {
		IProcedure proc = getBlock().getOwnerProcedure();
		IVariable variable = proc.getVariable(var);
		if(variable == null)
			variable = new IVariable.UnboundVariable(var);
		
		return getBlock().addAssignmentAt(variable, exp, index, flags);
	}
	
	
	default IVariableAssignment addIncrement(IVariable var) {
		return addIncrementAt(var, getBlock().getSize());
	}
	
	default IVariableAssignment addIncrementAt(IVariable var, int index) {
//		assert var.getType() == IType.INT;
		IVariableAssignment a = addAssignmentAt(var, IOperator.ADD.on(var, IType.INT.literal(1)), index, "INC");
		return a;
	}
	
	default IVariableAssignment addDecrement(IVariable var) {
		return addDecrementAt(var, getBlock().getSize());
	}

	default IVariableAssignment addDecrementAt(IVariable var, int index) {
		assert var.getType() == IType.INT;
		return addAssignmentAt(var, IOperator.SUB.on(var, IType.INT.literal(1)), index);
	}
	
	
	
	default IArrayElementAssignment addArrayElementAssignment(IExpression target, IExpression exp, List<IExpression> indexes) {
		return addArrayElementAssignmentAt(target, exp, getBlock().getSize(), indexes);
	}

	default IArrayElementAssignment addArrayElementAssignmentAt(IExpression target, IExpression exp, int index, List<IExpression> indexes) {
		return getBlock().addArrayElementAssignmentAt(target, exp, index, indexes);
	}
	
	default IArrayElementAssignment addArrayElementAssignment(IExpression target, IExpression exp, IExpression ... indexes) {
		return addArrayElementAssignmentAt(target, exp, getBlock().getSize(), indexes);
	}
	
	default IArrayElementAssignment addArrayElementAssignmentAt(IExpression target, IExpression exp, int index, IExpression ... indexes) {
		return addArrayElementAssignmentAt(target, exp, index, Arrays.asList(indexes));
	}

	
	
	default IRecordFieldAssignment addRecordFieldAssignment(IRecordFieldExpression target, IExpression exp) {
		return addRecordFieldAssignmentAt(target, exp, getBlock().getSize());
	}
	
	default IRecordFieldAssignment addRecordFieldAssignmentAt(IRecordFieldExpression target, IExpression exp, int index) {
		return getBlock().addRecordFieldAssignmentAt(target, exp, index);
	}
	
	
	
	default ISelection addSelection(IExpression guard) {
		return addSelectionAt(guard, getBlock().getSize());
	}
	
	default ISelection addSelectionAt(IExpression guard, int index) {
		return getBlock().addSelectionAt(guard, index);
	}

	default ISelection addSelectionWithAlternative(IExpression guard) {
		return addSelectionWithAlternativeAt(guard, getBlock().getSize());
	}
	
	default ISelection addSelectionWithAlternativeAt(IExpression guard, int index) {
		return getBlock().addSelectionWithAlternativeAt(guard, index);
	}

	
	
	
	default ILoop addLoop(IExpression guard, String ... flags) {
		return addLoopAt(guard, getBlock().getSize(), flags);
	}
	
	default ILoop addLoopAt(IExpression guard, int index, String ... flags) {
		return getBlock().addLoopAt(guard, index, flags);
	}

	
	
	default IReturn addReturn() {
		return addReturnAt(getBlock().getSize());
	}
	
	default IReturn addReturnAt(int index) {
		return getBlock().addReturnAt(index);
	}
	
	default IReturn addReturn(IExpression expression) {
		return addReturnAt(expression, getBlock().getSize());
	}
	
	default IReturn addReturnAt(IExpression expression, int index) {
		return getBlock().addReturnAt(expression, index);
	}
	
	
	
	default IBreak addBreak() {
		return addBreakAt(getBlock().getSize());
	}
	
	default IBreak addBreakAt(int index) {
		return getBlock().addBreakAt(index);
	}

	
	
	default IContinue addContinue() {
		return addContinueAt(getBlock().getSize());
	}
	
	default IContinue addContinueAt(int index) {
		return getBlock().addContinueAt(index);
	}
	
	default IProcedureCall addCall(IProcedure procedure, List<IExpression> args) {
		return addCallAt(procedure, getBlock().getSize(), args);
	}
	
	default IProcedureCall addCallAt(IProcedure procedure, int index, List<IExpression> args) {
		return getBlock().addCallAt(procedure, index, args);
	}
	
	default IProcedureCall addCall(IProcedure procedure, IExpression ... args) {
		return addCallAt(procedure, getBlock().getSize(), args);
	}
	
	default IProcedureCall addCallAt(IProcedure procedure, int index, IExpression ... args) {
		return addCallAt(procedure, index, Arrays.asList(args));
	}
	
	default IBlockElement removeElement(int index) {
		return getBlock().removeElement(index);
	}

}
