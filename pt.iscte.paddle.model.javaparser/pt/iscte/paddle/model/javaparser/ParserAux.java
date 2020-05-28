package pt.iscte.paddle.model.javaparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import pt.iscte.paddle.model.IArrayType;
import pt.iscte.paddle.model.IBinaryOperator;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.ConstructorDeclarationContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.MethodDeclarationContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.ModifierContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.PrimitiveTypeContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.TypeTypeContext;

class ParserAux {
	final static String THIS_VAR = "$" + Keyword.THIS.keyword();
	final static String EFOR_ITVAR = "$it";
	final static String EFOR_SRCVAR = "$src";

	final static String INSTANCE_FLAG = "INSTANCE";
	final static String CONSTRUCTOR_FLAG = "CONSTRUCTOR";
	
	final static String FOR_FLAG = Keyword.FOR.name();
	final static String EFOR_FLAG = "E" + Keyword.FOR.name();
	final static String DO_FLAG = Keyword.DO.name();
	
//	final static String THROW_FLAG = Keyword.THROW.keyword();


	boolean hasModifier(List<ModifierContext> list, Keyword mod) {
		for(ModifierContext m : list)
			if(m.classOrInterfaceModifier() != null) {
				if(m.classOrInterfaceModifier().getChild(0).getText().equals(mod.keyword()))
					return true;
			}
			else {
				unsupported("modifier", m);
			}
		return false;
	}

	private final IModule module;
	List<Object> unsupported = new ArrayList<>();
	private Map<String, IProcedure> methods = new HashMap<>();
	private Map<String, IProcedure> constructors = new HashMap<>();

	public ParserAux(IModule module) {
		this.module = module;
	}

	static boolean containedIn(RuleContext ctx, Class<?> parentType) {
		RuleContext parent = ctx.getParent();
		while(parent != null) {
			if(parent.getClass().equals(parentType))
				return true;
			parent = parent.getParent();
		}
		return false;
	}
	
	void unsupported(String text, Token t) {
				System.err.println("unsupported " + text + ": " + t.getText() +
						" location " + t.getInputStream().getSourceName() + ":" + t.getLine());
		unsupported.add(t);
	}
	
	void unsupported(String text, ParseTree ctx) {
		Token t = ctx instanceof TerminalNode ? ((TerminalNode) ctx).getSymbol() : ((ParserRuleContext) ctx).getStart();
				System.err.println("unsupported " + text + ": " + ctx.getText() +
						" location " + t.getInputStream().getSourceName() + ":" + t.getLine());
		unsupported.add(ctx);
	}

	IBinaryOperator matchBinaryOperator(Token token) {
		IBinaryOperator op = matchBinaryOperator(token.getText());
		if(op == null)
			unsupported("binary operator", token);
		return op;
	}
	
	IBinaryOperator matchBinaryOperator(TerminalNode token) {
		IBinaryOperator op = matchBinaryOperator(token.getText());
		if(op == null)
			unsupported("binary operator", token);
		return op;
	}
	
	IBinaryOperator matchBinaryOperator(String token) {
		switch(token) {
		case "+": case "+=": return IBinaryOperator.ADD;
		case "-": case "-=": return IBinaryOperator.SUB;
		case "*": case "*=": return IBinaryOperator.MUL;
		case "/": case "/=": return IBinaryOperator.DIV;
		case "%": case "%=": return IBinaryOperator.MOD;

		case "==": return IBinaryOperator.EQUAL;
		case "!=": return IBinaryOperator.DIFFERENT;
		case "<": return IBinaryOperator.SMALLER;
		case "<=": return IBinaryOperator.SMALLER_EQ;
		case ">": return IBinaryOperator.GREATER;
		case ">=": return IBinaryOperator.GREATER_EQ;

		case "&": case "&&": return IBinaryOperator.AND;
		case "|": case "||": return IBinaryOperator.OR;
		case "^": return IBinaryOperator.XOR;

		default:
			return null;
		}
	}

	IType matchType(TypeTypeContext ctx) {
		int arrayDims = 0;
		String s = ctx.getText();
		while(s.endsWith("[]")) {
			s = s.substring(0, s.length()-2);
			arrayDims++;
		}
		
		IType t = null;
		if(ctx.primitiveType() != null)
			t = matchPrimitiveType(ctx.primitiveType());
		else
			t = matchRecordType(ctx.classOrInterfaceType().IDENTIFIER().get(0).getText()); // TODO support qualified name
		
		if(t == null)
			return new IRecordType.UnboundRecordType("?" + ctx.getText());
		
		while(arrayDims-- > 0)
			t = t.array();

		if(t instanceof IArrayType)
			t = t.reference();
		
		return t;
	}


	IType matchPrimitiveType(PrimitiveTypeContext ctx) {
		IType t = null;
		switch(ctx.getChild(0).getText()) {
		case "int" : case "short": case "long": case "byte": t = IType.INT; break;
		case "double" : case "float": t = IType.DOUBLE; break;
		case "boolean" : t = IType.BOOLEAN; break;
		case "char": t = IType.CHAR; break; 
//		unsupported("primitive type", ctx); return null;
		}
		return t;
	}

	IType matchRecordType(String type) {
		IType recType = null;
		for(IRecordType t : module.getRecordTypes())
			if(t.getId().equals(type))
				recType = t;

		if(recType == null)
			recType = new IRecordType.UnboundRecordType(type);

		return recType.reference();
	}

	public void addMethod(MethodDeclarationContext ctx, String namespace, IProcedure proc) {
		methods.put(namespace + ctx.start.getTokenIndex(), proc);
	}

	public void addConstructor(ConstructorDeclarationContext ctx, String namespace, IProcedure proc) {
		constructors.put(namespace + ctx.start.getTokenIndex(), proc);
	}

	public IProcedure getMethod(MethodDeclarationContext ctx, String namespace) {
		return methods.get(namespace + ctx.start.getTokenIndex());
	}

	public IProcedure getMethod(String namespace, String methodId) {
		for(IProcedure p : methods.values())
			if(namespace.equals(p.getNamespace()) && methodId.equals(p.getId()))
				return p;
		return null;
	}

	public IProcedure getConstructor(ConstructorDeclarationContext ctx, String namespace) {
		return constructors.get(namespace + ctx.start.getTokenIndex());
	}

	public IProcedure getConstructor(IType type, int nParams) {
		for(IProcedure p : constructors.values())
			if(p.getReturnType().getId().equals(type.getId()) && p.getParameters().size() == nParams)
				return p;

		return null;
	}


	public Iterable<IProcedure> allMethods() {
		return methods.values();
	}




}
