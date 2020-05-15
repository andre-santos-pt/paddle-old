package pt.iscte.paddle.model.java;

import pt.iscte.paddle.java.antlr.Java8Parser.ConstructorDeclarationContext;
import pt.iscte.paddle.java.antlr.Java8Parser.FieldDeclarationContext;
import pt.iscte.paddle.java.antlr.Java8Parser.FormalParameterContext;
import pt.iscte.paddle.java.antlr.Java8Parser.MethodDeclarationContext;
import pt.iscte.paddle.java.antlr.Java8Parser.UnannTypeContext;
import pt.iscte.paddle.java.antlr.Java8Parser.VariableDeclaratorContext;
import pt.iscte.paddle.java.antlr.Java8Parser.VariableDeclaratorListContext;

import org.antlr.v4.runtime.ParserRuleContext;

import pt.iscte.paddle.java.antlr.Java8ParserBaseListener;
import pt.iscte.paddle.model.IConstantDeclaration;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IValueType;
import pt.iscte.paddle.model.IVariableDeclaration;

public class PreParserListener extends Java8ParserBaseListener {
	
	private final IModule module;
	private final IRecordType selfType;
	private final ParserAux aux;
	private IProcedure proc;
	
	public PreParserListener(IModule module, IRecordType selfType, ParserAux aux) {
		this.module = module;
		this.selfType = selfType;
		this.aux = aux;
	}
	
	// TODO global vars
	// TODO init fields before constructor
	
	@Override
	public void exitVariableDeclarator(VariableDeclaratorContext ctx) {
		VariableDeclaratorListContext varList = (VariableDeclaratorListContext) ctx.getParent();
		ParserRuleContext parent = varList.getParent();
		if(parent instanceof FieldDeclarationContext) {
			FieldDeclarationContext fieldDec = (FieldDeclarationContext) parent;
			IType type = aux.matchType(fieldDec.unannType());
			String varId = ctx.variableDeclaratorId().Identifier().getText();
//			System.out.println(ctx.variableDeclaratorId().Identifier().getText() + ": "  );
			boolean constant = ParserAux.hasModifier(fieldDec, Keyword.STATIC) && ParserAux.hasModifier(fieldDec, Keyword.FINAL);
			if(constant) {
				if(type instanceof IValueType) {
					String val = ctx.getChild(2).getText();
					Object obj = null;
					if(type == IType.INT)
						obj = Integer.parseInt(val);
					else if(type == IType.DOUBLE)
						obj = Double.parseDouble(val);
					else if(type == IType.BOOLEAN)
						obj = Boolean.parseBoolean(val);
					else
						aux.unsupported("constant type", ctx);
					
					IConstantDeclaration con = module.addConstant(type, ((IValueType) type).literal(obj));
					con.setId(selfType.getId() + "." + varId);
				}
				else {
					aux.unsupported("constant type", fieldDec.unannType());
				}
			}
			else {
				if(ctx.variableInitializer() != null)
					aux.unsupported("field initializer", ctx.variableInitializer());
				else {
					selfType.addField(type, varId);
				}
				
			}
		}
	}

	public void enterMethodDeclaration(MethodDeclarationContext ctx) {
		String id = ctx.methodHeader().methodDeclarator().Identifier().toString();
		UnannTypeContext t = ctx.methodHeader().result().unannType();
		IType type = t == null ? IType.VOID : aux.matchType(t);
		proc = module.addProcedure(id, type);
		proc.setProperty("namespace", selfType.getId());
		if(!ParserAux.hasModifier(ctx, Keyword.STATIC)) {
			proc.setFlag(ParserAux.INSTANCE_FLAG);
			IVariableDeclaration self = proc.addParameter(selfType.reference());
			self.setId(ParserAux.THIS);
		}
		aux.addMethod(ctx, selfType, proc);
	}

	@Override
	public void enterConstructorDeclaration(ConstructorDeclarationContext ctx) {
		proc = module.addProcedure(IType.VOID);
		proc.setFlag(ParserAux.CONSTRUCTOR_FLAG);
		proc.setId("$" + selfType.getId());
		IVariableDeclaration self = proc.addParameter(selfType.reference());
		self.setId(ParserAux.THIS);
		aux.addConstructor(ctx, selfType, proc);
	}
	
	@Override
	public void enterFormalParameter(FormalParameterContext ctx) {
		String id = ctx.variableDeclaratorId().Identifier().getText();
		IType t = aux.matchType(ctx.unannType());
		IVariableDeclaration p = proc.addParameter(t);
		p.setId(id);
	}


}
