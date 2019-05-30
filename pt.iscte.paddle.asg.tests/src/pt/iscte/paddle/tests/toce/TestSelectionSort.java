package pt.iscte.paddle.tests.toce;
import static pt.iscte.paddle.asg.IOperator.*;
import static pt.iscte.paddle.asg.IType.*;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProcedureCall;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.ISelection;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.tests.asg.BaseTest;

public class TestSelectionSort extends BaseTest {

	void sort(int arr[]) 
	{ 
		// One by one move boundary of unsorted subarray 
		int i = 0;
		while ( i < arr.length-1) 
		{ 
			// Find the minimum element in unsorted array 
			int min = i; 
			int j = i + 1;
			while (j < arr.length) {
				if (arr[j] < arr[min]) 
					min = j; 
				j++;
			}

			// Swap the found minimum element with the first 
			// element 
			int temp = arr[min]; 
			arr[min] = arr[i]; 
			arr[i] = temp; 
			i++;
		} 
	} 

	private static IProcedure swap = new TestSwap().swap;
	static {
		swap.setId("swap");
	}
	
	IProcedure sort = module.addProcedure(VOID);
	IVariable array = sort.addParameter(INT.array().reference());
	IBlock body = sort.getBody();
	IVariable i = body.addVariable(INT, INT.literal(0));

	ILoop outloop = body.addLoop(SMALLER.on(i, SUB.on(array.length(), INT.literal(1))));
	IVariable min = outloop.addVariable(INT, i);
	IVariable j = outloop.addVariable(INT, ADD.on(i, INT.literal(1)));

	ILoop inloop = outloop.addLoop(SMALLER.on(j, array.length()));
	ISelection ifstat = inloop.addSelection(SMALLER.on(array.element(j), array.element(min)));
	IVariableAssignment minAss = ifstat.getBlock().addAssignment(min, j);
	IVariableAssignment jInc = inloop.addIncrement(j);
	IProcedureCall swapCall = outloop.addCall(swap, array, i, min);
	IVariableAssignment iInc = outloop.addIncrement(i);
}
