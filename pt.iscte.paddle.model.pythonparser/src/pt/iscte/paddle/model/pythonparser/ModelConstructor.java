package pt.iscte.paddle.model.pythonparser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.pythonparser.NameScopeNode.FunctionNode;
import pt.iscte.paddle.model.pythonparser.antlr.Python3Parser;
import pt.iscte.paddle.model.pythonparser.antlr.Python3Parser.AtomContext;
import pt.iscte.paddle.model.pythonparser.antlr.Python3Parser.Expr_stmtContext;
import pt.iscte.paddle.model.pythonparser.antlr.Python3Parser.FuncdefContext;
import pt.iscte.paddle.model.pythonparser.antlr.Python3Parser.Testlist_star_exprContext;
import pt.iscte.paddle.model.pythonparser.antlr.Python3Parser.TfpdefContext;
import pt.iscte.paddle.model.pythonparser.antlr.Python3Parser.VfpdefContext;

public class ModelConstructor extends DelayedFuncsBaseListener {

	IModule module;
	Deque<FunctionNode> scopeStack;
	Deque<IBlock> blockStack;
	List<String> errors;

	int exprStmtState = 0;
	List<String> exprStmtLeft = new ArrayList<>();

	public ModelConstructor(IModule module) {
		super();
		this.module = module;
		scopeStack = new ArrayDeque<>();
		blockStack = new ArrayDeque<>();
		errors = new ArrayList<>();
		createGlobalScope();
	}

	public void createGlobalScope() {
		IProcedure main = module.addProcedure(IType.VOID);
		scopeStack.push(new FunctionNode(main, null));
		blockStack.push(main.getBody());
	}

	@Override
	public void enterFuncdef(FuncdefContext ctx) {
		IProcedure def = module.addProcedure(IType.UNBOUND);
		def.setId(ctx.NAME().getText());
		scopeStack.peek().addFunction(def);
	}

	@Override
	public boolean enterFuncdefDelayed(FuncdefContext ctx) {
		FunctionNode f = scopeStack.peek().searchFunction(ctx.NAME().getText());
		if (f == null) {
			errors.add("System failed to track function definition (must never happen)");
			return false;
		}
		scopeStack.push(f);
		blockStack.push(f.model.getBody());
		return true;
	}

	@Override
	public void exitFuncdefDelayed(Python3Parser.FuncdefContext ctx) {
		scopeStack.pop();
		blockStack.pop();
	}

	@Override
	public void enterTfpdef(TfpdefContext ctx) {
		handleParameter(ctx.NAME().getText());
	}
	
	@Override
	public void enterVfpdef(VfpdefContext ctx) {
		handleParameter(ctx.NAME().getText());
	}
	
	private void handleParameter(String name) {
		IVariableDeclaration arg = scopeStack.peek().model.addParameter(IType.UNBOUND);
		arg.setId(name);
		scopeStack.peek().addVariable(arg);
	}

	@Override
	public void enterExpr_stmt(Expr_stmtContext ctx) {
		exprStmtState = 1;
	}

	@Override
	public void exitTestlist_star_expr(Testlist_star_exprContext ctx) {
		if (exprStmtState == 1) {
			exprStmtState = 2;
		}
	}
	
	@Override
	public void exitExpr_stmt(Expr_stmtContext ctx) {
		for (String name: exprStmtLeft) {
			if (!scopeStack.peek().hasLocalName(name)) {
				IVariableDeclaration var = blockStack.peek().addVariable(IType.UNBOUND);
				var.setId(name);
				scopeStack.peek().addVariable(var);
			}
		}
		exprStmtLeft.clear();
		exprStmtState = 0;
	}

	@Override
	public void enterAtom(AtomContext ctx) {
		if (exprStmtState == 1) {
			TerminalNode name = ctx.NAME();
			if (name != null) {
				exprStmtLeft.add(name.getText());
			}
		}
	}

	public void checkForErrors() throws ParseException {
		if (errors.size() > 0) {
			throw new ParseException(String.join("; ", errors));
		}
	}
}
