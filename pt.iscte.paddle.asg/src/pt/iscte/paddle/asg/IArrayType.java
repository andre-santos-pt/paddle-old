package pt.iscte.paddle.asg;

public interface IArrayType extends IDataType {
	int getDimensions();
	IDataType getComponentType();
}
