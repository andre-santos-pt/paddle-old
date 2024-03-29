package pt.iscte.paddle.model.javaparser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

class MemberParserListener extends JavaParserBaseListener {

	private final IModule module;
	private IRecordType toplevelType;
	private final ParserAux aux;
	private IProcedure proc;
//	private File file;
	private final String location;
	
	private IRecordType currentType;

	public MemberParserListener(IModule module, String location, ParserAux aux) {
		this.module = module;
		this.aux = aux;
		this.location = location;
	}


	// TODO global vars
	// TODO init fields before constructor


	@Override
	public void enterClassDeclaration(ClassDeclarationContext ctx) {
			
		currentType = module.getRecordType(ctx.IDENTIFIER().getText());
		if(toplevelType == null)
			toplevelType = currentType;
		
		assert currentType != null;
		
		// nested class
		if(ctx.getParent() instanceof MemberDeclarationContext) {
			
			if(ctx.EXTENDS() != null || ctx.IMPLEMENTS() != null)
				aux.unsupported("extends/implements", ctx);
		}

	}

	@Override
	public void exitClassDeclaration(ClassDeclarationContext ctx) {
		// nested class
		if(ctx.getParent() instanceof MemberDeclarationContext)
			currentType = toplevelType;
	}
	

	@Override
	public void exitFieldDeclaration(FieldDeclarationContext ctx) {
		ClassBodyDeclarationContext classMember = (ClassBodyDeclarationContext) ctx.getParent().getParent();
		IType type = aux.matchType(ctx.typeType());
		for (VariableDeclaratorContext varDec : ctx.variableDeclarators().variableDeclarator()) {
			String varId = varDec.variableDeclaratorId().IDENTIFIER().getText();
			IType t = ParserAux.handleRightBrackets(type, varDec.variableDeclaratorId().getText());
			
			String[] modifiers = aux.getModifiers(classMember, Keyword.fieldModifiers());
			
			boolean global = aux.hasModifier(classMember.modifier(), Keyword.STATIC);
			if(global) {
				IConstantDeclaration con = module.addConstant(t, null, modifiers);
				con.setId(varId);
				con.setNamespace(currentType.getId());
			}
			else {
				if (varDec.variableInitializer() != null) {
					aux.unsupported("field initializer", varDec.variableInitializer());
				}
				currentType.addField(t, varId, modifiers);
			}
		}
	}


	


	@Override
	public void enterMethodDeclaration(MethodDeclarationContext ctx) {
		ClassBodyDeclarationContext classMember = (ClassBodyDeclarationContext) ctx.getParent().getParent();
		String id = ctx.IDENTIFIER().getText();
		TypeTypeOrVoidContext typeTypeOrVoid = ctx.typeTypeOrVoid();
		IType type = typeTypeOrVoid.VOID() != null ? 
				IType.VOID : aux.matchType(typeTypeOrVoid.typeType());
		proc = module.addProcedure(type, p -> { 
			p.setId(id); 
			p.setFlag(aux.getModifiers(classMember, Keyword.methodModifiers()));
		});
		proc.setNamespace(currentType.getId());
		proc.setProperty(SourceLocation.class, new SourceLocation(location, ctx.getStart().getLine()));
		if(!aux.hasModifier(classMember.modifier(), Keyword.STATIC)) {
			proc.setFlag(ParserAux.INSTANCE_FLAG);
			IVariableDeclaration self = proc.addParameter(toplevelType.reference());
			self.setId(ParserAux.THIS_VAR);
			self.setFlag(ParserAux.INSTANCE_FLAG);
		}
		aux.addMethod(ctx, proc);
	}

	@Override
	public void enterInterfaceDeclaration(InterfaceDeclarationContext ctx) {
		aux.unsupported("interfaces", ctx);
	}

	@Override
	public void enterConstructorDeclaration(ConstructorDeclarationContext ctx) {
		proc = module.addProcedure(currentType.reference());
		proc.setFlag(ParserAux.CONSTRUCTOR_FLAG);
		proc.setId(currentType.getId());
		proc.setNamespace(currentType.getId());
		aux.addConstructor(ctx, proc);
	}


	@Override
	public void enterFormalParameterList(FormalParameterListContext ctx) {
		if(ctx.lastFormalParameter() != null)
			aux.unsupported("varargs", ctx);
	}

	@Override
	public void enterFormalParameter(FormalParameterContext ctx) {
		if( !ParserAux.containedIn(ctx, InterfaceMethodDeclarationContext.class)) {
			String id = ctx.variableDeclaratorId().IDENTIFIER().getText();
			IType t = aux.matchType(ctx.typeType());
			t = ParserAux.handleRightBrackets(t, ctx.variableDeclaratorId().getText());
			IVariableDeclaration p = proc.addParameter(t);
			p.setId(id);
		}
	}
}
