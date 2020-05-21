package pt.iscte.paddle.model.javaparser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import pt.iscte.paddle.model.IArrayElement;
import pt.iscte.paddle.model.IBinaryOperator;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IBlockElement;
import pt.iscte.paddle.model.IConstantDeclaration;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ILiteral;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IRecordFieldExpression;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IReferenceType;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.ITargetExpression;
import pt.iscte.paddle.model.IType;
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

	private Map<IVariableDeclaration, IExpression> fieldInit = new HashMap<>();

	private final ParserAux aux;

	public ParserListener(IModule module, IRecordType classType, ParserAux aux) {
		assert module.getId() != null;
		this.module = module;
		this.classType = classType;
		this.aux = aux;
		blockStack = new ArrayDeque<>();
		expStack = new ArrayDeque<>();
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

	@Override
	public void exitLocalVariableDeclaration(LocalVariableDeclarationContext ctx) {
		IType type = aux.matchType(ctx.typeType());
		int n = 0;
		for (VariableDeclaratorContext v : ctx.variableDeclarators().variableDeclarator())
			if(v.variableInitializer() != null)
				n++;
		List<IExpression> stackTop = getStackTop(n);
		int i = 0;
		for (VariableDeclaratorContext v : ctx.variableDeclarators().variableDeclarator()) {
			IVariableDeclaration var = blockStack.peek().addVariable(type);
			var.setId(v.variableDeclaratorId().IDENTIFIER().getText());
			if(v.variableInitializer() != null)
				blockStack.peek().addAssignment(var, stackTop.get(i++));
		}
	}

	@Override
	public void exitParExpression(ParExpressionContext ctx) {
		StatementContext stat = (StatementContext) ctx.getParent();
		if(stat.IF() != null) {
			ISelection sel;
			if(stat.ELSE() == null) {
				sel = blockStack.peek().addSelection(expStack.pop());
			}
			else {
				sel = blockStack.peek().addSelectionWithAlternative(expStack.pop());
			}
			blockStack.push(sel.getBlock());
		}
		else if(stat.WHILE() != null) {
			ILoop loop = blockStack.peek().addLoop(expStack.pop());
			blockStack.push(loop.getBlock());
		}
	}

	@Override
	public void enterForControl(ForControlContext ctx) {
		// create block
		IBlock forBlock = blockStack.peek().addBlock();
		forBlock.setFlag(ParserAux.FOR_FLAG);
		blockStack.push(forBlock);
	}

	@Override
	public void exitForControl(ForControlContext ctx) {
		// TODO check guard
		for(IBlockElement e : blockStack.peek())
			e.setFlag(ParserAux.FOR_FLAG);
		//ILoop loop = blockStack.peek().addLoop(expStack.pop());
		//		blockStack.pop();
	}

	@Override
	public void exitEnhancedForControl(EnhancedForControlContext ctx) {
		aux.unsupported("enhanced for", ctx);
	}

	static boolean is(Token t, String ... tokens) {
		if(t == null)
			return false;

		for(String s : tokens)
			if(t.getText().equals(s))
				return true;

		return false;
	}

	@Override
	public void exitStatement(StatementContext ctx) {
		if(ctx.RETURN() != null) {
			if(ctx.expression().isEmpty())
				blockStack.peek().addReturn();
			else 
				blockStack.peek().addReturn(expStack.pop());
		}
		else if(ctx.THROW() != null) {
			blockStack.peek().addReturnError(expStack.pop());
		}

		if(ctx.getParent() instanceof StatementContext) {
			IBlock b = blockStack.pop();

			if(((StatementContext) ctx.getParent()).IF() != null) {
				ISelection sel = (ISelection) b.getParent();
				if(sel.hasAlternativeBlock() && ((StatementContext) ctx.getParent()).statement(0) == ctx)
					blockStack.push(sel.getAlternativeBlock());
			}

			if(b.getParent().is(ParserAux.FOR_FLAG)) {
				int n = 0;
				for(IBlockElement e : b)
					if(e.is(ParserAux.FOR_FLAG))
						n++;
				while(n-- > 0)
					b.moveAfter(b.getFirst(), b.getLast());
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

	private IExpression matchVariableExpression(String id) {
		IVariableDeclaration var = findVariable(id);
		if(var != null)
			return var.expression();

		//		IConstantDeclaration con = findConstant(id);
		//		if(con != null)
		//			return con.expression();

		System.err.println("unbound " + id + "  " + classType.getId());
		return new IVariableDeclaration.UnboundVariable("?" + id).expression();
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
		String key = namespace + "." + id;
		for(IConstantDeclaration c : module.getConstants())
			if(c.getId().equals(key))
				return c;

		return null;
	}

	@Override
	public void exitExpression(ExpressionContext ctx) {
		//		System.out.println("EXP " + ctx.getText() + "--" + ctx.getParent().getText());
		if(ctx.primary() != null ) { // && not(ctx.getParent(), ExpressionContext.class)) { // TODO this {
			//									System.out.println("PRIM " + ctx.getText() + "  " + ctx.getParent().getText());
			if(ctx.primary().THIS() != null) {
				IVariableDeclaration thisVar = findVariable(ctx.primary().THIS().getText());
				if(thisVar == null)
					System.err.println("no this param exists in " + currentProcedure.getId());
				expStack.push(thisVar.expression());
			}
			else if(ctx.primary().IDENTIFIER() != null) {
				String id = ctx.primary().IDENTIFIER().getText();
				IVariableDeclaration var = findVariable(id);
				if(var != null)
					expStack.push(var.expression());
				else if(currentProcedure != null) {
					IConstantDeclaration con = findConstant(currentProcedure.getId(), id);
					if(con != null)
						expStack.push(con.expression());
					else {
						System.err.println("unbound " + id + "  " + classType.getId());
						expStack.push(new IVariableDeclaration.UnboundVariable("?" + id).expression());
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
						left instanceof IVariableExpression &&
						((IVariableExpression) left).getVariable().getType().isArrayReference()) {
					expStack.push(((IVariableExpression) left).length());
				}
				else
					expStack.push(left.field(fieldId));
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
						blockStack.peek().addAssignment(var, right);
					else {
						assert currentProcedure.is(ParserAux.INSTANCE_FLAG);
						IVariableDeclaration thisVar = currentProcedure.getVariable(ParserAux.THIS);
						blockStack.peek().addRecordFieldAssignment(thisVar.field(var), right);
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
					blockStack.peek().addArrayElementAssignment(t, right, indexes);
				}
				else if(left instanceof IRecordFieldExpression) {
					blockStack.peek().addRecordFieldAssignment((IRecordFieldExpression)left, right);
				}
			}
		}

		else if(is(ctx.bop, "+=", "-=", "*=", "/=", "%=")) {
			IBinaryOperator op = aux.matchBinaryOperator(ctx.bop);
			IExpression exp = expStack.pop();
			IExpression left = expStack.pop();
			if(left instanceof IVariableExpression) {
				exp = op.on((IVariableExpression) left, exp);
				blockStack.peek().addAssignment(((IVariableExpression) left).getVariable(), exp);
			}
			else 
				aux.unsupported("+= on array", ctx);
		}

		else if(is(ctx.postfix, "++") || is(ctx.prefix, "++")) {
			if(child(ctx, ExpressionContext.class))
				aux.unsupported("incrementor as expression", ctx);
			else {	
				IExpression left = expStack.pop();
				if(left instanceof IVariableExpression) {
					blockStack.peek().addIncrement(((IVariableExpression) left).getVariable());
				}
				else
					aux.unsupported("++ on array", ctx);
			}
		}

		else if(is(ctx.postfix, "--") || is(ctx.prefix, "--")) {
			if(child(ctx, ExpressionContext.class))
				aux.unsupported("decrementor as expression", ctx);
			else {
				IExpression left = expStack.pop();
				if(left instanceof IVariableExpression) {
					blockStack.peek().addDecrement(((IVariableExpression) left).getVariable());
				}
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
					constructor = new IProcedure.UnboundProcedure("? " + type.getId());
				expStack.push(constructor.expression(getStackTop(n)));
			}
		}

		else if(ctx.bop != null) {
			IBinaryOperator op = aux.matchBinaryOperator(ctx.bop);
			if(op != null)
				pushBinaryOperation(op);
		}

		if(ctx.getParent() instanceof ForControlContext) {
			//			System.out.println(ctx.getParent().getText());
			ILoop loop = blockStack.peek().addLoop(expStack.pop(), ParserAux.FOR_FLAG);
			blockStack.push(loop.getBlock());
		}
	}



	private void handleMethodCall(MethodCallContext ctx, boolean dotCall) {
		int n = ctx.expressionList() == null ? 0 : ctx.expressionList().expression().size();
		IProcedure p = null;
		if(ctx.SUPER() != null) 
			aux.unsupported("super call", ctx);
		else if(ctx.THIS() != null)
			p = aux.getConstructor(classType, n);
		else
			p = aux.getMethod(classType.getId(), ctx.IDENTIFIER().getText());

		if(p == null)
			p = new IProcedure.UnboundProcedure(ctx.getText());

		List<IExpression> args = dotCall ? getStackTop(n+1) : getStackTop(n);

		if(!dotCall && currentProcedure.is(ParserAux.INSTANCE_FLAG) && p.is(ParserAux.INSTANCE_FLAG))
			args.add(0, currentProcedure.getVariable(ParserAux.THIS).expression());

		if(ctx.getParent().getParent() instanceof StatementContext &&
				((StatementContext) ctx.getParent().getParent()).statementExpression != null)
			blockStack.peek().addCall(p, args);
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
		if(ctx.STRING_LITERAL() != null)
			return((IRecordType) ((IReferenceType) aux.matchRecordType(String.class.getName())).getTarget()).heapAllocation(); 
		if(ctx.NULL_LITERAL() != null)
			return ILiteral.getNull();
		return null;
	}


}
