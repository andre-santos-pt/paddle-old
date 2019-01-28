package pt.iscte.paddle.asg;

import java.util.Arrays;
import java.util.List;

import pt.iscte.paddle.asg.impl.Factory;

public interface IFactory {
	IFactory INSTANCE = new Factory();
	
	IModule createModule(String id);
	
//	IVariableExpression variableExpression(IVariableDeclaration var);
//	IArrayElementExpression arrayElementExpression(IArrayVariableDeclaration var, List<IExpression> indexes);
	
	IUnaryExpression unaryExpression(IUnaryOperator operator, IExpression exp);
	
	IBinaryExpression binaryExpression(IBinaryOperator operator, IExpression left, IExpression right);

	
	ILiteral literal(IValueType type, String string);
	ILiteral literalMatch(String string);
	ILiteral literal(int value);
	ILiteral literal(double value);
	ILiteral literal(boolean value);
	ILiteral nullLiteral();

	IArrayAllocation arrayAllocation(IDataType type, int nDims, List<IExpression> dimensions);
	default IArrayAllocation arrayAllocation(IDataType type, int nDims, IExpression ... dimensions) {
		return arrayAllocation(type, nDims, Arrays.asList(dimensions));
	}
	
	IArrayType arrayType(IDataType componentType, int dimensions);
	
//	IReferenceType referenceType(IDataType targetType);

}
