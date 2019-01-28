package pt.iscte.paddle.asg;

import java.util.Collection;

interface IAbstractDataType extends IDataType {
	Collection<IProcedureDeclaration> getProcedures();
}
