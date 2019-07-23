package pt.iscte.paddle.semantics.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.iscte.paddle.model.IConstant;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IVariable;
import pt.iscte.paddle.model.validation.ISemanticProblem;
import pt.iscte.paddle.model.validation.Rule;

public class Identifiers extends Rule {

	
	Map<String, IConstant> constantMap = new HashMap<String, IConstant>();
	List<IRecordType> records = new ArrayList<IRecordType>(); // TODO name
	
	@Override
	protected void moduleChecks(IModule module) {
		module.getConstants().forEach(constant -> {
			if(constantMap.containsKey(constant.getId()))
				addProblem(ISemanticProblem.create("duplicate constant name", constant, constantMap.get(constant.getId())));
			else
				constantMap.put(constant.getId(), constant);
		});
		
		module.getProcedures().forEach(p -> visit(p));
	}
	
//	@Override
//	public boolean visit(IRecordType struct) {
//		return super.visit(struct);
//	}
	
//	@Override
	public void visit(IProcedure procedure) {
		getModule().getProcedures().forEach(p -> { 
			if(p != procedure && p.hasSameSignature(procedure))
				addProblem(ISemanticProblem.create("duplicate procedure signature", p, procedure));
		});
		
		Map<String, IVariable> vars = new HashMap<String, IVariable>();
		procedure.getVariables().forEach(v -> {
			if(vars.containsKey(v.getId()))
				addProblem(ISemanticProblem.create("duplicate local variable name", vars.get(v.getId()), v));
			else
				vars.put(v.getId(), v);
		});
		
//		return super.visit(procedure);
	}
}
