package pt.iscte.paddle.model.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import pt.iscte.paddle.java.antlr.Java8Parser.ConstructorDeclarationContext;
import pt.iscte.paddle.java.antlr.Java8Parser.FieldDeclarationContext;
import pt.iscte.paddle.java.antlr.Java8Parser.FieldModifierContext;
import pt.iscte.paddle.java.antlr.Java8Parser.MethodDeclarationContext;
import pt.iscte.paddle.java.antlr.Java8Parser.MethodModifierContext;
import pt.iscte.paddle.model.IArrayType;
import pt.iscte.paddle.model.IBinaryOperator;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IValueType;

public class ParserAux {

	final static String INSTANCE_FLAG = "instance";
	final static String CONSTRUCTOR_FLAG = "constructor";
	final static String THIS = Keyword.THIS.keyword();

	static boolean hasModifier(MethodDeclarationContext ctx, Keyword keyword) {
		for (MethodModifierContext mCtx : ctx.methodModifier()) {
			if(mCtx.getText().equals(keyword.keyword()))
				return true;
		}
		return false;
	}

	static boolean hasModifier(FieldDeclarationContext ctx, Keyword keyword) {
		for (FieldModifierContext fCtx : ctx.fieldModifier()) {
			if(fCtx.getText().equals(keyword.keyword()))
				return true;
		}
		return false;
	}

	private final IModule module;
	private List<ParseTree> unsupported = new ArrayList<>();
	private Map<String, IProcedure> methods = new HashMap<>();
	private Map<String, IProcedure> constructors = new HashMap<>();

	public ParserAux(IModule module) {
		this.module = module;
	}

	void unsupported(String text, ParseTree ctx) {
		Token t = ctx instanceof TerminalNode ? ((TerminalNode) ctx).getSymbol() : ((ParserRuleContext) ctx).getStart();
		System.err.println("unsupported " + text + ": " + ctx.getText() +
				" location " + t.getInputStream().getSourceName() + ":" + t.getLine());
		unsupported.add(ctx);
	}

	IBinaryOperator matchBinaryOperator(ParseTree node) {
		switch(node.getText()) {
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
			unsupported("binary operator", node);
			return null;
		}
	}

	IType matchType(ParseTree ctx) {
		IType t = matchPrimitiveType(ctx);
		if(t == null)
			t = matchRecordType(ctx);
		return t;
	}

	IType matchPrimitiveType(ParseTree ctx) {
		String type = ctx.getText();
		int arrayDims = 0;
		while(type.contains("[]")) {
			type = type.replace("[]", "");
			arrayDims++;
		}
		IType t = null;
		switch(type) {
		case "int" : t = IType.INT; break;
		case "double" : t = IType.DOUBLE; break;
		case "boolean" : t = IType.BOOLEAN; break;
		case "char": 
		case "short": 
		case "byte" :
		case "long" :
		case "float" : unsupported("primitive type", ctx); return null;
		}
		if(t == null)
			return null;

		while(arrayDims-- > 0)
			t = t.array();

		if(t instanceof IArrayType)
			t = t.reference();
		return t;
	}

	// TODO arrays on records?
	IType matchRecordType(ParseTree ctx) {
		String type = ctx.getText();
		int arrayDims = 0;
		while(type.contains("[]")) {
			type = type.replace("[]", "");
			arrayDims++;
		}
		IType recType = null;
		for(IRecordType t : module.getRecordTypes())
			if(t.getId().equals(type))
				recType = t;

		if(recType == null)
			recType = new IRecordType.UnboundRecordType(type);

		while(arrayDims-- > 0)
			recType = recType.array();

		return recType.reference();
	}

	public void addMethod(MethodDeclarationContext ctx, IRecordType namespace, IProcedure proc) {
		methods.put(namespace.getId() + ctx.start.getTokenIndex(), proc);
	}

	public void addConstructor(ConstructorDeclarationContext ctx, IRecordType namespace, IProcedure proc) {
		constructors.put(namespace.getId() + ctx.start.getTokenIndex(), proc);
	}

	public IProcedure getMethod(MethodDeclarationContext ctx, IRecordType namespace) {
		return methods.get(namespace.getId() + ctx.start.getTokenIndex());
	}

	public IProcedure getConstructor(ConstructorDeclarationContext ctx, IRecordType namespace) {
		return constructors.get(namespace.getId() + ctx.start.getTokenIndex());
	}

	public Iterable<IProcedure> allMethods() {
		return methods.values();
	}


}
