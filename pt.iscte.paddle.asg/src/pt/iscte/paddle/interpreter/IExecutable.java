package pt.iscte.paddle.interpreter;

import java.util.List;

public interface IExecutable {

	void execute(ICallStack stack, List<IValue> expressions) throws ExecutionError;
}
