package pt.iscte.paddle.asg.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Iterables;

import pt.iscte.paddle.IModel2CodeTranslator;
import pt.iscte.paddle.asg.IBlockElement;
import pt.iscte.paddle.asg.IConstant;
import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.asg.ILiteral;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProcedureDeclaration;
import pt.iscte.paddle.asg.IRecordType;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.semantics.AsgSemanticChecks;
import pt.iscte.paddle.asg.semantics.ISemanticProblem;
import pt.iscte.paddle.asg.semantics.SemanticChecker;

public class Module extends ProgramElement implements IModule {
	private final List<IConstant> constants;
	private final List<IRecordType> records;
	private final List<IProcedure> procedures;

	private final List<IProcedure> builtinProcedures;

	public Module() {
		constants = new ArrayList<>();
		records = new ArrayList<>();
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
	public Collection<IConstant> getConstants() {
		return Collections.unmodifiableList(constants);
	}

	@Override
	public Collection<IRecordType> getRecordTypes() {
		return Collections.unmodifiableList(records);
	}

	@Override
	public Collection<IProcedure> getProcedures() {
		return Collections.unmodifiableList(procedures);
	}

	@Override
	public IConstant addConstant(IType type, ILiteral value) {
		assert type != null;
		assert value != null;
		ConstantDeclaration dec = new ConstantDeclaration(this, type, value);
		constants.add(dec);
		return dec;
	}

	@Override
	public IRecordType addRecordType() {
		IRecordType struct = new RecordType();
		records.add(struct);
		return struct;
	}

	@Override
	public IProcedure addProcedure(IType returnType) {
		IProcedure proc = new Procedure(returnType);
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
	public IProcedure resolveProcedure(String id, IType... paramTypes) {
		for(IProcedure p : Iterables.concat(procedures, builtinProcedures))
			if(p.matchesSignature(id, paramTypes))
				return p;

		return null;
	}

	@Override
	public String toString() {
		String text = "";
		for(IConstant c : constants)
			text += c.getType() + " " + c.getId() + " = " + c.getValue() + ";\n";

		if(!constants.isEmpty())
			text += "\n";
		
		for(IRecordType r : records) {
			text += "typedef struct\n{\n";
			for (IVariable member : r.getFields()) {
				text += "\t" + member.getDeclaration() + ";\n";
			}
			text += "} " + r.getId() + ";\n\n";
		}
		
		for (IProcedure p : builtinProcedures)
			System.out.println(p.longSignature() + "\t(built-in)\n");
		
		text += "\n";
		
		for (IProcedure p : procedures)
			text += p + "\n\n";
		
		return text;
	}
	
	@Override
	public List<ISemanticProblem> checkSemantics() {
		SemanticChecker checker = new SemanticChecker(new AsgSemanticChecks());
		return checker.check(this);
	}
	
	@Override
	public String translate(IModel2CodeTranslator t) {
		String text = t.header(this);
		for(IConstant c : constants)
			text += t.declaration(c);

		for(IRecordType r : records)
			text += t.declaration(r);
		
		for (IProcedure p : builtinProcedures)
			System.out.println(p.longSignature() + "\t(built-in)\n");
		
//		text += "\n";
		
		for (IProcedure p : procedures)
			text += p.translate(t);
		
		return text + t.close(this);
	}
	
	public void addProcedure(IProcedure p) {
		procedures.add(p);
	}
}
