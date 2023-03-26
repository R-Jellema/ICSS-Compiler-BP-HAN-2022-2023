package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.Expression;
import nl.han.ica.icss.ast.IfClause;
import nl.han.ica.icss.ast.types.ExpressionType;

public class IfClauseExpressionsChecker {

    private final ExpressionsChecker exprChecker;

    public IfClauseExpressionsChecker(ExpressionsChecker exprChecker) {
        this.exprChecker = exprChecker;
    }

    public ExpressionType checkIfClauseExpr(IfClause ifClause) {
        Expression conditionalExpr = ifClause.conditionalExpression;
        ExpressionType exprType = this.exprChecker.checkExprType(conditionalExpr);

        if (exprType != ExpressionType.BOOL) {
            ifClause.setError("ConditionalExpression should be a boolean literal. At line: " + ifClause.getLine(), ErrorType.ERROR);
            return ExpressionType.UNDEFINED;
        }

        return ExpressionType.BOOL;
    }
}
