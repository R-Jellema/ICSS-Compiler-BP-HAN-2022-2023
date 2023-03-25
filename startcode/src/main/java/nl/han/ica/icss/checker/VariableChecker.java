package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.ScopeHolder;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.VariableAssignment;
import nl.han.ica.icss.ast.VariableReference;
import nl.han.ica.icss.ast.types.ExpressionType;

public class VariableChecker {
    private final ScopeHolder<String, ExpressionType> variableTypes;
    private final ExpressionsChecker expressionsChecker;

    public VariableChecker(ScopeHolder<String, ExpressionType> variableTypes) {
        this.variableTypes = variableTypes;
        this.expressionsChecker = new ExpressionsChecker(this);
    }

    public void variableAssignmentChecker(ASTNode astNode) {
        var variableAssignment = (VariableAssignment) astNode;
        var variableReference = variableAssignment.name;
        var expressionType = this.expressionsChecker.expressionCheck(variableAssignment.expression);

        if (expressionType == null || expressionType == ExpressionType.UNDEFINED) {
            astNode.setError("Variable assignment is invalid because of faulty expression.", ErrorType.ERROR);
            return;
        }

        ExpressionType previousExpressionType = this.variableTypes.getVariable(variableReference.name);
        if (knownVariableIsNowDifferentType(expressionType, previousExpressionType)) {
            astNode.setError("Variable of type \"" + previousExpressionType + "\" can't hold a value of type \"" + expressionType + "\"", ErrorType.ERROR);
        }

        this.variableTypes.addVariable(variableReference.name, expressionType);
    }

    public ExpressionType variableReferenceChecker(VariableReference variableReference) {
        var expressionType = variableTypes.getVariable((variableReference).name);
        if (expressionType == null) {
            variableReference.setError("Variable not yet declared or in scope.", ErrorType.ERROR);
            return null;
        }
        return expressionType;
    }

    private boolean knownVariableIsNowDifferentType(ExpressionType expressionType, ExpressionType previousExpressionType) {
        return (previousExpressionType != null) && expressionType != previousExpressionType;
    }
}
