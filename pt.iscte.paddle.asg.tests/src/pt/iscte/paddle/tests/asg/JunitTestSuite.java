package pt.iscte.paddle.tests.asg;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import pt.iscte.paddle.tests.toce.TestStack;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	TestArithmeticExpressions.class,
//	TestRandom.class,
	TestSelection.class,
	TestSum.class,
	TestSwap.class,
	TestArrays.class,
	Test2DArrays.class,
	TestConstants.class,
	TestRecord.class,
	TestRecursion.class,
//	TestBuiltinProcedures.class,
	
	TestStack.class
})

public class JunitTestSuite {   
}  