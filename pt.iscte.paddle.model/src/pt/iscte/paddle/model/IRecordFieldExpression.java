package pt.iscte.paddle.model;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IRecord;
import pt.iscte.paddle.interpreter.IReference;

public interface IRecordFieldExpression extends ISimpleExpression {

	IExpression getTarget();
	IVariable getField();
	
	IRecordFieldExpression field(IVariable field);
	
	IArrayElement element(List<IExpression> indexes);
	default IArrayElement element(IExpression ... indexes) {
		return element(Arrays.asList(indexes));
	}

	default IRecord resolveTarget(ICallStack stack) {
		IExpression f = getTarget();
		Queue<IVariable> queue = new ArrayDeque<>();
		
		while(f instanceof IRecordFieldExpression) {
			queue.add(((IRecordFieldExpression) f).getField());
			f = ((IRecordFieldExpression) f).getTarget();
		}
			
		IRecord r = (IRecord) stack.getTopFrame().getVariableStore((IVariable) f).getTarget();
		while(queue.size() > 1) {
			IReference ref = r.getField(queue.remove());
			r = (IRecord) ref.getTarget();
		}
		
		return r;
	}
}