package pt.iscte.paddle.asg;

import pt.iscte.paddle.asg.impl.ArithmeticOperator;
import pt.iscte.paddle.asg.impl.LogicalOperator;
import pt.iscte.paddle.asg.impl.RelationalOperator;
import pt.iscte.paddle.asg.impl.UnaryOperator;

public interface IOperator extends IProgramElement {
		
	IBinaryOperator ADD = ArithmeticOperator.ADD;
	IBinaryOperator SUB = ArithmeticOperator.SUB;
	IBinaryOperator MUL = ArithmeticOperator.MUL;
	IBinaryOperator DIV = ArithmeticOperator.DIV;
	IBinaryOperator MOD = ArithmeticOperator.MOD;
	
	IBinaryOperator EQUAL = RelationalOperator.EQUAL;
	IBinaryOperator DIFFERENT = RelationalOperator.DIFFERENT;
	IBinaryOperator GREATER = RelationalOperator.GREATER;
	IBinaryOperator GREATER_EQ = RelationalOperator.GREATER_EQUAL;
	IBinaryOperator SMALLER = RelationalOperator.SMALLER;
	IBinaryOperator SMALLER_EQ = RelationalOperator.SMALLER_EQUAL;
	
	IUnaryOperator NOT = UnaryOperator.NOT;
	
	IBinaryOperator AND = LogicalOperator.AND;
	IBinaryOperator OR = LogicalOperator.OR;
	IBinaryOperator XOR = LogicalOperator.XOR;
	
	IUnaryOperator TRUNCATE = UnaryOperator.TRUNCATE;

	OperationType getOperationType();

	default String getSymbol() {
		return getId();
	}


	enum OperationType {
		ARITHMETIC, RELATIONAL, LOGICAL, CALL, OTHER;
	}
	
	
}
