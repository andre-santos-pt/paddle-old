package pt.iscte.paddle.model;

public interface IModuleTranslator {
	String translate(IModuleView module);
	String translate(IExpression expression);
	String translate(IType type);
}
