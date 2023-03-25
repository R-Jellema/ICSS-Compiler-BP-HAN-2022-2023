// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ICSSParser}.
 */
public interface ICSSListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ICSSParser#icss}.
	 * @param ctx the parse tree
	 */
	void enterIcss(ICSSParser.IcssContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#icss}.
	 * @param ctx the parse tree
	 */
	void exitIcss(ICSSParser.IcssContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#styleRule}.
	 * @param ctx the parse tree
	 */
	void enterStyleRule(ICSSParser.StyleRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#styleRule}.
	 * @param ctx the parse tree
	 */
	void exitStyleRule(ICSSParser.StyleRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(ICSSParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(ICSSParser.PropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#propertyName}.
	 * @param ctx the parse tree
	 */
	void enterPropertyName(ICSSParser.PropertyNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#propertyName}.
	 * @param ctx the parse tree
	 */
	void exitPropertyName(ICSSParser.PropertyNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#varAssign}.
	 * @param ctx the parse tree
	 */
	void enterVarAssign(ICSSParser.VarAssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#varAssign}.
	 * @param ctx the parse tree
	 */
	void exitVarAssign(ICSSParser.VarAssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(ICSSParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(ICSSParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#elseStmt}.
	 * @param ctx the parse tree
	 */
	void enterElseStmt(ICSSParser.ElseStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#elseStmt}.
	 * @param ctx the parse tree
	 */
	void exitElseStmt(ICSSParser.ElseStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(ICSSParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(ICSSParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#boolLit}.
	 * @param ctx the parse tree
	 */
	void enterBoolLit(ICSSParser.BoolLitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#boolLit}.
	 * @param ctx the parse tree
	 */
	void exitBoolLit(ICSSParser.BoolLitContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#colorLit}.
	 * @param ctx the parse tree
	 */
	void enterColorLit(ICSSParser.ColorLitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#colorLit}.
	 * @param ctx the parse tree
	 */
	void exitColorLit(ICSSParser.ColorLitContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#percentLit}.
	 * @param ctx the parse tree
	 */
	void enterPercentLit(ICSSParser.PercentLitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#percentLit}.
	 * @param ctx the parse tree
	 */
	void exitPercentLit(ICSSParser.PercentLitContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#pixelLit}.
	 * @param ctx the parse tree
	 */
	void enterPixelLit(ICSSParser.PixelLitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#pixelLit}.
	 * @param ctx the parse tree
	 */
	void exitPixelLit(ICSSParser.PixelLitContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#scalarLit}.
	 * @param ctx the parse tree
	 */
	void enterScalarLit(ICSSParser.ScalarLitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#scalarLit}.
	 * @param ctx the parse tree
	 */
	void exitScalarLit(ICSSParser.ScalarLitContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#varReference}.
	 * @param ctx the parse tree
	 */
	void enterVarReference(ICSSParser.VarReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#varReference}.
	 * @param ctx the parse tree
	 */
	void exitVarReference(ICSSParser.VarReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(ICSSParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(ICSSParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#classSel}.
	 * @param ctx the parse tree
	 */
	void enterClassSel(ICSSParser.ClassSelContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#classSel}.
	 * @param ctx the parse tree
	 */
	void exitClassSel(ICSSParser.ClassSelContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#tagSel}.
	 * @param ctx the parse tree
	 */
	void enterTagSel(ICSSParser.TagSelContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#tagSel}.
	 * @param ctx the parse tree
	 */
	void exitTagSel(ICSSParser.TagSelContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#idSel}.
	 * @param ctx the parse tree
	 */
	void enterIdSel(ICSSParser.IdSelContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#idSel}.
	 * @param ctx the parse tree
	 */
	void exitIdSel(ICSSParser.IdSelContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void enterSelector(ICSSParser.SelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void exitSelector(ICSSParser.SelectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#ruleBody}.
	 * @param ctx the parse tree
	 */
	void enterRuleBody(ICSSParser.RuleBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#ruleBody}.
	 * @param ctx the parse tree
	 */
	void exitRuleBody(ICSSParser.RuleBodyContext ctx);
}