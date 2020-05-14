package pt.iscte.paddle.model.java;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import pt.iscte.paddle.java.antlr.Java8Parser.AdditiveExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ArgumentListContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ArrayAccess_lfno_primaryContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ArrayCreationExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ArrayInitializerContext;
import pt.iscte.paddle.java.antlr.Java8Parser.AssignmentContext;
import pt.iscte.paddle.java.antlr.Java8Parser.BasicForStatementContext;
import pt.iscte.paddle.java.antlr.Java8Parser.BreakStatementContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ClassInstanceCreationExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ClassInstanceCreationExpression_lfno_primaryContext;
import pt.iscte.paddle.java.antlr.Java8Parser.CompilationUnitContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ConstructorDeclarationContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ContinueStatementContext;
import pt.iscte.paddle.java.antlr.Java8Parser.EqualityExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ExpressionNameContext;
import pt.iscte.paddle.java.antlr.Java8Parser.FieldDeclarationContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ForInitContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ForStatementContext;
import pt.iscte.paddle.java.antlr.Java8Parser.FormalParameterContext;
import pt.iscte.paddle.java.antlr.Java8Parser.IfThenElseStatementContext;
import pt.iscte.paddle.java.antlr.Java8Parser.IfThenElseStatementNoShortIfContext;
import pt.iscte.paddle.java.antlr.Java8Parser.IfThenStatementContext;
import pt.iscte.paddle.java.antlr.Java8Parser.LiteralContext;
import pt.iscte.paddle.java.antlr.Java8Parser.LocalVariableDeclarationContext;
import pt.iscte.paddle.java.antlr.Java8Parser.MethodDeclarationContext;
import pt.iscte.paddle.java.antlr.Java8Parser.MethodInvocationContext;
import pt.iscte.paddle.java.antlr.Java8Parser.MethodInvocation_lfno_primaryContext;
import pt.iscte.paddle.java.antlr.Java8Parser.MethodModifierContext;
import pt.iscte.paddle.java.antlr.Java8Parser.MultiplicativeExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.PostDecrementExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.PostDecrementExpression_lf_postfixExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.PostIncrementExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.PostIncrementExpression_lf_postfixExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.PostfixExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.PreDecrementExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.PreIncrementExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.RelationalExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ReturnStatementContext;
import pt.iscte.paddle.java.antlr.Java8Parser.StatementExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.StatementNoShortIfContext;
import pt.iscte.paddle.java.antlr.Java8Parser.UnannTypeContext;
import pt.iscte.paddle.java.antlr.Java8Parser.VariableDeclaratorContext;
import pt.iscte.paddle.java.antlr.Java8Parser.VariableDeclaratorListContext;
import pt.iscte.paddle.java.antlr.Java8Parser.WhileStatementContext;
import pt.iscte.paddle.java.antlr.Java8ParserBaseListener;
import pt.iscte.paddle.model.IArrayType;
import pt.iscte.paddle.model.IBinaryOperator;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IBlockElement;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProcedureCallExpression;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IReferenceType;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IStatement;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.cfg.IStatementNode;

class Listener extends Java8ParserBaseListener {
	private final String INSTANCE_FLAG = "instance";
	private final String CONSTRUCTOR_FLAG = "constructor";

	private final IModule module;
	private IProcedure currentProcedure;
	private Deque<IBlock> blockStack;
	private Deque<IExpression> expressionStack = new ArrayDeque<>();

	private Map<MethodDeclarationContext, IProcedure> procMap;
	private IRecordType classType;

	private List<ParseTree> unsupported;

	public Listener(IModule module) {
		assert module.getId() != null;
		this.module = module;
		procMap = new HashMap<>();
		unsupported = new ArrayList<>();
	}

	private void unsupported(ParseTree ctx) {
		Token t = ctx instanceof TerminalNode ? ((TerminalNode) ctx).getSymbol() : ((ParserRuleContext) ctx).getStart();
		System.err.println("unsupported: " + ctx.getText() + " line " + t.getLine());
		unsupported.add(ctx);
	}

	@Override
	public void enterCompilationUnit(CompilationUnitContext ctx) {
		classType = module.addRecordType();
		classType.setId(module.getId());
		module.setProperty(IRecordType.class, classType);
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(new Java8ParserBaseListener() {
			IProcedure proc;
			public void enterMethodDeclaration(MethodDeclarationContext ctx) {
				String id = ctx.methodHeader().methodDeclarator().Identifier().toString();
				UnannTypeContext t = ctx.methodHeader().result().unannType();
//				String type = t == null ? Keyword.VOID.keyword() : t.getText();
				IType type = t == null ? IType.VOID : matchPrimitiveType(t);
				proc = module.addProcedure(id, type);
				if(!hasModifier(ctx, Keyword.STATIC.keyword())) {
					proc.setFlag(INSTANCE_FLAG);
					IVariableDeclaration self = proc.addParameter(classType);
					self.setId(Keyword.THIS.keyword());
				}
				procMap.put(ctx, proc);
			}

			@Override
			public void enterConstructorDeclaration(ConstructorDeclarationContext ctx) {
				// TODO constructor
				System.err.println("CONST " + ctx.getText());
			}
			@Override
			public void enterFormalParameter(FormalParameterContext ctx) {
				String id = ctx.variableDeclaratorId().Identifier().getText();
				IType t = matchPrimitiveType(ctx.unannType());
				if(t == null)
					t = matchRecordType(ctx.unannType());
				IVariableDeclaration p = proc.addParameter(t);
				p.setId(id);
			}
		}, ctx);
		blockStack = new ArrayDeque<>();
	}

	private boolean hasModifier(MethodDeclarationContext ctx, String keyword) {
		for (MethodModifierContext mCtx : ctx.methodModifier()) {
			if(mCtx.getText().equals(keyword))
				return true;
		}
		return false;
	}

	@Override
	public void enterMethodDeclaration(MethodDeclarationContext ctx) {
		currentProcedure = procMap.get(ctx);
		assert currentProcedure != null : ctx;
		blockStack.push(currentProcedure.getBody());
	}

	@Override
	public void exitMethodDeclaration(MethodDeclarationContext ctx) {
		blockStack.pop();
	}

	@Override
	public void exitReturnStatement(ReturnStatementContext ctx) {
		if(ctx.expression() == null)
			blockStack.peek().addReturn();
		else
			blockStack.peek().addReturn(expressionStack.pop());
	}

	@Override
	public void exitVariableDeclarator(VariableDeclaratorContext ctx) {
		VariableDeclaratorListContext varList = (VariableDeclaratorListContext) ctx.getParent();
		String varId = ctx.variableDeclaratorId().Identifier().getText();
		ParserRuleContext parent = ctx.getParent().getParent();

		if(parent instanceof LocalVariableDeclarationContext) {
			LocalVariableDeclarationContext con = (LocalVariableDeclarationContext) ctx.getParent().getParent();
			boolean isFor = con.getParent() instanceof ForInitContext;
			IVariableDeclaration dec = blockStack.peek().addVariable(matchPrimitiveType(con.unannType()));
			dec.setId(varId);
			if(isFor)
				dec.setFlag(Keyword.FOR.name());
			if(ctx.variableInitializer() != null) {
				IVariableAssignment ass = blockStack.peek().addAssignment(dec, expressionStack.pop());
				if(isFor)
					ass.setFlag(Keyword.FOR.name());
			}
		}
		else if(parent instanceof FieldDeclarationContext) {
			UnannTypeContext unannType = ((FieldDeclarationContext) parent).unannType();
			classType.addField(matchPrimitiveType(unannType), varId);
		}

	}

	@Override
	public void exitAssignment(AssignmentContext ctx) {
		IStatement statement = null;
		if(ctx.leftHandSide().expressionName() != null) {
			String id = ctx.leftHandSide().expressionName().getText();
			IVariableDeclaration var = matchVariable(id);
			if(ctx.assignmentOperator().ASSIGN() != null)
				blockStack.peek().addAssignment(var, expressionStack.pop());
			else {
				IBinaryOperator op = matchBinaryOperator(ctx.assignmentOperator());
				statement = blockStack.peek().addAssignment(var, op.on(var, expressionStack.pop()));
			}
		}
		else if(ctx.leftHandSide().arrayAccess() != null) {
			String id = ctx.leftHandSide().arrayAccess().expressionName().getText();

			IVariableDeclaration var = matchVariable(id);
			if(ctx.assignmentOperator().ASSIGN() != null) {
				IExpression exp = expressionStack.pop();
				int dims = ctx.leftHandSide().arrayAccess().expression().size();
				IExpression[] indexes = new IExpression[dims];
				while(dims-- > 0)
					indexes[dims] = expressionStack.pop();
				statement = blockStack.peek().addArrayElementAssignment(var, exp, indexes);
			}
		}
		else
			unsupported(ctx);

		if(statement != null && containedIn(ctx, BasicForStatementContext.class))
			statement.setFlag(Keyword.FOR.name());

		// TODO records
	}







	@Override
	public void exitExpression(ExpressionContext ctx) {
		if(ctx.getParent() instanceof IfThenStatementContext) {
			ISelection sel = blockStack.peek().addSelection(expressionStack.pop());
			blockStack.push(sel.getBlock());
		}
		else if(ctx.getParent() instanceof IfThenElseStatementContext) {
			ISelection sel = blockStack.peek().addSelectionWithAlternative(expressionStack.pop());
			blockStack.push(sel.getBlock());
		}
		else if(ctx.getParent() instanceof WhileStatementContext) {
			ILoop loop = blockStack.peek().addLoop(expressionStack.pop());
			blockStack.push(loop.getBlock());
		}
		else if(ctx.getParent() instanceof BasicForStatementContext) {
			ILoop loop = blockStack.peek().addLoop(expressionStack.pop());
			loop.setFlag(Keyword.FOR.name());
			blockStack.push(loop.getBlock());
		}
	}

	@Override
	public void exitIfThenElseStatementNoShortIf(IfThenElseStatementNoShortIfContext ctx) {
		unsupported(ctx);
	}

	@Override
	public void exitStatementNoShortIf(StatementNoShortIfContext ctx) {
		IBlock block = blockStack.pop();
		ISelection sel = (ISelection) block.getParent();
		blockStack.push(sel.getAlternativeBlock());
	}

	@Override
	public void exitIfThenStatement(IfThenStatementContext ctx) {
		blockStack.pop();
	}

	@Override
	public void exitIfThenElseStatement(IfThenElseStatementContext ctx) {
		blockStack.pop();
	}

	@Override
	public void exitWhileStatement(WhileStatementContext ctx) {
		blockStack.pop();
	}

	@Override
	public void enterForStatement(ForStatementContext ctx) {
		IBlock forBlock = blockStack.peek().addBlock();
		forBlock.setFlag(Keyword.FOR.name());
		blockStack.push(forBlock);
	}

	@Override
	public void exitForStatement(ForStatementContext ctx) {
		int n = count(ctx.basicForStatement().forUpdate().statementExpressionList(), StatementExpressionContext.class);
		IBlock forBlock = blockStack.pop();
		while(n-- > 0) {
			IBlockElement first = forBlock.getFirst();
			first.setFlag(Keyword.FOR.name());
			forBlock.moveAfter(first, forBlock.getLast());
		}
		blockStack.pop();
	}

	@Override
	public void exitMethodInvocation(MethodInvocationContext ctx) {
		boolean memberCall = ctx.primary() != null || ctx.typeName() != null;
		String methodId = memberCall ? ctx.Identifier().getText() : ctx.methodName().getText();
		String varId = ctx.primary() != null ? ctx.primary().getText() : ctx.typeName() != null ? ctx.typeName().getText() : null;
		handleMethodCall(varId, methodId, ctx.argumentList(), true);
	}

	private void handleMethodCall(String targetVarId, String methodId, ArgumentListContext argList, boolean statement) {
		boolean memberCall = targetVarId != null;

		List<IExpression> args = new ArrayList<>();
		IProcedure p = null;
		if(memberCall) {
			IRecordType instanceType = memberCall ? findVariableType(targetVarId) : null;
			p = matchInstanceProcedure(methodId, instanceType, argList);
			IVariableDeclaration var = currentProcedure.getVariable(targetVarId);
			if(var == null)
				var = new IVariableDeclaration.UnboundVariable(targetVarId);
			args.add(var.expression());
		}
		else {
			p = matchInstanceProcedure(methodId, module.getProperty(IRecordType.class), argList);
			if(p != null)
				args.add(currentProcedure.getVariable(Keyword.THIS.keyword()).expression());
			else
				p = matchStaticProcedure(methodId, argList);
		}
		if(p == null)
			p = new IProcedure.UnboundProcedure(methodId);

		if(argList != null) {
			argList.children.forEach(c -> {
				if(!c.getText().equals(",")) {
					IExpression a = expressionStack.pop();
					args.add(a);
				}
			});
		}
		if(statement)
			blockStack.peek().addCall(p, args);
		else
			expressionStack.push(p.expression(args));
	}









	private IRecordType findVariableType(String id) {
		if(id.equals(Keyword.THIS.keyword()) && currentProcedure.is(INSTANCE_FLAG))
			return module.getProperty(IRecordType.class);

		IVariableDeclaration v = currentProcedure.getVariable(id);
		IType t = v == null ? null : v.getType();
		return t instanceof IRecordType ? (IRecordType) t : null;
	}

	@Override
	public void enterExpressionName(ExpressionNameContext ctx) {
		if(ctx.getParent() instanceof PostfixExpressionContext)
			expressionStack.push(matchVariable(ctx.Identifier().getText()).expression());
	}

	@Override
	public void exitArrayAccess_lfno_primary(ArrayAccess_lfno_primaryContext ctx) {
		String id = ctx.expressionName().getText();
		expressionStack.push(matchVariable(id).element(getIndexes(ctx.expression().size())).expression());
		super.exitArrayAccess_lfno_primary(ctx);
	}

	private IExpression[] getIndexes(int n) {
		IExpression[] indexes = new IExpression[n];
		while(n-- > 0)
			indexes[n] = expressionStack.pop();
		return indexes;
	}

	@Override
	public void enterLiteral(LiteralContext ctx) {
		if(ctx.IntegerLiteral() != null)
			expressionStack.push(IType.INT.literal(Integer.parseInt(ctx.getText())));
		else if(ctx.FloatingPointLiteral() != null)
			expressionStack.push(IType.DOUBLE.literal(Double.parseDouble(ctx.getText())));
		// TODO other types
	}

	@Override
	public void exitAdditiveExpression(AdditiveExpressionContext ctx) {
		pushBinaryOperation(ctx.ADD(), ctx.SUB());
	}

	@Override
	public void exitMultiplicativeExpression(MultiplicativeExpressionContext ctx) {
		pushBinaryOperation(ctx.MUL(), ctx.DIV(), ctx.MOD());
	}

	@Override
	public void exitEqualityExpression(EqualityExpressionContext ctx) {
		pushBinaryOperation(ctx.EQUAL(), ctx.NOTEQUAL());
	}

	@Override
	public void exitRelationalExpression(RelationalExpressionContext ctx) {
		pushBinaryOperation(ctx.LT(), ctx.LE(), ctx.GT(), ctx.GE());
	}

	@Override
	public void exitArrayCreationExpression(ArrayCreationExpressionContext ctx) {
		IType t = null;
		if(ctx.primitiveType() != null)
			t = matchPrimitiveType(ctx.primitiveType());
		else if(ctx.classOrInterfaceType() != null)
			t = matchRecordType(ctx.classOrInterfaceType());
		//		else if(ctx.arrayInitializer() != null) {
		//			// TODO array initializer
		//		}
		else 
			unsupported(ctx);

		expressionStack.push(t.array().heapAllocation(getIndexes(ctx.dimExprs().dimExpr().size())));
		// TODO not primitive
	}

	@Override
	public void exitClassInstanceCreationExpression_lfno_primary(
			ClassInstanceCreationExpression_lfno_primaryContext ctx) {
		IRecordType t = matchRecordType(ctx.Identifier().get(0));
		expressionStack.push(t.heapAllocation());
	}

	@Override
	public void exitClassInstanceCreationExpression(ClassInstanceCreationExpressionContext ctx) {
		IRecordType t = matchRecordType(ctx.expressionName());
		expressionStack.push(t.heapAllocation());
	}

	@Override
	public void exitArrayInitializer(ArrayInitializerContext ctx) {
		unsupported(ctx);
		//		System.out.println("**** " + ctx.getParent().getParent().getText());
	}

	@Override
	public void exitMethodInvocation_lfno_primary(MethodInvocation_lfno_primaryContext ctx) {
		String varId = ctx.typeName() != null ? ctx.typeName().getText() : null;
		String methodId = ctx.methodName() != null ? ctx.methodName().Identifier().getText() : ctx.Identifier().getText();
		handleMethodCall(varId, methodId, ctx.argumentList(), false);
	}





	// BREAK / CONTINUE ------------------------------------------------

	@Override
	public void exitBreakStatement(BreakStatementContext ctx) {
		blockStack.peek().addBreak();
	}

	@Override
	public void exitContinueStatement(ContinueStatementContext ctx) {
		blockStack.peek().addContinue();
	}




	// INCREMENTS / DECREMENTS -----------------------------------------------

	@Override
	public void enterPostIncrementExpression(PostIncrementExpressionContext ctx) {
		IVariableDeclaration var = matchVariable(ctx.postfixExpression().getText()); // TODO array[]++
		blockStack.peek().addIncrement(var);
	}

	@Override
	public void enterPreIncrementExpression(PreIncrementExpressionContext ctx) {
		IVariableDeclaration var = matchVariable(ctx.unaryExpression().getText()); // TODO ++array
		blockStack.peek().addIncrement(var);
	}

	@Override
	public void enterPostDecrementExpression(PostDecrementExpressionContext ctx) {
		IVariableDeclaration var = matchVariable(ctx.postfixExpression().getText()); // TODO array[]++
		blockStack.peek().addDecrement(var);
	}

	@Override
	public void enterPreDecrementExpression(PreDecrementExpressionContext ctx) {
		if(ctx.getParent() instanceof StatementExpressionContext) {
			IVariableDeclaration var = matchVariable(ctx.unaryExpression().getText()); // TODO ++array
			blockStack.peek().addDecrement(var);
		}
		else 
			unsupported(ctx);
	}

	@Override
	public void enterPostIncrementExpression_lf_postfixExpression(
			PostIncrementExpression_lf_postfixExpressionContext ctx) {
		unsupported(ctx);
	}

	@Override
	public void enterPostDecrementExpression_lf_postfixExpression(
			PostDecrementExpression_lf_postfixExpressionContext ctx) {
		unsupported(ctx);
	}


	// AUX---------------------------------------------------------

	private int count(RuleContext ctx, Class<?> childType) {
		int c = 0;
		for(int i = 0; i < ctx.getChildCount(); i++)
			if(ctx.getChild(i).getClass().equals(childType))
				c++;
		return c;
	}

	private boolean containedIn(RuleContext ctx, Class<?> parentType) {
		RuleContext parent = ctx.getParent();
		while(parent != null) {
			if(parent.getClass().equals(parentType))
				return true;
			parent = parent.getParent();
		}
		return false;
	}

	private void pushBinaryOperation(TerminalNode ... nodes) {
		for(TerminalNode node : nodes) {
			if(node != null) {
				IBinaryOperator op = matchBinaryOperator(node);
				if(op != null) {
					IExpression r = expressionStack.pop();
					IExpression l = expressionStack.pop();
					expressionStack.push(op.on(l, r));
					return;
				}
			}
		}
	}

	private IProcedure matchInstanceProcedure(String id, IRecordType instanceType, ArgumentListContext args) {
		assert instanceType != null;
		int n = args == null ? 0 : args.getChildCount();
		if(n > 1)
			n = n - (n-1)/2;
		if(instanceType != null)
			n++;

		IProcedure proc = null;
		for (IProcedure p : procMap.values()) {
			if(p.is(INSTANCE_FLAG) && p.getId().equals(id) && p.getParameters().size() == n) // TODO arg types
				proc = p;
		}

		return proc;
	}

	private IProcedure matchStaticProcedure(String id, ArgumentListContext args) {
		int n = args.getChildCount();
		if(n != 1)
			n = n - (n-1)/2;

		IProcedure proc = null;
		for (IProcedure p : procMap.values()) {
			if(!p.is(INSTANCE_FLAG) && p.getId().equals(id) && p.getParameters().size() == n) // TODO arg types
				proc = p;
		}

		return proc;
	}

	private IVariableDeclaration matchVariable(String id) {
		for(IVariableDeclaration v : currentProcedure.getVariables())
			if(v.getId().equals(id))
				return v;

		return new IVariableDeclaration.UnboundVariable(id);
	}

	private IBinaryOperator matchBinaryOperator(ParseTree node) {
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
			unsupported(node);
			return null;
		}
	}


	// TODO arrays
	private IType matchPrimitiveType(ParseTree ctx) {
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
		case "float" : unsupported(ctx); return null;
		}
		if(t == null)
			return null;
		
		while(arrayDims-- > 0)
			t = t.array();
		
		if(t instanceof IArrayType)
			t = t.reference();
		return t;
	}
	
	// TODO arrays?
	private IRecordType matchRecordType(ParseTree ctx) {
		String type = ctx.getText();
		int arrayDims = 0;
		while(type.contains("[]")) {
			type = type.replace("[]", "");
			arrayDims++;
		}
		IRecordType recType = null;
		for(IRecordType t : module.getRecordTypes())
			if(t.getId().equals(type))
				recType = t;
		
		if(recType == null)
			recType = new IRecordType.UnboundRecordType(type);
		
//		while(arrayDims-- > 0)
//			recType = recType.array();
		
		return recType; //.reference();
	}
}
