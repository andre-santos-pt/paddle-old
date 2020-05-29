package pt.iscte.paddle.model.impl;

import java.util.Collections;
import java.util.List;

import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProcedureCallExpression;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariableDeclaration;

public class UnboundProcedure extends ProgramElement implements IProcedure {
	public UnboundProcedure(String id, String namespace) {
		setId(id);
		setNamespace(namespace);
	}

	@Override
	public List<IVariableDeclaration> getParameters() {
		return null;
	}

	@Override
	public IVariableDeclaration addParameter(IType type) {
		return null;
	}

	@Override
	public IProcedureCallExpression expression(List<IExpression> args) {
		return new ProcedureCall(null, this, -1, args);
	}

	@Override
	public List<IVariableDeclaration> getLocalVariables() {
		return Collections.emptyList();
	}

	@Override
	public List<IVariableDeclaration> getVariables() {
		return Collections.emptyList();
	}

	@Override
	public IType getReturnType() {
		return IType.UNBOUND;
	}

	@Override
	public IBlock getBody() {
		return null;
	}

	@Override
	public IModule getModule() {
		return null;
	}

}