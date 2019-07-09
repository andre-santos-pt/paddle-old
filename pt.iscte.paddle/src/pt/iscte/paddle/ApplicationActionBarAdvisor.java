package pt.iscte.paddle;

import java.io.File;
import java.util.List;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.interpreter.IMachine;
import pt.iscte.paddle.interpreter.IProgramState;
import pt.iscte.paddle.javali.translator.ElementLocation;
import pt.iscte.paddle.javali.translator.ISourceLocation;
import pt.iscte.paddle.javali.translator.Translator;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.validation.ISemanticProblem;
import pt.iscte.paddle.model.validation.SemanticChecker;
import pt.iscte.paddle.semantics.java.JavaSemanticChecker;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	@Override
	protected void makeActions(IWorkbenchWindow window) {
		super.makeActions(window);
	}

	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		super.fillMenuBar(menuBar);
	}

	
	
	
	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		super.fillCoolBar(coolBar);
		IToolBarManager toolBar = new ToolBarManager(coolBar.getStyle());
		coolBar.add(toolBar);
		
		toolBar.add(new RunAction(false));
		RunAction debugAction = new RunAction(true);
		toolBar.add(debugAction);
		toolBar.add(new StepInAction(debugAction));
		
		toolBar.add(new Action("test") {
			@Override
			public void run() {
				File fileToOpen = new File("/Users/andresantos/Desktop/test.javali");
				IFileStore fileStore = EFS.getLocalFileSystem().getStore(fileToOpen.toURI());
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					IDE.openEditorOnFileStore( page, fileStore );
				} catch ( PartInitException e ) {
					//Put your exception handler here if you wish to
				}
			}
		});

		

		toolBar.add(new Action("check") {
			@Override
			public void run() {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				IFileEditorInput editorInput = (IFileEditorInput) page.getActiveEditor().getEditorInput();
				IResource resource = editorInput.getFile();
				try {
					resource.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
				} catch (CoreException e1) {
					e1.printStackTrace();
				}
				Translator trans = new Translator(editorInput.getFile());
				IModule program = trans.createProgram();
				program.setId(editorInput.getFile().getName());
				SemanticChecker checker = new SemanticChecker(new JavaSemanticChecker());
				List<ISemanticProblem> problems = checker.check(program);
				for(ISemanticProblem p : problems) {
					IProgramElement e = p.getProgramElements().get(0);
					ISourceLocation idProp = (ISourceLocation) e.getProperty(ElementLocation.Part.ID);
					if(idProp != null) {
						createMarker(idProp, resource, p);
					}
					else {
						ElementLocation loc = e.getProperty(ElementLocation.class);
						if(loc != null)
							createMarker(loc, resource, p);
					}
				}
			}
		});

	}
	
	static IMarker createMarker(ISourceLocation loc, IResource r, ISemanticProblem p) {
		try {
			IMarker marker = r.createMarker(IMarker.PROBLEM);
			marker.setAttribute(IMarker.MESSAGE, p.getMessage());
			marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
			//                 marker.setAttribute(IMarker.LINE_NUMBER, line);
			marker.setAttribute(IMarker.CHAR_START, loc.getStartChar());
			marker.setAttribute(IMarker.CHAR_END, loc.getEndChar());
			return marker;
		} catch (CoreException ex) {
			ex.printStackTrace();
			return null;
		}
	}

}

