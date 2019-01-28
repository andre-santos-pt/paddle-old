package pt.iscte.paddle.machine;

import java.util.List;

import pt.iscte.paddle.asg.ILiteral;
import pt.iscte.paddle.asg.IProcedure;

public interface ICallStack {
	int getSize();
	
	IProgramState getProgramState();
	
	List<IStackFrame> getFrames();
	
	IStackFrame getTopFrame();
	
	IStackFrame newFrame(IProcedure procedure, List<IValue> args) throws ExecutionError;
	
	void terminateTopFrame(IValue returnValue);
	
	IStackFrame getLastTerminatedFrame();
	
	boolean isEmpty();

	
	default int getMemory() {
		int bytes = 0;
		for (IStackFrame f : getFrames()) {
			bytes += f.getMemory();
		}
		return bytes;
	}
	
	default IValue evaluate(ILiteral literal) {
		return getProgramState().getValue(literal.getStringValue());
	}
//	default IValue evaluate(IExpression expression) throws ExecutionError {
//		if(expression instanceof ILiteral)
//			return getProgramState().getValue(((ILiteral) expression).getStringValue());
//		else
//			return getTopFrame().evaluate(expression);
//	}
	
	interface IListener {
		default void stackFrameCreated(IStackFrame stackFrame) { }
		default void stackFrameTerminated(IStackFrame stackFrame, IValue returnValue) { }
	}

	void addListener(IListener listener);

	
	
	
}
