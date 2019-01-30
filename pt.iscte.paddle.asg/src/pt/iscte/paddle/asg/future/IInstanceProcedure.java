package pt.iscte.paddle.asg.future;

import pt.iscte.paddle.asg.IProcedure;

public interface IInstanceProcedure extends IProcedure {
	IObjectType getInstanceType();
	
	// TODO OCL: variables can also be own by object type
//	IBlock getBody();
}
