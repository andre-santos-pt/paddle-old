package pt.iscte.paddle.model.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProcedureCallExpression;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariableDeclaration;

class Procedure extends ProgramElement implements IProcedure {
	private final Module parent;
	private final List<IVariableDeclaration> variables;
	private final List<IVariableDeclaration> variablesView;
	private final ParamsView paramsView;
	private final LocalVariablesView localVarsView;
	private final IType returnType;
	private final Block body;
	private int parameters;
	
	public Procedure(Module parent, IType returnType) {
		this.parent = parent;
		this.variables = new ArrayList<>(5);
		this.parameters = 0;
		this.returnType = returnType;

		variablesView = Collections.unmodifiableList(variables);
		paramsView = new ParamsView();
		localVarsView = new LocalVariablesView();
		body = new Block(this, false, -1);
	}
	
	public Module getModule() {
		return parent;
	}
	
	@Override
	public List<IVariableDeclaration> getVariables() {
		return variablesView;
	}

	@Override
	public List<IVariableDeclaration> getParameters() {
		return paramsView;
	}

	@Override
	public List<IVariableDeclaration> getLocalVariables() {
		return localVarsView;
	}

	@Override
	public IVariableDeclaration addParameter(IType type) {
		IVariableDeclaration param = new VariableDeclaration(body, type);
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
		for(IVariableDeclaration p : paramsView) {
			if(!params.isEmpty())
				params += ", ";
			params += p.getType() + " " + p.getId();
		}

		return returnType + " " + getId() + "(" + params + ") " + body.toString();
	}

	private class ParamsView extends AbstractList<IVariableDeclaration> {
		@Override
		public IVariableDeclaration get(int index) {
			return variables.get(index);
		}

		@Override
		public int size() {
			return parameters;
		}
	}

	private class LocalVariablesView extends AbstractList<IVariableDeclaration> {
		@Override
		public IVariableDeclaration get(int index) {
			return variables.get(parameters + index);
		}

		@Override
		public int size() {
			return variables.size() - parameters;
		}
	}


	void addVariableDeclaration(IVariableDeclaration var) {
		variables.add(var);
	}

	@Override
	public IProcedureCallExpression expression(List<IExpression> args) {
		return new ProcedureCall(null, this, -1, args);
	}
}
