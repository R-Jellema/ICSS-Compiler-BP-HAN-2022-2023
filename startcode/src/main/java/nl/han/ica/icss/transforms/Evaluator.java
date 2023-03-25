package nl.han.ica.icss.transforms;

import nl.han.ica.datastructures.ScopeHolder;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.BoolLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplicationOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Evaluator implements Transform {
    private final ScopeHolder<String, Literal> variableValues;

    public Evaluator() {
        variableValues = new ScopeHolder<>();
    }

    @Override
    public void apply(AST ast) {
        var stylesheet = ast.root;
        this.stylesheetTransformation(stylesheet);
    }

    private void stylesheetTransformation(ASTNode astNode) {
        var toRemove = new ArrayList<ASTNode>();

        this.variableValues.openScope();

        for (ASTNode child : astNode.getChildren()) {
            if (child instanceof VariableAssignment) {
                this.variableAssignmentTransformation((VariableAssignment) child);
                toRemove.add(child);
                continue;
            }

            if (child instanceof Stylerule) {
                this.styleRuleTransformation((Stylerule) child);
            }
        }

        this.variableValues.closeScope();

        toRemove.forEach(astNode::removeChild);
    }

    private void styleRuleTransformation(Stylerule stylerule) {
        var toAdd = new ArrayList<ASTNode>();

        this.variableValues.openScope();

        for (ASTNode child : stylerule.body) {
            this.ruleBodyTransformation(child, toAdd);
        }

        this.variableValues.closeScope();

        stylerule.body = toAdd;
    }

    private void ruleBodyTransformation(ASTNode astNode, ArrayList<ASTNode> parentBody) {
        if (astNode instanceof VariableAssignment) {
            this.variableAssignmentTransformation((VariableAssignment) astNode);
            return;
        }

        if (astNode instanceof Declaration) {
            this.declarationTransformation((Declaration) astNode);
            parentBody.add(astNode);
            return;
        }

        if (astNode instanceof IfClause) {
            var ifClause = (IfClause) astNode;
            ifClause.conditionalExpression = this.expressionTransformation(ifClause.conditionalExpression);

            // If "if"-expression is true.
            if (((BoolLiteral) ifClause.conditionalExpression).value) {
                // Remove "else"-clause if there is none in body.
                if (ifClause.elseClause != null) {
                    ifClause.elseClause.body = new ArrayList<>();
                }
            } else {
                // If "if"-expression is false and there is no "else"-clause in body, remove else-clause.
                if (ifClause.elseClause == null) {
                    ifClause.body = new ArrayList<>();
                    return;
                } else {
                    // If "else"-clause available in body, move it to the "if" clause.
                    ifClause.body = ifClause.elseClause.body;
                    ifClause.elseClause.body = new ArrayList<>();
                }
            }

            this.ifClauseTransformation((IfClause) astNode, parentBody);
        }
    }

    private void ifClauseTransformation(IfClause ifClause, ArrayList<ASTNode> parentBody) {
        for (ASTNode child : ifClause.getChildren()) {
            this.ruleBodyTransformation(child, parentBody);
        }
    }

    private void declarationTransformation(Declaration declaration) {
        declaration.expression = this.expressionTransformation(declaration.expression);
    }

    private void variableAssignmentTransformation(VariableAssignment variableAssignment) {
        var expression = variableAssignment.expression;
        variableAssignment.expression = this.expressionTransformation(expression);
        this.variableValues.addVariable(variableAssignment.name.name, (Literal) variableAssignment.expression);
    }


    private Literal expressionTransformation(Expression expression) {
        if (expression instanceof Operation) {
            return this.operationTransformation((Operation) expression);
        }

        if (expression instanceof VariableReference) {
            return this.variableValues.getVariable(((VariableReference) expression).name);
        }

        return (Literal) expression;
    }

    private Literal operationTransformation(Operation operation) {
        Literal left;
        Literal right;

        if (operation.lhs instanceof Operation) {
            left = this.operationTransformation((Operation) operation.lhs);
        } else if (operation.lhs instanceof VariableReference) {
            left = this.variableValues.getVariable(((VariableReference) operation.lhs).name);
        } else {
            left = (Literal) operation.lhs;
        }

        if (operation.rhs instanceof Operation) {
            right = this.operationTransformation((Operation) operation.rhs);
        } else if (operation.rhs instanceof VariableReference) {
            right = this.variableValues.getVariable(((VariableReference) operation.rhs).name);
        } else {
            right = (Literal) operation.rhs;
        }

        var leftValue = this.getLiteralValue(left);
        var rightValue = this.getLiteralValue(right);

        if (operation instanceof AddOperation) {
            return this.newLiteral(left, leftValue + rightValue);
        } else if (operation instanceof SubtractOperation) {
            return this.newLiteral(left, leftValue - rightValue);
        } else if (operation instanceof MultiplicationOperation) {
            if (right instanceof ScalarLiteral) {
                return this.newLiteral(left, leftValue * rightValue);
            } else {
                return this.newLiteral(right, leftValue * rightValue);
            }
        } else {
            return this.newLiteral(left, leftValue / rightValue);
        }
    }

    private int getLiteralValue(Literal literal) {
        if (literal instanceof PixelLiteral) {
            return ((PixelLiteral) literal).value;
        } else if (literal instanceof ScalarLiteral) {
            return ((ScalarLiteral) literal).value;
        } else {
            return ((PercentageLiteral) literal).value;
        }
    }

    private Literal newLiteral(Literal literal, int value) {
        if (literal instanceof PixelLiteral) {
            return new PixelLiteral(value);
        } else if (literal instanceof ScalarLiteral) {
            return new ScalarLiteral(value);
        } else {
            return new PercentageLiteral(value);
        }
    }
}
