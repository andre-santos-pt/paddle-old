package pt.iscte.paddle.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import pt.iscte.paddle.model.impl.BuiltinProcedureReflective;

public interface IProcedureDeclaration extends INamespaceElement {

	String INSTANCE_FLAG = "INSTANCE";
	String CONSTRUCTOR_FLAG = "CONSTRUCTOR";
	
	List<IVariableDeclaration> getParameters();	

	IType getReturnType();

	IVariableDeclaration addParameter(IType type);

	IProcedureCallExpression expression(List<IExpression> args);

	default boolean sameNamespace(IProcedureDeclaration p) {
		return getNamespace().equals(p.getNamespace());
	}
	
	default IProcedureCallExpression expression(IExpression ... args) {
		return expression(Arrays.asList(args));
	}
	
	default IProcedureCallExpression expression(IExpressionView ... views) {
		return expression(IExpressionView.toList(views));
	}
	
	default boolean isBuiltIn() {
		return this instanceof BuiltinProcedureReflective;
	}
	

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

	default boolean matchesSignature(String id, List<IExpression> args) {
		IType[] types = new IType[args.size()];
		for(int i = 0; i < types.length; i++)
			types[i] = args.get(i).getType();
		return matchesSignature(id, types);
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
