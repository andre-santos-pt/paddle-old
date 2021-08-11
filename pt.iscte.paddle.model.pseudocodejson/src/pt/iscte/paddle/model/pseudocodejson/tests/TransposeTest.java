package pt.iscte.paddle.model.pseudocodejson.tests;

import org.json.simple.parser.ParseException;

import junit.framework.TestCase;
import pt.iscte.paddle.model.IModuleTranslator;
import pt.iscte.paddle.model.javaparser.Paddle2Java;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.pseudocodejson.Transpose;
import pt.iscte.paddle.model.pseudocodejson.TransposeException;

public class TransposeTest extends TestCase {

	public void testReadFunction() throws ParseException, TransposeException {
		IModule module = Transpose.read(FUNCTION_JSON);
		// TODO check structure
		System.out.println(new Paddle2Java().translate(module));
	}

	public void testReadBubblesort() throws ParseException, TransposeException {
		IModule module = Transpose.read(BUBBLESORT_JSON);
		// TODO check structure

		System.out.println(new Paddle2Java().translate(module));
//		System.out.println(new Paddle2Java("TestBubble").translate(module));
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
			      "type": "int",
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
			            "Expression": "Binary Op",
			            "op": "mul",
			            "left": {
			              "Expression": "Literal",
			              "type": "int",
			              "value": 2
			            },
			            "right": {
			              "Expression": "Variable",
			              "variable": "1aa70b1a-79fc-47db-ace9-e7b3f28abf3f"
			            }
			          }
			        }
			      ]
			    },
			    {
			      "uuid": "08175f73-e0cc-4656-b0be-081c7866b2bf",
			      "id": null,
			      "type": "void",
			      "parameters": [],
			      "body": [
			        {
			          "Statement": "Call",
			          "procedure": "c6672984-2a2d-4ffa-bb83-67dff5734aee",
			          "arguments": [
			            {
			              "Expression": "Literal",
			              "type": "int",
			              "value": 2
			            }
			          ]
			        }
			      ]
			    }
			  ]
			}
			""";

	public static String BUBBLESORT_JSON = """
			{
			  "format": "pseudocodejson",
			  "version": "1.0.0",
			  "type": "Module",
			  "id": null,
			  "constants": [],
			  "procedures": [
			    {
			      "uuid": "9ce394dd-c071-4ac2-ac0f-af28b65deedc",
			      "id": "bubblesort",
			      "type": "unknown",
			      "parameters": [
			        {
			          "Statement": "Variable",
			          "uuid": "f1ffef34-45a0-4fbd-84ef-bf5d63da749b",
			          "id": "a",
			          "type": "unknown"
			        }
			      ],
			      "body": [
			        {
			          "Statement": "Variable",
			          "uuid": "87140ef0-27bf-4c1a-befe-1f987fe78c19",
			          "id": "changes",
			          "type": "unknown"
			        },
			        {
			          "Statement": "Assignment",
			          "variable": "87140ef0-27bf-4c1a-befe-1f987fe78c19",
			          "expression": {
			            "Expression": "Literal",
			            "type": "boolean",
			            "value": true
			          }
			        },
			        {
			          "Statement": "Loop",
			          "guard": {
			            "Expression": "Variable",
			            "variable": "87140ef0-27bf-4c1a-befe-1f987fe78c19"
			          },
			          "body": [
			            {
			              "Statement": "Assignment",
			              "variable": "87140ef0-27bf-4c1a-befe-1f987fe78c19",
			              "expression": {
			                "Expression": "Literal",
			                "type": "boolean",
			                "value": false
			              }
			            },
			            {
			              "Statement": "Variable",
			              "uuid": "80b8e982-bf7f-4b08-ba48-1056a9e4578f",
			              "id": "i",
			              "type": "unknown"
			            },
			            {
			              "Statement": "Assignment",
			              "variable": "80b8e982-bf7f-4b08-ba48-1056a9e4578f",
			              "expression": {
			                "Expression": "Literal",
			                "type": "int",
			                "value": 1
			              }
			            },
			            {
			              "Statement": "Loop",
			              "guard": {
			                "Expression": "Binary Op",
			                "op": "different",
			                "left": {
			                  "Expression": "Variable",
			                  "variable": "80b8e982-bf7f-4b08-ba48-1056a9e4578f"
			                },
			                "right": {
			                  "Expression": "Array Length",
			                  "target": {
			                    "Expression": "Variable",
			                    "variable": "f1ffef34-45a0-4fbd-84ef-bf5d63da749b"
			                  },
			                  "indexes": []
			                }
			              },
			              "body": [
			                {
			                  "Statement": "Selection",
			                  "guard": {
			                    "Expression": "Binary Op",
			                    "op": "smaller",
			                    "left": {
			                      "Expression": "Array Element",
			                      "target": {
			                        "Expression": "Variable",
			                        "variable": "f1ffef34-45a0-4fbd-84ef-bf5d63da749b"
			                      },
			                      "indexes": [
			                        {
			                          "Expression": "Variable",
			                          "variable": "80b8e982-bf7f-4b08-ba48-1056a9e4578f"
			                        }
			                      ]
			                    },
			                    "right": {
			                      "Expression": "Array Element",
			                      "target": {
			                        "Expression": "Variable",
			                        "variable": "f1ffef34-45a0-4fbd-84ef-bf5d63da749b"
			                      },
			                      "indexes": [
			                        {
			                          "Expression": "Binary Op",
			                          "op": "sub",
			                          "left": {
			                            "Expression": "Variable",
			                            "variable": "80b8e982-bf7f-4b08-ba48-1056a9e4578f"
			                          },
			                          "right": {
			                            "Expression": "Literal",
			                            "type": "int",
			                            "value": 1
			                          }
			                        }
			                      ]
			                    }
			                  },
			                  "body": [
			                    {
			                      "Statement": "Array Assignment",
			                      "target": {
			                        "Expression": "Variable",
			                        "variable": "f1ffef34-45a0-4fbd-84ef-bf5d63da749b"
			                      },
			                      "indexes": [
			                        {
			                          "Expression": "Variable",
			                          "variable": "80b8e982-bf7f-4b08-ba48-1056a9e4578f"
			                        }
			                      ],
			                      "expression": {
			                        "Expression": "Array Element",
			                        "target": {
			                          "Expression": "Variable",
			                          "variable": "f1ffef34-45a0-4fbd-84ef-bf5d63da749b"
			                        },
			                        "indexes": [
			                          {
			                            "Expression": "Binary Op",
			                            "op": "sub",
			                            "left": {
			                              "Expression": "Variable",
			                              "variable": "80b8e982-bf7f-4b08-ba48-1056a9e4578f"
			                            },
			                            "right": {
			                              "Expression": "Literal",
			                              "type": "int",
			                              "value": 1
			                            }
			                          }
			                        ]
			                      }
			                    },
			                    {
			                      "Statement": "Array Assignment",
			                      "target": {
			                        "Expression": "Variable",
			                        "variable": "f1ffef34-45a0-4fbd-84ef-bf5d63da749b"
			                      },
			                      "indexes": [
			                        {
			                          "Expression": "Binary Op",
			                          "op": "sub",
			                          "left": {
			                            "Expression": "Variable",
			                            "variable": "80b8e982-bf7f-4b08-ba48-1056a9e4578f"
			                          },
			                          "right": {
			                            "Expression": "Literal",
			                            "type": "int",
			                            "value": 1
			                          }
			                        }
			                      ],
			                      "expression": {
			                        "Expression": "Array Element",
			                        "target": {
			                          "Expression": "Variable",
			                          "variable": "f1ffef34-45a0-4fbd-84ef-bf5d63da749b"
			                        },
			                        "indexes": [
			                          {
			                            "Expression": "Variable",
			                            "variable": "80b8e982-bf7f-4b08-ba48-1056a9e4578f"
			                          }
			                        ]
			                      }
			                    },
			                    {
			                      "Statement": "Assignment",
			                      "variable": "87140ef0-27bf-4c1a-befe-1f987fe78c19",
			                      "expression": {
			                        "Expression": "Literal",
			                        "type": "boolean",
			                        "value": true
			                      }
			                    }
			                  ],
			                  "alternative": []
			                },
			                {
			                  "Statement": "Assignment",
			                  "variable": "80b8e982-bf7f-4b08-ba48-1056a9e4578f",
			                  "expression": {
			                    "Expression": "Binary Op",
			                    "op": "add",
			                    "left": {
			                      "Expression": "Variable",
			                      "variable": "80b8e982-bf7f-4b08-ba48-1056a9e4578f"
			                    },
			                    "right": {
			                      "Expression": "Literal",
			                      "type": "int",
			                      "value": 1
			                    }
			                  }
			                }
			              ]
			            }
			          ]
			        },
			        {
			          "Statement": "Return",
			          "expression": {
			            "Expression": "Variable",
			            "variable": "f1ffef34-45a0-4fbd-84ef-bf5d63da749b"
			          }
			        }
			      ]
			    }
			  ]
			}
									""";
}
