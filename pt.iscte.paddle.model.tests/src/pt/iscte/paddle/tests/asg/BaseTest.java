package pt.iscte.paddle.tests.asg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static pt.iscte.paddle.model.IType.INT;

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

import pt.iscte.paddle.codequality.cfg.CFGBuilder;
import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.interpreter.IMachine;
import pt.iscte.paddle.interpreter.IProgramState;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IConstant;
import pt.iscte.paddle.model.ILiteral;
import pt.iscte.paddle.model.IModel2CodeTranslator;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IVariable;
import pt.iscte.paddle.model.cfg.IControlFlowGraph;
import pt.iscte.paddle.model.validation.ISemanticProblem;

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

	private IModel2CodeTranslator translator =  new IModel2CodeTranslator.Java();
	
	public BaseTest() {
		module = IModule.create();
		for(Class<?> builtin : getBuiltins())
			module.loadBuildInProcedures(builtin);		
		module.setId(getClass().getSimpleName());
	}

	@Before
	public void setup() {
		main = main();
		if(main != null)
			main.setId("main");
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
		String code = module.translate(translator);
//		File file = new File("src/" + module.getId() + ".java");
//		System.out.println("\\begin{lstlisting}");
		System.out.print(code + "\n");
//		System.out.println("\\end{lstlisting}");
//		try {
//			PrintWriter writer = new PrintWriter(file);
//			writer.println(code);
//			writer.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		
		problems = module.checkSemantics();
		for (ISemanticProblem p : problems) {
			System.err.println(p);
		}
		assertTrue("Semantic errors", problems.isEmpty());
		
		IControlFlowGraph cfg = cfg();
		if(cfg != null) {
			CFGBuilder cfgBuilder = new CFGBuilder(main);
			cfgBuilder.display();
			IControlFlowGraph cfg2 = cfgBuilder.getCFG();
			assertTrue("CFG does not match", cfg.isEquivalentTo(cfg2));
		}
	}

	public IModule getModule() {
		return module;
	}
	
	protected IProcedure main() {
		return null;
	}
	
	protected IControlFlowGraph cfg() {
		return null;
	}

	@Test
	public void run() throws Throwable {
		if(!problems.isEmpty())
			return;

		state = IMachine.create(module);
		state.addListener(new IProgramState.IListener() {
			@Override
			public void step(IProgramElement currentInstruction) {
				System.out.println(currentInstruction);
			}
		});
		boolean foundCase = false;
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
					foundCase = true;
					System.out.println("\n");
				} else
					System.err.println("Method should have a single parameter of type "
							+ IExecutionData.class.getSimpleName() + " " + method);
			}
		}
		assert foundCase : this.getClass().getSimpleName() + " has no @Case";
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
	
	protected IProcedure importProcedure(Class<? extends BaseTest> clazz, String fieldName) {
		try {
			Field f = clazz.getDeclaredField(fieldName);
			f.setAccessible(true);
			BaseTest testCase = clazz.newInstance();
			IProcedure proc = (IProcedure) f.get(testCase);
			proc.setId(fieldName);
			module.addProcedure(proc);
			System.out.println(proc.translate(translator));
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

	protected static void equal(int expected, IValue value) {
		assertTrue("value is not BigDecimal: " + value, value.getValue() instanceof BigDecimal);
		assertEquals(expected, ((BigDecimal) value.getValue()).intValue());
	}

	protected static void equal(double expected, IValue value) {
		assertTrue("value is not BigDecimal", value.getValue() instanceof BigDecimal);
		assertEquals(expected, ((BigDecimal) value.getValue()).doubleValue(), 0.000001);
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
	
	protected ILiteral[] literalIntArray(int ... array) {
		ILiteral[] lits = new ILiteral[array.length];
		for(int i = 0; i < array.length; i++)
			lits[i] = INT.literal(array[i]);
		return lits;
	}
	
}
