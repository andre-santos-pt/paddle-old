package pt.iscte.paddle.machine.impl;

import java.util.List;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IBlockChild;
import pt.iscte.paddle.asg.IControlStructure;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IProgramElement;
import pt.iscte.paddle.asg.ISelection;
import pt.iscte.paddle.asg.IStatement;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.IValue;

public class BlockIterator {
	private List<IBlockChild> elements;
	private int i;
	private IExpression eval;
	
	public BlockIterator(IBlock root) {
		assert !root.isEmpty();
		this.elements = root.getChildren();
		this.i = 0;
		
		if(current() instanceof IControlStructure)
			eval = ((IControlStructure) current()).getGuard();
	}

	public boolean hasNext() {
		return i != elements.size();
	}

	public BlockIterator moveNext(IValue last) throws ExecutionError {
		assert hasNext();
		if(last != null)
			eval = null;
		
		IBlockChild current = elements.get(i);
		System.out.println(current);
		if(current instanceof IStatement) {
			i++;
		}
		else if(current instanceof IControlStructure && last == null) {
			eval = ((IControlStructure) current).getGuard();
			return null;
		}
		else if(current instanceof ISelection) {
			ISelection sel = (ISelection) current;
			i++;
			if(last.isTrue()) {
				if(!sel.isEmpty())
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
				i++;
		}

		return null;
	}

	public IProgramElement current() {
		assert i >= 0 && i < elements.size();
		return eval == null ? elements.get(i) : eval;
	}

	@Override
	public String toString() {
		return hasNext() ? current().toString() : "over";
	}
}