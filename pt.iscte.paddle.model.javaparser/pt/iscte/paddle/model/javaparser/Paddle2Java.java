package pt.iscte.paddle.model.javaparser;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import pt.iscte.paddle.model.IArrayAllocation;
import pt.iscte.paddle.model.IArrayElement;
import pt.iscte.paddle.model.IArrayElementAssignment;
import pt.iscte.paddle.model.IArrayLength;
import pt.iscte.paddle.model.IArrayType;
import pt.iscte.paddle.model.IBinaryExpression;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IBlockElement;
import pt.iscte.paddle.model.IBreak;
import pt.iscte.paddle.model.IConditionalExpression;
import pt.iscte.paddle.model.IConstantDeclaration;
import pt.iscte.paddle.model.IConstantExpression;
import pt.iscte.paddle.model.IContinue;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ILiteral;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IModuleTranslator;
import pt.iscte.paddle.model.IModuleView;
import pt.iscte.paddle.model.IPredefinedArrayAllocation;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProcedureCall;
import pt.iscte.paddle.model.IProcedureDeclaration;
import pt.iscte.paddle.model.IRecordAllocation;
import pt.iscte.paddle.model.IRecordFieldAssignment;
import pt.iscte.paddle.model.IRecordFieldExpression;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IReferenceType;
import pt.iscte.paddle.model.IReturn;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IUnaryExpression;
import pt.iscte.paddle.model.IValueType;
import pt.iscte.paddle.model.IVariableAddress;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.IVariableDereference;
import pt.iscte.paddle.model.IVariableExpression;

public class Paddle2Java implements IModuleTranslator {

	public String translate(IModule m) {
		String modId = m.getId() + ""; // for null
		String code = "class " + modId + " {\n";

		for(String ns : m.getNamespaces()) {
			IModuleView view = m.createNamespaceView(ns);

			IRecordType type = m.getRecordType(ns);

			if(type != null && !type.is(IRecordType.BUILTIN)) {
				code += "static class " + type.getId() + "{\n";

				for (IVariableDeclaration member : type.getFields()) {
					code += "\t" + translate(member.getType()) + " " + member.getId() + ";\n";
				}
				
				code += "\n";

				for(IConstantDeclaration c : view.getConstants())
					code += declaration(c);

				for (IProcedure p : view.getProcedures())
					if(!p.isBuiltIn()) 
						code += header(p) + statements(p.getBody()) + "}\n\n";

				code += "}\n\n";
			}
		}

		return code + "}\n";
	}

	public String translate(IType t) {
		if(t instanceof IReferenceType) 
			return ((IReferenceType) t).getTarget().getId();
		
		return t.getId();
	}

	
	private String argsToString(List<IExpression> arguments) {
		String args = "";
		for(IExpression e : arguments) {
			if(!args.isEmpty())
				args += ", ";
			args += translate(e);
		}
		return args;
	}

	public String declaration(IConstantDeclaration c) {
		return Keyword.STATIC + " final " + c.getType().getId() + " " + c.getId() + " = " + c.getValue().getStringValue() + ";\n";
	}

	public String declaration(IRecordType t) {
		String text = Keyword.STATIC + " class " + t.getId() + " {\n";
		for (IVariableDeclaration member : t.getFields()) {
			text += "\t" + translate(member.getType()) + " " + member.getId() + ";\n";
		}
		text += "}\n";
		return text;
	}

	private String header(IProcedure p) {
		String text = Keyword.STATIC + " " + translate(p.getReturnType()) + " " + p.getId() + "(";
		String args = "";
		for (IVariableDeclaration param : p.getParameters()) {
			if(!args.isEmpty())
				args += ", ";
			args += translate(param.getType())  + " " + param.getId();
		}
		text += args + ") {\n";
		return text;
	}

	private String statements(IBlock b) {
		String tabs = tabs(b);
		String text = "";
		for(IBlockElement c : b) {
			if(c instanceof IBlock)
				text += tabs + "{\n" + statements((IBlock) c) + tabs + "}\n";
			else
				text += tabs + translate(c);
		}
		return text;
	}

	static String tabs(IBlock b) {
		String tabs = "";
		int d = b.getDepth();
		for(int i = 0; i < d; i++)
			tabs += "\t";
		return tabs;
	}


	public String translate(IBlockElement e) {
		String ret = null;
		if(e instanceof IVariableDeclaration) {
			IVariableDeclaration v = (IVariableDeclaration) e;
			ret = translate(v.getType()) + " " + v.getId() + ";";
		}
		else if(e instanceof IArrayElementAssignment) {
			IArrayElementAssignment a = (IArrayElementAssignment) e;
			String text = translate(a.getTarget());
			for(IExpression i : a.getIndexes())
				text += "[" + translate(i) + "]";

			text += " = " + translate(a.getExpression()) + ";";
			ret = text;
		}
		else if(e instanceof IRecordFieldAssignment) {
			IRecordFieldAssignment a = (IRecordFieldAssignment) e;
			ret = translate(a.getTarget()) + " = " + translate(a.getExpression()) + ";";
		}
		else if(e instanceof IVariableAssignment) {
			IVariableAssignment a = (IVariableAssignment) e;
			ret = a.getTarget().getId() + " = " + translate(a.getExpression()) + ";";
		}
		else if(e instanceof ISelection) {
			ISelection s = (ISelection) e;
			String tabs = tabs((IBlock) e.getParent());

			String text = "if(" + translate(s.getGuard()) + ") {\n" + statements(s.getBlock()) + tabs + "}";
			//			if(s.getBlock().getSize() == 1)
			//				text = "if(" + s.getGuard() + ")\n" + statements(s.getBlock());

			if(s.hasAlternativeBlock()) {
				//				if(s.getAlternativeBlock().getSize() == 1)
				//					text += tabs + "else\n" + statements(s.getAlternativeBlock());
				//				else
				text += "\n" + tabs + "else {\n" + statements(s.getAlternativeBlock()) + tabs + "}";

			}
			ret = text;
		}
		else if(e instanceof ILoop) {
			ILoop l = (ILoop) e;
			ret = "while(" + translate(l.getGuard()) + ") {\n" + statements(l.getBlock()) + 
					tabs((IBlock) e.getParent()) + "}"; 
		}
		
		else if(e instanceof IReturn) {
			IExpression exp = ((IReturn) e).getExpression();
			if(((IReturn) e).isError())
				ret = "throw " + translate(exp) + ";";
			else
				ret = exp == null ? "return;" : "return " + translate(exp) + ";";
		}
		
		else if(e instanceof IBreak)
			ret = "break;";
		
		else if(e instanceof IContinue)
			ret = "continue;";
		
		else if(e instanceof IProcedureCall)
			ret = procedureCall((IProcedureCall) e) + ";";
		
		if(ret == null)
			throw new RuntimeException("not supported: " + e);
		else
			return ret + flags(e) + "\n";
	}

	private String procedureCall(IProcedureCall call) {
		if(call.getProcedure().isBuiltIn() && call.getProcedure().is(IProcedureDeclaration.INSTANCE_FLAG)) {
			return translate(call.getArguments().get(0)) + "." + call.getProcedure().getId() + 
					"(" + argsToString(call.getArguments().subList(1, call.getArguments().size())) + ")";
		}
		else {
		String inv = call.getProcedure().isBuiltIn() && call.getProcedure().is(ParserAux.CONSTRUCTOR_FLAG) ?
			"new " + call.getProcedure().getId() :
//			call.getOwnerProcedure().sameNamespace(call.getProcedure()) ? call.getProcedure().getId() :
			call.getProcedure().getNamespace() + "." + call.getProcedure().getId();
	
		return inv + "(" + argsToString(call.getArguments()) + ")";
		}
		}
	
	private String flags(IBlockElement e) {
		Set<String> flags = e.getFlags();
		if(flags.isEmpty())
			return "";
		else
			return " // " + flags;
	}


	public String declaration(IVariableDeclaration v) {
		return v.getType().getId() + " " + v.getId() + ";\n";
	}

	public String translate(IExpression e) {
		if(e instanceof ILiteral)
			return ((ILiteral) e).getStringValue();
		
		else if(e instanceof IVariableExpression)
			return ((IVariableExpression) e).getVariable().getId();
		
		else if(e instanceof IConstantExpression)
			return ((IConstantExpression) e).getConstant().getId();
		
		else if(e instanceof IVariableAddress)
			return ((IVariableAddress) e).getTarget().getId();
		
		else if(e instanceof IVariableDereference)
			return ((IVariableDereference) e).getTarget().getId();
		
		
		// before allocation because it is subtype
		else if(e instanceof IPredefinedArrayAllocation) {
			IPredefinedArrayAllocation a = (IPredefinedArrayAllocation) e;
			IType aType = ((IReferenceType) a.getType()).getTarget();
			Iterator<IExpression> it = a.getElements().iterator();
			System.err.println("T " + a.getType());
			String ret = "new " + translate(a.getType()) + " {";
			if(!it.hasNext())
				return ret + "}";

			ret += it.next();
			
			while(it.hasNext())
				ret += ", " + translate(it.next());
			
			return ret + "}";
		}
		
		else if(e instanceof IArrayAllocation) {
			IArrayAllocation a = (IArrayAllocation) e;
			String ret = "new " + ((IArrayType) ((IReferenceType) a.getType()).getTarget()).getRootComponentType().getId();
			for(IExpression d : a.getDimensions())
				ret += "[" + translate(d) + "]";
			return ret;
		}
		
		else if(e instanceof IArrayLength) {
			return translate(((IArrayLength) e).getTarget()) + ".length";
		}
		
		else if(e instanceof IRecordAllocation) {
			IRecordType t = ((IRecordAllocation) e).getRecordType();
			if(t.getId().equals(String.class.getName()) && ((IRecordAllocation) e).hasProperty(String.class))
				return "\"" + ((IRecordAllocation) e).getProperty(String.class) + "\"";
			else
				return "new " + translate(t) + "()";
		}
		
		else if(e instanceof IArrayElement) {
			IArrayElement el = (IArrayElement) e;
			String text = translate(el.getTarget());
			for(IExpression ex : el.getIndexes())
				text += "[" + translate(ex) + "]";
			return text;
		}
		else if(e instanceof IRecordFieldExpression) {
			IRecordFieldExpression r = (IRecordFieldExpression) e;
			return translate(r.getTarget())  + "." + r.getField().getId();
		}
		
		else if(e instanceof IConditionalExpression) {
			IConditionalExpression c = (IConditionalExpression) e;
			return translate(c.getConditional()) + " ? " + translate(c.getTrueExpression()) + " : " + translate(c.getFalseExpression());
		}
		
		else if(e instanceof IProcedureCall)
			return procedureCall((IProcedureCall) e);

		else if(e instanceof IUnaryExpression) {
			IUnaryExpression ue = (IUnaryExpression) e;
			return ue.getOperator().getSymbol() + translate(ue.getOperand());
		}
		
		else if(e instanceof IBinaryExpression) {
			IBinaryExpression bi = (IBinaryExpression) e;
			return translate(bi.getLeftOperand()) + " " + bi.getOperator().getSymbol() +
					" " + translate(bi.getRightOperand());
		}

		throw new RuntimeException("not supported: " + e);
	}
}
