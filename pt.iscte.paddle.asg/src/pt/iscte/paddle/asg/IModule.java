package pt.iscte.paddle.asg;

import java.util.Collection;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.asg.impl.Module;

/**
 * Mutable
 */
public interface IModule extends IIdentifiableElement {
	default Collection<IModule> getImports() {
		// TODO imports
		return ImmutableList.of();
	}
	Collection<IConstant> getConstants();
	Collection<IStructType> getStructs();
	Collection<IProcedure> getProcedures();
	
//	Collection<IDataType> getDataTypes();

//	IDataType getDataType(String id);

	IConstant addConstant(String id, IDataType type, ILiteral value);
	IStructType addStruct(String id);
	IProcedure addProcedure(String id, IDataType returnType);

	void loadBuildInProcedures(Class<?> staticClass);

//	IConstantDeclaration getConstant(String id);
	//	default IConstantDeclaration getConstant(String id) {
	//		for (IConstantDeclaration c : getConstants()) {
	//			if(c.getId().equals(id))
	//				return c;
	//		}
	//		return null;
	//	}

//	default IProcedure getProcedure(String id, IDataType ... paramTypes) {
//		for(IProcedure p : getProcedures())
//			if(p.getId().equals(id) && p.getParameters().size() == paramTypes.length) {
//				boolean match = true;
//				int i = 0;
//				for (IVariableDeclaration param : p.getParameters()) {
//					if(!param.getType().equals(paramTypes[i++])) {
//						match = false;
//						break;
//					}
//				}
//				if(match)
//					return p;
//			}
//		return null;
//	}
	
	IProcedure resolveProcedure(String id, IDataType ... paramTypes);
	
	IProcedure resolveProcedure(IProcedureDeclaration procedureDeclaration);
	
//	default IProcedure resolveProcedure(IProcedureDeclaration procedureDeclaration) {
//		for(IProcedure p : getProcedures())
//			if(p.hasSameSignature(procedureDeclaration))
//				return p;
//		
//		return null;
//	}

	default void accept(IVisitor visitor) {
		getConstants().forEach(c -> {
			visitor.visit(c);
		});
		
		getStructs().forEach(s -> {
			if(visitor.visit(s))
				s.getMemberVariables().forEach(v -> {
					visitor.visit(v);
				});
		});
		
		getProcedures().forEach(p -> {
			if(visitor.visit(p))
				p.getBody().accept(visitor);
		});
	}	


	interface IVisitor extends IBlock.IVisitor {
//		default void setup(IModule module)						{ }
		default void 	visit(IConstant constant) 				{ }
		default boolean visit(IStructType struct) 				{ return true; }
		default boolean visit(IProcedure procedure) 			{ return true; }
	}


	static IModule create(String id) {
		return new Module(id);
	}

}
