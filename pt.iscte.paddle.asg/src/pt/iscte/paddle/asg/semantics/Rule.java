package pt.iscte.paddle.asg.semantics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pt.iscte.paddle.asg.IModule;

public abstract class Rule implements IModule.IVisitor {

//	boolean applicableTo(IProgramElement element);
	private final List<ISemanticProblem> problems = new ArrayList<>();
	private IModule module;
	
//	public Rule(IModule module) {
//		this.module = module;
//	}
	
	protected void setup(IModule module) {
		this.module = module;
	}
	
	public IModule getModule() {
		return module;
	}
	
	protected void addProblem(ISemanticProblem p) {
		problems.add(p);
	}
	
	public List<ISemanticProblem> getProblems() {
		return Collections.unmodifiableList(problems);
	}
}
