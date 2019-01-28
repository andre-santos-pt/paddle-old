package pt.iscte.paddle.asg.impl;

import java.util.List;

import pt.iscte.paddle.asg.IArrayAllocation;
import pt.iscte.paddle.asg.IArrayType;
import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IBinaryOperator;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IFactory;
import pt.iscte.paddle.asg.ILiteral;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IUnaryExpression;
import pt.iscte.paddle.asg.IUnaryOperator;
import pt.iscte.paddle.asg.IValueType;

public class Factory implements IFactory {
	@Override
	public IModule createModule(String id) {
		return new Module(id);
	}

//	@Override
//	public IVariableExpression variableExpression(IVariableDeclaration variable) {
//		return new VariableExpression(variable);
//	}
//
//	@Override
//	public IArrayElementExpression arrayElementExpression(IArrayVariableDeclaration variable, List<IExpression> indexes) {
//		return new ArrayElementExpression(variable, indexes);
//	}

	@Override
	public IUnaryExpression unaryExpression(IUnaryOperator operator, IExpression exp) {
		return new UnaryExpression(operator, exp);
	}

	@Override
	public IBinaryExpression binaryExpression(IBinaryOperator operator, IExpression left, IExpression right) {
		return new BinaryExpression(operator, left, right);
	}

	@Override
	public ILiteral literal(IValueType type, String value) {
		return new Literal(type, value);
	}

	@Override
	public ILiteral literalMatch(String string) {
		for(IValueType t : IDataType.VALUE_TYPES)
			if(t.matchesLiteral(string))
				return literal(t, string);
		return null;
	}
	
	@Override
	public ILiteral literal(int value) {
		return new Literal(IDataType.INT, Integer.toString(value));
	}

	@Override
	public ILiteral literal(double value) {
		return new Literal(IDataType.DOUBLE, Double.toString(value));
	}

	@Override
	public ILiteral literal(boolean value) {
		return new Literal(IDataType.BOOLEAN, Boolean.toString(value));
	}

	@Override
	public IArrayAllocation arrayAllocation(IDataType type, int nDims, List<IExpression> dimensions) {
		return new ArrayAllocation(type, nDims, dimensions);
	}

	@Override
	public IArrayType arrayType(IDataType componentType, int dimensions) {
		return new ArrayType(componentType, dimensions);
	}	
	
//	@Override
//	public IReferenceType referenceType(IDataType targetType) {
//		return new ReferenceType(targetType);
//	}
	
	@Override
	public ILiteral nullLiteral() {
		return Literal.NULL;
	}
}
