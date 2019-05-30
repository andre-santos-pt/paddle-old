package pt.iscte.paddle;

import pt.iscte.paddle.asg.IArrayElementAssignment;
import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IBlockElement;
import pt.iscte.paddle.asg.IBreak;
import pt.iscte.paddle.asg.IConstant;
import pt.iscte.paddle.asg.IContinue;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.ILoop;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IOperator;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IRecordFieldAssignment;
import pt.iscte.paddle.asg.IRecordType;
import pt.iscte.paddle.asg.IReferenceType;
import pt.iscte.paddle.asg.IReturn;
import pt.iscte.paddle.asg.ISelection;
import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;

public class CTranslator implements IModel2CodeTranslator {

	@Override
	public String type(IType t) {
		if(t instanceof IReferenceType) 
			return ((IReferenceType) t).getTarget().getId() + " *";
		return t.getId();
	}
	
	@Override
	public String header(IModule m) {
		return "";
	}
	
	@Override
	public String close(IModule m) {
		return "";
	}
	
	@Override
	public String declaration(IConstant c) {
		return "const " + c.getType().getId() + " " + c.getId() + " = " + c.getValue().getStringValue() + ";\n";
	}

	@Override
	public String declaration(IRecordType t) {
		String text = "typedef struct\n{\n";
		for (IVariable member : t.getFields()) {
			text += "\t" + member.getDeclaration() + ";\n";
		}
		text += "} " + type(t) + ";\n";
		return text;
	}

	@Override
	public String header(IProcedure p) {
		String text = p.getReturnType() + " " + p.getId() + "(";
		String args = "";
		for (IVariable param : p.getParameters()) {
			if(!args.isEmpty())
				args += ", ";
			args += type(param.getType())  + " " + param.getId();
		}
		text += args + ") {\n";
		for (IVariable v : p.getLocalVariables()) {
			text += "\t" + type(v.getType()) + " " + v.getId() + ";\n";
		}
		return text;
	}

	
	@Override
	public String close(IProcedure p) {
		return "}\n\n";
	}
	
	public String statements(IBlock b) {
		String tabs = "";
		int d = b.getDepth();
		for(int i = 0; i < d; i++)
			tabs += "\t";
		String text = "";
		for(IBlockElement c : b)
			text += tabs + statement(c);
		
		return text;
	}
	
	@Override
	public String statement(IBlockElement e) {
		if(e instanceof IVariable)
			return "";
		if(e instanceof IArrayElementAssignment) {
			IArrayElementAssignment a = (IArrayElementAssignment) e;
			String text = a.getVariable().getId();
			for(IExpression i : a.getIndexes())
				text += "[" + i + "]";
			
			text += " = " + a.getExpression() + ";\n";
			return text;
		}
		else if(e instanceof IRecordFieldAssignment)
			;
		else if(e instanceof IVariableAssignment) {
			IVariableAssignment a = (IVariableAssignment) e;
			return a.getVariable().getId() + " = " + a.getExpression() + ";\n";
		}
		else if(e instanceof ISelection) {
			ISelection s = (ISelection) e;
			String text = "if(" + s.getGuard() + ") {\n" + statements(s.getBlock()) + "}\n";
			if(s.hasAlternativeBlock())
				text += "else " + statements(s.getAlternativeBlock());
			return text;
		}
		else if(e instanceof ILoop) {
			ILoop l = (ILoop) e;
			String text = "while(" + l.getGuard() + ") {\n" + statements(l.getBlock()) + "}\n"; 
			return text;
		}
		else if(e instanceof IReturn) {
			return "return " + ((IReturn) e).getExpression() + ";\n";
		}
		else if(e instanceof IBreak)
			return "break;\n";
		else if(e instanceof IContinue)
			return "continue;\n";
		return "???";
	}
	
	@Override
	public String declaration(IVariable v) {
		return v.getType().getId() + " " + v.getId() + ";\n";
	}
	
	@Override
	public String assignment(IVariableAssignment a) {
		return a.getVariable().getId() + " = " + a.getExpression() + ";\n";
	}

	@Override
	public String expression(IExpression e) {
		return e.toString();
	}
	
	@Override
	public String operator(IOperator o) {
		return o.getSymbol();
	}
}
