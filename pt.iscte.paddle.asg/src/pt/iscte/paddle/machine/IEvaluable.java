package pt.iscte.paddle.machine;

import java.util.List;

public interface IEvaluable {

	IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError;
}
