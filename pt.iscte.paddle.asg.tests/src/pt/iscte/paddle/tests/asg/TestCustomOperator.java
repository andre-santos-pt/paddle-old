package pt.iscte.paddle.tests.asg;

import static pt.iscte.paddle.asg.IDataType.DOUBLE;
import static pt.iscte.paddle.asg.IDataType.INT;

import java.math.BigDecimal;

import org.junit.Test;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IBinaryOperator;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.IValue;

public class TestCustomOperator {

	IBinaryOperator POWER = new IBinaryOperator() {

		@Override
		public String getSymbol() {
			return "**";
		}

		@Override
		public OperationType getOperationType() {
			return OperationType.ARITHMETIC;
		}

		@Override
		public boolean isValidFor(IDataType left, IDataType right) {
			return left.isNumber() && right.equals(INT);
		}

		@Override
		public IDataType getResultType(IExpression left, IExpression right) {
			return DOUBLE;
		}

		@Override
		public IValue apply(IValue left, IValue right) throws ExecutionError {
			BigDecimal pow = ((BigDecimal) left.getValue()).pow(((BigDecimal) right.getValue()).intValue());
			return IValue.create(DOUBLE, pow);
		}

		@Override
		public IBinaryExpression on(IExpression leftOperand, IExpression rightOperand) {
			return IBinaryExpression.create(this, leftOperand, rightOperand);
		}
	};
	
	@Test
	public void test() {
		IModule module = IModule.create("test");
		IProcedure pow = module.addProcedure("pow", DOUBLE);
		IVariable base = pow.addParameter("base", DOUBLE);
		IVariable exp = pow.addParameter("exp", INT);
		pow.getBody().addReturn(POWER.on(base, exp));
		
		System.out.println(module);
	}
}
