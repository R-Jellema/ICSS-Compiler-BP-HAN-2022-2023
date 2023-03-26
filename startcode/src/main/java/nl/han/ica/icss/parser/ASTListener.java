package nl.han.ica.icss.parser;

import nl.han.ica.datastructures.Stack;
import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplicationOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.selectors.*;

import static nl.han.ica.icss.parser.ICSSParser.*;

/**
 * This class extracts the ICSS Abstract Syntax Tree from the Antlr Parse tree.
 */
public class ASTListener extends ICSSBaseListener {

	//Accumulator attributes:
	private final AST ast;

	//Use this to keep track of the parent nodes when recursively traversing the ast
	private final IHANStack<ASTNode> currentContainer;

	public ASTListener() {
		ast = new AST();
		currentContainer = new Stack<>();
	}
    public AST getAST() {
        return ast;
    }

	@Override
	public void enterStylesheet(StylesheetContext ctx) {
		var sheet = new Stylesheet();
		sheet.setLine(ctx.start.getLine());
		currentContainer.push(sheet);
	}

	@Override
	public void exitStylesheet(StylesheetContext ctx) {
		ast.setRoot((Stylesheet) currentContainer.pop());
	}

	@Override
	public void enterStyleRule(StyleRuleContext ctx) {
		var rule = new StyleRule();
		rule.setLine(ctx.start.getLine());
		currentContainer.push(rule);
	}

	@Override
	public void exitStyleRule(StyleRuleContext ctx) {
		var rule = currentContainer.pop();
		currentContainer.peek().addChild(rule);
	}

	@Override
	public void enterTagSel(TagSelContext ctx) {
		var tagSel = new TagSelector(ctx.getText());
		tagSel.setLine(ctx.start.getLine());
		currentContainer.push(tagSel);
	}

	@Override
	public void exitTagSel(TagSelContext ctx) {
		var tagSel = currentContainer.pop();
		currentContainer.peek().addChild(tagSel);
	}

	@Override
	public void enterClassSel(ClassSelContext ctx) {
		var classSel = new ClassSelector(ctx.getText());
		classSel.setLine(ctx.start.getLine());
		currentContainer.push(classSel);
	}

	@Override
	public void exitClassSel(ClassSelContext ctx) {
		var classSel = currentContainer.pop();
		currentContainer.peek().addChild(classSel);
	}

	@Override
	public void enterIdSel(IdSelContext ctx) {
		var idSel = new IdSelector(ctx.getText());
		idSel.setLine(ctx.start.getLine());
		currentContainer.push(idSel);
	}

	@Override
	public void exitIdSel(IdSelContext ctx) {
		var idSel = currentContainer.pop();
		currentContainer.peek().addChild(idSel);
	}

	public void enterVarRef(VarRefContext ctx) {
		var varRef = new VariableReference(ctx.getText());
		varRef.setLine(ctx.start.getLine());
		currentContainer.peek().addChild(varRef);
	}

	@Override
	public void enterVarAssign(VarAssignContext ctx) {
		var varAssign = new VariableAssignment();
		varAssign.setLine(ctx.start.getLine());
		currentContainer.push(varAssign);
	}

	@Override
	public void exitVarAssign(VarAssignContext ctx) {
		var varAssign = currentContainer.pop();
		currentContainer.peek().addChild(varAssign);
	}

	@Override
	public void enterDecl(DeclContext ctx) {
		var decl = new Declaration();
		decl.setLine(ctx.start.getLine());
		currentContainer.push(decl);
	}

	@Override
	public void exitDecl(DeclContext ctx) {
		var decl = currentContainer.pop();
		currentContainer.peek().addChild(decl);
	}

	@Override
	public void enterPropertyName(PropertyNameContext ctx) {
		var propName = new PropertyName(ctx.getText());
		propName.setLine(ctx.start.getLine());
		currentContainer.peek().addChild(propName);
	}

	@Override
	public void enterBoolLit(BoolLitContext ctx) {
		var boolLit = new BoolLiteral(ctx.getText());
		boolLit.setLine(ctx.start.getLine());
		currentContainer.peek().addChild(boolLit);
	}

	@Override
	public void enterColorLit(ColorLitContext ctx) {
		var colorLit = new ColorLiteral(ctx.getText());
		colorLit.setLine(ctx.start.getLine());
		currentContainer.peek().addChild(colorLit);
	}

	@Override
	public void enterPercentLit(PercentLitContext ctx) {
		var percentageLit = new PercentageLiteral(ctx.getText());
		percentageLit.setLine(ctx.start.getLine());
		currentContainer.peek().addChild(percentageLit);
	}

	@Override
	public void enterPixelLit(PixelLitContext ctx) {
		var pixelLit = new PixelLiteral(ctx.getText());
		pixelLit.setLine(ctx.start.getLine());
		currentContainer.peek().addChild(pixelLit);
	}

	@Override
	public void enterScalarLit(ScalarLitContext ctx) {
		var scalarLit = new ScalarLiteral(ctx.getText());
		scalarLit.setLine(ctx.start.getLine());
		currentContainer.peek().addChild(scalarLit);
	}

	@Override
	public void enterExpr(ExprContext ctx) {
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

			operation.setLine(ctx.start.getLine());
			currentContainer.push(operation);
		}
	}

	@Override
	public void exitExpr(ExprContext ctx) {
		if (exprHasOperation(ctx)) {
			var operation = currentContainer.pop();
			currentContainer.peek().addChild(operation);
		}
	}

	@Override
	public void enterIfStmt(IfStmtContext ctx) {
		var ifStmt = new IfClause();
		ifStmt.setLine(ctx.start.getLine());
		currentContainer.push(ifStmt);
	}

	@Override
	public void exitIfStmt(IfStmtContext ctx) {
		var ifStmt = currentContainer.pop();
		currentContainer.peek().addChild(ifStmt);
	}

	@Override
	public void enterElseStmt(ElseStmtContext ctx) {
		var elseStmt = new ElseClause();
		elseStmt.setLine(ctx.start.getLine());
		currentContainer.push(elseStmt);
	}

	@Override
	public void exitElseStmt(ElseStmtContext ctx) {
		var elseStmt = currentContainer.pop();
		currentContainer.peek().addChild(elseStmt);
	}

	private boolean exprHasOperation(ExprContext ctx) {
		return ctx.PLUS() != null || ctx.MIN() != null || ctx.MUL() != null;
	}
}