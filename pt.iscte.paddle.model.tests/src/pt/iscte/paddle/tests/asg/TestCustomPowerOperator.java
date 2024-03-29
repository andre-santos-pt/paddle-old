package pt.iscte.paddle.tests.asg;

import static pt.iscte.paddle.model.IType.DOUBLE;

import java.math.BigDecimal;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IBinaryExpression;
import pt.iscte.paddle.model.IBinaryOperator;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.tests.BaseTest;

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
		public boolean isValidFor(IExpression left, IExpression right) {
			return left.getType().isNumber() && right.getType().isNumber();
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
			return this.on(leftOperand, rightOperand);
		}

		@Override
		public void setProperty(Object key, Object value) {

		}

		@Override
		public Object getProperty(Object key) {
			return null;
		}
		
		@Override
		public void cloneProperties(IProgramElement e) {
			
			
		}
	};

	IProcedure pow = module.addProcedure(DOUBLE);
	IVariableDeclaration base = pow.addParameter(DOUBLE);
	IVariableDeclaration exp = pow.addParameter(DOUBLE);
	IBlock body = pow.getBody();
	IReturn return1 = body.addReturn(POWER.on(base, exp));

	@Case({"2.5", "4.2"})
	public void test(IExecutionData data) {
		equal(46.9189232, data.getReturnValue());
	}

}
