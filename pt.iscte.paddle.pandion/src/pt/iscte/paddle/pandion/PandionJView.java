package pt.iscte.paddle.pandion;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.part.ViewPart;

import pt.iscte.pandionj.extensibility.IRuntimeModel;
import pt.iscte.pandionj.extensibility.PandionJConstants;
import pt.iscte.pandionj.extensibility.PandionJUI;

public class PandionJView extends ViewPart { 
	private static PandionJView instance;

//	private ILaunch launch; 
	private IRuntimeModel runtime;
//	private IStackFrame exceptionFrame;
	private String exception;

//	private ErrorHandler logListener;

	private RuntimeViewer runtimeView;
	private Composite parent;

	private IContextService contextService;

	private IToolBarManager toolBar;

	private static int arrayMax = 10;

	private Composite introScreen;

	public PandionJView() {
		instance = this;
	}

	public static PandionJView getInstance() {
		return instance;
	}

	public static int getMaxArrayLength() {
		return arrayMax;
	}

	public static void setArrayMaximumLength(int val) {
		arrayMax = val;
	}

	@Override
	public void createPartControl(Composite parent) {
		contextService = (IContextService) PlatformUI.getWorkbench().getService(IContextService.class);
		createWidgets(parent);

//		logListener = new ErrorHandler(this);
//		Platform.addLogListener(logListener);
	}



	@Override
	public void dispose() {
		super.dispose();
		FontManager.dispose();
		instance = null;
	}

	private void createWidgets(Composite parent) {
		this.parent = parent;
		String toolTipVersion = ""; //"Version " + Platform.getBundle(PandionJConstants.PLUGIN_ID).getVersion().toString();
		setTitleToolTip(toolTipVersion);
		parent.setLayout(new GridLayout());
		introScreen = createIntroScreen(parent);
	}

	private Composite createIntroScreen(Composite parent) {
		Composite introComp = new Composite(parent, SWT.NONE);
		introComp.setLayoutData(new GridData(GridData.FILL_BOTH));
		introComp.setLayout(new GridLayout());

		Image image = PandionJUI.getImage("pandionj.png");
		Label imageLabel = new Label(introComp, SWT.NONE);
		imageLabel.setImage(image);
		imageLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));

		Label versionLabel = new Label(introComp, SWT.NONE);
		versionLabel.setText(getTitleToolTip());
		versionLabel.setLayoutData(new GridData(SWT.CENTER, SWT.BEGINNING, false, false));

		Link pluginLabel = new Link(introComp, SWT.NONE);
		pluginLabel.setText("<a>" + PandionJConstants.Messages.INSTALLED_TAGS + "</a>");
		pluginLabel.setLayoutData(new GridData(SWT.CENTER, SWT.BEGINNING, false, false));
		pluginLabel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String info = "";
				for (ExtensionManager.TagDescription desc : ExtensionManager.getTagDescriptions()) {
					String where = desc.description;
					if(where == null)
						where = "";
					else
						where += "\n";
					info += "@" + desc.tag + "\n" + where + "\n";
				}
				MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Installed tags", info);
			}
		});

		return introComp;
	}


	public void setFocus() {
	}




	
	

	
	private static class Exc {
		final String typeName;
		final String message;

		public Exc(String typeName, String message) {
			this.typeName = typeName;
			this.message = message;
		}
		
		boolean matches(Class<?> c) {
			return c.getName().equals(typeName);
		}
	}

//	private static Exc findException(IStackFrame frame) throws DebugException {
//		for(IVariable var : frame.getVariables()) {
//			if(var instanceof JDIReturnValueVariable) {
//				JDIReturnValueVariable retvar = (JDIReturnValueVariable) var;
//				if(retvar.hasResult) {
//					IJavaValue retVal = (IJavaValue) var.getValue();
//					if(retVal instanceof IJavaObject) {
//						IJavaObject retObj = (IJavaObject) retVal;
//						IJavaType javaType = retObj.getJavaType();
//						IJavaFieldVariable field = retObj.getField("detailMessage", true);
//						String msg = field == null ? "" : field.getValue().getValueString(); 
//						return new Exc(javaType.getName(), msg);
//					}
//				}
//			}
//		}
//		return null;
//	}

	


//	public void executeInternal(PandionJUI.DebugRun r) {
//		try {
//			r.run();
//		}
//		catch(DebugException e) {
//			e.printStackTrace();
//			runtime.setTerminated();
//		}
//	}
//
//	public <T> T executeInternal(PandionJUI.DebugOperation<T> r, T defaultValue) {
//		try {
//			return r.run();
//		}
//		catch(DebugException e) {
//			e.printStackTrace();
//			runtime.setTerminated();
//			return null;
//		}
//	}



	private void addToolbarAction(String name, boolean toggle, String imageName, String description, Action action) {
		IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
		action.setImageDescriptor(ImageDescriptor.createFromImage(PandionJUI.getImage(imageName)));
		String tooltip = name;
		if(description != null)
			tooltip += "\n" + description;
		action.setToolTipText(tooltip);
		menuManager.add(action);
	}

	private void addToolbarAction(String name, boolean toggle, String imageName, String description, Runnable runnable) {
		Action a = new Action(name, toggle ? Action.AS_CHECK_BOX : Action.AS_PUSH_BUTTON) {
			public void run() {
				runnable.run();
			}
		};
		addToolbarAction(name, toggle, imageName, description, a);
	}


//	public void terminateProcess() {
//		try {
//			if(launch != null)
//				launch.terminate();
//		} catch (DebugException e) {
//			if(runtime != null)
//				runtime.setTerminated();
//		}
//		logListener.clear();
//	}



//	private MessageConsole findConsole(String name) {
//		ConsolePlugin plugin = ConsolePlugin.getDefault();
//		IConsoleManager conMan = plugin.getConsoleManager();
//		IConsole[] existing = conMan.getConsoles();
//		for (int i = 0; i < existing.length; i++) {
//			System.out.println("CONSOLE: " + existing[i].getName());
//			if (name.equals(existing[i].getName()))
//				return (MessageConsole) existing[i];
//		}
//		//no console found, so create a new one
//		MessageConsole myConsole = new MessageConsole(name, null);
//		conMan.addConsoles(new IConsole[]{myConsole});
//		return myConsole;
//	}

//	public static ErrorHandler getErrorHandler() {
//		return instance.logListener;
//	}

}
