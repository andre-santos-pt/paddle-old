package pt.iscte.paddle.model.pythonparser;

import pt.iscte.paddle.model.pythonparser.antlr.Python3Parser;
import pt.iscte.paddle.model.pythonparser.antlr.Python3ParserBaseListener;

public class DelayedFuncsBaseListener extends Python3ParserBaseListener {

	public boolean enterFuncdefDelayed(Python3Parser.FuncdefContext ctx) {
		return true; // False skips the function contents and node exit.
	}

	public void exitFuncdefDelayed(Python3Parser.FuncdefContext ctx) {
	}

}
