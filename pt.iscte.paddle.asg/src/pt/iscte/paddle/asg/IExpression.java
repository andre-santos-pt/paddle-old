package pt.iscte.paddle.asg;

import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.IModel2CodeTranslator;
import pt.iscte.paddle.asg.IOperator.OperationType;

/**
 * Immutable
 *
 */
public interface IExpression extends IProgramElement {
	IType getType();

	// TODO concretize expression
	//String concretize();
	//	ISourceElement getParent();


	default boolean isDecomposable() {
		return this instanceof ICompositeExpression;
	}
	
	int getNumberOfParts();

	default List<IExpression> decompose() {
		return ImmutableList.of();
	}

	default OperationType getOperationType() {
		return OperationType.OTHER;
	}

	default String translate(IModel2CodeTranslator t) {
		return t.expression(this);
	}
	
	IConditionalExpression conditional(IExpression trueCase, IExpression falseCase);
	
	
	default void accept(IVisitor visitor) {
		visitPart(visitor, this);
	}

	static void visitPart(IVisitor visitor, IExpression part) {
		if(part instanceof IArrayAllocation) {
			IArrayAllocation alloc = (IArrayAllocation) part; 
			if(visitor.visit(alloc))
				part.decompose().forEach(p -> visitPart(visitor, p));
		}
		else if(part instanceof IArrayLength) {
			IArrayLength len = (IArrayLength) part; 
			if(visitor.visit(len))
				part.decompose().forEach(p -> visitPart(visitor, p));
		}
		else if(part instanceof IArrayElement) {
			IArrayElement el = (IArrayElement) part; 
			if(visitor.visit(el))
				part.decompose().forEach(p -> visitPart(visitor, p));
		}
		
		else if(part instanceof IUnaryExpression) {
			IUnaryExpression un = (IUnaryExpression) part; 
			if(visitor.visit(un))
				part.decompose().forEach(p -> visitPart(visitor, p));
		}
		else if(part instanceof IBinaryExpression) {
			IBinaryExpression bi = (IBinaryExpression) part; 
			if(visitor.visit(bi))
				part.decompose().forEach(p -> visitPart(visitor, p));
		}
		
		else if(part instanceof IProcedureCall) {
			IProcedureCall call = (IProcedureCall) part; 
			if(visitor.visit(call))
				part.decompose().forEach(p -> visitPart(visitor, p));
		}

//		else if(part instanceof IConstantExpression) {
//			IConstantExpression con = (IConstantExpression) part; 
//			visitor.visit(con);
//		}
		
		else if(part instanceof IConstant) {
			IConstant con = (IConstant) part; 
			visitor.visit(con);
		}
		
		else if(part instanceof ILiteral) {
			ILiteral lit = (ILiteral) part; 
			visitor.visit(lit);
		}
		
		
		else if(part instanceof IRecordAllocation) {
			IRecordAllocation str = (IRecordAllocation) part; 
			visitor.visit(str);
		}
		else if(part instanceof IRecordFieldExpression) {
			IRecordFieldExpression sm = (IRecordFieldExpression) part; 
			visitor.visit(sm);
		}
		
		// before IVariable because it is subtype
		else if(part instanceof IVariableDereference) {
			IVariableDereference varadd = (IVariableDereference) part; 
			visitor.visit(varadd);
		}
		
		else if(part instanceof IVariable) {
			IVariable var = (IVariable) part; 
			visitor.visit(var);
		}
//		else if(part instanceof IVariableExpression) {
//			IVariableExpression var = (IVariableExpression) part; 
//			visitor.visit(var);
//		}
		else if(part instanceof IVariableAddress) {
			IVariableAddress varadd = (IVariableAddress) part; 
			visitor.visit(varadd);
		}
		
		
		else
			assert false: "missing case " + part.getClass().getName();
	}

	// TODO include parent in visit(...) ?
	interface IVisitor {
		default boolean visit(IArrayAllocation exp) 		{ return true; }
		default boolean visit(IArrayLength exp) 	{ return true; }
		default boolean visit(IArrayElement exp) 	{ return true; }
		
		default boolean visit(IUnaryExpression exp) 		{ return true; }
		default boolean visit(IBinaryExpression exp) 		{ return true; }
		
		default boolean visit(IProcedureCall exp) 			{ return true; }

//		default void 	visit(IConstantExpression exp) 		{ }
		default void 	visit(IConstant exp) 				{ }
		default void 	visit(ILiteral exp) 				{ }
		
		default void 	visit(IRecordAllocation exp) 		{ }
		default void 	visit(IRecordFieldExpression exp) 	{ }
	
//		default void 	visit(IVariableExpression exp) 		{ }
		default void 	visit(IVariable exp)		 		{ }
		default void	visit(IVariableAddress exp) 		{ }
		default void	visit(IVariableDereference exp) 	{ }
	}



	
}
