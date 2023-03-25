package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.ScopeHolder;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.ArrayList;


public class Checker {
    private final ScopeHolder<String, ExpressionType> variableTypes;
    private final ExpressionsChecker expressionsChecker;
    private final IfClauseExpressionChecker ifClauseExpressionChecker;
    private final VariableChecker variableChecker;

    public Checker() {
        this.variableTypes = new ScopeHolder<>();
        this.variableChecker = new VariableChecker(variableTypes);
        this.expressionsChecker = new ExpressionsChecker(variableChecker);
        this.ifClauseExpressionChecker = new IfClauseExpressionChecker(expressionsChecker);
    }

    public void check(AST ast) {
        this.checkStylesheet(ast.root);
    }

    private void checkStylesheet(ASTNode node) {
        var stylesheet = (Stylesheet) node;

        variableTypes.openScope();

        stylesheet.getChildren().forEach(child -> {
            if (child instanceof VariableAssignment) {
                variableChecker.variableAssignmentChecker(child);
            } else if (child instanceof Stylerule) {
                variableTypes.openScope();
                checkStylerule(child);
                variableTypes.closeScope();
            }
        });

        variableTypes.closeScope();
    }

    private void checkStylerule(ASTNode node) {
        var stylerule = (Stylerule) node;
        this.checkRuleBody(stylerule.body);
    }

    private void checkRuleBody(ArrayList<ASTNode> nodes) {
        nodes.forEach(node -> {
            if (node instanceof Declaration) {
                this.checkDeclaration(node);
            } else if (node instanceof IfClause) {
                this.checkIfClause(node);
            } else if (node instanceof VariableAssignment) {
                this.variableChecker.variableAssignmentChecker(node);
            }
        });
    }

    private void checkIfClause(ASTNode astNode) {
        var ifClause = (IfClause) astNode;
        this.variableTypes.openScope();
        this.ifClauseExpressionChecker.check(ifClause);
        this.checkRuleBody(ifClause.body);
        this.variableTypes.closeScope();

        if (ifClause.elseClause == null)  return;

        this.variableTypes.openScope();
        this.checkElseClause(ifClause.elseClause);
        this.variableTypes.closeScope();
    }

    private void checkElseClause(ASTNode astNode) {
        var elseClause = (ElseClause) astNode;
        this.checkRuleBody(elseClause.body);
    }

    private void checkDeclaration(ASTNode astNode) {
        var declaration = (Declaration) astNode;
        var expressionType = this.expressionsChecker.expressionCheck(declaration.expression);

        switch (declaration.property.name) {
            case "color":
                if (expressionType != ExpressionType.COLOR) {
                    astNode.setError("A color property-value can only be a color literal.", ErrorType.WARN);
                }
                break;
            case "background-color":
                if (expressionType != ExpressionType.COLOR) {
                    astNode.setError("A background-color property-value can only be a color literal.", ErrorType.WARN);
                }
                break;
            case "width":
                if (expressionType != ExpressionType.PIXEL && expressionType != ExpressionType.PERCENTAGE) {
                    astNode.setError("A width property-value can only be a pixel or percentage literal.", ErrorType.WARN);
                }
                break;
            case "height":
                if (expressionType != ExpressionType.PIXEL && expressionType != ExpressionType.PERCENTAGE) {
                    astNode.setError("A height property-value can only be a pixel or percentage literal.", ErrorType.WARN);
                }
                break;
            default:
                astNode.setError("The property \"" + declaration.property.name + "\" cannot be resolved.", ErrorType.ERROR);
                break;
        }
    }

    
}
