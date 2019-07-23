package pt.iscte.paddle.model.impl;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import pt.iscte.paddle.model.IBinaryOperator;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IStatement;
import pt.iscte.paddle.model.IUnaryOperator;

public class ArchRules {

	public static Collection<Class<?>> properSubTypes(Class<?> superType, String basePackage, boolean onlyInterface) throws IOException {
		ClassPath cp = ClassPath.from(ClassLoader.getSystemClassLoader());
		List<Class<?>> list = new ArrayList<Class<?>>();
		for (ClassInfo classInfo : cp.getTopLevelClassesRecursive(basePackage)) {
			Class<?> c = classInfo.load();
			if((!onlyInterface || c.isInterface()) && c != superType && superType.isAssignableFrom(c))
				list.add(c);
		}
		return list;
	}

	public static void main(String[] args) throws IOException {
		String basePackage = IModule.class.getPackage().getName();

		Collection<Class<?>> statements = properSubTypes(IStatement.class, basePackage, true);
		System.out.println("Statements: " + IStatement.class.getName());
		statements.forEach(s -> {
			System.out.println("\t" + s.getName());
		});
		System.out.println("total: " + statements.size());

		System.out.println();

		Collection<Class<?>> expressions = properSubTypes(IExpression.class, basePackage, true);
		System.out.println("Expressions: " + IExpression.class.getName());
		expressions.forEach(e -> {
			System.out.println("\t" + e.getName());
			try {
				IExpression.IVisitor.class.getDeclaredMethod("visit", e);
			} catch (NoSuchMethodException e1) {
				System.out.println("\t\t! missing visit method: " + e);
			} catch (SecurityException e1) {
				e1.printStackTrace();
			}

		});
		System.out.println("total: " + expressions.size());

		System.out.println();

		Collection<Class<?>> uoperators = properSubTypes(IUnaryOperator.class, basePackage, false);
		System.out.println("Unary operators: " + IUnaryOperator.class.getName());
		uoperators.forEach(o -> printEnum(o));

		System.out.println();

		Collection<Class<?>> boperators = properSubTypes(IBinaryOperator.class, basePackage, false);
		System.out.println("Binary operators: " + IBinaryOperator.class.getName());
		boperators.forEach(o -> printEnum(o));
	}

	private static void printEnum(Class<?> o) {
		if(o.isEnum()) {
			for(Field f : o.getFields())
				if(f.isEnumConstant())
					System.out.println("\t" + f.getName() + "\t\t\t(" + o.getSimpleName() + ")");
		}
		else
			System.out.println("\t" + o.getName());
	}

}
