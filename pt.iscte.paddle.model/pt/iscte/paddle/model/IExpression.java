package pt.iscte.paddle.model;

import java.util.Arrays;
import java.util.List;

import pt.iscte.paddle.model.IOperator.OperationType;
import pt.iscte.paddle.model.IVariableDeclaration.UnboundVariable;
import pt.iscte.paddle.model.impl.Literal;

/**
 * Immutable
 *
 */
public interface IExpression extends IProgramElement, IExpressionView<IExpression> {
	IType getType();

	// TODO concretize expression
	//String concretize();
	
	//	ISourceElement getParent();

	@Override
	default IExpression expression() {
		return this;
	}
	
	
	IArrayLength length(List<IExpression> indexes);
	default IArrayLength length(IExpression ... indexes) {
		return length(Arrays.asList(indexes));
	}
	default IArrayLength length(IExpressionView ... views) {
		return length(IExpressionView.toList(views));
	}
	
	IArrayElement element(List<IExpression> indexes);
	default IArrayElement element(IExpression ... indexes) {
		return element(Arrays.asList(indexes));
	}
	default IArrayElement element(IExpressionView ... views) {
		return element(IExpressionView.toList(views));
	}

	IRecordFieldExpression field(IVariableDeclaration field); 
	
	default IRecordFieldExpression field(String id) {
		return field(new UnboundVariable(id));
	}
	
	default boolean isSimple() {
		return this instanceof ISimpleExpression;
	}
	
	default boolean isComposite() {
		return this instanceof ICompositeExpression;
	}
	
	int getNumberOfParts();

	default List<IExpression> getParts() {
		return List.of();
	}

	default OperationType getOperationType() {
		return OperationType.OTHER;
	}

	default boolean isNull() {
		return this == Literal.NULL;
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
	
	boolean includes(IVariableDeclaration variable);
	
	default void accept(IVisitor visitor) {
		visitPart(visitor, this);
	}
	
	static boolean areSame(List<IExpression> a, List<IExpression> b) {
		if(a.size() != b.size())
				return false;
			
		int i = 0;
		for(IExpression e : a)
			if(!e.isSame(b.get(i++)))
				return false;
			
		return true;
	}

	static void visitPart(IVisitor visitor, IExpression part) {
		visitor.visitAny(part);
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

		else if(part instanceof IProcedureCallExpression) {
			IProcedureCallExpression exp = (IProcedureCallExpression) part; 
			if(visitor.visit(exp))
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

		default boolean visit(IProcedureCallExpression exp) { return true; }

		default boolean visit(IConditionalExpression exp) 	{ return true; }

		default void 	visit(ILiteral exp) 				{ }

		default void 	visit(IVariableExpression exp) 		{ }
		default void 	visit(IConstantExpression exp) 		{ }
		
		default void	visit(IVariableAddress exp) 		{ }
		default void	visit(IVariableDereference exp) 	{ }

		default void 	visit(IRecordAllocation exp) 		{ }
		default void 	visit(IRecordFieldExpression exp) 	{ }
		
		default void 	visitAny(IExpression exp)			{ }

	}
}
