package nl.han.ica.icss.parser;

import nl.han.ica.datastructures.Stack;
import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplicationOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;
import static nl.han.ica.icss.parser.ICSSParser.*;

/**
 * This class extracts the ICSS Abstract Syntax Tree from the Antlr Parse tree.
 */
public class ASTListener extends ICSSBaseListener {

	//Accumulator attributes:
	private AST ast;

	//Use this to keep track of the parent nodes when recursively traversing the ast
	private IHANStack<ASTNode> currentContainer;

	public ASTListener() {
		ast = new AST();
		currentContainer = new Stack<>();
	}
    public AST getAST() {
        return ast;
    }

	@Override
	public void enterStylesheet(StylesheetContext ctx) {
		var stylesheet = new Stylesheet();
		currentContainer.push(stylesheet);
	}

	@Override
	public void exitStylesheet(StylesheetContext ctx) {
		ast.setRoot((Stylesheet) currentContainer.pop());
	}

	@Override
	public void enterStyleRule(StyleRuleContext ctx) {
		var styleRule = new Stylerule();
		currentContainer.push(styleRule);
	}

	@Override
	public void exitStyleRule(StyleRuleContext ctx) {
		var styleRule = currentContainer.pop();
		currentContainer.peek().addChild(styleRule);
	}

	@Override
	public void enterTagSelector(TagSelectorContext ctx) {
		var tagSelector = new TagSelector(ctx.getText());
		currentContainer.push(tagSelector);
	}

	@Override
	public void exitTagSelector(TagSelectorContext ctx) {
		var tagSelector = currentContainer.pop();
		currentContainer.peek().addChild(tagSelector);
	}

	@Override
	public void enterClassSelector(ClassSelectorContext ctx) {
		var classSelector = new ClassSelector(ctx.getText());
		currentContainer.push(classSelector);
	}

	@Override
	public void exitClassSelector(ClassSelectorContext ctx) {
		var classSelector = currentContainer.pop();
		currentContainer.peek().addChild(classSelector);
	}

	@Override
	public void enterIdSelector(IdSelectorContext ctx) {
		var idSelector = new IdSelector(ctx.getText());
		currentContainer.push(idSelector);
	}

	@Override
	public void exitIdSelector(IdSelectorContext ctx) {
		var idSelector = currentContainer.pop();
		currentContainer.peek().addChild(idSelector);
	}

	@Override
	public void enterVariableReference(VariableReferenceContext ctx) {
		var variableReference = new VariableReference(ctx.getText());
		currentContainer.peek().addChild(variableReference);
	}

	@Override
	public void enterVariableAssignment(VariableAssignmentContext ctx) {
		var variableAssignment = new VariableAssignment();
		currentContainer.push(variableAssignment);
	}

	@Override
	public void exitVariableAssignment(VariableAssignmentContext ctx) {
		var variableAssignment = currentContainer.pop();
		currentContainer.peek().addChild(variableAssignment);
	}

	@Override
	public void enterDeclaration(DeclarationContext ctx) {
		var declaration = new Declaration();
		currentContainer.push(declaration);
	}

	@Override
	public void exitDeclaration(DeclarationContext ctx) {
		var declaration = currentContainer.pop();
		currentContainer.peek().addChild(declaration);
	}

	@Override
	public void enterPropertyName(PropertyNameContext ctx) {
		var propertyName = new PropertyName(ctx.getText());
		currentContainer.peek().addChild(propertyName);
	}

	@Override
	public void enterBoolLiteral(BoolLiteralContext ctx) {
		var boolLiteral = new BoolLiteral(ctx.getText());
		currentContainer.peek().addChild(boolLiteral);
	}

	@Override
	public void enterColorLiteral(ColorLiteralContext ctx) {
		var colorLiteral = new ColorLiteral(ctx.getText());
		currentContainer.peek().addChild(colorLiteral);
	}

	@Override
	public void enterPercentageLiteral(PercentageLiteralContext ctx) {
		var percentageLiteral = new PercentageLiteral(ctx.getText());
		currentContainer.peek().addChild(percentageLiteral);
	}

	@Override
	public void enterPixelLiteral(PixelLiteralContext ctx) {
		var pixelLiteral = new PixelLiteral(ctx.getText());
		currentContainer.peek().addChild(pixelLiteral);
	}

	@Override
	public void enterScalarLiteral(ScalarLiteralContext ctx) {
		var scalarLiteral = new ScalarLiteral(ctx.getText());
		currentContainer.peek().addChild(scalarLiteral);
	}

	@Override
	public void enterExpression(ExpressionContext ctx) {
		Operation operation;

		if (ctx.getChildCount() == 3) {
			switch (ctx.getChild(1).getText()) {
				case "*":
					operation = new MultiplicationOperation();
					break;
				case "+":
					operation = new AddOperation();
					break;
				default:
					operation = new SubtractOperation();
			}
			currentContainer.push(operation);
		}
	}

	@Override
	public void exitExpression(ExpressionContext ctx) {
		if (expressionHasTerminalNode(ctx)) {
			var operation = currentContainer.pop();
			currentContainer.peek().addChild(operation);
		}
	}

	@Override
	public void enterIfClause(IfClauseContext ctx) {
		var ifClause = new IfClause();
		currentContainer.push(ifClause);
	}

	@Override
	public void exitIfClause(IfClauseContext ctx) {
		var ifClause = currentContainer.pop();
		currentContainer.peek().addChild(ifClause);
	}

	@Override
	public void enterElseClause(ElseClauseContext ctx) {
		var elseClause = new ElseClause();
		currentContainer.push(elseClause);
	}

	@Override
	public void exitElseClause(ElseClauseContext ctx) {
		var elseClause = currentContainer.pop();
		currentContainer.peek().addChild(elseClause);
	}

	private boolean expressionHasTerminalNode(ExpressionContext ctx) {
		return ctx.PLUS() != null || ctx.MIN() != null || ctx.MUL() != null || ctx.DIV() != null;
	}

}