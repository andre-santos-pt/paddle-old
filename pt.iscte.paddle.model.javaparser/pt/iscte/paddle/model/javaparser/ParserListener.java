package pt.iscte.paddle.model.javaparser;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import pt.iscte.paddle.model.IArrayElement;
import pt.iscte.paddle.model.IArrayType;
import pt.iscte.paddle.model.IBinaryOperator;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IBlockElement;
import pt.iscte.paddle.model.IConstantDeclaration;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ILiteral;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IOperator;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IRecordFieldExpression;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IReferenceType;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.ITargetExpression;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.IVariableExpression;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.ArgumentsContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.ConstructorDeclarationContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.EnhancedForControlContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.ExpressionContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.ForControlContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.LiteralContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.LocalVariableDeclarationContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.MethodCallContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.MethodDeclarationContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.ParExpressionContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.StatementContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.VariableDeclaratorContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParserBaseListener;

class ParserListener extends JavaParserBaseListener {
	private final IModule module;
	private IProcedure currentProcedure;
	Deque<IBlock> blockStack;
	Deque<IExpression> expStack;

	private final IRecordType classType;
	private final File file;
	
	private Map<IVariableDeclaration, IExpression> fieldInit = new HashMap<>();

	private final ParserAux aux;

	public ParserListener(IModule module, IRecordType classType, File file, ParserAux aux) {
		assert module.getId() != null;
		this.module = module;
		this.classType = classType;
		this.aux = aux;
		blockStack = new ArrayDeque<>();
		expStack = new ArrayDeque<>();
		this.file = file;
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
		IVariableDeclaration thisVar = body.addVariable(classType.reference(), ParserAux.CONSTRUCTOR_FLAG);
		body.addAssignment(thisVar, classType.heapAllocation(), ParserAux.CONSTRUCTOR_FLAG);
		thisVar.setId(ParserAux.THIS_VAR);
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
		IVariableDeclaration thisVar = currentProcedure.getVariable(ParserAux.THIS_VAR);
		IReturn ret = body.addReturn(thisVar.expression());
		ret.setFlag(ParserAux.CONSTRUCTOR_FLAG);
	}

	private IType handleRightBrackets(IType type, String varAndBrackets) {
		IType t = type;
		while(varAndBrackets.endsWith("[]")) {
			varAndBrackets = varAndBrackets.substring(0, varAndBrackets.length()-2);
			t = t.array();
		}
		if(t != type)
			t = t.reference();
		return t;
	}
	
	@Override
	public void exitLocalVariableDeclaration(LocalVariableDeclarationContext ctx) {
		IType type = aux.matchType(ctx.typeType());
		int n = 0;
		for (VariableDeclaratorContext v : ctx.variableDeclarators().variableDeclarator())
			if(v.variableInitializer() != null) {
				IType t = handleRightBrackets(type, v.variableDeclaratorId().getText());
				n++;
				if(v.variableInitializer().arrayInitializer() != null) {
					int len = v.variableInitializer().arrayInitializer().variableInitializer().size();
					IArrayType at = (IArrayType) ((IReferenceType) t).getTarget();
					expStack.push(at.heapAllocationWith(getStackTop(len)));
				}
			}
		
		List<IExpression> stackTop = getStackTop(n);
		
		AtomicInteger i = new AtomicInteger(0);
		for (VariableDeclaratorContext v : ctx.variableDeclarators().variableDeclarator()) {
			IType t = handleRightBrackets(type, v.variableDeclaratorId().getText());
			IVariableDeclaration var = addStatement(b -> b.addVariable(t), ctx);
			var.setId(v.variableDeclaratorId().IDENTIFIER().getText());
			if(v.variableInitializer() != null) {
				addStatement(b -> {
					IVariableAssignment ass = b.addAssignment(var, stackTop.get(i.getAndAdd(1)));
					ass.setFlag(ParserAux.INITIALIZER_FLAG);
					return ass;
				}, ctx);
			
			}
		}
	}

	@Override
	public void exitParExpression(ParExpressionContext ctx) {
		StatementContext stat = (StatementContext) ctx.getParent();
		if(stat.IF() != null) {
			ISelection sel;
			if(stat.ELSE() == null)
				sel = addStatement(b -> b.addSelection(expStack.pop()), ctx);
			else
				sel = addStatement(b -> b.addSelectionWithAlternative(expStack.pop()), ctx);
			blockStack.push(sel.getBlock());
		}
		else if(stat.WHILE() != null && stat.DO() == null) {
			ILoop loop = addStatement(b -> b.addLoop(expStack.pop()), ctx);
			blockStack.push(loop.getBlock());
		}
	}

	@Override
	public void enterForControl(ForControlContext ctx) {
		// create block
		IBlock forBlock = blockStack.peek().addBlock();
		forBlock.setFlag(ParserAux.FOR_FLAG);
		if(ctx.enhancedForControl() != null)
			forBlock.setFlag(ParserAux.EFOR_FLAG);
		blockStack.push(forBlock);
	}

	@Override
	public void exitForControl(ForControlContext ctx) {
		// TODO check guard
		for(IBlockElement e : blockStack.peek())
			e.setFlag(ParserAux.FOR_FLAG);
	}
	


	static boolean is(Token t, String ... tokens) {
		if(t == null)
			return false;

		for(String s : tokens)
			if(t.getText().equals(s))
				return true;

		return false;
	}

	private <T extends IBlockElement> T addStatement(Function<IBlock, T> c, ParserRuleContext ctx) {
		T s = c.apply(blockStack.peek());
		s.setProperty(SourceLocation.class, new SourceLocation(file, ctx.getStart().getLine()));
		return s;
	}
	
	@Override
	public void enterStatement(StatementContext ctx) {
		if(ctx.DO() != null) {
			blockStack.push(blockStack.peek().addBlock());
			aux.unsupported("do-while", ctx);
		}
	}
	
	@Override
	public void exitStatement(StatementContext ctx) {
		if(ctx.RETURN() != null) {
			if(ctx.expression().isEmpty())
				addStatement(b -> b.addReturn(), ctx);
			else 
				addStatement(b -> b.addReturn(expStack.pop()), ctx);
		}
		
		else if(ctx.THROW() != null)
			addStatement(b -> b.addReturnError(expStack.pop()), ctx);
		
		else if(ctx.BREAK() != null)
			addStatement(b -> b.addBreak(), ctx);
		
		else if(ctx.CONTINUE() != null)
			addStatement(b -> b.addContinue(), ctx);
			
		if(ctx.getParent() instanceof StatementContext) {
			IBlock b = blockStack.pop();

			if(((StatementContext) ctx.getParent()).IF() != null) {
				ISelection sel = (ISelection) b.getParent();
				if(sel.hasAlternativeBlock() && ((StatementContext) ctx.getParent()).statement(0) == ctx)
					blockStack.push(sel.getAlternativeBlock());
			}

			if(b.getParent().is(ParserAux.FOR_FLAG) && !b.isEmpty()) {
				int iFirst = 0;
				for(IBlockElement e : b)
					if(e.is(ParserAux.EFOR_FLAG))
						iFirst++;
					else
						break;
				
				int n = 0;
				for(IBlockElement e : b)
					if(e.is(ParserAux.FOR_FLAG))
						n++;
				
				while(n-- > 0 && !b.getLast().is(ParserAux.FOR_FLAG))
					b.moveAfter(b.getChildren().get(iFirst), b.getLast());
				
				blockStack.pop();
			}
		}
	}

	static boolean not(ParserRuleContext ctx, Class<? extends ParserRuleContext> c) {
		return !c.isInstance(ctx);
	}

	static boolean child(ParserRuleContext ctx, Class<? extends ParserRuleContext> c) {
		return c.isInstance(ctx.getParent());
	}

	private List<IExpression> getStackTop(int n) {
		List<IExpression> expressions = new ArrayList<>(n);
		while(n-- > 0)
			expressions.add(0, expStack.pop());
		return expressions;
	}


	private IVariableDeclaration findVariable(String id) {
		// local
		if(currentProcedure != null) {
			for(IVariableDeclaration v : currentProcedure.getVariables())
				if(v.getId().equals(id))
					return v;

			// fields
			if(currentProcedure.is(ParserAux.INSTANCE_FLAG) || currentProcedure.is(ParserAux.CONSTRUCTOR_FLAG)) {
				for(IVariableDeclaration v : classType)
					if(v.getId().equals(id))
						return v;
			}
		}
		return null;
	}

	private IConstantDeclaration findConstant(String namespace, String id) {
		for(IConstantDeclaration c : module.getConstants())
			if(c.getNamespace().equals(namespace) && c.getId().equals(id))
				return c;

		return null;
	}

	@Override
	public void exitExpression(ExpressionContext ctx) {
//		System.out.println("E " + ctx.getText());
		if(ctx.primary() != null ) {
			if(ctx.primary().THIS() != null) {
				IVariableDeclaration thisVar = findVariable(ParserAux.THIS_VAR);
				if(thisVar == null)
					System.err.println("no this param exists in " + currentProcedure.getId());
				expStack.push(thisVar.expression());
			}
			else if(ctx.primary().IDENTIFIER() != null) {
				String id = ctx.primary().IDENTIFIER().getText();
				IVariableDeclaration var = findVariable(id);
				if(var != null) {
					if(var.isRecordField()) {
						IVariableDeclaration thisVar = findVariable(ParserAux.THIS_VAR);
						expStack.push(thisVar.field(var));
					}
					else
						expStack.push(var.expression());
				}
				else if(currentProcedure != null) {
					IConstantDeclaration con = findConstant(currentProcedure.getId(), id);
					if(con != null)
						expStack.push(con.expression());
					else {
						System.err.println("unbound var " + id + "  " + classType.getId());
						expStack.push(new IVariableDeclaration.UnboundVariable(id).expression());
					}
					// TODO constants in other namespaces
				}
			}
			else if(ctx.primary().literal() != null)
				expStack.push(matchLiteral(ctx.primary().literal()));


			// TODO resolve this / implicit
		}

		else if(ctx.bop != null && ctx.bop.getText().equals(".")) {
			if(ctx.IDENTIFIER() != null) {
				IExpression left = expStack.pop();
				String fieldId = ctx.IDENTIFIER().getText();
				if(fieldId.equals("length") && 
						(left.getType().isArrayReference() || left instanceof IArrayElement)) {
//						left instanceof IVariableExpression &&
//						((IVariableExpression) left).getVariable().getType().isArrayReference()) {
					expStack.push(left.length());
				}
				else if(left.getType().isRecordReference()){
					IVariableDeclaration field = ((IRecordType)((IReferenceType) left.getType()).getTarget()).getField(fieldId);
					expStack.push(left.field(field));
				}
				else
					expStack.push(ILiteral.getNull());
			}
			else if(ctx.methodCall() != null)
				handleMethodCall(ctx.methodCall(), true);
			else
				aux.unsupported("dot access", ctx);
			//			System.out.println("DOT " + ctx.getText() + "  " + expStack.peek());
		}

		else if(ctx.methodCall() != null) {
			handleMethodCall(ctx.methodCall(), false);
		}

		else if(ctx.LBRACK() != null) {
			IExpression index = expStack.pop();
			IArrayElement arrEl = expStack.pop().element(index);
			expStack.push(arrEl);
		}

		else if(is(ctx.bop, "=")) { 
			if(ctx.getParent() instanceof StatementContext &&
					((StatementContext) ctx.getParent()).statementExpression == null) {
				aux.unsupported("assignments as expressions", ctx);
				expStack.push(ILiteral.getNull());
			}
			else {
				IExpression right = expStack.pop();
				IExpression left = expStack.pop();
				if(left instanceof IVariableExpression) {
					IVariableDeclaration var = ((IVariableExpression) left).getVariable();
					if(var.isLocalVariable())
						addStatement(b -> b.addAssignment(var, right), ctx);
					else {
						assert currentProcedure.is(ParserAux.INSTANCE_FLAG);
						IVariableDeclaration thisVar = currentProcedure.getVariable(ParserAux.THIS_VAR);
						addStatement(b -> b.addRecordFieldAssignment(thisVar.field(var), right), ctx);
					}
				}
				else if(left instanceof IArrayElement) {
					IArrayElement e = (IArrayElement) left;
					ITargetExpression t = e.getTarget();
					List<IExpression> indexes = new ArrayList<IExpression>(e.getIndexes());
					while(t instanceof IArrayElement) {
						indexes.add(0, ((IArrayElement) t).getIndexes().get(0));
						t = ((IArrayElement)t).getTarget();
					}
					ITargetExpression tt = t;
					addStatement(b -> b.addArrayElementAssignment(tt, right, indexes), ctx);
				}
				else if(left instanceof IRecordFieldExpression) {
					addStatement(b -> b.addRecordFieldAssignment((IRecordFieldExpression)left, right), ctx);
				}
			}
		}

		else if(is(ctx.bop, "+=", "-=", "*=", "/=", "%=")) {
			IBinaryOperator op = aux.matchBinaryOperator(ctx.bop);
			IExpression exp = expStack.pop();
			IExpression left = expStack.pop();
			if(left instanceof IVariableExpression) {
				IExpression expp = op.on((IVariableExpression) left, exp);
 				addStatement(b -> b.addAssignment(((IVariableExpression) left).getVariable(), expp), ctx);
			}
			else 
				aux.unsupported("+= on array", ctx);
		}

		else if(is(ctx.postfix, "++") || is(ctx.prefix, "++")) {
			if(child(ctx, ExpressionContext.class))
				aux.unsupported("incrementor as expression", ctx);
			else {	
				IExpression left = expStack.pop();
				if(left instanceof IVariableExpression)
					addStatement(b -> b.addIncrement(((IVariableExpression) left).getVariable()), ctx);
				else
					aux.unsupported("++ on array", ctx);
			}
		}

		else if(is(ctx.postfix, "--") || is(ctx.prefix, "--")) {
			if(child(ctx, ExpressionContext.class))
				aux.unsupported("decrementor as expression", ctx);
			else {
				IExpression left = expStack.pop();
				if(left instanceof IVariableExpression)
					addStatement(b -> b.addDecrement(((IVariableExpression) left).getVariable()), ctx);
				else
					aux.unsupported("-- on array", ctx);
			}
		}

		else if(ctx.NEW() != null) {
			IType type;

			if(ctx.creator().createdName().IDENTIFIER().isEmpty())
				type = aux.matchPrimitiveType(ctx.creator().createdName().primitiveType());
			else
				type = aux.matchRecordType(ctx.creator().createdName().IDENTIFIER().get(0).getText());

			if(ctx.creator().arrayCreatorRest() != null) {
				if(ctx.creator().arrayCreatorRest().arrayInitializer() != null) {
					int n = ctx.creator().arrayCreatorRest().arrayInitializer().variableInitializer().size();
					expStack.push(type.array().heapAllocationWith(getStackTop(n)));
				}
				else {
					int n = ctx.creator().arrayCreatorRest().expression().size();
					expStack.push(type.array().heapAllocation(getStackTop(n)));
				}
			}
			else {
				ArgumentsContext arguments = ctx.creator().classCreatorRest().arguments();
				int n = arguments.expressionList() == null ? 0 : arguments.expressionList().expression().size();
				IProcedure constructor = aux.getConstructor(type, n);
				if(constructor == null)
					constructor = IProcedure.createUnbound(type.getId());
				expStack.push(constructor.expression(getStackTop(n)));
			}
		}

		
		else if(ctx.typeType() != null && ctx.typeType().getText().equals(int.class.getName())) { // cast
			expStack.push(IOperator.TRUNCATE.on(expStack.pop()));
		}

		else if(is(ctx.bop, "?")) {
			aux.unsupported("conditional expression", ctx);
		}

		else if(ctx.bop != null) {
			IBinaryOperator op = aux.matchBinaryOperator(ctx.bop);
			if(op != null)
				pushBinaryOperation(op);
		}

		
		if(ctx.getParent() instanceof ForControlContext) {
			ILoop loop = blockStack.peek().addLoop(expStack.pop(), ParserAux.FOR_FLAG);
			blockStack.push(loop.getBlock());
		}
		else if (ctx.getParent() instanceof EnhancedForControlContext) {
			handleEnhancedFor((EnhancedForControlContext) ctx.getParent());
		}
	}

	private void handleEnhancedFor(EnhancedForControlContext enFon) {
		IBlock forBlock = blockStack.peek();
		int depth = forBlock.getDepth();
		
		IVariableDeclaration itVar = forBlock.addVariable(IType.INT);
		itVar.setId("$it" + depth);
		itVar.setFlag(ParserAux.EFOR_FLAG);
		IVariableAssignment itInit = forBlock.addAssignment(itVar, IType.INT.literal(0));
		itInit.setFlag(ParserAux.EFOR_FLAG);
		
		IExpression srcExp = expStack.pop();
		IVariableDeclaration arrayVar = forBlock.addVariable(srcExp.getType());
		arrayVar.setId("$src" + depth);
		arrayVar.setFlag(ParserAux.EFOR_FLAG);
		IVariableAssignment srcAss = forBlock.addAssignment(arrayVar, srcExp);
		srcAss.setFlag(ParserAux.EFOR_FLAG);

		IType t = aux.matchType(enFon.typeType());
		IVariableDeclaration var = addStatement(b -> b.addVariable(t), enFon.variableDeclaratorId());
		String id = enFon.variableDeclaratorId().IDENTIFIER().getText();
		var.setId(id);
		
		IExpression guard = IBinaryOperator.SMALLER.on(itVar, arrayVar.length());
		ILoop loop = forBlock.addLoop(guard, ParserAux.FOR_FLAG);
		blockStack.push(loop.getBlock());
		
		IVariableAssignment itAss = loop.addAssignment(var, arrayVar.element(itVar));
		itAss.setFlag(ParserAux.EFOR_FLAG);
		
		IVariableAssignment inc = loop.addIncrement(itVar);
		inc.setFlag(ParserAux.FOR_FLAG);
	}



	private void handleMethodCall(MethodCallContext ctx, boolean dotCall) {
		String methodId = ctx.IDENTIFIER() != null ? ctx.IDENTIFIER().getText() : null;
		int n = ctx.expressionList() == null ? 0 : ctx.expressionList().expression().size();
		
		List<IExpression> args = dotCall ? getStackTop(n+1) : getStackTop(n);

		IProcedure p = null;
		if(ctx.SUPER() != null) 
			aux.unsupported("super call", ctx);
		else if(ctx.THIS() != null)
			p = aux.getConstructor(classType, n);
		else {
			if(dotCall) {
				if(args.get(0) instanceof IVariableExpression &&
					((IVariableExpression) args.get(0)).isUnbound()) {
					String namespace = args.remove(0).getId();
					p = aux.getMethod(namespace, methodId);
					if(p == null)
						p = IProcedure.createUnbound(namespace, methodId);
				}
				else {
					String namespace = args.get(0).getType().getId();
					p = aux.getMethod(namespace, methodId);
				}
			}
			else {
				p = aux.getMethod(classType.getId(), methodId);
			}
		}
		
		if(p == null) {
			p = IProcedure.createUnbound(methodId);
			System.err.println("unbound loose proc+: " + methodId);
		}
		
		if(!dotCall &&  p.is(ParserAux.INSTANCE_FLAG)) {
			IVariableDeclaration thisVar = currentProcedure.getVariable(ParserAux.THIS_VAR);
			IExpression exp = thisVar == null ? ILiteral.getNull() : thisVar.expression();
			args.add(0, exp);
		}

		IProcedure pp = p;
		if(ctx.getParent().getParent() instanceof StatementContext &&
				((StatementContext) ctx.getParent().getParent()).statementExpression != null)
			addStatement(b -> b.addCall(pp, args), ctx);
		else
			expStack.push(p.expression(args));
	}
	

	private void pushBinaryOperation(IBinaryOperator op) {
		IExpression r = expStack.pop();
		IExpression l = expStack.pop();
		expStack.push(op.on(l, r));
	}

	private IExpression matchLiteral(LiteralContext ctx) {
		if(ctx.integerLiteral() != null)
			return IType.INT.literal(Integer.parseInt(ctx.integerLiteral().getText()));
		if(ctx.floatLiteral() != null)
			return IType.DOUBLE.literal(Double.parseDouble(ctx.floatLiteral().getText()));
		if(ctx.BOOL_LITERAL() != null)
			return IType.BOOLEAN.literal(Boolean.parseBoolean(ctx.BOOL_LITERAL().getText()));
		if(ctx.CHAR_LITERAL() != null)
			return IType.CHAR.literal(Character.valueOf(ctx.CHAR_LITERAL().getText().charAt(1)));
		if(ctx.NULL_LITERAL() != null)
			return ILiteral.getNull();
		if(ctx.STRING_LITERAL() != null) {
			IExpression e = ((IRecordType) ((IReferenceType) aux.matchRecordType(String.class.getName())).getTarget()).heapAllocation(); 
			int len = ctx.STRING_LITERAL().getText().length();
			String content = ctx.STRING_LITERAL().getText().substring(1, len-1);
			e.setProperty(String.class, content);
			return e;
		}
		aux.unsupported("literal", ctx);
		return null;
	}


}
