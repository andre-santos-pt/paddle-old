package pt.iscte.paddle.asg.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Iterables;

import pt.iscte.paddle.asg.IConstant;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.ILiteral;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProcedureDeclaration;
import pt.iscte.paddle.asg.IStructType;

public class Module extends ProgramElement implements IModule {
	private final String id;
	private final List<IConstant> constants;
	private final List<IStructType> structs;
	private final List<IProcedure> procedures;

	private final List<IProcedure> builtinProcedures;

	public Module(String id) {
		this.id = id;
		constants = new ArrayList<>();
		structs = new ArrayList<>();
		procedures = new ArrayList<>();
		builtinProcedures = new ArrayList<>();
	}

	public void loadBuildInProcedures(Class<?> staticClass) {
		for (Method method : staticClass.getDeclaredMethods()) {
			if(BuiltinProcedure.isValidForBuiltin(method))
				builtinProcedures.add(new BuiltinProcedure(method));
			else
				System.err.println("not valid for built-in procedure: " + method);
		}
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public Collection<IConstant> getConstants() {
		return Collections.unmodifiableList(constants);
	}

	@Override
	public Collection<IStructType> getStructs() {
		return Collections.unmodifiableList(structs);
	}

	@Override
	public Collection<IProcedure> getProcedures() {
		return Collections.unmodifiableList(procedures);
	}

	@Override
	public IConstant addConstant(String id, IDataType type, ILiteral value) {
		assert id != null;
		assert type != null;
		assert value != null;
		ConstantDeclaration dec = new ConstantDeclaration(this, id, type, value);
		constants.add(dec);
		return dec;
	}

	@Override
	public IStructType addStruct(String id) {
		IStructType struct = new StructType(id);
		structs.add(struct);
		return struct;
	}

	@Override
	public IProcedure addProcedure(String id, IDataType returnType) {
		IProcedure proc = new Procedure(id, returnType);
		procedures.add(proc);
		return proc;
	}

	@Override
	public IProcedure resolveProcedure(IProcedureDeclaration procedureDeclaration) {
		for(IProcedure p : Iterables.concat(procedures, builtinProcedures))
			if(p.hasSameSignature(procedureDeclaration))
				return p;

		return null;
	}

	@Override
	public IProcedure resolveProcedure(String id, IDataType... paramTypes) {
		for(IProcedure p : Iterables.concat(procedures, builtinProcedures))
			if(p.matchesSignature(id, paramTypes))
				return p;

		return null;
	}

	@Override // TODO pretty print
	public String toString() {
		String text = "";
		for(IConstant c : constants)
			text += c + ";\n";

		for(IStructType s : structs)
			text += s + "\n";

		for (IProcedure p : builtinProcedures) {
			System.out.println(p.longSignature() + "\t(built-in)\n");
		}
		
		for (IProcedure p : procedures)
			text += p + "\n\n";
		
		text = text.replaceAll(";", ";\n"); // wrong replacement of tabs
		text = text.replaceAll("\\{", "{\n");
		text = text.replaceAll("\\}", "}\n");
		return text;
	}
}
