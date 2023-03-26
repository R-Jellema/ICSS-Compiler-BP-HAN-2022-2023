package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.Operation;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplicationOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.types.ExpressionType;

public class OperationsChecker {
    private final ExpressionsChecker exprChecker;

    public OperationsChecker(ExpressionsChecker exprChecker) {
        this.exprChecker = exprChecker;
    }

    public ExpressionType operationChecker(Operation operation) {

        var leftExprType = ExpressionType.UNDEFINED;
        var rightExprType = ExpressionType.UNDEFINED;

        if (operation.lhs instanceof Operation) {
            leftExprType = this.operationChecker((Operation) operation.lhs);
        } else {
            leftExprType = this.exprChecker.checkExprType(operation.lhs);
        }

        if (operation.rhs instanceof Operation) {
            rightExprType = this.operationChecker((Operation) operation.rhs);
        } else {
            rightExprType = this.exprChecker.checkExprType(operation.rhs);
        }

        if (leftExprType == ExpressionType.COLOR || rightExprType == ExpressionType.COLOR || leftExprType == ExpressionType.BOOL || rightExprType == ExpressionType.BOOL) {
            operation.setError("Using color literals or boolean literals in Mutliplication, Addition or Subtraction operations is not allowed. At line: " + operation.getLine(), ErrorType.ERROR);
            return ExpressionType.UNDEFINED;
        }

        if (operation instanceof MultiplicationOperation) {
            if (leftExprType != ExpressionType.SCALAR && rightExprType != ExpressionType.SCALAR) {
                operation.setError("Multiplication is only possible if either left or right side op the expression conatins a scalar literal at line: " + operation.getLine(), ErrorType.ERROR);
                return ExpressionType.UNDEFINED;
            }
            return rightExprType != ExpressionType.SCALAR ? rightExprType : leftExprType;
        } else if ((operation instanceof SubtractOperation || operation instanceof AddOperation) && leftExprType != rightExprType) {
            operation.setError("Subtraction or Addition operations are only allowed with the same types. At line: " + operation.getLine(), ErrorType.ERROR);
            return ExpressionType.UNDEFINED;
        }

        return leftExprType;
    }
}
