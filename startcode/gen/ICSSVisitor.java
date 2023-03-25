// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ICSSParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ICSSVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ICSSParser#icss}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIcss(ICSSParser.IcssContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#styleRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStyleRule(ICSSParser.StyleRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperty(ICSSParser.PropertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#propertyName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyName(ICSSParser.PropertyNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#varAssign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarAssign(ICSSParser.VarAssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(ICSSParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#elseStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseStmt(ICSSParser.ElseStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(ICSSParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#boolLit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLit(ICSSParser.BoolLitContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#colorLit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColorLit(ICSSParser.ColorLitContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#percentLit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPercentLit(ICSSParser.PercentLitContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#pixelLit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPixelLit(ICSSParser.PixelLitContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#scalarLit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarLit(ICSSParser.ScalarLitContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#varReference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarReference(ICSSParser.VarReferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(ICSSParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#classSel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassSel(ICSSParser.ClassSelContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#tagSel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagSel(ICSSParser.TagSelContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#idSel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdSel(ICSSParser.IdSelContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelector(ICSSParser.SelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#ruleBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleBody(ICSSParser.RuleBodyContext ctx);
}