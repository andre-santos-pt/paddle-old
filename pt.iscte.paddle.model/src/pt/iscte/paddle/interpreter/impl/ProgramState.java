package pt.iscte.paddle.interpreter.impl;

import java.util.ArrayList;
import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IArray;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IExecutable;
import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.interpreter.IExpressionEvaluator;
import pt.iscte.paddle.interpreter.IHeapMemory;
import pt.iscte.paddle.interpreter.IProgramState;
import pt.iscte.paddle.interpreter.IRecord;
import pt.iscte.paddle.interpreter.IStackFrame;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IBinaryExpression;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ILiteral;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProcedureCall;
import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IStatement;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IValueType;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.validation.AsgSemanticChecks;
import pt.iscte.paddle.model.validation.ISemanticProblem;
import pt.iscte.paddle.model.validation.SemanticChecker;

public class ProgramState implements IProgramState {

	private final IModule program;
	private final CallStack stack;
	private final IHeapMemory heap;
	private final int callStackMax;
	private final int loopIterationMax;
	private final int availableMemory;

	private ExecutionData data;

	private List<IListener> listeners;
	
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
		heap = new Memory(); 
		
		listeners = new ArrayList<>();
		addStateListener();
	}

	public IModule getProgram() {
		return program;
	}
	
	@Override
	public String toString() {
		return data.toString();
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
		return stack.nextInstruction();
	}

	@Override
	public IExecutionData getExecutionData() {
		return data;
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


	public boolean isOver() {
		return stack.isEmpty();
	}

	public void stepIn() throws ExecutionError {
		assert !isOver();
		stack.stepIn();
		while(!stack.isEmpty() && stack.getTopFrame().isOver())
			stack.getTopFrame().terminateFrame();
		
		// TODO stop on frame termination?
	}

	public void stepOver() throws ExecutionError {
		assert !isOver();
		// TODO step over
	}

	public void setupExecution(IProcedure procedure, Object... args) throws ExecutionError {
		if(args.length != procedure.getParameters().size())
			throw new RuntimeException("incorrect number of arguments for " + procedure);

		data = new ExecutionData();

		List<IValue> argsValues = new ArrayList<>();
		for(int i = 0; i < procedure.getParameters().size(); i++) {
			if(args[i] instanceof IValue) {
				argsValues.add((IValue) args[i]);
			}
			else {
				ILiteral lit = ILiteral.matchValue(args[i].toString());
				IValue arg = stack.evaluate(lit);
				argsValues.add(arg);
			}
		}
		stack.newFrame(procedure, argsValues);
		listeners.forEach(l -> l.programStarted());
	}

	public IExecutionData execute(IProcedure procedure, Object ... args) throws ExecutionError {
		SemanticChecker checker = new SemanticChecker(new AsgSemanticChecks());
		List<ISemanticProblem> problems = checker.check(program, procedure);
		problems.forEach(p -> System.err.println(p));
		if(!problems.isEmpty())
			return new ExecutionData();

		setupExecution(procedure, args);
		while(!isOver())
			stepIn();
		return data;
	}


	private void addStateListener() {
		stack.addListener(new ICallStack.IListener() {
			public void stackFrameCreated(IStackFrame stackFrame) {
				stackFrame.addListener(new IStackFrame.IListener() {
					@Override
					public void statementExecutionEnd(IStatement statement) {
						listeners.forEach(l -> l.step(statement));
					}
				});
			}

			@Override
			public void stackFrameTerminated(IStackFrame stackFrame, IValue returnValue) {
				data.setVariableState(stackFrame.getVariables());
				data.setReturnValue(returnValue);
				data.countCall();
				if(isOver())
					listeners.forEach(l -> l.programEnded());
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
	


	public void addListener(IListener listener) {
		listeners.add(listener);
	}

	@Override
	public String nextStepExplanation() {
//		IProgramElement ip = getInstructionPointer();
//		stack.getTopFrame().get
//		if(ip instanceof IExecutable)
//		return ((IExecutable) ip).getExplanation(stack, expressions);
		return "?";
	}
}
