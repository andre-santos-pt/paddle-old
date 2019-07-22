package pt.iscte.paddle.interpreter;

import java.util.List;

public interface IExecutable {

	void execute(ICallStack stack, List<IValue> expressions) throws ExecutionError;
	
	default String getExplanation(ICallStack stack, List<IValue> expressions) { 
		return "?no explanation?";
	}
}
