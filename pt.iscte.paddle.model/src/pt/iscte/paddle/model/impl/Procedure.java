package pt.iscte.paddle.model.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProcedureCall;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariable;

class Procedure extends ProgramElement implements IProcedure {
	private final List<IVariable> variables;
	private final List<IVariable> variablesView;
	private final ParamsView paramsView;
	private final LocalVariablesView localVarsView;
	private final IType returnType;
	private final Block body;
	private int parameters;

	public Procedure(IType returnType) {
		this.variables = new ArrayList<>(5);
		this.parameters = 0;
		this.returnType = returnType;

		variablesView = Collections.unmodifiableList(variables);
		paramsView = new ParamsView();
		localVarsView = new LocalVariablesView();
		body = new Block(this, false);
	}

	@Override
	public List<IVariable> getVariables() {
		return variablesView;
	}

	@Override
	public List<IVariable> getParameters() {
		return paramsView;
	}

	@Override
	public List<IVariable> getLocalVariables() {
		return localVarsView;
	}

	@Override
	public IVariable addParameter(IType type) {
		IVariable param = new Variable(body, type);
		variables.add(parameters, param);
		parameters++;
		return param;
	}


	
	
//	@Override
//	public IVariable getVariable(String id) {
//		for(IVariable v : variables)
//			if(v.getId().equals(id))
//				return v;
//		return null;
//	}

	@Override
	public IType getReturnType() {
		return returnType;
	}

	@Override
	public IBlock getBody() {
		return body;
	}

	@Override
	public String toString() {
		String params = "";
		for(IVariable p : paramsView) {
			if(!params.isEmpty())
				params += ", ";
			params += p.getType().getId() + " " + p.getId();
		}

		return returnType + " " + getId() + "(" + params + ") " + body.toString();
	}

	private class ParamsView extends AbstractList<IVariable> {
		@Override
		public IVariable get(int index) {
			return variables.get(index);
		}

		@Override
		public int size() {
			return parameters;
		}
	}

	private class LocalVariablesView extends AbstractList<IVariable> {
		@Override
		public IVariable get(int index) {
			return variables.get(parameters + index);
		}

		@Override
		public int size() {
			return variables.size() - parameters;
		}
	}


	void addVariableDeclaration(IVariable var) {
		variables.add(var);
	}

	@Override
	public IProcedureCall call(List<IExpression> args) {
		return new ProcedureCall(null, this, args);
	}
}
