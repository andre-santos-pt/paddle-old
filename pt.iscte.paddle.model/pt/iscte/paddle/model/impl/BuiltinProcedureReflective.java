package pt.iscte.paddle.model.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.List;


import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.interpreter.impl.Value;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedureDeclaration;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IValueType;
import pt.iscte.paddle.model.IVariableDeclaration;

// TODO different types
public class BuiltinProcedureReflective extends Procedure {

	private Executable executable;
	
	public BuiltinProcedureReflective(Module module, Executable e) {
		super(module, type(module, e));
		assert isValidForBuiltin(module, e);
		this.executable = e;
		for (Parameter p : e.getParameters()) {
			IVariableDeclaration v = addParameter(matchType(module, p.getType()));
			v.setId(p.getName());
		}
		if(e instanceof Method) {
			setId(e.getName());
			if(!Modifier.isStatic(e.getModifiers())) {
				setFlag(IProcedureDeclaration.INSTANCE_FLAG);
				IRecordType t = module.getRecordType(e.getDeclaringClass().getSimpleName());
				if(t == null) {
					t = module.addRecordType(e.getDeclaringClass().getSimpleName());
					t.setFlag(IRecordType.BUILTIN);
				}
				IVariableDeclaration p = addParameter(t.reference());
				p.setId("$this"); // TODO extract string
			}
		}
		else {
			setId(e.getDeclaringClass().getSimpleName());
			setFlag(IProcedureDeclaration.CONSTRUCTOR_FLAG);
		}
		setNamespace(e.getDeclaringClass().getSimpleName());
	}

	private static IType type(IModule module, Executable e) {
		Class<?> t = e instanceof Method ? 
				((Method) e).getReturnType() : ((Constructor) e).getDeclaringClass();
		return matchType(module, t);
	}
	
	public static boolean isValidForBuiltin(IModule module, Executable m) {
		return !Modifier.isPrivate(m.getModifiers()) &&
				valid(module, m.getParameterTypes()) &&
				(m instanceof Constructor || valid(module, ((Method) m).getReturnType()));
	}

	private static boolean valid(IModule module, Class<?> ... types) {
		for (Class<?> c : types) {
			if(matchType(module, c) != null || module.getRecordType(c.getSimpleName()) != null)
				return true;
		}
		return true;
	}
	
	private static IType matchType(IModule module, Class<?> c) {
		if(IType.VOID.getId().equals(c.getSimpleName()))
			return IType.VOID;
		
		for(IValueType t : IType.VALUE_TYPES)
			if(t.matchesPrimitiveType(c))
				return t;
		
		IRecordType t = module.getRecordType(c.getSimpleName());
		if(t == null) {
			t = module.addRecordType(c.getSimpleName());
			t.setNamespace(c.getSimpleName());
			t.setFlag(IRecordType.BUILTIN);
		}
		return t.reference();
	}
	
	private static Object match(Object o, IType t) {
		if(t == IType.INT)
			return ((Number) o).intValue();
		if(t == IType.DOUBLE)
			return ((Number) o).doubleValue();
		if(t == IType.BOOLEAN)
			return ((Boolean) o).booleanValue();
		if(t == IType.CHAR)
			return ((Character) o).charValue();
		return null;
	}
	
	public IValue hookAction(List<IValue> arguments) throws Exception {
		Object[] args = new Object[arguments.size()];
		for(int i = 0; i < arguments.size(); i++) {
			args[i] = match(arguments.get(i).getValue(), arguments.get(i).getType());
		}
		try {
			Object ret = executable instanceof Method ?
					((Method) executable).invoke(null, args) :
					((Constructor) executable).newInstance(args);
			return Value.create(getReturnType(), ret);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			throw e;
		}
		return null;
	}
}
