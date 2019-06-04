package pt.iscte.paddle.tests.asg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pt.iscte.paddle.CTranslator;
import pt.iscte.paddle.JavaTranslator;
import pt.iscte.paddle.JavaTranslatorTOCE;
import pt.iscte.paddle.asg.IConstant;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProgramElement;
import pt.iscte.paddle.asg.IRecordType;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.semantics.ISemanticProblem;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.machine.IMachine;
import pt.iscte.paddle.machine.IProgramState;
import pt.iscte.paddle.machine.IValue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class BaseTest {

	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	protected @interface Case {
		String[] value() default {};
	}

	final protected IModule module;

	private IProcedure main;
	private IProgramState state;

	private List<ISemanticProblem> problems;


	public BaseTest() {
		module = IModule.create();
		for(Class<?> builtin : getBuiltins())
			module.loadBuildInProcedures(builtin);		
		module.setId(getClass().getSimpleName());
	}

	@Before
	public void setup() {
		try {
			for (Field f : getClass().getDeclaredFields()) {
				if(!Modifier.isPrivate(f.getModifiers()) && !Modifier.isStatic(f.getModifiers())) {
					f.setAccessible(true);
					Object o = f.get(this);
					if(isIdElement(o)) {
						((IProgramElement) o).setId(f.getName().replaceAll("_*", ""));

						if (o instanceof IProcedure) {
							if (main == null || f.getName().equals("main"))
								main = (IProcedure) o;
						} 
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		compile();
	}

	private boolean isIdElement(Object element) {
		return element instanceof IProcedure || element instanceof IVariable || element instanceof IConstant || element instanceof IRecordType;
	}

	private void compile() {
		String code = module.translate(new JavaTranslatorTOCE());
		File file = new File("src/" + module.getId() + ".java");
//		System.out.println("\\begin{lstlisting}");
		System.out.print(code + "\n");
//		System.out.println("\\end{lstlisting}");
		try {
			PrintWriter writer = new PrintWriter(file);
			writer.println(code);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		problems = module.checkSemantics();
//		for (ISemanticProblem p : problems) {
//			System.err.println(p);
//		}
		assertTrue("Semantic errors", problems.isEmpty());
	}

	public IModule getModule() {
		return module;
	}

	@Test
	public void run() throws Throwable {
		if(!problems.isEmpty())
			return;

		state = IMachine.create(module);

		for (Method method : getClass().getMethods()) {
			if (method.isAnnotationPresent(Case.class)) {
				if (method.getParameterCount() == 1 && method.getParameterTypes()[0].equals(IExecutionData.class)) {
					String[] value = method.getAnnotation(Case.class).value();
					Object[] args = new Object[value.length];
					for (int i = 0; i < value.length; i++)
						args[i] = value[i];
					IExecutionData data = runCase(args);
					try {
						method.invoke(this, data);
					} catch (InvocationTargetException e) {
						if (e.getCause() instanceof AssertionError)
							throw e.getCause();
						else
							e.printStackTrace();
					} catch (IllegalAccessException | IllegalArgumentException e) {
						e.printStackTrace();
					}
				} else
					System.err.println("Method should have a single parameter of type "
							+ IExecutionData.class.getSimpleName() + " " + method);
			}
		}
	}

	private IExecutionData runCase(Object[] args) {
		IExecutionData data = null;
		try {
			data = state.execute(main, args);
		} catch (ExecutionError e) {
			e.printStackTrace();
		}
		assertNotNull(data);
		commonAsserts(data);
		return data;
	}

	protected Class<?>[] getBuiltins() {
		return new Class[0];
	}

	protected void commonAsserts(IExecutionData data) {

	}

	protected static void equal(int expected, IValue value) {
		assertTrue("value is not BigDecimal: " + value, value.getValue() instanceof BigDecimal);
		assertEquals(expected, ((BigDecimal) value.getValue()).intValue());
	}

	protected static void equal(double expected, IValue value) {
		assertTrue("value is not BigDecimal", value.getValue() instanceof BigDecimal);
		assertEquals(expected, ((BigDecimal) value.getValue()).doubleValue(), 0.0001);
	}

	protected static void equal(String expected, IValue value) {
		assertTrue("value is not BigDecimal", value.getValue() instanceof BigDecimal);
		assertEquals(expected, ((BigDecimal) value.getValue()).toString());
	}

	protected static void isTrue(IValue value) {
		assertTrue("value is not Boolean", value.getValue() instanceof Boolean);
		assertEquals(true, ((Boolean) value.getValue()).booleanValue());
	}

	protected static void isFalse(IValue value) {
		assertTrue("value is not Boolean", value.getValue() instanceof Boolean);
		assertEquals(false, ((Boolean) value.getValue()).booleanValue());
	}
	
	protected static IProcedure importProcedure(Class<? extends BaseTest> clazz, String fieldName) {
		try {
			Field f = clazz.getDeclaredField(fieldName);
			f.setAccessible(true);
			BaseTest testCase = clazz.newInstance();
			IProcedure proc = (IProcedure) f.get(testCase);
//			proc.setId(clazz.getSimpleName() + "." + fieldName);
			proc.setId(fieldName);
			return proc;
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
