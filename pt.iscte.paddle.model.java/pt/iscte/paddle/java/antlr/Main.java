package pt.iscte.paddle.java.antlr;
import java.nio.charset.Charset;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import pt.iscte.paddle.java.antlr.Java8Parser.AssignmentContext;
import pt.iscte.paddle.java.antlr.Java8Parser.CompilationUnitContext;
import pt.iscte.paddle.java.antlr.Java8Parser.MethodDeclarationContext;
import pt.iscte.paddle.java.antlr.Java8Parser.ReturnStatementContext;
import pt.iscte.paddle.java.antlr.Java8Parser.VariableDeclaratorContext;
import pt.iscte.paddle.java.antlr.Java8Parser.WhileStatementContext;

public class Main {
	public static void main(String[] args) throws Exception {
		CharStream stream = CharStreams.fromFileName("pt/iscte/paddle/java/Test.java",Charset.forName("UTF-8"));
		Java8Lexer lexer = new Java8Lexer(stream);
		Java8Parser parser = new Java8Parser(new CommonTokenStream(lexer));


		Java8ParserListener lis = new Java8ParserBaseListener() {
			@Override
			public void enterCompilationUnit(CompilationUnitContext ctx) {
				System.out.println(ctx.importDeclaration());
			}
			@Override
			public void enterMethodDeclaration(MethodDeclarationContext ctx) {
				System.out.println("MET " + ctx.methodHeader().methodDeclarator().Identifier());
			}

			@Override
			public void enterReturnStatement(ReturnStatementContext ctx) {
				System.out.println("RET " + ctx.expression());
			}
			
			@Override
			public void enterAssignment(AssignmentContext ctx) {
				System.out.println("ASS " + ctx.assignmentOperator().getText());
			}
			
			@Override
			public void enterVariableDeclarator(VariableDeclaratorContext ctx) {
				System.out.println("VAR " + ctx.variableDeclaratorId().Identifier());
			}
			
			@Override
			public void enterWhileStatement(WhileStatementContext ctx) {
				// TODO Auto-generated method stub
				super.enterWhileStatement(ctx);
			}
			
		};
		
		  ParseTreeWalker walker = new ParseTreeWalker();
		    walker.walk(lis, parser.compilationUnit());
		//		ParseTree tree = parser.compilationUnit();
		parser.addParseListener(lis);
		

		//		Java8ParserVisitor visitor = new Java8ParserBaseVisitor() {
		//			public Object visitMethodDeclaration(MethodDeclarationContext ctx) {
		//				TerminalNode id = ctx.methodHeader().methodDeclarator().Identifier();
		//				System.out.println(id);
		//				return null;
		//			}
		//
		//		};
		//		visitor.visit(tree);
	}
}
