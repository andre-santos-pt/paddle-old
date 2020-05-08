package pt.iscte.paddle.asg.future;

import java.util.List;

import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IReferenceType;
import pt.iscte.paddle.model.IType;

public interface IProcedureType extends IType {
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
}
