package pt.iscte.paddle.model.impl;

import java.util.Optional;
import java.util.ServiceLoader;

import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IModuleTranslator;
import pt.iscte.paddle.model.IModuleView;
import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.IType;

public enum Translator implements IModuleTranslator {

	INSTANCE;
	
	private static final IModuleTranslator translator;
	
	static {
		Optional<IModuleTranslator> t = ServiceLoader.load(IModuleTranslator.class).findFirst();
		translator = t.isPresent() ? t.get() : null;
	}

	private Translator() {
	
	}

	@Override
	public String translate(IModuleView module) {
		return translator == null ? defaultString(module) : translator.translate(module);
	}

	@Override
	public String translate(IExpression expression) {
		return translator == null ? defaultString(expression) : translator.translate(expression);
	}
	
	@Override
	public String translate(IType type) {
		return translator == null ? defaultString(type) : translator.translate(type);
	}
	
	private String defaultString(IProgramElement e) {
		return e.getClass().getSimpleName() + ":" + e.getId();
	}
}
