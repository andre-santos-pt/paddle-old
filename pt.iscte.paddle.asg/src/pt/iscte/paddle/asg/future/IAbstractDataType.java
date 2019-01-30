package pt.iscte.paddle.asg.future;

import java.util.Collection;

import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IProcedureDeclaration;

interface IAbstractDataType extends IDataType {
	Collection<IProcedureDeclaration> getProcedures();
}
