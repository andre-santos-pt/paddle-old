package pt.iscte.paddle;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextEditor;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.javali.translator.ElementLocation;
import pt.iscte.paddle.javali.translator.ISourceLocation;

public class StepInAction extends Action {
	RunAction runAction;

	public StepInAction(RunAction runAction) {
		super("STEP");
		this.runAction = runAction;
	}

	@Override
	public void run() {
		if(runAction.state == null)
			MessageDialog.open(MessageDialog.ERROR, Display.getDefault().getActiveShell(), "Program not running", "Program not running", SWT.NONE);
		else if(runAction.state.isOver())
			MessageDialog.open(MessageDialog.ERROR, Display.getDefault().getActiveShell(), "Program is over", "Program is over", SWT.NONE);
		else {
			try {
				runAction.state.stepIn();
				if(runAction.state.isOver()) {
					MessageDialog.open(MessageDialog.INFORMATION, Display.getDefault().getActiveShell(), "Result", runAction.state.getExecutionData().getReturnValue().toString(), SWT.NONE);
				}
				else {
					ISourceLocation loc = (ISourceLocation) runAction.state.getInstructionPointer().getProperty(ElementLocation.Part.WHOLE);
					if(loc != null) {
						TextEditor editor = (TextEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
						editor.selectAndReveal(loc.getStartChar(), loc.getOffset());
					}
					System.out.println(runAction.state);
				}
			} catch (ExecutionError e) {
				MessageDialog.open(MessageDialog.ERROR, Display.getDefault().getActiveShell(), "Runtime error", e.getMessage(), SWT.NONE);
			}
		}
	}
}
