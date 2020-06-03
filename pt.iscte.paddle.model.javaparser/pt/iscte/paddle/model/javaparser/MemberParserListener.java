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

class MemberParserListener extends JavaParserBaseListener {

	private final IModule module;
	private IRecordType toplevelType;
	private final ParserAux aux;
	private IProcedure proc;
	private File file;

	private IRecordType currentType;

	public MemberParserListener(IModule module, File file, ParserAux aux) {
		this.module = module;
		this.aux = aux;
		this.file = file;
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
			
			boolean constant = 
					varDec.variableInitializer() != null &&
					aux.hasModifier(classMember.modifier(), Keyword.STATIC) && 
					aux.hasModifier(classMember.modifier(), Keyword.FINAL);
			if(constant) {
				IConstantDeclaration con;
				if(t instanceof IValueType) {
					String val = varDec.variableInitializer().expression().getText();
					Object obj = null;
					if(t == IType.INT)
						obj = Integer.parseInt(val);
					else if(t == IType.DOUBLE)
						obj = Double.parseDouble(val);
					else if(t == IType.BOOLEAN)
						obj = Boolean.parseBoolean(val);
					else
						aux.unsupported("constant type", ctx);

					con = module.addConstant(t, ((IValueType) t).literal(obj));
				}
				else {
					con = module.addConstant(t, ILiteral.getNull());
					aux.unsupported("constant type", varDec);
				}
				con.setId(varId);
				con.setNamespace(currentType.getId());
			}
			else {
				//				if(ctx.variableInitializer() != null) {
				//					aux.unsupported("field initializer", ctx.variableInitializer());
				//				}
				currentType.addField(t, varId);
			}
		}
	}


	@Override
	public void enterMethodDeclaration(MethodDeclarationContext ctx) {
		String id = ctx.IDENTIFIER().getText();
		TypeTypeOrVoidContext typeTypeOrVoid = ctx.typeTypeOrVoid();
		IType type = typeTypeOrVoid.VOID() != null ? 
				IType.VOID : aux.matchType(typeTypeOrVoid.typeType());
		proc = module.addProcedure(id, type);
		proc.setNamespace(currentType.getId());
		proc.setProperty(SourceLocation.class, new SourceLocation(file, ctx.getStart().getLine()));
		ClassBodyDeclarationContext classMember = (ClassBodyDeclarationContext) ctx.getParent().getParent();
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
