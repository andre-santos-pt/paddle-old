package pt.iscte.paddle.model.pythonparser;

import java.util.ArrayDeque;
import java.util.Queue;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import pt.iscte.paddle.model.pythonparser.antlr.Python3Parser.File_inputContext;
import pt.iscte.paddle.model.pythonparser.antlr.Python3Parser.FuncdefContext;

/**
 * In Python, names can be resolved in order when function bodies are parsed
 * after all statements in the parent function scope are parsed.
 */
public class DelayedFuncsTreeWalker extends ParseTreeWalker {

	Queue<FuncdefContext> delayedFuncSuites = new ArrayDeque<>();

	public void walk(DelayedFuncsBaseListener listener, ParseTree t) {
		if (t instanceof ErrorNode) {
			listener.visitErrorNode((ErrorNode) t);
			return;
		} else if (t instanceof TerminalNode) {
			listener.visitTerminal((TerminalNode) t);
			return;
		}
		RuleNode r = (RuleNode) t;
		enterRule(listener, r);

		if (r.getRuleContext() instanceof FuncdefContext) {
			FuncdefContext ctx = (FuncdefContext) r.getRuleContext();
			walk(listener, ctx.DEF());
			walk(listener, ctx.NAME());
			delayedFuncSuites.add(ctx);
		} else {
			int n = r.getChildCount();
			for (int i = 0; i < n; i++) {
				walk(listener, r.getChild(i));
			}
			if (r.getRuleContext() instanceof FuncdefContext || r.getRuleContext() instanceof File_inputContext) {
				while (!delayedFuncSuites.isEmpty()) {
					FuncdefContext ctx = delayedFuncSuites.remove();
					if (listener.enterFuncdefDelayed(ctx)) {
						walk(listener, ctx.parameters());
						walk(listener, ctx.suite());
						listener.exitFuncdefDelayed(ctx);
					}
				}
			}
		}
		exitRule(listener, r);
	}

}
