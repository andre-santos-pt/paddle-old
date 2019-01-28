package pt.iscte.paddle.asg;

public interface IInstanceProcedure extends IProcedure {
	IObjectType getInstanceType();
	
	// TODO OCL: variables can also be own by object type
//	IBlock getBody();
}
