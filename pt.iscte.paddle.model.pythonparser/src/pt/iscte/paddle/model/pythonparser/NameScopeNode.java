package pt.iscte.paddle.model.pythonparser;

import java.util.HashMap;
import java.util.Map;

import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IVariableDeclaration;

public class NameScopeNode {

	public static class VariableNode extends NameScopeNode {

		public IVariableDeclaration model;

		public VariableNode(IVariableDeclaration variable) {
			model = variable;
		}
	}

	public static class FunctionNode extends NameScopeNode {

		public IProcedure model;
		FunctionNode parent;
		Map<String, NameScopeNode> names;

		public FunctionNode(IProcedure function, FunctionNode parent) {
			model = function;
			this.parent = parent;
			names = new HashMap<>();
		}

		public boolean hasLocalName(String name) {
			return names.containsKey(name);
		}

		public void addVariable(IVariableDeclaration variable) {
			names.put(variable.getId(), new VariableNode(variable));
		}

		public void addFunction(IProcedure function) {
			names.put(function.getId(), new FunctionNode(function, this));
		}

		public NameScopeNode search(String name) {
			NameScopeNode node = names.get(name);
			if (node != null) {
				return node;
			} else if (parent != null) {
				return parent.search(name);
			} else {
				return null;
			}
		}

		public VariableNode searchVariable(String name) {
			NameScopeNode node = search(name);
			if (node != null && node instanceof VariableNode) {
				return (VariableNode) node;
			}
			return null;
		}

		public FunctionNode searchFunction(String name) {
			NameScopeNode node = search(name);
			if (node != null && node instanceof FunctionNode) {
				return (FunctionNode) node;
			}
			return null;
		}

		public String toString() {
			return toString(0);
		}

		public String toString(int level) {
			String out = "";
			for (String name : names.keySet()) {
				NameScopeNode node = names.get(name);
				if (node instanceof VariableNode) {
					out += String.format("%d.var %s\n", level, name);
				} else if (node instanceof FunctionNode) {
					out += String.format("%d.fun %s\n%s", level, name, ((FunctionNode) node).toString(level + 1));
				}
			}
			return out;
		}
	}
}
