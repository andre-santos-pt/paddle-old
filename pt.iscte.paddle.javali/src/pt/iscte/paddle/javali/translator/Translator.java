package pt.iscte.paddle.javali.translator;

import static pt.iscte.paddle.model.IOperator.AND;
import static pt.iscte.paddle.model.IOperator.OR;
import static pt.iscte.paddle.model.IOperator.XOR;
import static pt.iscte.paddle.model.IType.BOOLEAN;
import static pt.iscte.paddle.model.IType.DOUBLE;
import static pt.iscte.paddle.model.IType.INT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import pt.iscte.paddle.JavaliStandaloneSetup;
import pt.iscte.paddle.javali.Addition;
import pt.iscte.paddle.javali.And;
import pt.iscte.paddle.javali.Block;
import pt.iscte.paddle.javali.Break;
import pt.iscte.paddle.javali.Constant;
import pt.iscte.paddle.javali.Continue;
import pt.iscte.paddle.javali.DoWhile;
import pt.iscte.paddle.javali.Equality;
import pt.iscte.paddle.javali.Expression;
import pt.iscte.paddle.javali.For;
import pt.iscte.paddle.javali.IfElse;
import pt.iscte.paddle.javali.Literal;
import pt.iscte.paddle.javali.Module;
import pt.iscte.paddle.javali.Multiplication;
import pt.iscte.paddle.javali.NewArray;
import pt.iscte.paddle.javali.NewObject;
import pt.iscte.paddle.javali.Null;
import pt.iscte.paddle.javali.Or;
import pt.iscte.paddle.javali.ProcCall;
import pt.iscte.paddle.javali.Procedure;
import pt.iscte.paddle.javali.Record;
import pt.iscte.paddle.javali.Relation;
import pt.iscte.paddle.javali.Return;
import pt.iscte.paddle.javali.Statement;
import pt.iscte.paddle.javali.Type;
import pt.iscte.paddle.javali.VarAssign;
import pt.iscte.paddle.javali.VarDeclaration;
import pt.iscte.paddle.javali.VarExpression;
import pt.iscte.paddle.javali.While;
import pt.iscte.paddle.javali.Xor;
import pt.iscte.paddle.model.IArrayType;
import pt.iscte.paddle.model.IBinaryOperator;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IConstant;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ILiteral;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IOperator;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariable;

public class Translator {
	private Map<String, IVariable> varTable;
	private Resource resource;

	public Translator(IFile file) {
		this(file.getLocationURI().toString());
	}
	
	public Translator(String locationURI) {
		new JavaliStandaloneSetup().createInjectorAndDoEMFRegistration();
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		ResourceSet rs = new ResourceSetImpl();
		resource = rs.getResource(URI.createURI(locationURI), true);
	}

	public IModule createProgram() {
		Module module = (Module) resource.getContents().get(0);
		ICompositeNode node = NodeModelUtils.getNode(module);

		IModule program = IModule.create();

		handleConstants(module, program);
		handleRecords(module, program);
		handleProcedures(module, program);

		return program;
	}

	private Map<String, IConstant> constantsTable = new HashMap<>();

	private void handleConstants(Module module, IModule program) {
		for(Constant c : module.getConstants()) {
			IType type = matchValueType(c.getType().getId().getId());
			if(type == null)
				throw new RuntimeException("invalid constant type: " + c.getType());
			
			IConstant constant = program.addConstant(type, mapValue(c.getValue()));
			constant.setId(c.getId().getId());
			constantsTable.put(constant.getId(), constant);
		}
	}

	private ILiteral mapValue(Literal value) {
		try {
			int i = Integer.parseInt(value.getValue());
			return IType.INT.literal(i);
		}
		catch (NumberFormatException e) {
		}
		try {
			double d = Double.parseDouble(value.getValue());
			return IType.DOUBLE.literal(d);
		}
		catch (NumberFormatException e) {
		}

		if(value.getValue().matches("true|false"))
			return IType.BOOLEAN.literal(value.getValue());

		throw new RuntimeException("unsupported value: " + value);
	}

	private BiMap<IRecordType, Record> recordTypes = HashBiMap.create();

	private void handleRecords(Module module, IModule program) {
		for (Record r : module.getRecords()) {
			IRecordType recordType = program.addRecordType();
			recordType.setId(r.getId().getId());
			recordTypes.put(recordType, r);
		}			

		for (Entry<IRecordType, Record> e : recordTypes.entrySet()) {
			for (VarDeclaration f : e.getValue().getFields()) {
				IVariable field = e.getKey().addField(toModelType(f.getType()));
				field.setId(f.getId().getId());
			}
		}
	}



	private BiMap<IProcedure, Procedure> procedures = HashBiMap.create();

	private void handleProcedures(Module module, IModule program) {
		for (Procedure p : module.getProcedures()) {
			Type retType = p.getRetType();
			IProcedure proc = program.addProcedure(retType == null ? IType.VOID : toModelType(retType));
			proc.setId(p.getId().getId());
			if(p.getComment() != null)
				proc.setProperty("DOCUMENTATION", p.getComment());

			for (VarDeclaration paramDec : p.getParams()) {
				IVariable param = proc.addParameter(toModelType(paramDec.getType()));
				param.setId(paramDec.getId().getId());
			}

			procedures.put(proc, p);
		}

		for (Entry<IProcedure, Procedure> e : procedures.entrySet()) {
			varTable = new HashMap<>();
			IProcedure proc = e.getKey();
			Procedure p = e.getValue();

			for(IVariable param : proc.getParameters())
				varTable.put(param.getId(), param);

			p.getBody().getStatements().forEach(s -> mapStatement(s, proc.getBody()));

			ICompositeNode pnode = NodeModelUtils.getNode(p);
			ElementLocation loc = new ElementLocation(pnode);
			proc.setProperty(ElementLocation.Part.WHOLE, loc);
			ICompositeNode idNode = NodeModelUtils.getNode(p.getId());
			proc.setProperty(ElementLocation.Part.ID, new ElementLocation(idNode));
		}
	}

	private void mapStatement(Statement s, IBlock block) {
		IProgramElement instr = null;
		if(s instanceof Return) {
			Expression retExp = ((Return) s).getExp();
			if(retExp == null)
				instr = block.addReturn();
			else
				instr = block.addReturn(mapExpression(retExp));
		}
		else if(s instanceof VarDeclaration) {
			VarDeclaration varDec = (VarDeclaration) s;

			String id = varDec.getId().getId();
			IVariable var = block.addVariable(toModelType(varDec.getType()));
			var.setId(id);
			varTable.put(id, var);

			if(varDec.getInit() != null)
				instr = block.addAssignment(var, mapExpression(varDec.getInit()));
		}
		else if(s instanceof VarAssign) {
			VarAssign ass = (VarAssign) s;
			VarExpression varExp = ass.getVar();
			IVariable var = varTable.get(varExp.getParts().get(0).getId());
			EList<Expression> arrayIndexes = ass.getVar().getArrayIndexes();
			IExpression expression = mapExpression(ass.getExp());
			if(arrayIndexes.isEmpty())
				instr = block.addAssignment(var, expression);
			else
				instr = block.addArrayElementAssignment(var, expression, mapExpressions(arrayIndexes));
		}

		else if (s instanceof IfElse) {
			IfElse ifElse = (IfElse) s;
			IExpression guard = mapExpression(ifElse.getGuard());
			Block elseBlock = ifElse.getAlternativeBlock();
			ISelection sel = elseBlock == null ? block.addSelection(guard) : block.addSelectionWithAlternative(guard);
			ifElse.getSelectionBlock().getStatements().forEach(st -> mapStatement(st, sel.getBlock()));
			if(elseBlock != null)
				elseBlock.getStatements().forEach(st -> mapStatement(st, sel.getAlternativeBlock()));
			instr = sel;
		}
		else if(s instanceof While) {
			While whi = (While) s;
			IExpression guard = mapExpression(whi.getGuard());
			ILoop loop = block.addLoop(guard);
			whi.getBlock().getStatements().forEach(st -> mapStatement(st, loop.getBlock()));
			instr = loop;
		}
		else if(s instanceof For) {
			For fo = (For) s;
			IExpression guard = mapExpression(fo.getGuard());
			IBlock fBlock = block.addBlock();
			fo.getInitStatements().forEach(st -> mapStatement(st, fBlock));
			ILoop loop = fBlock.addLoop(guard);
			fo.getBlock().getStatements().forEach(st -> mapStatement(st, loop.getBlock()));
			fo.getProgressStatements().forEach(st -> mapStatement(st, loop.getBlock()));
		}
		else if(s instanceof DoWhile) {
			// TODO do while
		}
		else if(s instanceof Break) {
			instr = block.addBreak();
		}
		else if(s instanceof Continue) {
			instr = block.addContinue();
		}
		else if(s instanceof ProcCall) {
			ProcCall call = (ProcCall) s;
			EList<Expression> args = call.getArgs();
			IProcedure target = matchProcedure(call.getId().getId(), args); 
			List<IExpression> expArgs = new ArrayList<>();
			args.forEach(a -> expArgs.add(mapExpression(a)));
			instr = block.addCall(target, expArgs);
		}
		
		if(instr != null) {
			instr.setProperty(ElementLocation.Part.WHOLE, new ElementLocation(s));
		}
		
		else if(!(s instanceof VarDeclaration)) 
			throw new RuntimeException("unsupported statement: " + s);
	}


	private IProcedure matchProcedure(String id, EList<Expression> args) {
		// TODO match expressions
		for(IProcedure p : procedures.keySet())
			if(p.getId().equals(id))
				return p;

		throw new RuntimeException("cannot find procedure");
	}

	private IExpression[] mapExpressions(List<Expression> expressions) {
		IExpression[] exps = new IExpression[expressions.size()];
		for(int i = 0; i < exps.length; i++)
			exps[i] = mapExpression(expressions.get(i));
		
		return exps;
	}
	
	private IExpression mapExpression(Expression e) {
		// TODO unary
		IExpression exp = null;
		if(e instanceof Literal) {
			exp = ILiteral.matchValue(((Literal) e).getValue());
		}
		else if(e instanceof Null) {
			exp = ILiteral.NULL;
		}
		else if(e instanceof VarExpression) {
			// include constant
			VarExpression var = (VarExpression) e;
			String id = var.getParts().get(0).getId();
			exp = varTable.get(id);

			boolean single = var.getParts().size() == 1;
			//			if(single)

			if(exp == null)
				exp = constantsTable.get(id);
			if(exp == null)
				System.err.println("var not found: " + id + " " + NodeModelUtils.getNode(var).getStartLine());
		}
		else if(e instanceof ProcCall) {
			ProcCall call = (ProcCall) e;
			EList<Expression> args = call.getArgs();
			IProcedure target = matchProcedure(call.getId().getId(), args); 
			List<IExpression> expArgs = new ArrayList<>();
			args.forEach(a -> expArgs.add(mapExpression(a)));
			exp = target.call(expArgs);
		}
		else if(e instanceof NewObject) {
			NewObject o = (NewObject) e;
			IType t = toModelType(o.getType().getId());
			if(!(t instanceof IRecordType))
				System.err.println("not record type");

			exp = ((IRecordType) t).heapAllocation();
		}
		else if(e instanceof NewArray) {
			NewArray a = (NewArray) e;
			IType t = toModelType(a.getType().getId());
			int dims = a.getArrayDims().size();
			t = arrayType(t, dims);
			List<IExpression> dimArgs = new ArrayList<>();
			a.getArrayDims().forEach(d -> dimArgs.add(mapExpression(d)));
			exp = ((IArrayType) t).heapAllocation(dimArgs);
		}		
		else if(e instanceof Or) {
			Or o = (Or) e;
			exp = OR.on(mapExpression(o.getLeft()), mapExpression(o.getRight()));
		}
		else if(e instanceof Xor) {
			Xor x = (Xor) e;
			exp = XOR.on(mapExpression(x.getLeft()), mapExpression(x.getRight()));
		}
		else if(e instanceof And) {
			And a = (And) e;
			exp = AND.on(mapExpression(a.getLeft()), mapExpression(a.getRight()));
		}
		else if(e instanceof Equality) {
			Equality eq = (Equality) e;
			IExpression left = mapExpression(eq.getLeft());
			IExpression right = mapExpression(eq.getRight());
			IBinaryOperator operator = mapBinaryOperator(eq.getOperator(), left, right);
			exp = operator.on(left, right);
		}
		else if(e instanceof Relation) {
			Relation r = (Relation) e;
			IExpression left = mapExpression(r.getLeft());
			IExpression right = mapExpression(r.getRight());
			IBinaryOperator operator = mapBinaryOperator(r.getOperator(), left, right);
			exp = operator.on(left, right);
		}
		else if(e instanceof Addition) {
			Addition a = (Addition) e;
			IExpression left = mapExpression(a.getLeft());
			IExpression right = mapExpression(a.getRight());
			IBinaryOperator operator = mapBinaryOperator(a.getOperator(), left, right);
			exp = operator.on(left, right);
		}
		else if(e instanceof Multiplication) {
			Multiplication m = (Multiplication) e;
			IExpression left = mapExpression(m.getLeft());
			IExpression right = mapExpression(m.getRight());
			IBinaryOperator operator = mapBinaryOperator(m.getOperator(), left, right);
			exp = operator.on(left, right);
		}

		if(exp == null)
			throw new RuntimeException("unsupported expression: " + e);
		
		exp.setProperty(ElementLocation.Part.WHOLE, new ElementLocation(e));
		return exp;
	}


	
	IType matchValueType(String retType) {
		switch(retType) {
		case "int": 	return INT;
		case "double": 	return DOUBLE;
		case "boolean": return BOOLEAN;
		default:		return null;
		}
	}

	IType toModelType(String retType) {
		IType valueType = matchValueType(retType);
		if(valueType == null) {
			for (Entry<IRecordType, Record> e : recordTypes.entrySet())
				if(e.getValue().getId().getId().equals(retType))
					return e.getKey();
		}
		else
			return valueType;
		
		throw new RuntimeException("unsupported type: " + retType);
	}


	IType toModelType(Type retType) {
		String id = retType.getId().getId();
		IType modelType = toModelType(id);
		int dims = retType.getArrayDims().size();
		return dims != 0 ? arrayType(modelType, dims).reference() : matchValueType(id) == null ? modelType.reference() : modelType;
	}

	static IType arrayType(IType base, int dims) {
		IType t = base;
		while(dims-- > 0)
			t = t.array();
		return t;
	}


	static IBinaryOperator mapBinaryOperator(String op, IExpression left, IExpression right) {
		switch(op) {
		case "+": return IOperator.ADD;
		case "-": return IOperator.SUB;
		case "*": return IOperator.MUL;
		case "/": return left.getType().equals(INT) && right.getType().equals(INT) ? IOperator.IDIV : IOperator.DIV;
		case "%": return IOperator.MOD;

		case "==": return IOperator.EQUAL;
		case "!=": return IOperator.DIFFERENT;
		case "<": return IOperator.SMALLER;
		case "<=": return IOperator.SMALLER_EQ;
		case ">": return IOperator.GREATER;
		case ">=": return IOperator.GREATER_EQ;

		case "&&": return IOperator.AND;
		case "||": return IOperator.OR;
		case "^": return IOperator.XOR;

		//		case "!": return IOperator.NOT;
		}
		throw new RuntimeException("unsupported operator:" + op);
	}

	//	public static void main(String[] args) {
	//	new org.eclipse.emf.mwe.utils.StandaloneSetup().setPlatformUri("../");
	//	Injector injector = new JavaliStandaloneSetup().createInjectorAndDoEMFRegistration();
	//	XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
	//	resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
	//	Resource resource = resourceSet.getResource(
	//			URI.createURI("platform:/resource/Test/test.javali"), true);
	//	Module module = (Module) resource.getContents().get(0);
	//
	//	Transformer v = new Transformer();
	//	IModule program = v.createProgram(module);
	//
	//	System.out.println(program);
	//
	//	program.accept(new IVisitor() {
	//		@Override
	//		public boolean visit(IReturn returnStatement) {
	//			System.out.println(returnStatement.getProperty("SOURCE"));
	//			return true;
	//		}
	//		
	//		@Override
	//		public boolean visit(IProcedure procedure) {
	//			System.out.println(procedure.getProperty("DOCUMENTATION").toString());
	//			System.out.println(procedure);
	//			return false;
	//		}
	//		
	//	});
	//	//		InteractiveMode interact = new InteractiveMode(program);
	//	//		interact.start();
	//	ProgramState state = new ProgramState(program);
	//	state.execute(program.getProcedures().iterator().next(), "-5");
	//
	//}
}
