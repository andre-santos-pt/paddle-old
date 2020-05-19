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
import org.antlr.v4.runtime.tree.TerminalNode;

import pt.iscte.paddle.interpreter.IRecord;
import pt.iscte.paddle.java.antlr.Java8Parser.AdditiveExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.AndExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ArgumentListContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ArrayAccess_lfno_primaryContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ArrayCreationExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ArrayInitializerContext;
import pt.iscte.paddle.java.antlr.Java8Parser.AssignmentContext;
import pt.iscte.paddle.java.antlr.Java8Parser.BasicForStatementContext;
import pt.iscte.paddle.java.antlr.Java8Parser.BreakStatementContext;
import pt.iscte.paddle.java.antlr.Java8Parser.CastExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ClassInstanceCreationExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ClassInstanceCreationExpression_lfno_primaryContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ConditionalAndExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ConditionalOrExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ConstructorDeclarationContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ContinueStatementContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ElementValueArrayInitializerContext;
import pt.iscte.paddle.java.antlr.Java8Parser.EqualityExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ExclusiveOrExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ExpressionNameContext;
import pt.iscte.paddle.java.antlr.Java8Parser.FieldAccessContext;
import pt.iscte.paddle.java.antlr.Java8Parser.FieldAccess_lf_primaryContext;
import pt.iscte.paddle.java.antlr.Java8Parser.FieldAccess_lfno_primaryContext;
import pt.iscte.paddle.java.antlr.Java8Parser.FieldDeclarationContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ForInitContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ForStatementContext;
import pt.iscte.paddle.java.antlr.Java8Parser.IfThenElseStatementContext;
import pt.iscte.paddle.java.antlr.Java8Parser.IfThenElseStatementNoShortIfContext;
import pt.iscte.paddle.java.antlr.Java8Parser.IfThenStatementContext;
import pt.iscte.paddle.java.antlr.Java8Parser.InclusiveOrExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.LiteralContext;
import pt.iscte.paddle.java.antlr.Java8Parser.LocalVariableDeclarationContext;
import pt.iscte.paddle.java.antlr.Java8Parser.MethodDeclarationContext;
import pt.iscte.paddle.java.antlr.Java8Parser.MethodInvocationContext;
import pt.iscte.paddle.java.antlr.Java8Parser.MethodInvocation_lf_primaryContext;
import pt.iscte.paddle.java.antlr.Java8Parser.MethodInvocation_lfno_primaryContext;
import pt.iscte.paddle.java.antlr.Java8Parser.MultiplicativeExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.PostDecrementExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.PostDecrementExpression_lf_postfixExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.PostIncrementExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.PostIncrementExpression_lf_postfixExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.PostfixExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.PreDecrementExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.PreIncrementExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.PrimaryContext;
import pt.iscte.paddle.java.antlr.Java8Parser.PrimaryNoNewArray_lfno_arrayAccessContext;
import pt.iscte.paddle.java.antlr.Java8Parser.PrimaryNoNewArray_lfno_primaryContext;
import pt.iscte.paddle.java.antlr.Java8Parser.RelationalExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ReturnStatementContext;
import pt.iscte.paddle.java.antlr.Java8Parser.StatementExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.StatementNoShortIfContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ThrowStatementContext;
import pt.iscte.paddle.java.antlr.Java8Parser.UnaryExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.UnaryExpressionNotPlusMinusContext;
import pt.iscte.paddle.java.antlr.Java8Parser.VariableDeclaratorContext;
import pt.iscte.paddle.java.antlr.Java8Parser.VariableDeclaratorListContext;
import pt.iscte.paddle.java.antlr.Java8Parser.VariableInitializerContext;
import pt.iscte.paddle.java.antlr.Java8Parser.WhileStatementContext;
import pt.iscte.paddle.java.antlr.Java8ParserBaseListener;
import pt.iscte.paddle.model.IArrayType;
import pt.iscte.paddle.model.IBinaryOperator;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IBlockElement;
import pt.iscte.paddle.model.IConstantDeclaration;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ILiteral;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProcedure.UnboundProcedure;
import pt.iscte.paddle.model.IRecordFieldExpression;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IReferenceType;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IStatement;
import pt.iscte.paddle.model.ITargetExpression;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IUnaryOperator;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.util.MultiMapList;

class ParserListener extends Java8ParserBaseListener {
	private final IModule module;
	private IProcedure currentProcedure;
	Deque<IBlock> blockStack;
	Deque<IExpression> expressionStack;

	private final IRecordType classType;

	private final ParserAux aux;

	public ParserListener(IModule module, IRecordType classType, ParserAux aux) {
		assert module.getId() != null;
		this.module = module;
		this.classType = classType;
		this.aux = aux;
		blockStack = new ArrayDeque<>();
		expressionStack = new ArrayDeque<>();
	}

	@Override
	public void enterMethodDeclaration(MethodDeclarationContext ctx) {
		currentProcedure =  aux.getMethod(ctx, classType.getId());
		assert currentProcedure != null : ctx;
		blockStack.push(currentProcedure.getBody());
	}

	@Override
	public void exitMethodDeclaration(MethodDeclarationContext ctx) {
		blockStack.pop();
	}

	@Override
	public void enterConstructorDeclaration(ConstructorDeclarationContext ctx) {
		currentProcedure = aux.getConstructor(ctx, classType.getId());
		assert currentProcedure != null : ctx;
		IBlock body = currentProcedure.getBody();
		IVariableDeclaration thisVar = body.addVariable(classType, ParserAux.CONSTRUCTOR_FLAG);
		body.addAssignment(thisVar, classType.heapAllocation(), ParserAux.CONSTRUCTOR_FLAG);
		thisVar.setId(ParserAux.THIS);
		for (IVariableDeclaration f : classType.getFields()) {
			if(fieldInit.containsKey(f)) {
				IExpression exp = fieldInit.get(f); // TODO field init to pre-parsing?
				body.addRecordFieldAssignment(thisVar.field(f), exp);
			}
		}
		blockStack.push(body);
	}

	@Override
	public void exitConstructorDeclaration(ConstructorDeclarationContext ctx) {
		IBlock body = blockStack.pop();
		IVariableDeclaration thisVar = currentProcedure.getVariable(ParserAux.THIS);
		body.addReturn(thisVar.expression());
		body.setFlag(ParserAux.CONSTRUCTOR_FLAG);
	}
	
	private Map<IVariableDeclaration, IExpression> fieldInit = new HashMap<>();
	

	@Override
	public void exitReturnStatement(ReturnStatementContext ctx) {
		if(ctx.expression() != null && contains(ctx.expression(), AssignmentContext.class)) {
			aux.unsupported("assignments as expressions", ctx);
			blockStack.peek().addReturn();
			return;
		}

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
			IVariableDeclaration dec = blockStack.peek().addVariable(aux.matchType(con.unannType()));
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
			FieldDeclarationContext fieldDec = (FieldDeclarationContext) parent;
			IType type = aux.matchType(fieldDec.unannType());
			boolean constant = ParserAux.hasModifier(fieldDec, Keyword.STATIC) && ParserAux.hasModifier(fieldDec, Keyword.FINAL);
			if(!constant && ctx.variableInitializer() != null) {
				IVariableDeclaration field = classType.getField(varId);
				fieldInit.put(field, expressionStack.pop());
			}
		}

	}

	@Override
	public void exitAssignment(AssignmentContext ctx) {
//		System.out.println("ASS " + ctx.getText());
		IStatement statement = null;
		if(ctx.leftHandSide().expressionName() != null) {
			IVariableDeclaration var = matchVariable(ctx.leftHandSide().expressionName());
			if(var.isRecordField()) {
				blockStack.peek().addRecordFieldAssignment(currentProcedure.getVariable(ParserAux.THIS).field(var), expressionStack.pop());
			}
			else {
				if(ctx.assignmentOperator().ASSIGN() != null)
					blockStack.peek().addAssignment(var, expressionStack.pop());
				else {
					IBinaryOperator op = aux.matchBinaryOperator(ctx.assignmentOperator());
					statement = blockStack.peek().addAssignment(var, op.on(var, expressionStack.pop()));
				}
			}
		}
		else if(ctx.leftHandSide().arrayAccess() != null && ctx.assignmentOperator().ASSIGN() != null) {
			PrimaryNoNewArray_lfno_arrayAccessContext prim = ctx.leftHandSide().arrayAccess().primaryNoNewArray_lfno_arrayAccess();
			ITargetExpression target;
			if(prim != null && prim.getChild(0) instanceof FieldAccessContext) {
				FieldAccessContext fa = (FieldAccessContext) prim.getChild(0);
				String varId = fa.primary().getText();
				String fieldId = fa.Identifier().getText();
				IVariableDeclaration var = currentProcedure.getVariable(varId);
				if(var == null)
					var = new IVariableDeclaration.UnboundVariable("?" + varId);
				
				target = var.field(fieldId);
			}
			else
				target = matchVariable(ctx.leftHandSide().arrayAccess().expressionName()).expression(); // TODO may send null
			IExpression exp = expressionStack.pop();
			int dims = ctx.leftHandSide().arrayAccess().expression().size();
			// TODO this.?
			statement = blockStack.peek().addArrayElementAssignment(target, exp, getStackTop(dims));
		}
		else if(ctx.leftHandSide().fieldAccess() != null) {
			System.out.println(ctx.leftHandSide().fieldAccess().getText());
			FieldAccessContext f = ctx.leftHandSide().fieldAccess();
			IVariableDeclaration var = matchVariable(f.getChild(0));
			IExpression exp = var.expression();
			for(int i = 2; i < f.getChildCount(); i += 2)
				exp = exp.field(f.getChild(i).getText());

			statement = blockStack.peek().addRecordFieldAssignment((IRecordFieldExpression) exp, expressionStack.pop());
		}
		else
			aux.unsupported("asssignment", ctx);

		if(statement != null && containedIn(ctx, BasicForStatementContext.class))
			statement.setFlag(Keyword.FOR.name());
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
		aux.unsupported("if-else", ctx);
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
			IVariableDeclaration var = currentProcedure.getVariable(targetVarId); // local
			if(var == null) { // field
				for(IVariableDeclaration f : classType.getFields())
					if(f.getId().equals(targetVarId)) {
						var = f;
						break;
					}
			}	
			if(var != null) {
				args.add(var.expression());
				p = aux.getMethod(var.getType().getId(), methodId);
			}

			if(var == null) { // namespace
				for(IProcedure pr : module.getProcedures())
					if(targetVarId.equals(pr.getProperty("namespace")) && pr.getId().equals(methodId)) {
						p = pr;
						break;
					}	
			}

			if(var == null) {
				var = new IVariableDeclaration.UnboundVariable(targetVarId);
			}
		}
		else {
			p = matchInstanceProcedure(methodId, classType, argList);
			if(p != null)
				args.add(currentProcedure.getVariable(Keyword.THIS.keyword()).expression());
			else
				p = matchStaticProcedure(methodId, argList);
		}

		if(p == null)
			p = new IProcedure.UnboundProcedure("??" + methodId);

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
		IVariableDeclaration v = currentProcedure.getVariable(id);
		IType t = v == null ? null : v.getType();
		return t instanceof IRecordType ? (IRecordType) t : null;
	}




	@Override
	public void enterExpressionName(ExpressionNameContext ctx) {
		//		System.out.println("EXP " + ctx.getText() + "  " + ctx.getParent().getClass());
		if(ctx.getParent() instanceof PostfixExpressionContext) {
			IConstantDeclaration con = matchConstant(ctx.getChild(0));
			if(con != null) {
				expressionStack.push(con.expression());
			}
			else {
				IVariableDeclaration var = matchVariable(ctx.getChild(0));
				if(var.getType() instanceof IArrayType && ctx.getChildCount() == 3 && ctx.getChild(2).getText().equals("length")) {
					expressionStack.push(var.length());
				}
				else {
					IExpression exp = var.isRecordField() ? currentProcedure.getVariable(ParserAux.THIS).field(var) : var.expression();
					for(int i = 2; i < ctx.getChildCount(); i += 2) {
						exp = exp.field(ctx.getChild(i).getText());
					}
					expressionStack.push(exp);
				}
			}
		}
		//		else
		//			System.out.println("exp " + ctx.getText() + " " + ctx.getParent().getText() + "   " + ctx.start.getLine() +  "   " + ctx.getParent().getClass());

	}


//	@Override
//	public void enterFieldAccess_lf_primary(FieldAccess_lf_primaryContext ctx) {
//		System.out.println("fiel ass primary" + ctx.getText() + " " + ctx.getParent().getClass());
//	}
//
//	@Override
//	public void enterFieldAccess_lfno_primary(FieldAccess_lfno_primaryContext ctx) {
//		System.out.println("fiel ass lfno" + ctx.getText() + " " + ctx.getParent().getClass());
//	}
//
//	@Override
//	public void enterFieldAccess(FieldAccessContext ctx) {
//		System.out.println("fiel ass " + ctx.getText() + " " + ctx.getParent().getClass());
//	}

	@Override
	public void exitArrayAccess_lfno_primary(ArrayAccess_lfno_primaryContext ctx) {
		// TODO array access call target
		// tmp
		if(ctx.expressionName() == null)
			System.err.println("problem array access: " + ctx.getText());
		IExpression[] indexes = getStackTop(ctx.expression().size());
		IVariableDeclaration var = matchVariable(ctx.expressionName());
		if(var.isRecordField())
			expressionStack.push(currentProcedure.getVariable(ParserAux.THIS).field(var).element(indexes));
		else
			expressionStack.push(var.element(indexes));
	}

	private IExpression[] getStackTop(int n) {
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
		else if(ctx.BooleanLiteral() != null)
			expressionStack.push(IType.BOOLEAN.literal(Boolean.parseBoolean(ctx.getText())));
		else if(ctx.StringLiteral() != null)
			expressionStack.push(module.getRecordType("String").heapAllocation());
		else if(ctx.NullLiteral() != null)
			expressionStack.push(ILiteral.getNull());
		else
			aux.unsupported("literal", ctx);
	}



	@Override
	public void exitArrayCreationExpression(ArrayCreationExpressionContext ctx) {
		IType t = null;
		if(ctx.primitiveType() != null)
			t = aux.matchPrimitiveType(ctx.primitiveType());
		else if(ctx.classOrInterfaceType() != null)
			t = aux.matchRecordType(ctx.classOrInterfaceType());


		if(ctx.arrayInitializer() != null) {
			int elements = ctx.arrayInitializer().variableInitializerList().variableInitializer().size();
			expressionStack.push(t.array().heapAllocationWith(getStackTop(elements)));
		}
		else {
			int dims = ctx.dimExprs().dimExpr().size();
			expressionStack.push(t.array().heapAllocation(getStackTop(dims)));

		}
	}

	@Override
	public void exitClassInstanceCreationExpression_lfno_primary(
			ClassInstanceCreationExpression_lfno_primaryContext ctx) {
		IType t = aux.matchRecordType(ctx.Identifier().get(0));
		if(t instanceof IReferenceType && (((IReferenceType) t).getTarget() instanceof IRecordType)) {
			int size = ctx.argumentList() == null ? 0 : ctx.argumentList().expression().size();
			if(size == 0) {
				expressionStack.push(((IRecordType)((IReferenceType)t).getTarget()).heapAllocation());
			}
			else {

				IProcedure c = aux.getConstructor(t, size); // TODO arg types
				if(c == null)
					c = new IProcedure.UnboundProcedure("?" + t.getId()); 
				expressionStack.push(c.expression(getStackTop(size)));
			}
		}
		else
			aux.unsupported("class type", ctx);
	}

	@Override
	public void exitClassInstanceCreationExpression(ClassInstanceCreationExpressionContext ctx) {
		//		IType t = aux.matchRecordType(ctx.expressionName());
		//		expressionStack.push(t.heapAllocation());
		System.err.println("class instance " + ctx.getText());
	}

	//	@Override
	//	public void exitElementValueArrayInitializer(ElementValueArrayInitializerContext ctx) {
	//		int size = ctx.elementValueList().elementValue().size();
	//		aux.unsupported("array init " + size, ctx);
	//	}

	//	@Override
	//	public void exitArrayInitializer(ArrayInitializerContext ctx) {
	//		expressionStack.push(IType.INT.array().heapAllocation(dimensions))
	//		aux.unsupported("array init " + ctx.getParent().getClass(), ctx);
	//		for (VariableInitializerContext i : ctx.variableInitializerList().variableInitializer()) {
	//			System.err.println(i.getText());
	//		}
	//		expressionStack.push(e);
	// pop unused
	//		expressionStack.push(ILiteral.getNull());
	//	}

	@Override
	public void exitMethodInvocation_lf_primary(MethodInvocation_lf_primaryContext ctx) {
//		System.out.println("MP " + prim.getText());
		PrimaryContext prim = (PrimaryContext) ctx.getParent().getParent();
		PrimaryNoNewArray_lfno_primaryContext c = prim.primaryNoNewArray_lfno_primary();
		String fieldId = c.getText();
		String methodId = ctx.Identifier().getText();
		handleMethodCall(fieldId, methodId, ctx.argumentList(), false);
	}
	
	@Override
	public void exitMethodInvocation_lfno_primary(MethodInvocation_lfno_primaryContext ctx) {
//		System.out.println("M " + ctx.getText());
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


	// UPSUPPORTED ---------------------------------

	@Override
	public void exitThrowStatement(ThrowStatementContext ctx) {
		aux.unsupported("throws", ctx);
		expressionStack.pop(); // exception object
		blockStack.peek().addReturn();
	}

	@Override
	public void exitCastExpression(CastExpressionContext ctx) {
		//		aux.unsupported("cast", ctx);
		expressionStack.push(IUnaryOperator.TRUNCATE.on(expressionStack.pop()));
	}

	// OPERATORS ---------------------------------

	@Override
	public void exitUnaryExpression(UnaryExpressionContext ctx) {
		if(ctx.SUB() != null)
			expressionStack.push(IUnaryOperator.MINUS.on(expressionStack.pop()));
	}

	@Override
	public void exitUnaryExpressionNotPlusMinus(UnaryExpressionNotPlusMinusContext ctx) {
		if(ctx.BANG() != null)
			expressionStack.push(IUnaryOperator.NOT.on(expressionStack.pop()));
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
	public void exitAndExpression(AndExpressionContext ctx) {
		pushBinaryOperation(ctx.BITAND());
	}

	@Override
	public void exitConditionalAndExpression(ConditionalAndExpressionContext ctx) {
		pushBinaryOperation(ctx.AND());
	}

	@Override
	public void exitInclusiveOrExpression(InclusiveOrExpressionContext ctx) {
		pushBinaryOperation(ctx.BITOR());
	}

	@Override
	public void exitConditionalOrExpression(ConditionalOrExpressionContext ctx) {
		pushBinaryOperation(ctx.OR());
	}

	@Override
	public void exitExclusiveOrExpression(ExclusiveOrExpressionContext ctx) {
		pushBinaryOperation(ctx.CARET());
	}



	// INCREMENTS / DECREMENTS -----------------------------------------------

	@Override
	public void enterPostIncrementExpression(PostIncrementExpressionContext ctx) {
		IVariableDeclaration var = matchVariable(ctx.postfixExpression()); // TODO array[]++
		if(var.isRecordField()) {
			IRecordFieldExpression fexp = currentProcedure.getVariable(ParserAux.THIS).field(var);
			IExpression acc = IBinaryOperator.ADD.on(fexp, IType.INT.literal(1));
			blockStack.peek().addRecordFieldAssignment(fexp, acc);
		}
		else
			blockStack.peek().addIncrement(var);
	}

	@Override
	public void enterPreIncrementExpression(PreIncrementExpressionContext ctx) {
		IVariableDeclaration var = matchVariable(ctx.unaryExpression()); // TODO ++array
		blockStack.peek().addIncrement(var);
	}

	@Override
	public void enterPostDecrementExpression(PostDecrementExpressionContext ctx) {
		IVariableDeclaration var = matchVariable(ctx.postfixExpression()); // TODO array[]++
		blockStack.peek().addDecrement(var);
	}

	@Override
	public void enterPreDecrementExpression(PreDecrementExpressionContext ctx) {
		if(ctx.getParent() instanceof StatementExpressionContext) {
			IVariableDeclaration var = matchVariable(ctx.unaryExpression()); // TODO ++array
			blockStack.peek().addDecrement(var);
		}
		else 
			aux.unsupported("decrement", ctx);
	}

	@Override
	public void enterPostIncrementExpression_lf_postfixExpression(
			PostIncrementExpression_lf_postfixExpressionContext ctx) {
		aux.unsupported("increment", ctx);
	}

	@Override
	public void enterPostDecrementExpression_lf_postfixExpression(
			PostDecrementExpression_lf_postfixExpressionContext ctx) {
		aux.unsupported("decrement", ctx);
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

	private boolean contains(RuleContext ctx, Class<?> t) {
		RuleContext parent = ctx.getParent();
		for(int i = 0; i < ctx.getChildCount(); i++) {
			ParseTree child = ctx.getChild(i);
			if(child.getClass().equals(t))
				return true;
			else if(child instanceof RuleContext && contains((RuleContext) child, t))
				return true;
		}
		return false;
	}

	private void pushBinaryOperation(TerminalNode ... nodes) {
		for(TerminalNode node : nodes) {
			if(node != null) {
				IBinaryOperator op = aux.matchBinaryOperator(node);
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
		for (IProcedure p : aux.allMethods()) {
			if(p.is(ParserAux.INSTANCE_FLAG) && id.equals(p.getId()) && p.getParameters().size() == n) // TODO arg types
				proc = p;
		}

		return proc;
	}

	private IProcedure matchStaticProcedure(String id, ArgumentListContext args) {
		int n = args == null ? 0 : args.getChildCount();
		if(n > 1)
			n = n - (n-1)/2;

		IProcedure proc = null;
		for (IProcedure p : aux.allMethods()) {
			if(!p.is(ParserAux.INSTANCE_FLAG) && p.getId().equals(id) && p.getParameters().size() == n) // TODO arg types
				proc = p;
		}

		return proc;
	}

	private IVariableDeclaration matchVariable(ParseTree ctx) {
		String id = ctx.getText();
		for(IVariableDeclaration v : currentProcedure.getVariables())
			if(v.getId().equals(id))
				return v;

		if(currentProcedure.is(ParserAux.INSTANCE_FLAG) || currentProcedure.is(ParserAux.CONSTRUCTOR_FLAG)) {
			for(IVariableDeclaration v : classType)
				if(v.getId().equals(id))
					return v;
		}

		Token t = ctx instanceof TerminalNode ? ((TerminalNode) ctx).getSymbol() : ((ParserRuleContext) ctx).getStart();

		System.err.println("unbound " + id + "  " + ctx.getClass() + "  " + classType.getId() + "  " + t.getLine());
		return new IVariableDeclaration.UnboundVariable("?" + id);
	}

	private IConstantDeclaration matchConstant(ParseTree ctx) {
		String id = classType.getId() + "." + ctx.getText();
		for(IConstantDeclaration c : module.getConstants())
			if(c.getId().equals(id))
				return c;

		return null;
	}
}
