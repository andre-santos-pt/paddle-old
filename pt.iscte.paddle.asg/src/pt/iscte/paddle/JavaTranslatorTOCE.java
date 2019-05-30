package pt.iscte.paddle;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IModule;

public class JavaTranslatorTOCE extends JavaTranslator {
	@Override
	public String header(IModule m) {
		return "";
	}

	@Override
	public String close(IModule m) {
		return "";
	}

//	@Override
//	String tabs(IBlock b) {
//		return super.tabs(b).substring(1);
//	}
}
