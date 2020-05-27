package pt.iscte.paddle.model;

import pt.iscte.paddle.model.impl.ArithmeticOperator;
import pt.iscte.paddle.model.impl.LogicalOperator;
import pt.iscte.paddle.model.impl.RelationalOperator;
import pt.iscte.paddle.model.impl.UnaryOperator;

public interface IOperator extends IProgramElement {
		
	IBinaryOperator ADD = ArithmeticOperator.ADD;
	IBinaryOperator SUB = ArithmeticOperator.SUB;
	IBinaryOperator MUL = ArithmeticOperator.MUL;
	IBinaryOperator DIV = ArithmeticOperator.DIV;
	IBinaryOperator IDIV = ArithmeticOperator.IDIV;
	IBinaryOperator MOD = ArithmeticOperator.MOD;
	
	IBinaryOperator EQUAL = RelationalOperator.EQUAL;
	IBinaryOperator DIFFERENT = RelationalOperator.DIFFERENT;
	IBinaryOperator GREATER = RelationalOperator.GREATER;
	IBinaryOperator GREATER_EQ = RelationalOperator.GREATER_EQUAL;
	IBinaryOperator SMALLER = RelationalOperator.SMALLER;
	IBinaryOperator SMALLER_EQ = RelationalOperator.SMALLER_EQUAL;
	
	
	IBinaryOperator AND = LogicalOperator.AND;
	IBinaryOperator OR = LogicalOperator.OR;
	IBinaryOperator XOR = LogicalOperator.XOR;
	
	// TODO CAND??
	// TODO COR??
	
	IUnaryOperator NOT = UnaryOperator.NOT;
	IUnaryOperator MINUS = UnaryOperator.MINUS;
	IUnaryOperator TRUNCATE = UnaryOperator.TRUNCATE;
//	IUnaryOperator PLUS = UnaryOperator.PLUS;

	OperationType getOperationType();

	default String getSymbol() {
		return getId();
	}


	enum OperationType {
		ARITHMETIC, RELATIONAL, LOGICAL, CALL, OTHER;
	}
	
	
}
