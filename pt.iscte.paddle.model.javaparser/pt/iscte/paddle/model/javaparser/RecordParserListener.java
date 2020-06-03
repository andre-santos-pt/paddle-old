package pt.iscte.paddle.model.javaparser;

import java.io.File;

import pt.iscte.paddle.model.IConstantDeclaration;
import pt.iscte.paddle.model.ILiteral;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IValueType;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.ClassBodyDeclarationContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.ClassDeclarationContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.CompilationUnitContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.ConstructorDeclarationContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.FieldDeclarationContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.FormalParameterContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.FormalParameterListContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.InterfaceDeclarationContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.InterfaceMethodDeclarationContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.MemberDeclarationContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.MethodDeclarationContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.TypeTypeOrVoidContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.VariableDeclaratorContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParserBaseListener;

class RecordParserListener extends JavaParserBaseListener {

	private final IModule module;
	private final IRecordType toplevelType;
	private final ParserAux aux;
	private File file;


	public RecordParserListener(IModule module, IRecordType selfType, File file, ParserAux aux) {
		this.module = module;
		this.toplevelType = selfType;
		this.aux = aux;
		this.file = file;
	}


	@Override
	public void enterClassDeclaration(ClassDeclarationContext ctx) {
		if(ctx.EXTENDS() != null || ctx.IMPLEMENTS() != null)
			aux.unsupported("extends/implements", ctx);

		// nested class
		if(ctx.getParent() instanceof MemberDeclarationContext) {
			IRecordType t = module.addRecordType(ctx.IDENTIFIER().getText());
			t.setNamespace(t.getId());
		}
		
		// check nested-nested
	}

}
