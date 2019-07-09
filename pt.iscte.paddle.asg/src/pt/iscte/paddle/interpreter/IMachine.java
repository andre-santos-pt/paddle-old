package pt.iscte.paddle.interpreter;

import pt.iscte.paddle.interpreter.impl.ProgramState;
import pt.iscte.paddle.model.IModule;

public interface IMachine {
	static IProgramState create(IModule program) {
		return new ProgramState(program);
	}
}
