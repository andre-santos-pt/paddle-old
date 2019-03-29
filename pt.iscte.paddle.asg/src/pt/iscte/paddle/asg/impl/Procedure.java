package pt.iscte.paddle.asg.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProcedureCall;
import pt.iscte.paddle.asg.IVariable;

class Procedure extends ProgramElement implements IProcedure {
	private final List<IVariable> variables;
	private final List<IVariable> variablesView;
	private final ParamsView paramsView;
	private final LocalVariablesView localVarsView;
	private final IDataType returnType;
	private final Block body;
	private int parameters;

	public Procedure(IDataType returnType) {
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
	public IVariable addParameter(IDataType type) {
		IVariable param = new Variable(body, type);
		variables.add(parameters, param);
		parameters++;
		return param;
	}


	@Override
	public IVariable getVariable(String id) {
		for(IVariable v : variables)
			if(v.getId().equals(id))
				return v;
		return null;
	}

	@Override
	public IDataType getReturnType() {
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

		String vars = "";
		for(IVariable var : variables)
			vars += var.getType().getId() + " " + var.getId() +"\n";
		return returnType + " " + getId() + "(" + params + ")" + "\n" + vars + body.toString();
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
