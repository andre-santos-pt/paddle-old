package pt.iscte.paddle;

import pt.iscte.paddle.asg.IArrayElement;
import pt.iscte.paddle.asg.IArrayElementAssignment;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IBlockElement;
import pt.iscte.paddle.asg.IBreak;
import pt.iscte.paddle.asg.IConditionalExpression;
import pt.iscte.paddle.asg.IConstant;
import pt.iscte.paddle.asg.IContinue;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IOperator;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProcedureCall;
import pt.iscte.paddle.asg.IRecordAllocation;
import pt.iscte.paddle.asg.IRecordFieldAssignment;
import pt.iscte.paddle.asg.IRecordFieldExpression;
import pt.iscte.paddle.asg.IRecordType;
import pt.iscte.paddle.asg.IReferenceType;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.ISelection;
import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAddress;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.asg.IVariableDereference;

public class JavaTranslator implements IModel2CodeTranslator {

	static final String STATIC = "";
	
	@Override
	public String type(IType t) {
		if(t instanceof IReferenceType) 
			return ((IReferenceType) t).getTarget().getId();
		return t.getId();
	}
	
	@Override
	public String header(IModule m) {
		return "class " + m.getId() + " {\n";
	}
	
	@Override
	public String close(IModule m) {
		return "}\n";
	}
	
	@Override
	public String declaration(IConstant c) {
		return STATIC + "final " + c.getType().getId() + " " + c.getId() + " = " + c.getValue().getStringValue() + ";\n";
	}

	@Override
	public String declaration(IRecordType t) {
		String text = STATIC + "class " + t.getId() + " {\n";
		for (IVariable member : t.getFields()) {
			text += "\t" + member.getDeclaration() + ";\n";
		}
		text += "}\n";
		return text;
	}

	@Override
	public String header(IProcedure p) {
		String text = STATIC + p.getReturnType() + " " + p.getId() + "(";
		String args = "";
		for (IVariable param : p.getParameters()) {
			if(!args.isEmpty())
				args += ", ";
			args += type(param.getType())  + " " + param.getId();
		}
		text += args + ") {\n";
		return text;
	}

	
	@Override
	public String close(IProcedure p) {
		return "}\n";
	}
	
	public String statements(IBlock b) {
		String tabs = tabs(b);
		String text = "";
		for(IBlockElement c : b)
			text += tabs + statement(c);
		return text;
	}

	String tabs(IBlock b) {
		String tabs = "";
		int d = b.getDepth();
		for(int i = 0; i < d; i++)
			tabs += "\t";
		return tabs;
	}
	
	public String id(IVariable v) {
		// TODO
		return v.getId();
	}
	
	@Override
	public String statement(IBlockElement e) {
		if(e instanceof IVariable) {
			IVariable v = (IVariable) e;
			return type(v.getType()) + " " + v.getId() + ";\n";
		}
		else if(e instanceof IArrayElementAssignment) {
			IArrayElementAssignment a = (IArrayElementAssignment) e;
			String text = id(a.getVariable());
			for(IExpression i : a.getIndexes())
				text += "[" + expression(i) + "]";
			
			text += " = " + expression(a.getExpression()) + ";\n";
			return text;
		}
		else if(e instanceof IRecordFieldAssignment) {
			IRecordFieldAssignment a = (IRecordFieldAssignment) e;
			return a.getVariable().getId() + "." + a.getField().getId() + " = " + a.getExpression().translate(this) + ";\n";
		}
		else if(e instanceof IVariableAssignment) {
			IVariableAssignment a = (IVariableAssignment) e;
			return a.getVariable().getId() + " = " + a.getExpression().translate(this) + ";\n";
		}
		else if(e instanceof ISelection) {
			ISelection s = (ISelection) e;
			String tabs = tabs((IBlock) e.getParent());
				
			String text = "if(" + s.getGuard() + ") {\n" + statements(s.getBlock()) + tabs + "}\n";
			if(s.getBlock().getSize() == 1)
				text = "if(" + s.getGuard() + ")\n" + statements(s.getBlock());
			
			if(s.hasAlternativeBlock()) {
				if(s.getAlternativeBlock().getSize() == 1)
					text += tabs + "else\n" + statements(s.getAlternativeBlock());
				else
					text += tabs + "else {\n" + statements(s.getAlternativeBlock()) + tabs + "}\n";
				
			}
			return text;
		}
		else if(e instanceof ILoop) {
			ILoop l = (ILoop) e;
			String text = "while(" + l.getGuard() + ") {\n" + statements(l.getBlock()) + tabs((IBlock) e.getParent()) + "}\n"; 
			return text;
		}
		else if(e instanceof IReturn) {
			return "return " + ((IReturn) e).getExpression().translate(this) + ";\n";
		}
		else if(e instanceof IBreak)
			return "break;\n";
		else if(e instanceof IContinue)
			return "continue;\n";
		else if(e instanceof IProcedureCall)
			return ((IProcedureCall) e).translate(this) + ";\n";
		return "???";
	}
	
	@Override
	public String declaration(IVariable v) {
		return v.getType().getId() + " " + v.getId() + ";\n";
	}
	
	@Override
	public String assignment(IVariableAssignment a) {
		return a.getVariable().getId() + " = " + a.getExpression().translate(this) + ";\n";
	}

	@Override
	public String expression(IExpression e) {
		if(e instanceof IVariableAddress)
			return ((IVariableAddress) e).getVariable().getId();
		else if(e instanceof IVariableDereference)
			return ((IVariableDereference) e).getVariable().getId();
		else if(e instanceof IRecordAllocation)
			return "new " + ((IRecordAllocation) e).getType().getId() + "()";
		else if(e instanceof IArrayElement) {
			IArrayElement el = (IArrayElement) e;
			String text = el.getVariable().getId();
			for(IExpression ex : el.getIndexes())
				text += "[" + ex.translate(this) + "]";
			return text;
		}
		else if(e instanceof IRecordFieldExpression) {
			IRecordFieldExpression r = (IRecordFieldExpression) e;
			return r.getVariable().getId()  + "." + r.getField().getId();
		}
		else if(e instanceof IConditionalExpression) {
			IConditionalExpression c = (IConditionalExpression) e;
			return c.getConditional().translate(this) + " ? " + c.getTrueExpression().translate(this) + " : " + c.getFalseExpression().translate(this);
		}
		return e.toString();
	}
	
	@Override
	public String operator(IOperator o) {
		return o.getSymbol();
	}
}
