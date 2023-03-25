package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.Operation;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplicationOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.types.ExpressionType;

public class OperationsChecker {
    private final ExpressionsChecker expressionsChecker;

    public OperationsChecker(ExpressionsChecker expressionsChecker) {
        this.expressionsChecker = expressionsChecker;
    }

    public ExpressionType check(Operation operation) {

        var leftExpressionType = ExpressionType.UNDEFINED;
        var rightExpressionType = ExpressionType.UNDEFINED;

        if (operation.lhs instanceof Operation) {
            leftExpressionType = this.check((Operation) operation.lhs);
        } else {
            leftExpressionType = this.expressionsChecker.expressionTypeCheck(operation.lhs);
        }

        if (operation.rhs instanceof Operation) {
            rightExpressionType = this.check((Operation) operation.rhs);
        } else {
            rightExpressionType = this.expressionsChecker.expressionTypeCheck(operation.rhs);
        }

        if (leftExpressionType == ExpressionType.COLOR || rightExpressionType == ExpressionType.COLOR || leftExpressionType == ExpressionType.BOOL || rightExpressionType == ExpressionType.BOOL) {
            operation.setError("Using color literals or boolean literals in operations is not allowed.", ErrorType.ERROR);
            return ExpressionType.UNDEFINED;
        }

        if (operation instanceof MultiplicationOperation) {
            if (leftExpressionType != ExpressionType.SCALAR && rightExpressionType != ExpressionType.SCALAR) {
                operation.setError("Multiplication is only allowed with at least one scalar literal.", ErrorType.ERROR);
                return ExpressionType.UNDEFINED;
            }
            return rightExpressionType != ExpressionType.SCALAR ? rightExpressionType : leftExpressionType;
        } else if ((operation instanceof SubtractOperation || operation instanceof AddOperation) && leftExpressionType != rightExpressionType) {
            operation.setError("Subtraction or Addition operations are only allowed with the same scalar literal .", ErrorType.ERROR);
            return ExpressionType.UNDEFINED;
        }

        return leftExpressionType;
    }
}
