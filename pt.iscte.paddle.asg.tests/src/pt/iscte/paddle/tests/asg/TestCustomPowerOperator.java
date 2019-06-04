package pt.iscte.paddle.tests.asg;

import static pt.iscte.paddle.asg.IType.DOUBLE;

import java.math.BigDecimal;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IBinaryOperator;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.machine.IValue;

public class TestCustomPowerOperator extends BaseTest {

	IBinaryOperator POWER = new IBinaryOperator() {

		@Override
		public String getId() {
			return "**";
		}

		@Override
		public OperationType getOperationType() {
			return OperationType.ARITHMETIC;
		}

		@Override
		public boolean isValidFor(IType left, IType right) {
			return left.isNumber() && right.isNumber();
		}

		@Override
		public IType getResultType(IExpression left, IExpression right) {
			return DOUBLE;
		}

		@Override
		public IValue apply(IValue left, IValue right) throws ExecutionError {
			double p = Math.pow(((BigDecimal) left.getValue()).doubleValue(), ((BigDecimal) right.getValue()).doubleValue());
			return IValue.create(DOUBLE, new BigDecimal(p));
		}

		@Override
		public IBinaryExpression on(IExpression leftOperand, IExpression rightOperand) {
			return IBinaryExpression.create(this, leftOperand, rightOperand);
		}

		@Override
		public void setProperty(Object key, Object value) {

		}

		@Override
		public Object getProperty(Object key) {
			return null;
		}
	};

	IProcedure pow = module.addProcedure(DOUBLE);
	IVariable base = pow.addParameter(DOUBLE);
	IVariable exp = pow.addParameter(DOUBLE);
	IBlock body = pow.getBody();
	IReturn return1 = body.addReturn(POWER.on(base, exp));

	@Case({"2.5", "4.2"})
	public void test(IExecutionData data) {
		equal(46.9189232, data.getReturnValue());
	}

}
