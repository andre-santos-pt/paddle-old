package pt.iscte.paddle.asg.future;

import java.util.List;

import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IReferenceType;
import pt.iscte.paddle.model.IType;

public interface IProcedureType extends IType, IProcedure {
	IType getReturnType();
	List<IType> getParameterTypes();
	
	@Override
	default int getMemoryBytes() {
		return 4;
	}
	
	@Override
	default IExpression getDefaultExpression() {
		throw new UnsupportedOperationException();
	}
	
	 @Override
	default Object getDefaultValue() {
		 throw new UnsupportedOperationException();
	}
	 
	 @Override
	default IReferenceType reference() {
		throw new UnsupportedOperationException();
	}
	 
	public static IProcedureType create(IType returnType, List<IType> paramTypes) {
		return new ProcedureType(returnType, paramTypes);
	}
}
