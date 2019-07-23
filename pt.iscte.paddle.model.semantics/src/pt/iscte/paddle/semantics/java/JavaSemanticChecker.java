package pt.iscte.paddle.semantics.java;

import java.util.Collection;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.model.validation.ISemanticChecker;
import pt.iscte.paddle.model.validation.Rule;

public class JavaSemanticChecker implements ISemanticChecker {

	@Override
	public Collection<Class<? extends Rule>> getRules() {
		return ImmutableList.of(
				Identifiers.class
				);
	}
}
