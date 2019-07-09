package pt.iscte.paddle.asg.future;

import java.util.Collection;

import pt.iscte.paddle.model.IProcedureDeclaration;
import pt.iscte.paddle.model.IType;

interface IAbstractDataType extends IType {
	Collection<IProcedureDeclaration> getProcedures();
}
