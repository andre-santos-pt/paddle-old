package pt.iscte.paddle;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import pt.iscte.paddle.machine.ExecutionError;

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
			} catch (ExecutionError e) {
				MessageDialog.open(MessageDialog.ERROR, Display.getDefault().getActiveShell(), "Runtime error", e.getMessage(), SWT.NONE);
			}
		}
	}
}
