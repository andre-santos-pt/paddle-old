package pt.iscte.paddle.asg.future;

import java.util.Collection;

import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.asg.IProcedureDeclaration;

interface IAbstractDataType extends IType {
	Collection<IProcedureDeclaration> getProcedures();
}
