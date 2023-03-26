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
        this.scopes = new ScopeHolder<>();
        this.varChecker = new VariableChecker(scopes);
        this.exprChecker = new ExpressionsChecker(varChecker);
        this.ifClauseExprChecker = new IfClauseExpressionsChecker(exprChecker);
    }

    public void check(AST ast) {
        this.stylesheetChecker(ast.root);
    }

    private void stylesheetChecker(ASTNode node) {
        var sheet = (Stylesheet) node;

        scopes.openScope();

        sheet.getChildren().forEach(child -> {
            if (child instanceof VariableAssignment) {
                varChecker.varAssignChecker(child);
            } else if (child instanceof StyleRule) {
                scopes.openScope();
                    styleRuleChecker(child);
                scopes.closeScope();
            }
        });

        scopes.closeScope();
    }

    private void styleRuleChecker(ASTNode node) {
        var stylerule = (StyleRule) node;
        this.ruleBodyChecker(stylerule.body);
    }

    private void ruleBodyChecker(ArrayList<ASTNode> nodes) {
        nodes.forEach(node -> {
            if (node instanceof Declaration) {
                this.declChecker(node);
            } else if (node instanceof IfClause) {
                this.ifClauseChecker(node);
            } else if (node instanceof VariableAssignment) {
                this.varChecker.varAssignChecker(node);
            }
        });
    }

    private void ifClauseChecker(ASTNode astNode) {
        var ifClause = (IfClause) astNode;
        this.scopes.openScope();
            this.ifClauseExprChecker.checkIfClauseExpr(ifClause);
            this.ruleBodyChecker(ifClause.body);
        this.scopes.closeScope();

        if (ifClause.elseClause == null)  return;

        this.scopes.openScope();
        this.elseClauseChecker(ifClause.elseClause);
        this.scopes.closeScope();
    }

    private void elseClauseChecker(ASTNode astNode) {
        var elseClause = (ElseClause) astNode;
        this.ruleBodyChecker(elseClause.body);
    }

    private void declChecker(ASTNode astNode) {
        var decl = (Declaration) astNode;
        var exprType = this.exprChecker.checkExpr(decl.expression);

        switch (decl.property.name) {
            case "color":
                if (exprType != ExpressionType.COLOR) {
                    astNode.setError("A color property-value can only hold color literal type. At line: " + decl.getLine(), ErrorType.SYNTAX_ERROR);
                }
                break;
            case "background-color":
                if (exprType != ExpressionType.COLOR) {
                    astNode.setError("A background-color property-value can only hold color literal type. At line: " + decl.getLine(), ErrorType.SYNTAX_ERROR);
                }
                break;
            case "width":
                if (exprType != ExpressionType.PIXEL && exprType != ExpressionType.PERCENTAGE) {
                    astNode.setError("A width property-value can only hold pixel literal or percentage literal types. At line: " + decl.getLine(), ErrorType.SYNTAX_ERROR);
                }
                break;
            case "height":
                if (exprType != ExpressionType.PIXEL && exprType != ExpressionType.PERCENTAGE) {
                    astNode.setError("A height property-value can only hold a pixel literal or percentage literal type. At line: " + decl.getLine(), ErrorType.SYNTAX_ERROR);
                }
                break;
            default:
                astNode.setError("The property \"" + decl.property.name + "\" cannot be resolved. At line: " + decl.getLine(), ErrorType.ERROR);
                break;
        }
    }

    
}
