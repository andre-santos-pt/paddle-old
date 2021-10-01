package pt.iscte.paddle.model.pseudocodejson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import pt.iscte.paddle.model.IBinaryOperator;
import pt.iscte.paddle.model.IConstantDeclaration;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ILiteral;
import pt.iscte.paddle.model.ILoop;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IOperator;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.ISelection;
import pt.iscte.paddle.model.IStatementContainer;
import pt.iscte.paddle.model.ITargetExpression;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IUnaryOperator;
import pt.iscte.paddle.model.IValueType;
import pt.iscte.paddle.model.IVariableDeclaration;

public class Transpose {

	public static IModule readFile(String path, Class<?> ... builtins) throws IOException, ParseException, TransposeException {
		return Transpose.readFile(Paths.get(path), builtins);
	}

	public static IModule readFile(File file, Class<?> ... builtins) throws IOException, ParseException, TransposeException {
		return Transpose.readFile(file.toPath(), builtins);
	}

	public static IModule readFile(Path path, Class<?> ... builtins) throws IOException, ParseException, TransposeException {
		return Transpose.read(Files.readString(path), builtins);
	}

	public static IModule read(String json, Class<?> ... builtins) throws ParseException, TransposeException {
		JSONParser parser = new JSONParser();
		JSONObjectReader root = JSONObjectReader.from(parser.parse(json));

		if (!root.getString("format", "").equals("pseudocodejson")) {
			throw new TransposeException("Source not recognized as pseudocodejson");
		}
		int[] version = root.getSemanticVersion("version");
		if (version == null || version[0] > 1 || version[1] > 0) {
			throw new TransposeException("Unsupported pseudocodejson version");
		}

		IModule module = IModule.create();
		module.setId(root.getString("id"));
	
		for(Class<?> c : builtins) {
			// loads static methods of Class c as builtin procedures;
			// each will have the id of the method name
			module.loadBuiltInProcedures(c);
		}
		
		TransposeState state = new TransposeState(module);
		

		for (JSONObjectReader constantEntry : root.getArray("constants")) {
			IConstantDeclaration constant = module.addConstant(parsePureType(constantEntry),
					parseLiteral(constantEntry));
			constant.setId(constantEntry.getString("id"));
			state.addConstant(constantEntry.requireString("uuid"), constant);
		}

		for (JSONObjectReader procedureEntry : root.getArray("procedures")) {
			IProcedure procedure = module.addProcedure(parseType(procedureEntry));
			procedure.setId(procedureEntry.getString("id"));
			state.addProcedure(procedureEntry.requireString("uuid"), procedure);

			for (JSONObjectReader parameterEntry : procedureEntry.getArray("parameters")) {
				IVariableDeclaration variable = procedure.addParameter(parseType(parameterEntry));
				variable.setId(parameterEntry.getString("id"));
				state.addVariable(parameterEntry.requireString("uuid"), variable);
			}
		}

		for (JSONObjectReader procedureEntry : root.getArray("procedures")) {
			IProcedure procedure = state.findProcedure(procedureEntry.requireString("uuid"));
			parseStatements(state, procedure.getBody(), procedureEntry.getArray("body"));
		}

		return module;
	}

	private static void parseStatements(TransposeState state, IStatementContainer body, List<JSONObjectReader> entries)
			throws TransposeException {
		for (JSONObjectReader entry : entries) {
			String type = entry.requireString("Statement");

			if (type.equals("Variable")) {
				IVariableDeclaration variable = body.addVariable(parseType(entry));
				variable.setId(entry.getString("id"));
				state.addVariable(entry.requireString("uuid"), variable);
			}

			else if (type.equals("Return")) {
				IExpression expression = parseExpression(state, entry.getObject("expression"));
				if (expression != null) {
					body.addReturn(expression);
				} else {
					body.addReturn();
				}
			}

			else if (type.equals("Call")) {
				body.addCall(state.findProcedure(entry.requireString("procedure")),
						parseExpressions(state, entry.getArray("arguments")));
			}

			else if (type.equals("Assignment")) {
				body.addAssignment(state.findVariable(entry.requireString("variable")),
						parseExpression(state, entry.requireObject("expression")));
			}

			else if (type.equals("Array Assignment")) {
				body.addArrayElementAssignment((ITargetExpression) parseExpression(state, entry.requireObject("target")),
						parseExpression(state, entry.requireObject("expression")),
						parseExpressions(state, entry.getArray("indexes")));
			}

			else if (type.equals("Loop")) {
				ILoop loop = body.addLoop(parseExpression(state, entry.requireObject("guard")));
				parseStatements(state, loop, entry.getArray("body"));
			}

			else if (type.equals("Selection")) {
				List<JSONObjectReader> alternative = entry.getArray("alternative");
				if (alternative.size() > 0) {
					ISelection selection = body
							.addSelectionWithAlternative(parseExpression(state, entry.requireObject("guard")));
					parseStatements(state, selection, entry.getArray("body"));
					parseStatements(state, selection.getAlternativeBlock(), entry.getArray("alternative"));
				} else {
					ISelection selection = body.addSelection(parseExpression(state, entry.requireObject("guard")));
					parseStatements(state, selection, entry.getArray("body"));
				}
			}

			else if (type.equals("Break")) {
				body.addBreak();
			}

			else if (type.equals("Continue")) {
				body.addContinue();
			}

			else {
				throw new TransposeException("Unsupported statement of type: " + type);
			}
		}
	}

	private static List<IExpression> parseExpressions(TransposeState state, List<JSONObjectReader> entries)
			throws TransposeException {
		ArrayList<IExpression> expressions = new ArrayList<>(entries.size());
		for (JSONObjectReader entry : entries) {
			expressions.add(parseExpression(state, entry));
		}
		return expressions;
	}

	private static IExpression parseExpression(TransposeState state, JSONObjectReader entry) throws TransposeException {
		if (entry == null) {
			return null;
		}
		String type = entry.requireString("Expression");

		if (type.equals("Literal")) {
			return parseLiteral(entry);
		}

		if (type.equals("Variable")) {
			return state.findVariable(entry.requireString("variable")).expression();
		}

		if (type.equals("Constant")) {
			return state.findConstant(entry.requireString("constant")).expression();
		}

		if (type.equals("Call")) {
			JSONObjectReader callEntry = entry.requireObject("call");
			return state.findProcedure(callEntry.requireString("procedure"))
					.expression(parseExpressions(state, callEntry.getArray("arguments")));
		}

		if (type.equals("Binary Op")) {
			IOperator op = parseOperator(entry);
			if (op instanceof IBinaryOperator) {
				return ((IBinaryOperator) op).on(parseExpression(state, entry.requireObject("left")),
						parseExpression(state, entry.requireObject("right")));
			}
			throw new TransposeException("Not a binary operator: " + entry.getString("op"));
		}

		if (type.equals("Unary Op")) {
			IOperator op = parseOperator(entry);
			if (op instanceof IUnaryOperator) {
				return ((IUnaryOperator) op).on(parseExpression(state, entry.requireObject("expression")));
			}
			throw new TransposeException("Not a unary operator: " + entry.getString("op"));
		}

		if (type.equals("Array Element")) {
			IExpression target = parseExpression(state, entry.requireObject("target"));
			return target.element(parseExpressions(state, entry.getArray("indexes")));
		}

		if (type.equals("Array Length")) {
			IExpression target = parseExpression(state, entry.requireObject("target"));
			return target.length(parseExpressions(state, entry.getArray("indexes")));
		}

		if (type.equals("Array Allocation")) {
			return parsePureType(entry).array().heapAllocation(parseExpressions(state, entry.getArray("dimensions")));
		}

		throw new TransposeException("Unsupported expression of type: " + type);
	}

	private static IType parseType(JSONObjectReader reader) throws TransposeException {
		IType type = parsePureType(reader);
		if (reader.getBoolean("array", false) == true) {
			return type.array().reference();
		}
		return type;
	}

	private static IType parsePureType(JSONObjectReader reader) throws TransposeException {
		String type = reader.requireString("type");
		if (type.equals("int")) {
			return IType.INT;
		}
		if (type.equals("double")) {
			return IType.DOUBLE;
		}
		if (type.equals("boolean")) {
			return IType.BOOLEAN;
		}
		if (type.equals("void")) {
			return IType.VOID;
		}
		if (type.equals("unbound") || type.equals("unknown")) {
			return IType.UNBOUND;
		}
		throw new TransposeException("Unknown type: " + type);
	}

	private static ILiteral parseLiteral(JSONObjectReader reader) throws TransposeException {
		IType type = parsePureType(reader);
		if (type instanceof IValueType) {
			Object value = reader.getAny("value");
			if (value instanceof Long) {
				value = ((Long) value).intValue();
			}
			return ((IValueType) type).literal(value);
		}
		throw new TransposeException("Non value type cannot be a literal: " + reader.getString("type"));
	}

	private static IOperator parseOperator(JSONObjectReader reader) throws TransposeException {
		String op = reader.requireString("op");
		if (op.equals("add")) {
			return IOperator.ADD;
		}
		if (op.equals("sub")) {
			return IOperator.SUB;
		}
		if (op.equals("mul")) {
			return IOperator.MUL;
		}
		if (op.equals("div")) {
			return IOperator.DIV;
		}
		if (op.equals("idiv")) {
			return IOperator.IDIV;
		}
		if (op.equals("mod")) {
			return IOperator.MOD;
		}
		if (op.equals("and")) {
			return IOperator.AND;
		}
		if (op.equals("or")) {
			return IOperator.OR;
		}
		if (op.equals("xor")) {
			return IOperator.XOR;
		}
		if (op.equals("equal")) {
			return IOperator.EQUAL;
		}
		if (op.equals("different")) {
			return IOperator.DIFFERENT;
		}
		if (op.equals("greater")) {
			return IOperator.GREATER;
		}
		if (op.equals("greater_eq")) {
			return IOperator.GREATER_EQ;
		}
		if (op.equals("smaller")) {
			return IOperator.SMALLER;
		}
		if (op.equals("smaller_eq")) {
			return IOperator.SMALLER_EQ;
		}
		if (op.equals("not")) {
			return IOperator.NOT;
		}
		if (op.equals("minus")) {
			return IOperator.MINUS;
		}
		if (op.equals("truncate")) {
			return IOperator.TRUNCATE;
		}
		throw new TransposeException("Unknown operator: " + op);
	}
}
