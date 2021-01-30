package pt.iscte.paddle.model.tests;
import static org.junit.Assert.assertEquals;
import static pt.iscte.paddle.model.IOperator.IDIV;
import static pt.iscte.paddle.model.IOperator.SMALLER;
import static pt.iscte.paddle.model.IOperator.SUB;
import static pt.iscte.paddle.model.IType.INT;
import static pt.iscte.paddle.model.IType.VOID;

import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.ILiteral;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProcedureCall;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.cfg.IBranchNode;
import pt.iscte.paddle.model.cfg.IControlFlowGraph;
import pt.iscte.paddle.model.cfg.IStatementNode;

public class TestInvert extends BaseTest {
	private IProcedure swap = importProcedure(TestSwap.class, "swap");
	
	IProcedure invert = module.addProcedure(VOID);
	IVariableDeclaration array = invert.addParameter(INT.array().reference());
	
	IBlock body = invert.getBody();
	IVariableDeclaration i = body.addVariable(INT);
	IVariableAssignment iAss = body.addAssignment(i, INT.literal(0));
	ILoop loop = body.addLoop(SMALLER.on(i, IDIV.on(array.length(), INT.literal(2))));
	IProcedureCall swapCall = loop.addCall(swap, array, i, SUB.on(SUB.on(array.length(), INT.literal(1)), i));
	IVariableAssignment iInc = loop.addIncrement(i);
	
	private IVariableDeclaration aEven;
	private IVariableDeclaration aOdd;

	private int[] integers = {-2, 0, 1, 4, 5, 8, 10, 11, 20, 23};
	private ILiteral[] literals = literalIntArray(integers);
	
	@Override
	protected IControlFlowGraph cfg() {
		IControlFlowGraph cfg = IControlFlowGraph.create(invert);
		
		IStatementNode iInit = cfg.newStatement(iAss);
		cfg.getEntryNode().setNext(iInit);
		
		IBranchNode whileLoop = cfg.newBranch(loop.getGuard());
		iInit.setNext(whileLoop);
		
		IStatementNode call = cfg.newStatement(swapCall);
		whileLoop.setBranch(call);
		
		IStatementNode iIncrement = cfg.newStatement(iInc);
		call.setNext(iIncrement);
		iIncrement.setNext(whileLoop);
		
		whileLoop.setNext(cfg.getExitNode());
		
		return cfg;
		
	}
	
	protected IProcedure main() {
		IProcedure test = module.addProcedure(VOID);
		IBlock body = test.getBody();
		
		aEven = body.addVariable(INT.array().reference(), INT.array().heapAllocation(INT.literal(literals.length)));
		for(int i = 0; i < literals.length; i++)
			body.addArrayElementAssignment(aEven, literals[i], INT.literal(i));
		
		aOdd = body.addVariable(INT.array().reference(), INT.array().heapAllocation(INT.literal(literals.length-1)));
		for(int i = 0; i < literals.length-1; i++)
			body.addArrayElementAssignment(aOdd, literals[i], INT.literal(i));

		body.addCall(invert, aEven);
		body.addCall(invert, aOdd);
		return test;
	}
	
	

	@Case
	public void test(IExecutionData data) {
		System.out.println(invert);
		IArray even = (IArray) data.getVariableValue(aEven);
		for(int i = 0; i < integers.length; i++)
			assertEquals(new Integer(integers[i]), new Integer(even.getElement(integers.length-1-i).toString()));
		
		IArray odd = (IArray) data.getVariableValue(aOdd);
		for(int i = 0; i < integers.length - 1; i++)
			assertEquals(new Integer(integers[i]), new Integer(odd.getElement(integers.length-2-i).toString()));
	}
	
}
