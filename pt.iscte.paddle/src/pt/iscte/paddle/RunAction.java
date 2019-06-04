package pt.iscte.paddle;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProgramElement;
import pt.iscte.paddle.asg.semantics.ISemanticProblem;
import pt.iscte.paddle.javali2asg.ElementLocation;
import pt.iscte.paddle.javali2asg.ISourceLocation;
import pt.iscte.paddle.javali2asg.Transformer;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.machine.IMachine;
import pt.iscte.paddle.machine.IProgramState;

public class RunAction extends Action {
	
	
	private static IProcedure findProcedure(IModule module, int offset) {
		for (IProcedure p : module.getProcedures()) {
			ISourceLocation loc = (ISourceLocation) p.getProperty(ElementLocation.Part.WHOLE);
			System.out.println(loc);
			if(loc.contains(offset))
				return p;
		}
		return null;
	}
	
	boolean debug;
	IProgramState state;
	
	public RunAction(boolean debug) {
		super(debug ? "DEBUG" : "RUN");
		this.debug = debug;
	}
	
	public void run() {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

		IFileEditorInput editorInput = (IFileEditorInput) page.getActiveEditor().getEditorInput();

		ITextEditor editor = (ITextEditor) page.getActiveEditor().getAdapter(ITextEditor.class);

		IDocumentProvider provider = editor.getDocumentProvider();
		IDocument document = provider.getDocument(editorInput);
		ITextSelection selection = (ITextSelection) editor.getSite().getSelectionProvider().getSelection();
		System.out.println(selection);
		int offset = selection.getOffset();
		int lineNumber = -1;
		try {
			lineNumber = document.getLineOfOffset(offset);
			System.out.println(lineNumber);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}

		Transformer trans = new Transformer(editorInput.getFile());
		IModule program = trans.createProgram();
		System.out.println(program.translate(new JavaTranslator()));

		try {
			editorInput.getFile().deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);    
			List<ISemanticProblem> problems = program.checkSemantics();
			for(ISemanticProblem p : problems) {
				List<IProgramElement> elements = p.getProgramElements();
				System.out.println("!! " + elements);
				for(IProgramElement e : elements) {
					ISourceLocation loc = (ISourceLocation) e.getProperty(ElementLocation.Part.WHOLE);
					loc.createMarker(editorInput.getFile(), p);
				}
			} 
		}
		catch (CoreException e) {
			e.printStackTrace();
		}
		state = IMachine.create(program);

		IProcedure targetProc = findProcedure(program, offset);
		if(targetProc != null) {
			InvokeDialog dialog = new InvokeDialog(Display.getDefault().getActiveShell(), state, targetProc, debug);
			dialog.open();
//			IExecutionData data = state.execute(targetProc, 2);
//			System.out.println(data);
		} else {
			MessageDialog.open(MessageDialog.ERROR, Display.getDefault().getActiveShell(), "Select procedure", "Place cursor on procedure definition", SWT.NONE);
		}
	}
}
