package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.ScopeHolder;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.ArrayList;


public class Checker {
    private final ScopeHolder<String, ExpressionType> scopes;
    private final ExpressionsChecker exprChecker;
    private final IfClauseExpressionsChecker ifClauseExprChecker;
    private final VariableChecker varChecker;

    public Checker() {
        scopes = new ScopeHolder<>();
        varChecker = new VariableChecker(scopes);
        exprChecker = new ExpressionsChecker(varChecker);
        ifClauseExprChecker = new IfClauseExpressionsChecker(exprChecker);
    }

    public void check(AST ast) {
        stylesheetChecker((Stylesheet) ast.root);
    }

    private void stylesheetChecker(Stylesheet sheet) {
        scopes.openScope();

        sheet.getChildren().forEach(child -> {
            if (child instanceof VariableAssignment)
                castToCorrectTypeAndCheck(child);
            else if (child instanceof StyleRule) {
                scopes.openScope();
                castToCorrectTypeAndCheck(child);
                scopes.closeScope();
            }
        });

        scopes.closeScope();
    }

    private void ruleBodyChecker(ArrayList<ASTNode> nodes) {
        nodes.forEach(this::castToCorrectTypeAndCheck);
    }

    private void castToCorrectTypeAndCheck(ASTNode node){
        if (node instanceof IfClause)
            ifClauseChecker((IfClause) node);
        else if (node instanceof Declaration)
            declChecker((Declaration) node);
        else if (node instanceof VariableAssignment)
            varChecker.varAssignChecker((VariableAssignment) node);
        else if (node instanceof StyleRule)
            ruleBodyChecker(((StyleRule) node).body);
    }

    private void ifClauseChecker(IfClause ifClause) {
        scopes.openScope();
            ifClauseExprChecker.checkIfClauseExpr(ifClause);
            ruleBodyChecker(ifClause.body);
        scopes.closeScope();

        if (ifClause.elseClause == null)  return;

        scopes.openScope();
        elseClauseChecker(ifClause.elseClause);
        scopes.closeScope();
    }

    private void elseClauseChecker(ElseClause elseClause) {
        ruleBodyChecker(elseClause.body);
    }

    private void declChecker(Declaration decl) {
        var exprType = exprChecker.checkExpr(decl.expression);

        switch (decl.property.name) {
            case "color":
                if (exprType != ExpressionType.COLOR) {
                    decl.setError("A color property-value can only hold color literal type. At line: " + decl.getLine(), ErrorType.SYNTAX_ERROR);
                }
                break;
            case "background-color":
                if (exprType != ExpressionType.COLOR) {
                    decl.setError("A background-color property-value can only hold color literal type. At line: " + decl.getLine(), ErrorType.SYNTAX_ERROR);
                }
                break;
            case "width":
                if (exprType != ExpressionType.PIXEL && exprType != ExpressionType.PERCENTAGE) {
                    decl.setError("A width property-value can only hold pixel literal or percentage literal types. At line: " + decl.getLine(), ErrorType.SYNTAX_ERROR);
                }
                break;
            case "height":
                if (exprType != ExpressionType.PIXEL && exprType != ExpressionType.PERCENTAGE) {
                    decl.setError("A height property-value can only hold a pixel literal or percentage literal type. At line: " + decl.getLine(), ErrorType.SYNTAX_ERROR);
                }
                break;
            default:
                decl.setError("The property \"" + decl.property.name + "\" cannot be resolved. At line: " + decl.getLine(), ErrorType.ERROR);
                break;
        }
    }

    
}
