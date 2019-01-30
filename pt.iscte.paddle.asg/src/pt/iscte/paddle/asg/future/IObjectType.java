package pt.iscte.paddle.asg.future;

import java.util.List;

import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProcedureDeclaration;
import pt.iscte.paddle.asg.IStructType;

interface IObjectType extends IStructType {

	List<IProcedure> getProcedures();
	
	IAbstractDataType getRealizations();
	
	default boolean isCompatibleWith(IAbstractDataType type) {
		for (IProcedureDeclaration tp : type.getProcedures()) {
			boolean match = false;
			for(IProcedure p : getProcedures())
				if(p.isEqualTo(tp)) {
					match = true;
					break;
				}
			if(!match)
				return false;
		}
		return true;
	}
}
