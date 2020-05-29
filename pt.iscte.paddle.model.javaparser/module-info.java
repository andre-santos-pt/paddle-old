import pt.iscte.paddle.model.IModuleTranslator;
import pt.iscte.paddle.model.javaparser.Paddle2Java;

module pt.iscte.paddle.model.javaparser {
	requires org.antlr.v4.runtime;
	requires transitive pt.iscte.paddle.model;
	requires java.desktop;
	requires com.github.albfernandez.juniversalchardet;
	
//	exports pt.iscte.paddle.model.java;
	exports pt.iscte.paddle.model.javaparser;
	
	provides IModuleTranslator with Paddle2Java;
}
