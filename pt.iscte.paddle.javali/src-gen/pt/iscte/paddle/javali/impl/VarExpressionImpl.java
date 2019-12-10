/**
 * generated by Xtext 2.19.0
 */
package pt.iscte.paddle.javali.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import pt.iscte.paddle.javali.Expression;
import pt.iscte.paddle.javali.Identifier;
import pt.iscte.paddle.javali.JavaliPackage;
import pt.iscte.paddle.javali.VarExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Var Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link pt.iscte.paddle.javali.impl.VarExpressionImpl#getParts <em>Parts</em>}</li>
 *   <li>{@link pt.iscte.paddle.javali.impl.VarExpressionImpl#getArrayIndexes <em>Array Indexes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VarExpressionImpl extends ExpressionImpl implements VarExpression
{
  /**
   * The cached value of the '{@link #getParts() <em>Parts</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParts()
   * @generated
   * @ordered
   */
  protected EList<Identifier> parts;

  /**
   * The cached value of the '{@link #getArrayIndexes() <em>Array Indexes</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArrayIndexes()
   * @generated
   * @ordered
   */
  protected EList<Expression> arrayIndexes;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected VarExpressionImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return JavaliPackage.Literals.VAR_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<Identifier> getParts()
  {
    if (parts == null)
    {
      parts = new EObjectContainmentEList<Identifier>(Identifier.class, this, JavaliPackage.VAR_EXPRESSION__PARTS);
    }
    return parts;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<Expression> getArrayIndexes()
  {
    if (arrayIndexes == null)
    {
      arrayIndexes = new EObjectContainmentEList<Expression>(Expression.class, this, JavaliPackage.VAR_EXPRESSION__ARRAY_INDEXES);
    }
    return arrayIndexes;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case JavaliPackage.VAR_EXPRESSION__PARTS:
        return ((InternalEList<?>)getParts()).basicRemove(otherEnd, msgs);
      case JavaliPackage.VAR_EXPRESSION__ARRAY_INDEXES:
        return ((InternalEList<?>)getArrayIndexes()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case JavaliPackage.VAR_EXPRESSION__PARTS:
        return getParts();
      case JavaliPackage.VAR_EXPRESSION__ARRAY_INDEXES:
        return getArrayIndexes();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case JavaliPackage.VAR_EXPRESSION__PARTS:
        getParts().clear();
        getParts().addAll((Collection<? extends Identifier>)newValue);
        return;
      case JavaliPackage.VAR_EXPRESSION__ARRAY_INDEXES:
        getArrayIndexes().clear();
        getArrayIndexes().addAll((Collection<? extends Expression>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case JavaliPackage.VAR_EXPRESSION__PARTS:
        getParts().clear();
        return;
      case JavaliPackage.VAR_EXPRESSION__ARRAY_INDEXES:
        getArrayIndexes().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case JavaliPackage.VAR_EXPRESSION__PARTS:
        return parts != null && !parts.isEmpty();
      case JavaliPackage.VAR_EXPRESSION__ARRAY_INDEXES:
        return arrayIndexes != null && !arrayIndexes.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //VarExpressionImpl
