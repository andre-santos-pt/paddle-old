package pt.iscte.paddle.roles;

public interface IStepper extends IVariableRole {
	Direction getDirection();

	default String getName() {
		return "Stepper";
	}

	enum Direction {
		INC, DEC;
	}
}
