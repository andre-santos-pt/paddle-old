package pt.iscte.paddle.asg.future;

import java.util.List;

import pt.iscte.paddle.model.IType;
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

}
