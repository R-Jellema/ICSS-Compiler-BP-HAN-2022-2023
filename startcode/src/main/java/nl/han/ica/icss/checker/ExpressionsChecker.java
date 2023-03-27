package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.Expression;
import nl.han.ica.icss.ast.Operation;
import nl.han.ica.icss.ast.VariableReference;
import nl.han.ica.icss.ast.types.ExpressionType;

public class ExpressionsChecker {
    private final OperationsChecker operationsChecker;
    private final VariableChecker varChecker;

    public ExpressionsChecker(VariableChecker varChecker) {
        this.varChecker = varChecker;
        this.operationsChecker = new OperationsChecker(this);
    }

    public ExpressionType checkExpr(Expression expr) {
        if (expr instanceof Operation) {
            return this.operationsChecker.operationChecker((Operation) expr);
        }

        return this.checkExprType(expr);
    }

    public ExpressionType checkExprType(Expression expr) {
        switch (expr.getClass().getSimpleName()) {
            case "VariableReference":
                return this.varChecker.checkVarRef((VariableReference) expr);
            case "PercentageLiteral":
                return ExpressionType.PERCENTAGE;
            case "PixelLiteral":
                return ExpressionType.PIXEL;
            case "ColorLiteral":
                return ExpressionType.COLOR;
            case "ScalarLiteral":
                return ExpressionType.SCALAR;
            case "BoolLiteral":
                return ExpressionType.BOOL;
            default:
                return ExpressionType.UNDEFINED;
        }
    }
}
