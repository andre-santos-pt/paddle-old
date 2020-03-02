package pt.iscte.paddle.model;

import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.model.IOperator.OperationType;

/**
 * Immutable
 *
 */
public interface IExpression extends IProgramElement, IExpressionView {
	IType getType();

	// TODO concretize expression
	//String concretize();
	//	ISourceElement getParent();

	@Override
	default IExpression expression() {
		return this;
	}
	
	default boolean isDecomposable() {
		return this instanceof ICompositeExpression;
	}

	int getNumberOfParts();

	default List<IExpression> getParts() {
		return ImmutableList.of();
	}

	default OperationType getOperationType() {
		return OperationType.OTHER;
	}

	default String translate(IModel2CodeTranslator t) {
		return t.expression(this);
	}

	default boolean isNull() {
		return this == ILiteral.NULL;
	}

	IConditionalExpression conditional(IExpression trueCase, IExpression falseCase);

	default IConditionalExpression conditional(IExpressionView trueCase, IExpressionView falseCase) {
		return conditional(trueCase.expression(), falseCase.expression());
	}
	
	default IExpression resolveReference() {
		if(this instanceof IVariableDereference)
			return ((IVariableDereference) this).getTarget();
		else
			return this;
	}
	
	// boolean refersTo(IVariable v); // TODO 

	default void accept(IVisitor visitor) {
		visitPart(visitor, this);
	}

	static void visitPart(IVisitor visitor, IExpression part) {
		if(part instanceof IArrayAllocation) {
			IArrayAllocation alloc = (IArrayAllocation) part; 
			if(visitor.visit(alloc))
				part.getParts().forEach(p -> visitPart(visitor, p));
		}
		else if(part instanceof IArrayLength) {
			IArrayLength len = (IArrayLength) part; 
			if(visitor.visit(len))
				part.getParts().forEach(p -> visitPart(visitor, p));
		}
		else if(part instanceof IArrayElement) {
			IArrayElement el = (IArrayElement) part; 
			if(visitor.visit(el))
				part.getParts().forEach(p -> visitPart(visitor, p));
		}

		else if(part instanceof IUnaryExpression) {
			IUnaryExpression un = (IUnaryExpression) part; 
			if(visitor.visit(un))
				part.getParts().forEach(p -> visitPart(visitor, p));
		}
		else if(part instanceof IBinaryExpression) {
			IBinaryExpression bi = (IBinaryExpression) part; 
			if(visitor.visit(bi))
				part.getParts().forEach(p -> visitPart(visitor, p));
		}

		else if(part instanceof IProcedureCall) {
			IProcedureCall call = (IProcedureCall) part; 
			if(visitor.visit(call))
				part.getParts().forEach(p -> visitPart(visitor, p));
		}

		else if(part instanceof IConditionalExpression) {
			IConditionalExpression con = (IConditionalExpression) part;
			if(visitor.visit(con))
				part.getParts().forEach(p -> visitPart(visitor, p));
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

		else if(part instanceof IVariableAddress) {
			IVariableAddress varadd = (IVariableAddress) part; 
			visitor.visit(varadd);
		}
		
		else if(part instanceof IVariableExpression) {
			IVariableExpression var = (IVariableExpression) part; 
			visitor.visit(var);
		}
		
		else if(part instanceof IConstantExpression) {
			IConstantExpression con = (IConstantExpression) part; 
			visitor.visit(con);
		}
		
		else
			assert false: "missing case " + part.getClass().getName();
	}

	interface IVisitor {
		default boolean visit(IArrayAllocation exp) 		{ return true; }
		default boolean visit(IArrayLength exp) 			{ return true; }
		default boolean visit(IArrayElement exp) 			{ return true; }

		default boolean visit(IUnaryExpression exp) 		{ return true; }
		default boolean visit(IBinaryExpression exp) 		{ return true; }

		default boolean visit(IProcedureCall exp) 			{ return true; }

		default boolean visit(IConditionalExpression exp) 	{ return true; }

		default void 	visit(ILiteral exp) 				{ }

		default void 	visit(IVariableExpression exp) 		{ }
		default void 	visit(IConstantExpression exp) 		{ }
		
		default void	visit(IVariableAddress exp) 		{ }
		default void	visit(IVariableDereference exp) 	{ }

		default void 	visit(IRecordAllocation exp) 		{ }
		default void 	visit(IRecordFieldExpression exp) 	{ }

	}
}
