package pt.iscte.paddle.tests.toce;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	TestCheckEven.class,
	TestMin.class,
	TestCircle.class,
	TestMax.class,
	TestFactorial.class,
	TestSumEven.class,
	TestSum.class, TestSumCopy.class,
	TestAverage.class,
	TestMaxArray.class,
	TestNaturals.class,
	TestArrayFind.class,
	TestBinarySearch.class,
	TestSwap.class,
	TestInvert.class,
	TestSelectionSort.class,
	
	TestMatrixScalar.class,
	TestMatrixTranspose.class,
	
	TestStack.class,
	TestList.class,
	
	TestArrayCount.class,
	
	IdMatrix.class
//	TestMaxBound.class,
})

public class JunitTOCESuite {   
}  