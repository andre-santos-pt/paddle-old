package pt.iscte.paddle.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public interface IProcedureDeclaration extends IProgramElement {

	List<IVariableDeclaration> getParameters();	

	IType getReturnType();

	IVariableDeclaration addParameter(IType type);

	IProcedureCallExpression expression(List<IExpression> args);

	default IProcedureCallExpression expression(IExpression ... args) {
		return expression(Arrays.asList(args));
	}
	
	default IProcedureCallExpression expression(IExpressionView ... views) {
		return expression(IExpressionView.toList(views));
	}

//	default IProcedureCallExpression expression(List<IExpression> args) {
//		return (IProcedureCallExpression) call(args);
//	}
//	
//	default IProcedureCallExpression expression(IExpression ... args) {
//		return expression(Arrays.asList(args));
//	}
//	
//	default IProcedureCallExpression expression(IExpressionView ... views) {
//		return expression(IExpressionView.toList(views));
//	}
	
	
	default String shortSignature() {
		return getId() + "(...)";
	}

	default String longSignature() {
		String args = "";
		for (IVariableDeclaration p : getParameters()) {
			if(!args.isEmpty())
				args += ", ";
			args += p.getType();
		}
		return getReturnType() + " " + getId() + "(" + args + ")";
	}

	default boolean matchesSignature(String id, IType... paramTypes) {
		if(!id.equals(getId()))
			return false;

		List<IVariableDeclaration> parameters = getParameters();
		if(parameters.size() != paramTypes.length)
			return false;

		int i = 0;
		for(IType t : paramTypes)
			if(!parameters.get(i++).getType().isSame(t))
				return false;

		return true;
	}

	// compares id and types of parameters
	// excludes return
	default boolean hasSameSignature(IProcedureDeclaration procedure) {
		if(!getId().equals(procedure.getId()) || getParameters().size() != procedure.getParameters().size())
			return false;

		Iterator<IVariableDeclaration> procParamsIt = procedure.getParameters().iterator();
		for(IVariableDeclaration p : getParameters())
			if(!p.getType().equals(procParamsIt.next().getType()))
				return false;

		return true;
	}

	default boolean isEqualTo(IProcedureDeclaration procedure) {
		return hasSameSignature(procedure) && getReturnType().equals(procedure.getReturnType());
	}



}
