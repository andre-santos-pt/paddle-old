package pt.iscte.paddle.model.impl;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.List;

import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.interpreter.impl.Value;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IValueType;

// TODO different types
public class BuiltinProcedure extends Procedure {

	private Method method;
	
	public BuiltinProcedure(Module parent, Method method) {
		super(parent, matchType(method.getReturnType()));
		assert isValidForBuiltin(method);
		this.method = method;
		for (Parameter p : method.getParameters()) {
			addParameter(matchType(p.getType()));
		}
		setId(method.getName());
		setProperty("namespace", method.getDeclaringClass().getSimpleName());
	}

	public static boolean isValidForBuiltin(Method method) {
		if(!Modifier.isPublic(method.getModifiers()) || 
			!Modifier.isStatic(method.getModifiers()) || 
			!validType(method.getReturnType()))
			return false;
		
		for (Class<?> ptype : method.getParameterTypes()) {
			if(!validType(ptype))
				return false;
		}
		return true;
	}

	private static IType matchType(Class<?> c) {
		for(IValueType t : IType.VALUE_TYPES)
			if(t.matchesPrimitiveType(c))
				return t;
		return null;
	}
	
	private static boolean validType(Class<?> c) {
		return matchType(c) != null;
	}
	
	private static Object match(Object o, IType t) {
		if(t == IType.INT)
			return ((Number) o).intValue();
		if(t == IType.DOUBLE)
			return ((Number) o).doubleValue();
		if(t == IType.BOOLEAN)
			return ((Boolean) o).booleanValue();
		return null;
	}
	
	public IValue hookAction(List<IValue> arguments) throws Exception {
		Object[] args = new Object[arguments.size()];
		for(int i = 0; i < arguments.size(); i++) {
			args[i] = match(arguments.get(i).getValue(), arguments.get(i).getType());
		}
		try {
			Object ret = method.invoke(null, args);
			return Value.create(getReturnType(), ret);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			throw e;
		}
		return null;
	}
}
