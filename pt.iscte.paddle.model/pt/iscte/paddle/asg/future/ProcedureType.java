package pt.iscte.paddle.asg.future;

import java.util.List;

import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedureCallExpression;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.impl.ProgramElement;

public class ProcedureType extends ProgramElement implements IProcedureType {
	private final IType retType;
	private final List<IType> paramTypes;
	
	ProcedureType(IType retType, List<IType> paramTypes, String... flags) {
		super(flags);
		this.retType = retType;
		this.paramTypes = List.copyOf(paramTypes);
	}

	@Override
	public IType getReturnType() {
		return retType;
	}

	@Override
	public List<IType> getParameterTypes() {
		return paramTypes;
	}

	@Override
	public IModule getModule() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IVariableDeclaration> getLocalVariables() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IVariableDeclaration> getVariables() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBlock getBody() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IVariableDeclaration> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IVariableDeclaration addParameter(IType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IProcedureCallExpression expression(List<IExpression> args) {
		// TODO Auto-generated method stub
		return null;
	}

}
