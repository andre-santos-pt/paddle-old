package pt.iscte.paddle.asg.semantics;

import java.util.Collection;

import com.google.common.collect.ImmutableList;


public class AsgSemanticChecks implements ISemanticChecker {

	@Override
	public Collection<Class<? extends Rule>> getRules() {
		return ImmutableList.of(
				Args.class,
				BreakContinueLocation.class,
				Types.class
				);
	}

}
