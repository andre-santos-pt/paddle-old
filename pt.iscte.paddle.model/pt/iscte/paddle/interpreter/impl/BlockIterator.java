package pt.iscte.paddle.interpreter.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IBlockElement;
import pt.iscte.paddle.model.IControlStructure;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IStatement;

public class BlockIterator {
	private List<IBlockElement> elements;
	private int next;
	private IExpression eval;
	
	public BlockIterator(IBlock root) {
		assert !root.isEmpty();
		
		this.elements = root.getChildren();
		this.next = 0;
		
		if(current() instanceof IControlStructure)
			eval = ((IControlStructure) current()).getGuard();
	}

	public boolean hasNext() {
		return next != elements.size();
	}

	public BlockIterator moveNext(IValue last) throws ExecutionError {
		assert hasNext();
		
		if(last != null)
			eval = null;
		
		IBlockElement current = elements.get(next);

		if(current instanceof IStatement) {
			next++;
		}
		else if(current instanceof IControlStructure && last == null) {
			eval = ((IControlStructure) current).getGuard();
			return null;
		}
		else if(current instanceof ISelection) {
			next++;
			ISelection sel = (ISelection) current;
			if(last.isTrue() && !sel.isEmpty()) {
				return new BlockIterator(sel.getBlock());
			}
			else if(sel.hasAlternativeBlock()) {
				IBlock elseBlock = sel.getAlternativeBlock();
				if(!elseBlock.isEmpty())
					return new BlockIterator(elseBlock);
			}
		}
		else if(current instanceof ILoop) {
			ILoop loop = (ILoop) current;
			if(last.isTrue() && !loop.isEmpty())
				return new BlockIterator(loop.getBlock());
			else
				next++;
		}

		return null;
	}

	public IProgramElement current() {
		assert next >= 0 && next < elements.size();
		return eval == null ? elements.get(next) : eval;
	}

	@Override
	public String toString() {
		return hasNext() ? current().toString() : "over";
	}
}