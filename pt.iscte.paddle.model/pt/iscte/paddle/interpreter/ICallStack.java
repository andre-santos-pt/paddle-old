package pt.iscte.paddle.interpreter;

import java.util.List;

import pt.iscte.paddle.model.ILiteral;
import pt.iscte.paddle.model.IProcedure;

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

	interface IListener {
		default void stackFrameCreated(IStackFrame stackFrame) { }
		default void stackFrameTerminated(IStackFrame stackFrame, IValue returnValue) { }
	}

	void addListener(IListener listener);
	
}
