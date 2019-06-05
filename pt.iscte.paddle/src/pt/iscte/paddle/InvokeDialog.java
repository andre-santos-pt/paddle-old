package pt.iscte.paddle;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextEditor;

import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.javali2asg.ElementLocation;
import pt.iscte.paddle.javali2asg.ISourceLocation;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.IExecutionData;
import pt.iscte.paddle.machine.IProgramState;

public class InvokeDialog extends Dialog {
	private IProgramState state;
	private IProcedure procedure;
	private Button invokeButton;

	private List<Text> argsText;
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
		composite.setLayout(new GridLayout(2, false));
		argsText = new ArrayList<>(procedure.getParameters().size());
		for(IVariable param : procedure.getParameters()) {
			new Label(composite, SWT.NONE).setText(param.getId());
			argsText.add(new Text(composite, SWT.BORDER));
		}
		return composite;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		invokeButton = createButton(parent, IDialogConstants.OK_ID, "Invoke", true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected Point getInitialSize() {
		return super.getInitialSize();
	}

	@Override
	protected void okPressed() {
		Object[] args = new Object[argsText.size()];
		int i = 0;
		for(Text t : argsText) {
			args[i++] =  Integer.parseInt(t.getText()); // TODO types
		}
		try {
			if(debug) {
				state.setupExecution(procedure, args);
				ISourceLocation loc = (ISourceLocation) state.getInstructionPointer().getProperty(ElementLocation.Part.WHOLE);
				if(loc != null) {
					TextEditor editor = (TextEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
					editor.selectAndReveal(loc.getStartChar(), loc.getOffset());
				}
				System.out.println(state.getInstructionPointer());
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