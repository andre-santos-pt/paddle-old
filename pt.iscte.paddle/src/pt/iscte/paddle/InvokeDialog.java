package pt.iscte.paddle;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.machine.IProgramState;

public class InvokeDialog extends Dialog {
	private IProgramState state;
	private IProcedure procedure;
	private Button invokeButton;
	private String invocationExpression;
	private String[] paramValues;
	private Text text;
	private boolean debug;

	public InvokeDialog(Shell parentShell, IProgramState state, IProcedure procedure, boolean debug) {
		super(parentShell);
		setShellStyle(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		this.state = state;
		this.procedure = procedure;
		this.debug = debug;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		text = new Text(composite, SWT.BORDER);
		return composite;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		invokeButton = createButton(parent, IDialogConstants.OK_ID, "Arguments", true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected Point getInitialSize() {
		return super.getInitialSize();
	}

	void setValid(boolean valid, String invocationExpression, String[] paramValues) {
		if(invokeButton != null)
			invokeButton.setEnabled(valid);
		this.invocationExpression = invocationExpression;
		this.paramValues = paramValues;
	}


	@Override
	protected void okPressed() {
		Object[] args = {Integer.parseInt(text.getText())};
		try {
			if(debug) {
				state.setupExecution(procedure, args);
				state.stepIn();
			}
			else {
				IExecutionData execute = state.execute(procedure, args);
				MessageDialog.open(MessageDialog.INFORMATION, this.getParentShell(), "Result", execute.getReturnValue().toString(), SWT.NONE);
			}
		} catch (ExecutionError e) {
			MessageDialog.open(MessageDialog.ERROR, this.getParentShell(), "Error", e.getMessage(), SWT.NONE);
			e.printStackTrace();
		}
		super.okPressed();
	}

	@Override
	public int open() {
		return super.open();
	}

}