package pt.iscte.paddle.machine.impl;

import java.util.ArrayList;
import java.util.List;

import pt.iscte.paddle.asg.IBinaryExpression;
import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.ILiteral;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProcedureCall;
import pt.iscte.paddle.asg.IProgramElement;
import pt.iscte.paddle.asg.IStatement;
import pt.iscte.paddle.asg.IRecordType;
import pt.iscte.paddle.asg.IValueType;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.asg.semantics.AsgSemanticChecks;
import pt.iscte.paddle.asg.semantics.ISemanticProblem;
import pt.iscte.paddle.asg.semantics.SemanticChecker;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.machine.IExpressionEvaluator;
import pt.iscte.paddle.machine.IHeapMemory;
import pt.iscte.paddle.machine.IProgramState;
import pt.iscte.paddle.machine.IStackFrame;
import pt.iscte.paddle.machine.IRecord;
import pt.iscte.paddle.machine.IValue;

public class ProgramState implements IProgramState {

	private final IModule program;
	private final CallStack stack;
	private final IHeapMemory heap;
	private final int callStackMax;
	private final int loopIterationMax;
	private final int availableMemory;

	private IProgramElement instructionPointer;

	private ExecutionData data;

	public ProgramState(IModule program) {
		this(program, 1024, 100, 1024); // TODO Constants?
	}

	public ProgramState(IModule program, int callStackMax, int loopIterationMax, int availableMemory) {
		assert program != null;
		assert callStackMax >= 1;
		this.program = program;
		this.callStackMax = callStackMax;
		this.loopIterationMax = loopIterationMax;
		this.availableMemory = availableMemory;
		stack = new CallStack(this);
		heap = new HeapMemory(this); 
		addStateListener();
	}

	public IModule getProgram() {
		return program;
	}

	@Override
	public ICallStack getCallStack() {
		return stack;
	}

	@Override
	public int getCallStackMaximum() {
		return callStackMax;
	}

	@Override
	public int getLoopIterationMaximum() {
		return loopIterationMax;
	}

	@Override
	public IProgramElement getInstructionPointer() {
		return instructionPointer;
	}

	@Override
	public IHeapMemory getHeapMemory() {
		return heap;
	}

	@Override
	public int getAvailableMemory() {
		return availableMemory;
	}

	@Override
	public int getUsedMemory() {
		return stack.getMemory() + heap.getMemory();
	}

	@Override
	public IValue getValue(String value) {
		if(value.equals("null"))
			return IValue.NULL;

		for(IValueType type : IType.VALUE_TYPES) {
			if(type.matchesLiteral(value))
				return Value.create(type, type.create(value));
		}
		return null;
	}

	@Override
	public IValue getValue(Object object) {
		if(object == null)
			return IValue.NULL;

		for(IValueType type : IType.VALUE_TYPES) {
			if(type.matches(object))
				return Value.create(type, type.create(object.toString()));
		}
		assert false;
		return null;
	}


	@Override
	public IArray allocateArray(IType baseType, int ... dimensions) {
		return heap.allocateArray(baseType, dimensions);
	}

	@Override
	public IRecord allocateObject(IRecordType type) {
		return heap.allocateRecord(type);
	}

	

	public void setupExecution(IProcedure procedure, Object... args) throws ExecutionError {
		if(args.length != procedure.getParameters().size())
			throw new RuntimeException("incorrect number of arguments for " + procedure);

		data = new ExecutionData();

		List<IExpression> procArgs = new ArrayList<>(args.length);
		for(Object a : args)
			procArgs.add(ILiteral.matchValue(a.toString()));

		IProcedureCall call = procedure.call(procArgs);
		instructionPointer = call;

		List<IValue> argsValues = new ArrayList<>();
		for(int i = 0; i < procedure.getParameters().size(); i++) {
			IValue arg = stack.evaluate((ILiteral) procArgs.get(i));
			argsValues.add(arg);
		}
		IStackFrame newFrame = stack.newFrame(procedure, argsValues);
	}

	public boolean isOver() {
		return stack.isEmpty();
	}

	public void stepIn() throws ExecutionError {
		assert !isOver();
		stack.stepIn();
	}

	public void stepOver() throws ExecutionError {
		assert !isOver();
		// TODO
	}

	


	public IExecutionData execute(IProcedure procedure, Object ... args) {
		SemanticChecker checker = new SemanticChecker(new AsgSemanticChecks());
		List<ISemanticProblem> problems = checker.check(program);
		problems.forEach(p -> System.err.println(p));
		if(!problems.isEmpty())
			return new ExecutionData();

		try {
			setupExecution(procedure, args);
			while(!isOver()) {
				stepIn();
				while(!stack.isEmpty() && stack.getTopFrame().isOver())
					stack.getTopFrame().terminateFrame();
			}
		}
		catch (ExecutionError e) {
			System.err.println("Execution error: " + e);
		}
		return data;
	}


	private void addStateListener() {
		stack.addListener(new ICallStack.IListener() {
			@Override
			public void stackFrameCreated(IStackFrame stackFrame) {
				data.updateCallStackSize(stack);
				stackFrame.addListener(new IStackFrame.IListener() {
					@Override
					public void statementExecutionStart(IStatement statement) {
						instructionPointer = statement;
					}

					public void statementExecutionEnd(IStatement statement) {
						if(statement instanceof IVariableAssignment)
							data.countAssignment(stackFrame.getProcedure());
					}

					@Override
					public void expressionEvaluationStart(IExpression expression) {
						if(expression instanceof IBinaryExpression) {
							instructionPointer = expression;
						}
					}

					@Override
					public void expressionEvaluationEnd(IExpression expression, IValue result) {
						data.countOperation(expression.getOperationType());
					}
				});
			}

			@Override
			public void stackFrameTerminated(IStackFrame stackFrame, IValue returnValue) {
				data.setVariableState(stackFrame.getVariables());
				data.setReturnValue(returnValue);
				data.countCall();
			}
		});
	}

	void countAssignment(IProcedure p) {
		data.countAssignment(p);
	}

	@Override
	public IExpressionEvaluator createExpressionEvaluator(IExpression e) {
		return new ExpressionEvaluator(e, stack);
	}

}
