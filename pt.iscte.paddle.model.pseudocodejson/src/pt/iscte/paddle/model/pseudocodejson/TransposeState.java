package pt.iscte.paddle.model.pseudocodejson;

import java.util.HashMap;

import pt.iscte.paddle.model.IConstantDeclaration;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IVariableDeclaration;

public class TransposeState {

	HashMap<String, IConstantDeclaration> constants = new HashMap<>();
	HashMap<String, IProcedure> procedures = new HashMap<>();
	HashMap<String, IVariableDeclaration> variables = new HashMap<>();

	final IModule module;
	
	public TransposeState(IModule module) {
		this.module = module;
	}

	
	public void addConstant(String uuid, IConstantDeclaration constant) {
		constants.put(uuid, constant);
	}

	public void addProcedure(String uuid, IProcedure procedure) {
		procedures.put(uuid, procedure);
	}

	public void addVariable(String uuid, IVariableDeclaration variable) {
		variables.put(uuid, variable);
	}

	public IConstantDeclaration findConstant(String uuid) throws TransposeException {
		if (constants.containsKey(uuid)) {
			return constants.get(uuid);
		}
		throw new TransposeException("Unexisting uuid referenced: " + uuid);
	}

	// checks on module for builtins, before uuid lookup
	public IProcedure findProcedure(String uuid) throws TransposeException {
		if(uuid.startsWith("builtin:")) {
			String id = uuid.split(":")[1];
			IProcedure procedure = module.getProcedure(id);
			if(procedure == null)
				throw new TransposeException("Builtin not found: " + id);
			return procedure;
		}
		else if (procedures.containsKey(uuid)) {
			return procedures.get(uuid);
		}
		throw new TransposeException("Unexisting uuid referenced: " + uuid);
	}

	public IVariableDeclaration findVariable(String uuid) throws TransposeException {
		if (variables.containsKey(uuid)) {
			return variables.get(uuid);
		}
		throw new TransposeException("Unexisting uuid referenced: " + uuid);
	}
}
