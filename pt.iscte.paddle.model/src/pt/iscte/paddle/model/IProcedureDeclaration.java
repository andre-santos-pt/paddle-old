package pt.iscte.paddle.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public interface IProcedureDeclaration extends IProgramElement {

	List<IVariable> getParameters();	

	IType getReturnType();

	IVariable addParameter(IType type);

	IProcedureCall call(List<IExpression> args);

	default IProcedureCall call(IExpression ... args) {
		return call(Arrays.asList(args));
	}

	default String shortSignature() {
		return getId() + "(...)";
	}

	default String longSignature() {
		String args = "";
		for (IVariable p : getParameters()) {
			if(!args.isEmpty())
				args += ", ";
			args += p.getType();
		}
		return getReturnType() + " " + getId() + "(" + args + ")";
	}

	default boolean matchesSignature(String id, IType... paramTypes) {
		if(!id.equals(getId()))
			return false;

		List<IVariable> parameters = getParameters();
		if(parameters.size() != paramTypes.length)
			return false;

		int i = 0;
		for(IType t : paramTypes)
			if(!parameters.get(i++).getType().equals(t))
				return false;

		return true;
	}

	// compares id and types of parameters
	// excludes return
	default boolean hasSameSignature(IProcedureDeclaration procedure) {
		if(!getId().equals(procedure.getId()) || getParameters().size() != procedure.getParameters().size())
			return false;

		Iterator<IVariable> procParamsIt = procedure.getParameters().iterator();
		for(IVariable p : getParameters())
			if(!p.getType().equals(procParamsIt.next().getType()))
				return false;

		return true;
	}

	default boolean isEqualTo(IProcedureDeclaration procedure) {
		return hasSameSignature(procedure) && getReturnType().equals(procedure.getReturnType());
	}

	IProcedureDeclaration UNBOUND = new IProcedureDeclaration() {

		@Override
		public void setProperty(Object key, Object value) {

		}

		@Override
		public Object getProperty(Object key) {
			return null;
		}
		
		@Override
		public String getId() {
			return "unresolved";
		}

		@Override
		public IType getReturnType() {
			return null;
		}

		@Override
		public List<IVariable> getParameters() {
			return Collections.emptyList();
		}

		@Override
		public IProcedureCall call(List<IExpression> args) {
			throw new UnsupportedOperationException();
		}

		@Override
		public IVariable addParameter(IType type) {
			throw new UnsupportedOperationException();
		}
	};

}
