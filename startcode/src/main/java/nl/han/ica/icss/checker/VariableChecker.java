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

    public void varAssignChecker(ASTNode astNode) {
        var variableAssign = (VariableAssignment) astNode;
        var varRef = variableAssign.name;
        var exprType = this.expressionsChecker.checkExpr(variableAssign.expression);

        if (exprType == null || exprType == ExpressionType.UNDEFINED) {
            astNode.setError("Variable assignment is invalid and thus not possible because of an invalid expression at line: " + variableAssign.getLine(), ErrorType.ERROR);
            return;
        }

        ExpressionType prevExprType = this.variableTypes.getVariable(varRef.name);
        if (isVarTypeSameOnReAssignment(exprType, prevExprType)) {
            astNode.setError("Variable of type \"" + prevExprType + "\" can't hold a value of type \"" + exprType + "\". At line: " + varRef.getLine(), ErrorType.ERROR);
        }

        this.variableTypes.addVariable(varRef.name, exprType);
    }

    public ExpressionType checkVarRef(VariableReference varRef) {
        var exprType = variableTypes.getVariable((varRef).name);
        if (exprType == null) {
            varRef.setError("Variable usage invalid. Is the variable not declared or possible declared in an unavailable scope? At line: " + varRef.getLine(), ErrorType.WARN);
            return null;
        }
        return exprType;
    }

    private boolean isVarTypeSameOnReAssignment(ExpressionType exprType, ExpressionType prevExprType) {
        return (prevExprType != null) && exprType != prevExprType;
    }
}
