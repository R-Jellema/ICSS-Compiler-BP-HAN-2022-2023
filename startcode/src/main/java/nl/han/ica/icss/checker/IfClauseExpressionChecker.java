package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.Expression;
import nl.han.ica.icss.ast.IfClause;
import nl.han.ica.icss.ast.types.ExpressionType;

public class IfClauseExpressionChecker {

    private final ExpressionsChecker expressionsChecker;

    public IfClauseExpressionChecker(ExpressionsChecker expressionsChecker) {
        this.expressionsChecker = expressionsChecker;
    }

    public void check(IfClause ifClause) {
        Expression conditionalExpression = ifClause.conditionalExpression;
        ExpressionType expressionType = this.expressionsChecker.expressionTypeCheck(conditionalExpression);

        if (expressionType != ExpressionType.BOOL) {
            ifClause.setError("ConditionalExpression should be a boolean literal.", ErrorType.ERROR);
        }

    }
}
