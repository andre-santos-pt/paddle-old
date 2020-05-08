package pt.iscte.paddle.model.validation;

import java.util.Collection;
import java.util.List;


public class AsgSemanticChecks implements ISemanticChecker {

	@Override
	public Collection<Class<? extends Rule>> getRules() {
		return List.of(
				Args.class,
				BreakContinueLocation.class,
				Types.class
				);
	}

}
