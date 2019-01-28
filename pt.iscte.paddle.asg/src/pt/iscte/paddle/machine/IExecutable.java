package pt.iscte.paddle.machine;

import java.util.List;

public interface IExecutable {

//	default IStatement getStatement() {
//		return (IStatement) this;
//	}
	
	// TODO to void?
	boolean execute(ICallStack stack, List<IValue> expressions) throws ExecutionError;
}
