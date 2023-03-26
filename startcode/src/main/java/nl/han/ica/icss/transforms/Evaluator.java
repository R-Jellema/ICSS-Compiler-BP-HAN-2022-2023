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

public class Evaluator implements Transform {
    private final ScopeHolder<String, Literal> variableValues;

    public Evaluator() {
        variableValues = new ScopeHolder<>();
    }

    @Override
    public void apply(AST ast) {
        var stylesheet = ast.root;
        this.transformStylesheet(stylesheet);
    }

    private void transformStylesheet(ASTNode astNode) {
        var toRemove = new ArrayList<ASTNode>();

        this.variableValues.openScope();

        for (var child : astNode.getChildren()) {
            if (child instanceof VariableAssignment) {
                this.transformVarAssign((VariableAssignment) child);
                toRemove.add(child);
                continue;
            }

            if (child instanceof StyleRule) {
                this.transformStyleRule((StyleRule) child);
            }
        }

        this.variableValues.closeScope();

        toRemove.forEach(astNode::removeChild);
    }

    private void transformStyleRule(StyleRule stylerule) {
        var toAdd = new ArrayList<ASTNode>();

        this.variableValues.openScope();

        for (var child : stylerule.body) {
            this.transformRuleBody(child, toAdd);
        }

        this.variableValues.closeScope();

        stylerule.body = toAdd;
    }

    private void transformRuleBody(ASTNode astNode, ArrayList<ASTNode> parentBody) {
        if (astNode instanceof VariableAssignment) {
            this.transformVarAssign((VariableAssignment) astNode);
            return;
        }

        if (astNode instanceof Declaration) {
            this.transformDecl((Declaration) astNode);
            parentBody.add(astNode);
            return;
        }

        if (astNode instanceof IfClause) {
            var ifClause = (IfClause) astNode;
            ifClause.conditionalExpression = this.transformExpr(ifClause.conditionalExpression);

            this.transformIfElseClause(ifClause);

            this.transformIfClause((IfClause) astNode, parentBody);
        }
    }



    private void transformIfClause(IfClause ifClause, ArrayList<ASTNode> parentBody) {
        for (ASTNode child : ifClause.getChildren()) {
            this.transformRuleBody(child, parentBody);
        }
    }

    private void transformDecl(Declaration decl) {
        decl.expression = this.transformExpr(decl.expression);
    }

    private void transformVarAssign(VariableAssignment varAssign) {
        var expression = varAssign.expression;
        varAssign.expression = this.transformExpr(expression);
        this.variableValues.addVariable(varAssign.name.name, (Literal) varAssign.expression);
    }

    private void transformIfElseClause(IfClause ifClause) {
        // Expression of if is true
        if (((BoolLiteral) ifClause.conditionalExpression).value && ifClause.body != null) {
            // set else body to null of if clause if there is no else
            if (ifClause.elseClause != null) {
                ifClause.elseClause.body = new ArrayList<>();
            }
        } else {
            // Expression of if is false, remove if clause body
            if (ifClause.elseClause == null) {
                ifClause.body = new ArrayList<>();
            } else {
                // no else clause body? Set to null and remove body.
                ifClause.body = ifClause.elseClause.body;
                ifClause.elseClause.body = new ArrayList<>();
            }
        }
    }

    private Literal transformExpr(Expression expr) {
        if (expr instanceof Operation) {
            return this.transformOperation((Operation) expr);
        }

        if (expr instanceof VariableReference) {
            return this.variableValues.getVariable(((VariableReference) expr).name);
        }

        return (Literal) expr;
    }

    private Literal transformOperation(Operation operation) {
        Literal left;
        Literal right;

        if (operation.lhs instanceof Operation) {
            left = this.transformOperation((Operation) operation.lhs);
        } else if (operation.lhs instanceof VariableReference) {
            left = this.variableValues.getVariable(((VariableReference) operation.lhs).name);
        } else {
            left = (Literal) operation.lhs;
        }

        if (operation.rhs instanceof Operation) {
            right = this.transformOperation((Operation) operation.rhs);
        } else if (operation.rhs instanceof VariableReference) {
            right = this.variableValues.getVariable(((VariableReference) operation.rhs).name);
        } else {
            right = (Literal) operation.rhs;
        }

        var leftValue = this.getLitValue(left);
        var rightValue = this.getLitValue(right);

        if (operation instanceof AddOperation) {
            return this.newLit(left, leftValue + rightValue);
        } else if (operation instanceof SubtractOperation) {
            return this.newLit(left, leftValue - rightValue);
        } else if (operation instanceof MultiplicationOperation) {
            if (right instanceof ScalarLiteral) {
                return this.newLit(left, leftValue * rightValue);
            } else {
                return this.newLit(right, leftValue * rightValue);
            }
        } else {
            return this.newLit(left, leftValue / rightValue);
        }
    }

    private int getLitValue(Literal lit) {
        if (lit instanceof PixelLiteral) {
            return ((PixelLiteral) lit).value;
        } else if (lit instanceof ScalarLiteral) {
            return ((ScalarLiteral) lit).value;
        } else {
            return ((PercentageLiteral) lit).value;
        }
    }

    private Literal newLit(Literal lit, int val) {
        if (lit instanceof PixelLiteral) {
            return new PixelLiteral(val);
        } else if (lit instanceof ScalarLiteral) {
            return new ScalarLiteral(val);
        } else {
            return new PercentageLiteral(val);
        }
    }

    
}
