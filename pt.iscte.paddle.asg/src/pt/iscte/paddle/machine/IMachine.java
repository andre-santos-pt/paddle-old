package pt.iscte.paddle.machine;

import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.machine.impl.ProgramState;

public interface IMachine {
	static IProgramState create(IModule program) {
		return new ProgramState(program);
	}
}
