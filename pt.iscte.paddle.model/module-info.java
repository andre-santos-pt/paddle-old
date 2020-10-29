import pt.iscte.paddle.model.IModuleTranslator;

module pt.iscte.paddle.model {
	exports pt.iscte.paddle.util;
	exports pt.iscte.paddle.model;
	exports pt.iscte.paddle.model.commands;
	exports pt.iscte.paddle.model.validation;
	exports pt.iscte.paddle.model.cfg;
	exports pt.iscte.paddle.interpreter;
	
	exports pt.iscte.paddle.asg.future;
	
	uses IModuleTranslator;
}