package pt.iscte.paddle.tests.toce;
import static pt.iscte.paddle.asg.IOperator.*;

import static pt.iscte.paddle.asg.IType.*;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestAverage extends BaseTest {
	private static IProcedure sum = new TestSum().summation;
	static {
		sum.setId("summation");
	}
	
	IProcedure average = getModule().addProcedure(DOUBLE);
	IVariable array = average.addParameter(DOUBLE.array());
	IReturn ret_ = average.getBody().addReturn(DIV.on(sum.call(array), array.length()));
}
