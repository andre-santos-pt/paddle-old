/**
 * generated by Xtext 2.17.0
 */
package pt.iscte.paddle.javali.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import pt.iscte.paddle.javali.Addition;
import pt.iscte.paddle.javali.And;
import pt.iscte.paddle.javali.Block;
import pt.iscte.paddle.javali.Break;
import pt.iscte.paddle.javali.Constant;
import pt.iscte.paddle.javali.Continue;
import pt.iscte.paddle.javali.Decrement;
import pt.iscte.paddle.javali.DoWhile;
import pt.iscte.paddle.javali.Equality;
import pt.iscte.paddle.javali.Expression;
import pt.iscte.paddle.javali.For;
import pt.iscte.paddle.javali.Identifier;
import pt.iscte.paddle.javali.IfElse;
import pt.iscte.paddle.javali.Increment;
import pt.iscte.paddle.javali.JavaliFactory;
import pt.iscte.paddle.javali.JavaliPackage;
import pt.iscte.paddle.javali.Literal;
import pt.iscte.paddle.javali.Multiplication;
import pt.iscte.paddle.javali.NewArray;
import pt.iscte.paddle.javali.NewObject;
import pt.iscte.paddle.javali.Null;
import pt.iscte.paddle.javali.Or;
import pt.iscte.paddle.javali.ProcCall;
import pt.iscte.paddle.javali.Procedure;
import pt.iscte.paddle.javali.Record;
import pt.iscte.paddle.javali.Relation;
import pt.iscte.paddle.javali.Return;
import pt.iscte.paddle.javali.Statement;
import pt.iscte.paddle.javali.Type;
import pt.iscte.paddle.javali.VarAssign;
import pt.iscte.paddle.javali.VarDeclaration;
import pt.iscte.paddle.javali.VarExpression;
import pt.iscte.paddle.javali.While;
import pt.iscte.paddle.javali.Xor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class JavaliFactoryImpl extends EFactoryImpl implements JavaliFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static JavaliFactory init()
  {
    try
    {
      JavaliFactory theJavaliFactory = (JavaliFactory)EPackage.Registry.INSTANCE.getEFactory(JavaliPackage.eNS_URI);
      if (theJavaliFactory != null)
      {
        return theJavaliFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new JavaliFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JavaliFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case JavaliPackage.MODULE: return createModule();
      case JavaliPackage.CONSTANT: return createConstant();
      case JavaliPackage.RECORD: return createRecord();
      case JavaliPackage.PROCEDURE: return createProcedure();
      case JavaliPackage.BLOCK: return createBlock();
      case JavaliPackage.STATEMENT: return createStatement();
      case JavaliPackage.RETURN: return createReturn();
      case JavaliPackage.BREAK: return createBreak();
      case JavaliPackage.CONTINUE: return createContinue();
      case JavaliPackage.VAR_DECLARATION: return createVarDeclaration();
      case JavaliPackage.VAR_ASSIGN: return createVarAssign();
      case JavaliPackage.IF_ELSE: return createIfElse();
      case JavaliPackage.WHILE: return createWhile();
      case JavaliPackage.FOR: return createFor();
      case JavaliPackage.DO_WHILE: return createDoWhile();
      case JavaliPackage.INCREMENT: return createIncrement();
      case JavaliPackage.DECREMENT: return createDecrement();
      case JavaliPackage.EXPRESSION: return createExpression();
      case JavaliPackage.LITERAL: return createLiteral();
      case JavaliPackage.NULL: return createNull();
      case JavaliPackage.VAR_EXPRESSION: return createVarExpression();
      case JavaliPackage.PROC_CALL: return createProcCall();
      case JavaliPackage.TYPE: return createType();
      case JavaliPackage.NEW_ARRAY: return createNewArray();
      case JavaliPackage.NEW_OBJECT: return createNewObject();
      case JavaliPackage.IDENTIFIER: return createIdentifier();
      case JavaliPackage.OR: return createOr();
      case JavaliPackage.XOR: return createXor();
      case JavaliPackage.AND: return createAnd();
      case JavaliPackage.EQUALITY: return createEquality();
      case JavaliPackage.RELATION: return createRelation();
      case JavaliPackage.ADDITION: return createAddition();
      case JavaliPackage.MULTIPLICATION: return createMultiplication();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public pt.iscte.paddle.javali.Module createModule()
  {
    ModuleImpl module = new ModuleImpl();
    return module;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Constant createConstant()
  {
    ConstantImpl constant = new ConstantImpl();
    return constant;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Record createRecord()
  {
    RecordImpl record = new RecordImpl();
    return record;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Procedure createProcedure()
  {
    ProcedureImpl procedure = new ProcedureImpl();
    return procedure;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Block createBlock()
  {
    BlockImpl block = new BlockImpl();
    return block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Statement createStatement()
  {
    StatementImpl statement = new StatementImpl();
    return statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Return createReturn()
  {
    ReturnImpl return_ = new ReturnImpl();
    return return_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Break createBreak()
  {
    BreakImpl break_ = new BreakImpl();
    return break_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Continue createContinue()
  {
    ContinueImpl continue_ = new ContinueImpl();
    return continue_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public VarDeclaration createVarDeclaration()
  {
    VarDeclarationImpl varDeclaration = new VarDeclarationImpl();
    return varDeclaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public VarAssign createVarAssign()
  {
    VarAssignImpl varAssign = new VarAssignImpl();
    return varAssign;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public IfElse createIfElse()
  {
    IfElseImpl ifElse = new IfElseImpl();
    return ifElse;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public While createWhile()
  {
    WhileImpl while_ = new WhileImpl();
    return while_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public For createFor()
  {
    ForImpl for_ = new ForImpl();
    return for_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public DoWhile createDoWhile()
  {
    DoWhileImpl doWhile = new DoWhileImpl();
    return doWhile;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Increment createIncrement()
  {
    IncrementImpl increment = new IncrementImpl();
    return increment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Decrement createDecrement()
  {
    DecrementImpl decrement = new DecrementImpl();
    return decrement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Expression createExpression()
  {
    ExpressionImpl expression = new ExpressionImpl();
    return expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Literal createLiteral()
  {
    LiteralImpl literal = new LiteralImpl();
    return literal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Null createNull()
  {
    NullImpl null_ = new NullImpl();
    return null_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public VarExpression createVarExpression()
  {
    VarExpressionImpl varExpression = new VarExpressionImpl();
    return varExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ProcCall createProcCall()
  {
    ProcCallImpl procCall = new ProcCallImpl();
    return procCall;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Type createType()
  {
    TypeImpl type = new TypeImpl();
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NewArray createNewArray()
  {
    NewArrayImpl newArray = new NewArrayImpl();
    return newArray;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NewObject createNewObject()
  {
    NewObjectImpl newObject = new NewObjectImpl();
    return newObject;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Identifier createIdentifier()
  {
    IdentifierImpl identifier = new IdentifierImpl();
    return identifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Or createOr()
  {
    OrImpl or = new OrImpl();
    return or;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Xor createXor()
  {
    XorImpl xor = new XorImpl();
    return xor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public And createAnd()
  {
    AndImpl and = new AndImpl();
    return and;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Equality createEquality()
  {
    EqualityImpl equality = new EqualityImpl();
    return equality;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Relation createRelation()
  {
    RelationImpl relation = new RelationImpl();
    return relation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Addition createAddition()
  {
    AdditionImpl addition = new AdditionImpl();
    return addition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Multiplication createMultiplication()
  {
    MultiplicationImpl multiplication = new MultiplicationImpl();
    return multiplication;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public JavaliPackage getJavaliPackage()
  {
    return (JavaliPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static JavaliPackage getPackage()
  {
    return JavaliPackage.eINSTANCE;
  }

} //JavaliFactoryImpl