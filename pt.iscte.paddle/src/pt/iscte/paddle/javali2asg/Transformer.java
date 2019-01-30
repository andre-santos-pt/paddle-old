package pt.iscte.paddle.javali2asg;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

import pt.iscte.paddle.JavaliStandaloneSetup;
import pt.iscte.paddle.asg.IBinaryOperator;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IInstruction;
import pt.iscte.paddle.asg.ILiteral;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IOperator;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.ISelection;
import pt.iscte.paddle.asg.ISelectionWithAlternative;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.javali.Block;
import pt.iscte.paddle.javali.Break;
import pt.iscte.paddle.javali.Continue;
import pt.iscte.paddle.javali.DoWhile;
import pt.iscte.paddle.javali.Expression;
import pt.iscte.paddle.javali.For;
import pt.iscte.paddle.javali.IfElse;
import pt.iscte.paddle.javali.Literal;
import pt.iscte.paddle.javali.Module;
import pt.iscte.paddle.javali.Procedure;
import pt.iscte.paddle.javali.Return;
import pt.iscte.paddle.javali.Statement;
import pt.iscte.paddle.javali.Type;
import pt.iscte.paddle.javali.VarAssign;
import pt.iscte.paddle.javali.VarDeclaration;
import pt.iscte.paddle.javali.VarExpression;
import pt.iscte.paddle.javali.While;

public class Transformer {
	Map<String, IVariable> varTable;
	private Resource resource;

	public Transformer(IFile file) {
//		new org.eclipse.emf.mwe.utils.StandaloneSetup().setPlatformUri("../");
		Injector injector = new JavaliStandaloneSetup().createInjectorAndDoEMFRegistration();
		XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
		resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
//		resource = resourceSet.getResource(URI.createURI("platform:/resource/Test/test.javali"), true);
		resource = resourceSet.getResource(URI.createURI(file.getLocationURI().toString()), true);
	}
	
	public IModule createProgram() {
		Module module = (Module) resource.getContents().get(0);
		ICompositeNode node = NodeModelUtils.getNode(module);
		IModule program = IModule.create("test");
		for (Procedure p : module.getProcedures()) {
			varTable = new HashMap<>();
			IProcedure proc = program.addProcedure(p.getId().getId(), toModelType(p.getRetType()));
			for (VarDeclaration paramDec : p.getParams()) {
				IVariable param = proc.addParameter(paramDec.getId().getId(), toModelType(p.getRetType()));
				varTable.put(param.getId(), param);
			}
			for (Statement statement : p.getBody().getStatements()) {
				map(statement, proc.getBody());
			}
			if(p.getComment() != null)
				proc.setProperty("DOCUMENTATION", p.getComment());
			
			ICompositeNode pnode = NodeModelUtils.getNode(p);
			ElementLocation loc = new ElementLocation(pnode);
			proc.setProperty(ElementLocation.class, loc);
			ICompositeNode idNode = NodeModelUtils.getNode(p.getId());
			proc.setProperty(IdLocation.class, new IdLocation(idNode.getOffset(), idNode.getLength()));
		}
		
		return program;
	}

	void map(Statement s, IBlock block) {
		IInstruction instr = null;
		if(s instanceof Return) {
			instr = block.addReturn(map(((Return) s).getExp()));
		}
		else if(s instanceof VarDeclaration) {
			VarDeclaration varDec = (VarDeclaration) s;
			String id = varDec.getId().getId();
			IVariable var = block.addVariable(id, toModelType(varDec.getType()));
			varTable.put(id, var);
			if(varDec.getAnnotation() != null)
				var.setProperty("ANNOTATION", varDec.getAnnotation().getId());
			if(varDec.getExp() != null) {
				instr = block.addAssignment(var, map(varDec.getExp()));
			}
		}
		else if(s instanceof VarAssign) {
			// TODO array indexes
			VarAssign ass = (VarAssign) s;
			IVariable var = varTable.get(ass.getId());
			instr = block.addAssignment(var, map(ass.getExp()));
		}
		else if (s instanceof IfElse) {
			IfElse ifElse = (IfElse) s;
			IExpression guard = map(ifElse.getGuard());
			Block elseBlock = ifElse.getAlternativeBlock();
			ISelection sel = elseBlock == null ? block.addSelection(guard) : block.addSelectionWithAlternative(guard);
			ifElse.getSelectionBlock().getStatements().forEach(st -> map(st, (ISelection) sel));
			if(elseBlock != null)
				elseBlock.getStatements().forEach(st -> map(st, ((ISelectionWithAlternative) sel).getAlternativeBlock()));
			instr = sel;
		}
		else if(s instanceof While) {
			While whi = (While) s;
			IExpression guard = map(whi.getGuard());
			ILoop loop = block.addLoop(guard);
			whi.getBlock().getStatements().forEach(st -> map(st, loop));
			instr = loop;
		}
		else if(s instanceof For) {
			For fo = (For) s;
			IExpression guard = map(fo.getGuard());
			IBlock fBlock = block.addBlock();
			fo.getInitStatements().forEach(st -> map(st, fBlock));
			ILoop loop = fBlock.addLoop(guard);
			fo.getBlock().getStatements().forEach(st -> map(st, loop));
			fo.getProgressStatements().forEach(st -> map(st, loop));
		}
		else if(s instanceof DoWhile) {
			
		}
		else if(s instanceof Break) {
			instr = block.addBreak();
		}
		else if(s instanceof Continue) {
			instr = block.addContinue();
		}
		
		if(instr != null) {
			ICompositeNode node = NodeModelUtils.getNode(s);
			ElementLocation loc = new ElementLocation(node.getText(), node.getOffset(), node.getLength(), 0);
			instr.setProperty(ElementLocation.class, loc);
		}
	}

	IExpression map(Expression e) {
		if(e instanceof Literal)
			return ILiteral.matchValue(((Literal) e).getValue());

		else if(e instanceof VarExpression) {
			return varTable.get(((VarExpression) e).getId().getId());
		}

		// TODO repor : um unico tipo?

//		else if(e instanceof Relation) {
//			Relation r = (Relation) e;
//			return fact.binaryExpression(map(r.getOperator()), map(r.getLeft()), map(r.getRight()));
//		}
//		else if(e instanceof Addition) {
//			Addition add = (Addition) e;
//			return fact.binaryExpression(map(add.getOperator()), map(add.getLeft()), map(add.getRight()));
//		}
//		else if(e instanceof Multiplication) {
//			Multiplication mul = (Multiplication) e;
//			return fact.binaryExpression(map(mul.getOperator()), map(mul.getLeft()), map(mul.getRight()));
//		}
		return null;
	}


//	public static void main(String[] args) {
//		new org.eclipse.emf.mwe.utils.StandaloneSetup().setPlatformUri("../");
//		Injector injector = new JavaliStandaloneSetup().createInjectorAndDoEMFRegistration();
//		XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
//		resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
//		Resource resource = resourceSet.getResource(
//				URI.createURI("platform:/resource/Test/test.javali"), true);
//		Module module = (Module) resource.getContents().get(0);
//
//		Transformer v = new Transformer();
//		IModule program = v.createProgram(module);
//
//		System.out.println(program);
//
//		program.accept(new IVisitor() {
//			@Override
//			public boolean visit(IReturn returnStatement) {
//				System.out.println(returnStatement.getProperty("SOURCE"));
//				return true;
//			}
//			
//			@Override
//			public boolean visit(IProcedure procedure) {
//				System.out.println(procedure.getProperty("DOCUMENTATION").toString());
//				System.out.println(procedure);
//				return false;
//			}
//			
//		});
//		//		InteractiveMode interact = new InteractiveMode(program);
//		//		interact.start();
//		ProgramState state = new ProgramState(program);
//		state.execute(program.getProcedures().iterator().next(), "-5");
//
//	}

	static IDataType toModelType(Type retType) {
		switch(retType.getId()) {
		case "int": return IDataType.INT;
		case "double": return IDataType.DOUBLE;
		case "boolean": return IDataType.BOOLEAN;
		}
		return null;
	}



	static IBinaryOperator map(String op) {
		switch(op) {
		case "+": return IOperator.ADD;
		case "*": return IOperator.MUL;

		case "==": return IOperator.EQUAL;
		case "<": return IOperator.SMALLER;
		}
		return null;
	}
}
