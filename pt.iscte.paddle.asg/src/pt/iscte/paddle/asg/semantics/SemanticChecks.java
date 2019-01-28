package pt.iscte.paddle.asg.semantics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.iscte.paddle.asg.IBlock.IVisitor;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.IVariable;

class SemanticChecks {
	static void checkVariableNames(IProcedure procedure, List<ISemanticProblem> problems) {
		Map<String, IVariable> ids = new HashMap<>();
		for(IVariable v : procedure.getVariables())
			if(ids.containsKey(v.getId()))
				problems.add(ISemanticProblem.create("duplicate variable names", v, ids.get(v.getId())));
			else
				ids.put(v.getId(), v);
		
	}
	
	static void checkReturn(IProcedure procedure, List<ISemanticProblem> problems) {
		IDataType returnType = procedure.getReturnType();
		procedure.getBody().accept(new IVisitor() {
			public boolean visit(IReturn returnStatement) {
				IDataType t = returnStatement.getReturnValueType();
				if(!t.equals(returnType))
					problems.add(ISemanticProblem.create("return not compatible with procedure result: " + t + " " + returnType, returnStatement, procedure));
				return true;
			}
		});
	}
}