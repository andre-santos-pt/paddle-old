package pt.iscte.paddle.model.javaparser;

import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.ClassDeclarationContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParser.MemberDeclarationContext;
import pt.iscte.paddle.model.javaparser.antlr.JavaParserBaseListener;

class RecordParserListener extends JavaParserBaseListener {

	private final IModule module;
	private final ParserAux aux;

	public RecordParserListener(IModule module, ParserAux aux) {
		this.module = module;
		this.aux = aux;
	}


	@Override
	public void enterClassDeclaration(ClassDeclarationContext ctx) {
		if(ctx.EXTENDS() != null || ctx.IMPLEMENTS() != null)
			aux.unsupported("extends/implements", ctx);

		String className = ctx.IDENTIFIER().getText();
		IRecordType t = module.addRecordType(className);
		t.setNamespace(className);
		
		// nested class
//		if(ctx.getParent() instanceof MemberDeclarationContext) {
//			IRecordType t = module.addRecordType(className);
//			t.setNamespace(t.getId());
//		}
//		else {
//			type.setId(className);
//			type.setNamespace(className);
//		}
	}

}
