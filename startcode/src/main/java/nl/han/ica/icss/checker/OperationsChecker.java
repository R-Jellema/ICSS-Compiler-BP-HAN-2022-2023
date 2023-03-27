package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.Expression;
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
        var leftExprType = getExpressionType(operation.lhs);
        var rightExprType = getExpressionType(operation.rhs);

        if (containsInvalidTypes(leftExprType, rightExprType)) {
            var message = "Using color literals or boolean literals in multiplication, addition, or subtraction operations is not allowed. At line: " + operation.getLine();
            operation.setError(message, ErrorType.ERROR);
            return ExpressionType.UNDEFINED;
        }

        if (operation instanceof MultiplicationOperation) {
            if (!containsScalarLiteral(leftExprType, rightExprType)) {
                var message = "Multiplication is only possible if either left or right side of the expression contains a scalar literal at line: " + operation.getLine();
                operation.setError(message, ErrorType.ERROR);
                return ExpressionType.UNDEFINED;
            }
            return rightExprType != ExpressionType.SCALAR ? rightExprType : leftExprType;
        } else if (operationIsOfTypeSubtractionOrAddition(operation) && !hasSameTypes(leftExprType, rightExprType)) {
            var message = "Subtraction or addition operations are only allowed with the same types. At line: " + operation.getLine();
            operation.setError(message, ErrorType.ERROR);
            return ExpressionType.UNDEFINED;
        }

        return leftExprType;
    }


    private boolean operationIsOfTypeSubtractionOrAddition(Operation operation) {
        return operation instanceof SubtractOperation || operation instanceof AddOperation;
    }
    private ExpressionType getExpressionType(Expression expr) {
        return (expr instanceof Operation) ? operationChecker((Operation) expr) : exprChecker.checkExprType(expr);
    }

    private boolean containsInvalidTypes(ExpressionType left, ExpressionType right) {
        return left == ExpressionType.COLOR || right == ExpressionType.COLOR || left == ExpressionType.BOOL || right == ExpressionType.BOOL;
    }

    private boolean containsScalarLiteral(ExpressionType left, ExpressionType right) {
        return left == ExpressionType.SCALAR || right == ExpressionType.SCALAR;
    }

    private boolean hasSameTypes(ExpressionType left, ExpressionType right) {
        return left == right;
    }
}
