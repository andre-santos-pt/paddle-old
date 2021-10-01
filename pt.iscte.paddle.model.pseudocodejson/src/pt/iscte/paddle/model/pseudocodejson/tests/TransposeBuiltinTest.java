package pt.iscte.paddle.model.pseudocodejson.tests;

import org.json.simple.parser.ParseException;

import junit.framework.TestCase;
import pt.iscte.paddle.model.IModuleTranslator;
import pt.iscte.paddle.model.javaparser.Paddle2Java;
import pt.iscte.paddle.interpreter.IMachine;
import pt.iscte.paddle.interpreter.IProgramState;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.pseudocodejson.Transpose;
import pt.iscte.paddle.model.pseudocodejson.TransposeException;

public class TransposeBuiltinTest extends TestCase {

	// all static methods that use applicable types are
	// mapped into a builtin with id equal to their name
	public static class Builtins {
		// to prevent being considered a type
		private Builtins() {}
		
		public static double pow(double b, double e) {
			return Math.pow(b, e);
		}

		public static double sqrt(double n) {
			return Math.sqrt(n);
		}

		public static void print(double n) {
			System.out.println(n);
		}
	}

	public void testBuiltinFunction() throws ParseException, TransposeException {
		IModule module = Transpose.read(FUNCTION_JSON, Builtins.class);
		
		System.out.println(new Paddle2Java().translate(module));
		
		IProgramState vm = IMachine.create(module);
		vm.execute(module.getProcedure("main"));
	}

	public static String FUNCTION_JSON = """
			{
			  "format": "pseudocodejson",
			  "version": "1.0.0",
			  "id": null,
			  "constants": [],
			  "procedures": [
			    {
			      "uuid": "c6672984-2a2d-4ffa-bb83-67dff5734aee",
			      "id": "sample",
			      "type": "double",
			      "parameters": [
			        {
			          "Statement": "Variable",
			          "uuid": "1aa70b1a-79fc-47db-ace9-e7b3f28abf3f",
			          "id": "a",
			          "type": "int"
			        }
			      ],
			      "body": [
			        {
			          "Statement": "Return",
			          "expression": {
			          "Expression": "Call",
			          "call": {
			          	"procedure": "builtin:sqrt",
			          	"arguments": [
			            {
			              "Expression": "Literal",
			              "type": "int",
			              "value": 9
			            }
			          ]
			          }
			        }
			        }
			      ]
			    },
			    {
			      "uuid": "08175f73-e0cc-4656-b0be-081c7866b2bf",
			      "id": "main",
			      "type": "void",
			      "parameters": [],
			      "body": [
			        {
			          "Statement": "Call",
			          "procedure": "builtin:print",
			          "arguments": [
			            {
			              "Expression": "Call",
			              "call": {
			              	"procedure": "c6672984-2a2d-4ffa-bb83-67dff5734aee",
			              	"arguments": [
			              	{
			              		"Expression": "Literal",
			              		"type": "int",
			              		"value": 9
			              	}
			              	]
			            }
			            }
			          ]
			        }
			      ]
			    }
			  ]
			}
			""";

}
