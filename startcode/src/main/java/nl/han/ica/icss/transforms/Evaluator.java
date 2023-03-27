package nl.han.ica.icss.transforms;

import nl.han.ica.datastructures.ScopeHolder;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.BoolLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;

import java.util.ArrayList;

public class Evaluator implements Transform {
    private final ScopeHolder<String, Literal> variableValues;

    public Evaluator() {
        variableValues = new ScopeHolder<>();
    }

    @Override
    public void apply(AST ast) {
        var stylesheet = ast.root;
        transformStylesheet(stylesheet);
    }

    private void transformStylesheet(ASTNode astNode) {
        var toRemove = new ArrayList<ASTNode>();

        variableValues.openScope();

        for (var line : astNode.getChildren()) {
            if (line instanceof VariableAssignment) {
                transformVarAssign((VariableAssignment) line);
                toRemove.add(line);
                continue;
            }

            if (line instanceof StyleRule) {
                transformStyleRule((StyleRule) line);
            }
        }

        variableValues.closeScope();

        toRemove.forEach(astNode::removeChild);
    }


    private void transformStyleRule(StyleRule stylerule) {
        var toAdd = new ArrayList<ASTNode>();

        variableValues.openScope();

        for (var child : stylerule.body) {
            transformRuleBody(child, toAdd);
        }

        variableValues.closeScope();

        stylerule.body = toAdd;
    }

    private void transformRuleBody(ASTNode astNode, ArrayList<ASTNode> parentBody) {
        if (astNode instanceof VariableAssignment) {
            transformVarAssign((VariableAssignment) astNode);
            return;
        }

        if (astNode instanceof Declaration) {
            transformDecl((Declaration) astNode);
            parentBody.add(astNode);
            return;
        }

        if (astNode instanceof IfClause) {
            var ifClause = (IfClause) astNode;
            ifClause.conditionalExpression = transformExpr(ifClause.conditionalExpression);

            transformIfElseClause(ifClause);

            transformIfClause((IfClause) astNode, parentBody);
        }
    }



    private void transformIfClause(IfClause ifClause, ArrayList<ASTNode> parentBody) {
        for (ASTNode child : ifClause.getChildren()) {
            transformRuleBody(child, parentBody);
        }
    }

    private void transformDecl(Declaration decl) {
        decl.expression = transformExpr(decl.expression);
    }

    private void transformVarAssign(VariableAssignment varAssign) {
        var expr = varAssign.expression;
        varAssign.expression = transformExpr(expr);
        variableValues.addVariable(varAssign.name.name, (Literal) varAssign.expression);
    }

    private void transformIfElseClause(IfClause ifClause) {
        boolean condition = ((BoolLiteral) ifClause.conditionalExpression).value;

        if(condition){
            if(ifClause.elseClause == null)
                return;


            ifClause.elseClause.body = null;
            return;
        }

        if(ifClause.elseClause.body == null)
            return;

        ifClause.body = ifClause.elseClause.body;
        ifClause.elseClause.body = null;
    }
    private Literal transformExpr(Expression expr) {
        if (expr instanceof Operation) {
            return transformOperation((Operation) expr);
        }

        if (expr instanceof VariableReference) {
            return variableValues.getVariableValue(((VariableReference) expr).name);
        }

        return (Literal) expr;

    }

    private Literal transformOperation(Operation operation) {
        var leftLit = transformOperationExpr(operation.lhs);
        var rightLit = transformOperationExpr(operation.rhs);

        var leftScalar = getLitValue(leftLit);
        var rightScalar = getLitValue(rightLit);

        switch (operation.getNodeLabel()) {
            case "Add":
                return newLit(leftLit, leftScalar + rightScalar);
            case "Subtract":
                return newLit(leftLit, leftScalar - rightScalar);
            default:
                if (rightLit instanceof ScalarLiteral) {
                    return newLit(leftLit, leftScalar * rightScalar);
                } else {
                    return newLit(rightLit, leftScalar * rightScalar);
                }
        }
    }

    private Literal transformOperationExpr(Expression expr){
        if(expr instanceof VariableReference){
            var varRef = (VariableReference) expr;
            return variableValues.getVariableValue(varRef.name);
        } else if (expr instanceof Literal) {
            return (Literal) expr;
        } else {
            var operation = (Operation) expr;
            return transformOperation(operation);
        }
    }

    private int getLitValue(Literal lit) {
        if(lit instanceof  PercentageLiteral){
            return ((PercentageLiteral) lit).value;
        } else if (lit instanceof ScalarLiteral){
            return ((ScalarLiteral) lit).value;
        } else {
            return ((PixelLiteral) lit).value;
        }
    }

    private Literal newLit(Literal lit, int val) {
        if(lit instanceof  PercentageLiteral){
            return new PercentageLiteral(val);
        } else if (lit instanceof ScalarLiteral){
            return new ScalarLiteral(val);
        } else {
            return new PixelLiteral(val);
        }
    }

    
}
