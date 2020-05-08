package pt.iscte.paddle.asg.future;

import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IProcedure;

public interface IProcedureReference extends IValue {
	IProcedure getTarget();
}
