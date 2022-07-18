package com.radian.foundation.os.persistence.spi.expression;

/**
 * An expression visitor.
 * 
 * @author Giedrius Trumpickas
 */
public interface ExpressionVisitor
{
	void visitOrExpression(OrExpression exp);
	void visitOrExpressionChild(OrExpression exp, int index);
	void leaveOrExpressionChild(OrExpression exp, int index);
	void leaveOrExpression(OrExpression exp);

	void visitAndExpression(AndExpression exp);
	void visitAndExpressionChild(AndExpression exp, int index);
	void leaveAndExpressionChild(AndExpression exp, int index);
	void leaveAndExpression(AndExpression exp);

	void visitNotExpression(NotExpression exp);
	void visitNotExpressionChild(NotExpression exp, int index);
	void leaveNotExpressionChild(NotExpression exp, int index);
	void leaveNotExpression(NotExpression exp);

	void visitPropertyExpression(PropertyExpression exp);
	void visitPropertyExpressionChild(PropertyExpression exp, int index);
	void leavePropertyExpressionChild(PropertyExpression exp, int index);
	void leavePropertyExpression(PropertyExpression exp);

	void visitUnnamedParameterExpression(UnnamedParameterExpression exp);
	void visitUnnamedParameterExpressionChild(UnnamedParameterExpression exp, int index);
	void leaveUnnamedParameterExpressionChild(UnnamedParameterExpression exp, int index);
	void leaveUnnamedParameterExpression(UnnamedParameterExpression exp);

	void visitNamedParameterExpression(NamedParameterExpression exp);
	void visitNamedParameterExpressionChild(NamedParameterExpression exp, int index);
	void leaveNamedParameterExpressionChild(NamedParameterExpression exp, int index);
	void leaveNamedParameterExpression(NamedParameterExpression exp);

	void visitEqualsExpression(EqualsExpression exp);
	void visitEqualsExpressionChild(EqualsExpression exp, int index);
	void leaveEqualsExpressionChild(EqualsExpression exp, int index);
	void leaveEqualsExpression(EqualsExpression exp);

	void visitNotEqualsExpression(NotEqualsExpression exp);
	void visitNotEqualsExpressionChild(NotEqualsExpression exp, int index);
	void leaveNotEqualsExpressionChild(NotEqualsExpression exp, int index);
	void leaveNotEqualsExpression(NotEqualsExpression exp);

	void visitGreaterThanExpression(GreaterThanExpression exp);
	void visitGreaterThanExpressionChild(GreaterThanExpression exp, int index);
	void leaveGreaterThanExpressionChild(GreaterThanExpression exp, int index);
	void leaveGreaterThanExpression(GreaterThanExpression exp);

	void visitGreaterThanEqualsExpression(GreaterThanEqualsExpression exp);
	void visitGreaterThanEqualsExpressionChild(GreaterThanEqualsExpression exp, int index);
	void leaveGreaterThanEqualsExpressionChild(GreaterThanEqualsExpression exp, int index);
	void leaveGreaterThanEqualsExpression(GreaterThanEqualsExpression exp);

	void visitLessThanExpression(LessThanExpression exp);
	void visitLessThanExpressionChild(LessThanExpression exp, int index);
	void leaveLessThanExpressionChild(LessThanExpression exp, int index);
	void leaveLessThanExpression(LessThanExpression exp);

	void visitLessThanEqualsExpression(LessThanEqualsExpression exp);
	void visitLessThanEqualsExpressionChild(LessThanEqualsExpression exp, int index);
	void leaveLessThanEqualsExpressionChild(LessThanEqualsExpression exp, int index);
	void leaveLessThanEqualsExpression(LessThanEqualsExpression exp);

	void visitValueExpression(ValueExpression exp);
	void visitValueExpressionChild(ValueExpression exp, int index);
	void leaveValueExpressionChild(ValueExpression exp, int index);
	void leaveValueExpression(ValueExpression exp);

	void visitLikeExpression(LikeExpression exp);
	void visitLikeExpressionChild(LikeExpression exp, int index);
	void leaveLikeExpressionChild(LikeExpression exp, int index);
	void leaveLikeExpression(LikeExpression exp);

	void visitInExpression(InExpression exp);
	void visitInExpressionChild(InExpression exp, int index);
	void leaveInExpression(InExpression exp);
	void leaveInExpressionChild(InExpression exp, int index);

	void visitValuesListExpression(ValuesListExpression exp);
	void leaveValuesListExpression(ValuesListExpression exp);
	void visitValuesListExpressionChild(ValuesListExpression exp, int index);
	void leaveValuesListExpressionChild(ValuesListExpression exp, int index);

	void visitBetweenExpression(BetweenExpression exp);
	void leaveBetweenExpression(BetweenExpression exp);
	void visitBetweenExpressionChild(BetweenExpression exp, int index);
	void leaveBetweenExpressionChild(BetweenExpression exp, int index);

	void visitIsNullExpression(IsNullExpression exp);
	void leaveIsNullExpression(IsNullExpression exp);
	void visitIsNullExpressionChild(IsNullExpression exp, int index);
	void leaveIsNullExpressionChild(IsNullExpression exp, int index);

	void visitToUpperCaseExpression(ToUpperCaseExpression exp);
	void leaveToUpperCaseExpression(ToUpperCaseExpression exp);
	void visitToUpperCaseExpressionChild(ToUpperCaseExpression exp, int index);
	void leaveToUpperCaseExpressionChild(ToUpperCaseExpression exp, int index);
	
	void visitElementsExpression(ElementsExpression exp);
	void leaveElementsExpression(ElementsExpression exp);
	void visitElementsExpressionChild(ElementsExpression exp, int index);
	void leaveElementsExpressionChild(ElementsExpression exp, int index);
}
