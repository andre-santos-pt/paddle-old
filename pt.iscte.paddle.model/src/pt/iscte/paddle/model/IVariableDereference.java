package pt.iscte.paddle.model;

public interface IVariableDereference extends IVariable {

	IVariable getVariable();
	
	default int procedureIndex() {
		return getOwnerProcedure().getVariables().indexOf(getVariable());
	}
}
