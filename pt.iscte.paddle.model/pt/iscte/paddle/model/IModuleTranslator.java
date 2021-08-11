package pt.iscte.paddle.model;

public interface IModuleTranslator {
	String translate(IModule module);
	String translate(IModuleView module);
	String translate(IBlockElement element);	
	String translate(IExpression expression);
	String translate(IType type);
	String translate(IProcedure procedure);
}
