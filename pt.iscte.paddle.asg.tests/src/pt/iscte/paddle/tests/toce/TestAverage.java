package pt.iscte.paddle.tests.toce;
import static pt.iscte.paddle.asg.IOperator.DIV;
import static pt.iscte.paddle.asg.IType.DOUBLE;

import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestAverage extends BaseTest {
	private static IProcedure sum = importProcedure(TestSum.class, "summation");
	
	IProcedure average = getModule().addProcedure(DOUBLE);
	IVariable array = average.addParameter(DOUBLE.array());
	IReturn ret_ = average.getBody().addReturn(DIV.on(sum.call(array), array.length()));
}
