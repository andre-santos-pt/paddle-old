package pt.iscte.paddle.tests.toce;

import static pt.iscte.paddle.asg.IOperator.*;
import static pt.iscte.paddle.asg.IType.*;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.ISelection;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestBinarySearch extends BaseTest {
	static int search(int v[], int n, int l, int r) {
		if (r >= l) {
			int m = l + (r - l) / 2;
			if (v[m] > n)
				return search(v, n, l, m - 1);
			if (v[m] < n)
				return search(v, n, m + 1, r);
			return m;
		}
		return -1;
	}

	boolean binarySearch(int arr[], int e) 
	{ 
		int l = 0;
		int r = arr.length - 1;
		while (l <= r) { 
			int m = l + (r - l) / 2; 
			if (arr[m] == e) 
				return true; 
			if (arr[m] < e) 
				l = m + 1; 
			else
				r = m - 1; 
		} 
		return false; 
	} 	



	IProcedure binarySearch = module.addProcedure(BOOLEAN);
	IVariable array = binarySearch.addParameter(INT.array().reference());
	IVariable e = binarySearch.addParameter(INT);
	IBlock body = binarySearch.getBody();

	IVariable l = body.addVariable(INT, INT.literal(0));
	IVariable r = body.addVariable(INT, SUB.on(array.length(), INT.literal(1)));

	ILoop loop = body.addLoop(SMALLER_EQ.on(l, r));
	IVariable m = loop.addVariable(INT, ADD.on(l, DIV.on(SUB.on(r, l), INT.literal(2)) ));

	ISelection iffound = loop.addSelection(EQUAL.on(array.element(m), e));
	IReturn retTrue = iffound.getBlock().addReturn(BOOLEAN.literal(true));

	ISelection ifnot = loop.addSelectionWithAlternative(SMALLER.on(array.element(m), e));
	IVariableAssignment lAss = ifnot.getBlock().addAssignment(l, ADD.on(m, INT.literal(1)));
	IVariableAssignment rAss = ifnot.getAlternativeBlock().addAssignment(r, SUB.on(m, INT.literal(1)));

	IReturn ret = body.addReturn(BOOLEAN.literal(false));
}
