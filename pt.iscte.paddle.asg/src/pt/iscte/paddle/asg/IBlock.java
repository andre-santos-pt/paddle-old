package pt.iscte.paddle.asg;

import java.util.Arrays;
import java.util.List;

/**
 * Mutable
 */
public interface IBlock extends IInstruction {
	IProgramElement getParent();

	IProcedure getProcedure();
	
	List<IInstruction> getInstructionSequence();

	boolean isEmpty();
	
	

	IVariable addVariable(String name, IDataType type);

	IBlock addBlock();

	IVariableAssignment addAssignment(IVariable var, IExpression exp);

	
	default IVariableAssignment addIncrement(IVariable var) {
		assert var.getType() == IDataType.INT;
		return addAssignment(var, IOperator.ADD.on(var, ILiteral.literal(1)));
	}

	default IVariableAssignment addDecrement(IVariable var) {
		assert var.getType() == IDataType.INT;
		return addAssignment(var, IOperator.SUB.on(var, ILiteral.literal(1)));
	}

	IArrayElementAssignment addArrayElementAssignment(IVariable var, IExpression exp, List<IExpression> indexes);
	default IArrayElementAssignment addArrayElementAssignment(IVariable var, IExpression exp, IExpression ... indexes) {
		return addArrayElementAssignment(var, exp, Arrays.asList(indexes));
	}

	IStructMemberAssignment addStructMemberAssignment(IVariable var, String memberId, IExpression exp);


	ISelection addSelection(IExpression guard);
	
	ISelectionWithAlternative addSelectionWithAlternative(IExpression guard);

	ILoop addLoop(IExpression guard);

	IReturn addReturn(IExpression expression);

	IBreak addBreak();

	IContinue addContinue();
	
	IProcedureCall addCall(IProcedure procedure, List<IExpression> args);
	default IProcedureCall addCall(IProcedure procedure, IExpression ... args) {
		return addCall(procedure, Arrays.asList(args));
	}


	default void accept(IVisitor visitor) {
		for(IInstruction s : getInstructionSequence()) {
			if(s instanceof IReturn) {
				IReturn ret = (IReturn) s;
				if(visitor.visit(ret) && !ret.getReturnValueType().isVoid())
					ret.getExpression().accept(visitor);
			}
			else if(s instanceof IArrayElementAssignment) {
				IArrayElementAssignment ass = (IArrayElementAssignment) s;
				if(visitor.visit(ass))
					ass.getExpression().accept(visitor);
			}
			else if(s instanceof IVariableAssignment) {
				IVariableAssignment ass = (IVariableAssignment) s;
				if(visitor.visit(ass))
					ass.getExpression().accept(visitor);
			}
			else if(s instanceof IStructMemberAssignment) {
				IStructMemberAssignment ass = (IStructMemberAssignment) s;
				if(visitor.visit(ass))
					ass.getExpression().accept(visitor);
			}
			else if(s instanceof IProcedureCall) {
				IProcedureCall call = (IProcedureCall) s;
				if(visitor.visit(call))
					call.getArguments().forEach(a -> a.accept(visitor));
			}
			else if(s instanceof IBreak) {
				visitor.visit((IBreak) s);
			}
			else if(s instanceof IContinue) {
				visitor.visit((IContinue) s);				
			}
			else if(s instanceof ISelection) {
				ISelection sel = (ISelection) s;
				if(visitor.visit(sel)) {
					sel.getGuard().accept(visitor);
					sel.accept(visitor);
					if(sel instanceof ISelectionWithAlternative)
						((ISelectionWithAlternative) sel).getAlternativeBlock().accept(visitor);
				}
				visitor.endVisit(sel);
			}
			else if(s instanceof ILoop) {
				ILoop loop = (ILoop) s;
				if(visitor.visit(loop)) {
					loop.getGuard().accept(visitor);
					loop.accept(visitor);
				}
				visitor.endVisit(loop);
			}
			else if(s instanceof IBlock) { // only single blocks
				IBlock b = (IBlock) s;
				if(visitor.visit(b))
					b.accept(visitor);
				visitor.endVisit(b);
			}
			else
				assert false: "missing case " + s.getClass().getName();
		}
	}

	interface IVisitor extends IExpression.IVisitor {
		
		// IStatement
		default boolean visit(IReturn returnStatement) 				{ return true; }
		default boolean visit(IArrayElementAssignment assignment) 	{ return true; }
		default boolean visit(IVariableAssignment assignment) 		{ return true; }
		default boolean visit(IStructMemberAssignment assignment) 	{ return true; }
		default boolean visit(IProcedureCall call) 					{ return true; }
		
		// IControlStructure
		default boolean visit(ISelection selection) 				{ return true; } // also ISelectionWithAlternative
		default void endVisit(ISelection selection) 				{ }
	
		default boolean visit(ILoop loop) 							{ return true; }
		default void endVisit(ILoop loop) 							{ }
		
		// other
		default boolean visit(IBlock block) 						{ return true; }
		default void endVisit(IBlock block) 						{ }
				
		default void 	visit(IBreak breakStatement) 				{ }
		default void 	visit(IContinue continueStatement) 			{ }
	
		 // TODO missing because it is not statement, only appears on expressions
		default void	visit(IVariable variable)					{ }
	}

}
