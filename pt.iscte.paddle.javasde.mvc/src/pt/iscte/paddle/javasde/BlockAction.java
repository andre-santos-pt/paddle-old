package pt.iscte.paddle.javasde;

import static pt.iscte.paddle.model.IType.BOOLEAN;
import static pt.iscte.paddle.model.IType.INT;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;

import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IBlockElement;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IOperator;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariable;

abstract class BlockAction extends InsertWidget.Action {

	final IBlock block;

	BlockAction(Keyword keyword, IBlock block) {
		this(keyword.toString(), keyword.getAccelerator(), block);
	}
	
	BlockAction(String text, char accelerator, IBlock block) {
		super(text, accelerator);
		this.block = block;
	}

	static List<BlockAction> all(IBlock block) {
		List<BlockAction> all = new ArrayList<>();
		all.add(declaration(block));
		all.add(assignment(block));
		all.add(ifStatement(block));
		all.add(elseStatement(block));
		all.add(whileLoop(block));
		all.add(forLoop(block));
		all.add(call(block));
		all.add(returnStatement(block));
		all.add(breakStatement(block));
		all.add(continueStatement(block));
		return all;
	}

	private static boolean atEnd(String text, int caret) {
		return caret == text.length();
	}
	
	// TODO other types
	static boolean isType(String text) {
		return  IType.match(text) != null;
	}
	
	static BlockAction declaration(IBlock block) {
		return new BlockAction("variable", 'v', block) {
			public boolean isEnabled(char c, String text, int index, int caret, int selection) {
				return isType(text) && atEnd(text, caret) && (c == SWT.SPACE || c == '[');
			}
			
			public void run(char c, String text, int index, int caret, int selection) {
				IType t = IType.match(text);
				if(c == '[')
					t = t.array();

				IVariable var = block.addVariableAt(t, index);
				var.setId(null);
//				block.addAssignmentAt(var, null, index);
			}
		};
	}
	
	static BlockAction assignment(IBlock block) {
		return new BlockAction("assignment", 'a', block) {
			public boolean isEnabled(char c, String text, int index, int caret, int selection) {
				return !isType(text) && !text.isEmpty() && atEnd(text, caret) && (c == '=' || c == '[');
			}
			public void run(char c, String text, int index, int caret, int selection) {
				IVariable var = block.getOwnerProcedure().getVariable(text);
				if(var == null)
					var = new IVariable.UnboundVariable(text);
				
				if(c == '=')
					block.addAssignmentAt(var, null, index);
				else
					block.addArrayElementAssignmentAt(var, null, index, IType.INT.literal(0));
			}
		};
	}

	static BlockAction ifStatement(IBlock block) {
		return new BlockAction(Keyword.IF, block) {
			public boolean isEnabled(char c, String text, int index, int caret, int selection) {
				return Keyword.IF.match(text) && (c == '(' || c == SWT.SPACE) && atEnd(text, caret);
			}
			
			public void run(char c, String text, int index, int caret, int selection) {
				block.addSelectionAt(BOOLEAN.literal(true), index);
			}
		};
	}
	
	static BlockAction elseStatement(IBlock block) {
		return new BlockAction(Keyword.ELSE, block) {
			public boolean isEnabled(char c, String text, int index, int caret, int selection) {
				if(Keyword.ELSE.match(text) && (c == '{' || c == SWT.SPACE) && index > 0 && atEnd(text, caret)) {
					IBlockElement e = block.getChildren().get(index - 1);
					return e instanceof ISelection && !((ISelection) e).hasAlternativeBlock();
				}
				return false;
			}
			
			public void run(char c, String text, int index, int caret, int selection) {
				IBlockElement e = block.getChildren().get(index - 1);
				((ISelection) e).createAlternativeBlock();
			}
		};
	}
	
	
	static BlockAction whileLoop(IBlock block) {
		return new BlockAction(Keyword.WHILE, block) {
			boolean isEnabled(char c, String text, int index, int caret, int selection) {
				return Keyword.WHILE.match(text) && (c == '(' || c == SWT.SPACE) && atEnd(text, caret);
			}

			void run(char c, String text, int index, int caret, int selection) {
				block.addLoopAt(BOOLEAN.literal(true), index);
			}
		};
	}
	
	static BlockAction forLoop(IBlock block) {
		return new BlockAction(Keyword.FOR, block) {
			boolean isEnabled(char c, String text, int index, int caret, int selection) {
				return Keyword.FOR.match(text) && (c == '(' || c == SWT.SPACE) && atEnd(text, caret);
			}

			void run(char c, String text, int index, int caret, int selection) {
				IBlock forBlock = block.addBlockAt(index, Constants.FOR_FLAG);
				IVariable progVar = forBlock.addVariable(INT, Constants.FOR_FLAG);
				ILoop loop = forBlock.addLoop(BOOLEAN.literal(true), Constants.FOR_FLAG);
				loop.addAssignment(progVar, IOperator.ADD.on(progVar, INT.literal(1)), Constants.FOR_FLAG);
			}
		};
	}
	
	static BlockAction call(IBlock block) {
		return new BlockAction("call(...)", 'p', block) {
			boolean isEnabled(char c, String text, int index, int caret, int selection) {
				return !Keyword.is(text) && !text.isEmpty() && atEnd(text, caret) && c == '(';
			}

			void run(char c, String text, int index, int caret, int selection) {
				block.addCallAt(null, index);
			}
		};
	}
	
	static BlockAction returnStatement(IBlock block) {
		return new BlockAction(Keyword.RETURN, block) {
			boolean isEnabled(char c, String text, int index, int caret, int selection) {
				return Keyword.RETURN.match(text) && (c == ';' || c == SWT.SPACE) && atEnd(text, caret);
			}

			void run(char c, String text, int index, int caret, int selection) {
				if(c == ';')
					block.addReturn();
				else
					block.addReturn(null);
			}
		};
	}

	
	static BlockAction breakStatement(IBlock block) {
		return new BlockAction(Keyword.BREAK, block) {
			boolean isEnabled(char c, String text, int index, int caret, int selection) {
				return block.isInLoop() && Keyword.BREAK.match(text) && (c == ';' || c == SWT.SPACE) && atEnd(text, caret);
			}

			void run(char c, String text, int index, int caret, int selection) {
				block.addBreakAt(index);
			}
		};
	}
	
	static BlockAction continueStatement(IBlock block) {
		return new BlockAction(Keyword.CONTINUE, block) {
			boolean isEnabled(char c, String text, int index, int caret, int selection) {
				return block.isInLoop() && Keyword.CONTINUE.match(text) && (c == ';' || c == SWT.SPACE) && atEnd(text, caret);
			}

			void run(char c, String text, int index, int caret, int selection) {
				block.addBreakAt(index);
			}
		};
	}
	
// TODO array type
//	else if(e.character == '[' && isType(text.getText()))
//		runAction(menu, 'v', e.widget, text.getText() + "[");

	
//	else if(e.character == '[' && text.getText().length() > 0)
//		runAction(menu, 'a', e.widget, text.getText());

	
}