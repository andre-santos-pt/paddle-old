package pt.iscte.paddle.model.java;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import pt.iscte.paddle.java.antlr.Java8Parser.AdditiveExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ArgumentListContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ArrayCreationExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.AssignmentContext;
import pt.iscte.paddle.java.antlr.Java8Parser.CompilationUnitContext;
import pt.iscte.paddle.java.antlr.Java8Parser.EqualityExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ExpressionNameContext;
import pt.iscte.paddle.java.antlr.Java8Parser.FormalParameterContext;
import pt.iscte.paddle.java.antlr.Java8Parser.IfThenElseStatementContext;
import pt.iscte.paddle.java.antlr.Java8Parser.IfThenElseStatementNoShortIfContext;
import pt.iscte.paddle.java.antlr.Java8Parser.IfThenStatementContext;
import pt.iscte.paddle.java.antlr.Java8Parser.LiteralContext;
import pt.iscte.paddle.java.antlr.Java8Parser.LocalVariableDeclarationContext;
import pt.iscte.paddle.java.antlr.Java8Parser.MethodDeclarationContext;
import pt.iscte.paddle.java.antlr.Java8Parser.MethodInvocationContext;
import pt.iscte.paddle.java.antlr.Java8Parser.MethodInvocation_lfno_primaryContext;
import pt.iscte.paddle.java.antlr.Java8Parser.MultiplicativeExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.RelationalExpressionContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ReturnStatementContext;
import pt.iscte.paddle.java.antlr.Java8Parser.StatementNoShortIfContext;
import pt.iscte.paddle.java.antlr.Java8Parser.VariableDeclaratorContext;
import pt.iscte.paddle.java.antlr.Java8Parser.VariableDeclaratorListContext;
import pt.iscte.paddle.java.antlr.Java8Parser.WhileStatementContext;
import pt.iscte.paddle.java.antlr.Java8ParserBaseListener;
import pt.iscte.paddle.model.IBinaryOperator;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariableDeclaration;

public class Listener extends Java8ParserBaseListener {
	private final IModule module;
	private IProcedure currentProcedure;
	private Deque<IBlock> blockStack;
	private Deque<IExpression> expressionStack = new ArrayDeque<>();

	private Map<MethodDeclarationContext, IProcedure> procMap;

	public Listener(IModule module) {
		this.module = module;
	}

	@Override
	public void enterCompilationUnit(CompilationUnitContext ctx) {
		procMap = new HashMap<>();
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(new Java8ParserBaseListener() {
			IProcedure proc;
			public void enterMethodDeclaration(MethodDeclarationContext ctx) {
				String id = ctx.methodHeader().methodDeclarator().Identifier().toString();
				proc = module.addProcedure(id, IType.INT);
				procMap.put(ctx, proc);
			}

			@Override
			public void enterFormalParameter(FormalParameterContext ctx) {
				IVariableDeclaration p = proc.addParameter(matchType(ctx.getChild(0).getText())); // todo type
				p.setId(ctx.getChild(1).getText());
				
				// TODO instance methods
			}
		}, ctx);
		blockStack = new ArrayDeque<>();
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
		LocalVariableDeclarationContext con = (LocalVariableDeclarationContext) ctx.getParent().getParent();

		IVariableDeclaration dec = blockStack.peek().addVariable(matchType(con.getChild(0).getText())); // TODO check 0...
		dec.setId( ctx.variableDeclaratorId().Identifier().getText());

		if(ctx.variableInitializer() != null)
			blockStack.peek().addAssignment(dec, expressionStack.pop());
	}

	@Override
	public void exitAssignment(AssignmentContext ctx) {
		if(ctx.leftHandSide().expressionName() != null) {
			String id = ctx.leftHandSide().expressionName().getText();
			IVariableDeclaration var = matchVariable(id);
			if(ctx.assignmentOperator().ASSIGN() != null)
				blockStack.peek().addAssignment(var, expressionStack.pop());
			else if(ctx.assignmentOperator().ADD_ASSIGN() != null)
				blockStack.peek().addAssignment(var, IBinaryOperator.ADD.on(var, expressionStack.pop()));
		}
		else if(ctx.leftHandSide().arrayAccess() != null) {
			String id = ctx.leftHandSide().arrayAccess().expressionName().getText();
			IVariableDeclaration var = matchVariable(id);
			if(ctx.assignmentOperator().ASSIGN() != null) {
				IExpression exp = expressionStack.pop();
				IExpression index1 = expressionStack.pop(); // TODO multi index
				blockStack.peek().addArrayElementAssignment(var, exp, index1);
			}
		}
		// TODO arrays, records
	}


	//	@Override
	//	public void enterIfThenStatement(IfThenStatementContext ctx) {
	//		ISelection sel = blockStack.peek().addSelection(match(ctx.expression()));
	//		ISelection sel = blockStack.peek().addSelection(IType.BOOLEAN.literal(true));
	//		blockStack.push(sel.getBlock());
	//	}

	//	@Override
	//	public void enterIfThenElseStatement(IfThenElseStatementContext ctx) {
	//		ISelection sel = blockStack.peek().addSelectionWithAlternative(IType.BOOLEAN.literal(true));
	//		blockStack.push(sel.getBlock());
	//	}

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
		// TODO for
	}

	@Override
	public void exitIfThenElseStatementNoShortIf(IfThenElseStatementNoShortIfContext ctx) {
		System.out.println("EX?? " + ctx.getText()); 
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

	//	@Override
	//	public void enterWhileStatement(WhileStatementContext ctx) {
	//		// TODO Auto-generated method stub
	//		super.enterWhileStatement(ctx);
	//	}

	@Override
	public void exitWhileStatement(WhileStatementContext ctx) {
		blockStack.pop();
	}

	@Override
	public void exitMethodInvocation(MethodInvocationContext ctx) {
		//		System.out.println("INV " + ctx.getText() + " " + ctx.argumentList().getChildCount());
		IProcedure p = matchProcedure(ctx.methodName().getText(), ctx.argumentList());

		List<IExpression> args = new ArrayList<>();
		ctx.argumentList().children.forEach(c -> {
			if(!c.getText().equals(",")) {
				IExpression a = expressionStack.pop();
				args.add(a);
			}
		});
		blockStack.peek().addCall(p, args);
	}






	//	class ExpressionListener extends Java8ParserBaseListener {


	@Override
	public void enterExpressionName(ExpressionNameContext ctx) {
		if(ctx.Identifier() != null) // todo bind
			expressionStack.push(matchVariable(ctx.Identifier().getText()).expression());
	}

	@Override
	public void enterLiteral(LiteralContext ctx) {
		if(ctx.IntegerLiteral() != null)
			expressionStack.push(IType.INT.literal(Integer.parseInt(ctx.getText())));
		else if(ctx.FloatingPointLiteral() != null)
			expressionStack.push(IType.DOUBLE.literal(Double.parseDouble(ctx.getText())));
		// other types
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
		if(ctx.primitiveType() != null) {
			IType t = matchType(ctx.primitiveType().getText());
			expressionStack.push(t.array().heapAllocation(expressionStack.pop())); // TODO dims
		}
	}
	
	@Override
	public void exitMethodInvocation_lfno_primary(MethodInvocation_lfno_primaryContext ctx) {
		String id = ctx.methodName().Identifier().getText();

		IProcedure proc = matchProcedure(id, ctx.argumentList());
		int n = proc.getParameters().size();
		List<IExpression> args = new ArrayList<>();
		while(n-- > 0)
			args.add(0, expressionStack.pop());
		expressionStack.push(proc.expression(args));
	}



	private void pushBinaryOperation(TerminalNode ... nodes) {
		for(TerminalNode node : nodes) {
			if(node != null) {
				IBinaryOperator op = match(node);
				if(op != null) {
					IExpression r = expressionStack.pop();
					IExpression l = expressionStack.pop();
					expressionStack.push(op.on(l, r));
					return;
				}
			}
		}
		//			System.err.println("no op match: " + Arrays.toString(nodes));
	}
	//	}

	private IProcedure matchProcedure(String id, ArgumentListContext args) {
		int n = args.getChildCount();
		if(n != 1)
			n = n - (n-1)/2;
		IProcedure proc = null;
		for (IProcedure p : procMap.values()) {
			if(p.getId().equals(id) && p.getParameters().size() == n) // TODO arg types
				proc = p;
		}

		if(proc == null)
			proc = new IProcedure.UnboundProcedure(id);

		return proc;
	}

	private IVariableDeclaration matchVariable(String id) {
		for(IVariableDeclaration v : currentProcedure.getVariables())
			if(v.getId().equals(id))
				return v;

		return new IVariableDeclaration.UnboundVariable(id);
	}

	private static IBinaryOperator match(TerminalNode node) {
		switch(node.getText()) {
		case "+": return IBinaryOperator.ADD;
		case "-": return IBinaryOperator.SUB;
		case "*": return IBinaryOperator.MUL;
		case "/": return IBinaryOperator.DIV;
		case "%": return IBinaryOperator.MOD;

		case "==": return IBinaryOperator.EQUAL;
		case "!=": return IBinaryOperator.DIFFERENT;
		case "<": return IBinaryOperator.SMALLER;
		case "<=": return IBinaryOperator.SMALLER_EQ;
		case ">": return IBinaryOperator.GREATER;
		case ">=": return IBinaryOperator.GREATER_EQ;

		// TODO logical
		default: return null;
		}
	}

	private IType matchType(String type) {
		switch(type) {
		case "int" : return IType.INT;
		case "int[]" : return IType.INT.array();
		
		case "double" : return IType.DOUBLE;
		case "boolean" : return IType.BOOLEAN;

		case "void" : return IType.VOID;
		default: return null;
		}
	}
}
