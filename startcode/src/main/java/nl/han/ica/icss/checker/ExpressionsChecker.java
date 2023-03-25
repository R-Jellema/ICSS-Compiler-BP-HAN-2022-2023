package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.Expression;
import nl.han.ica.icss.ast.Operation;
import nl.han.ica.icss.ast.VariableReference;
import nl.han.ica.icss.ast.types.ExpressionType;

public class ExpressionsChecker {
    private final OperationsChecker operationsChecker;
    private final VariableChecker variableChecker;

    public ExpressionsChecker(VariableChecker variableChecker) {
        this.variableChecker = variableChecker;
        this.operationsChecker = new OperationsChecker(this);
    }

    public ExpressionType expressionCheck(ASTNode astNode) {
        var expression = (Expression) astNode;

        if (expression instanceof Operation) {
            return this.operationsChecker.check((Operation) expression);
        }

        return this.expressionTypeCheck(expression);
    }

    public ExpressionType expressionTypeCheck(Expression expression) {
        switch (expression.getClass().getSimpleName()) {
            case "VariableReference":
                return this.variableChecker.variableReferenceChecker((VariableReference) expression);
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
