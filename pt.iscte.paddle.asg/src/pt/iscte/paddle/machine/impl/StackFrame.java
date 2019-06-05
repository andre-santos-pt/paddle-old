package pt.iscte.paddle.machine.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProgramElement;
import pt.iscte.paddle.asg.IRecordType;
import pt.iscte.paddle.asg.IStatement;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.IArray;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IEvaluable;
import pt.iscte.paddle.machine.IExecutable;
import pt.iscte.paddle.machine.IRecord;
import pt.iscte.paddle.machine.IReference;
import pt.iscte.paddle.machine.IStackFrame;
import pt.iscte.paddle.machine.IValue;

class StackFrame implements IStackFrame {

	private final CallStack callStack;
	private final IProcedure procedure;
	private final Map<IVariable, IReference> variables;
	private IValue returnValue;
	private ProcedureExecutor executor;

	private List<IListener> listeners = new ArrayList<>(5);
	public void addListener(IListener listener) {
		listeners.add(listener);
	}

	public StackFrame(CallStack callStack, StackFrame parent, IProcedure procedure, List<IValue> arguments) {
		assert procedure.getParameters().size() == arguments.size();

		this.callStack = callStack;
		this.procedure = procedure;
		this.variables = new LinkedHashMap<>();
		this.returnValue = IValue.NULL;


		int i = 0;
		for(IVariable param : procedure.getParameters()) {
//			IValue arg = param.getType().isReference() ? arguments.get(i) : arguments.get(i).copy();
			IValue copy = arguments.get(i).copy();
			IReference ref = param.getType().isReference() ? (IReference) copy : new Reference(copy);
			variables.put(param, ref);
			i++;
		}

		for (IVariable var : procedure.getLocalVariables()) {
			IValue defValue = Value.create(var.getType(), var.getType().getDefaultValue());
			Reference ref = new Reference(defValue);
			variables.put(var, ref);
		}

		executor = new ProcedureExecutor(this);
	}

	@Override
	public IProcedure getProcedure() {
		return procedure;
	}

	@Override
	public Map<IVariable, IValue> getVariables() {
		return variables.entrySet().stream().collect(Collectors.toMap(
				e -> e.getKey(),
				e -> e.getValue().getTarget()));
	}

	@Override 
	public IReference getVariableStore(IVariable variable) {
		assert variables.containsKey(variable);
		
		// TODO copy?
		return variables.get(variable);
	}

	@Override
	public IValue getReturn() {
		return returnValue;
	}

	public void setReturn(IValue value) {
		this.returnValue = value;
	}

	@Override
	public int getMemory() {
		int bytes = 0;
		for (IVariable var : procedure.getVariables())
			bytes += var.getType().getMemoryBytes();
		return bytes;
	}

	@Override
	public ICallStack getCallStack() {
		return callStack;
	}

	@Override
	public IStackFrame newChildFrame(IProcedure procedure, List<IValue> args) throws ExecutionError {
		return callStack.newFrame(procedure, args);
	}

	@Override
	public void terminateFrame() {
		callStack.terminateTopFrame(returnValue);
		for(IListener l : listeners)
			l.terminated(returnValue);
	}

	@Override
	public IValue getValue(String literal) {
		return callStack.getProgramState().getValue(literal);
	}

	@Override
	public IValue getValue(Object object) {
		return callStack.getProgramState().getValue(object);
	}

	@Override
	public IArray allocateArray(IType baseType, int[] dimensions) {
		return callStack.getProgramState().allocateArray(baseType, dimensions);
	}

	@Override
	public IRecord allocateRecord(IRecordType type) {
		return callStack.getProgramState().allocateObject(type);
	}

	public boolean isOver() {
		return executor.isOver();
	}

	@Override
	public void stepIn() throws ExecutionError {
		executor.stepIn();
	}

	public void stepOver() throws ExecutionError {
		// TODO step over
	}


	public IValue evaluate(IExpression expression, List<IValue> expressions) throws ExecutionError {
		for(IListener l : listeners)
			l.expressionEvaluationStart(expression);

		IValue value = ((IEvaluable) expression).evalutate(expressions, callStack);

		for(IListener l : listeners)
			l.expressionEvaluationEnd(expression, value);

		return value;
	}


	void execute(IStatement statement, List<IValue> values) throws ExecutionError {
		try {
			IExecutable executable = (IExecutable) statement;
			for(IListener l : listeners)
				l.statementExecutionStart(statement);

			executable.execute(this.getCallStack(), values);
			
			for(IListener l : listeners)
				l.statementExecutionEnd(statement);
		}
		catch(ExecutionError e) {
			throw e;
		}
	}


	@Override
	public String toString() {
		String text = procedure.getId() + "(...)"; // TODO pretty print
		for(Entry<IVariable, IReference> e : variables.entrySet())
			text += " " + e.getKey() + "=" + e.getValue();
		return text;
	}

	public IProgramElement nextInstruction() {
		assert !isOver();
		return executor.nextInstruction();
	}

}
