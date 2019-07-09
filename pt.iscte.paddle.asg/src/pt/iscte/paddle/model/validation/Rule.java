package pt.iscte.paddle.model.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProgramElement;

public abstract class Rule implements IBlock.IVisitor {

	enum Kind {
		// ?
	}
	
	private final List<ISemanticProblem> problems = new ArrayList<>();
	private IModule module;
	
	protected void setup(IModule module) {
		this.module = module;
		moduleChecks(module);
	}
	
	protected void moduleChecks(IModule module) {
		
	}
	
	public IModule getModule() {
		return module;
	}
	
	protected void addProblem(ISemanticProblem p) {
		problems.add(p);
	}
	
	protected void addProblem(String message, IProgramElement ... elements) {
		problems.add(ISemanticProblem.create(getClass().getSimpleName() + ": " + message, elements));
	}
	
	public List<ISemanticProblem> getProblems() {
		return Collections.unmodifiableList(problems);
	}
}
