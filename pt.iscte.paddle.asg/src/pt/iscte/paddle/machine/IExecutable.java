package pt.iscte.paddle.machine;

import java.util.List;

public interface IExecutable {

	void execute(ICallStack stack, List<IValue> expressions) throws ExecutionError;
}
