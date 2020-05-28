package pt.iscte.paddle.model;

public interface IModuleTranslator {
	String translate(IModule module);
	String translate(IExpression expression);
}
