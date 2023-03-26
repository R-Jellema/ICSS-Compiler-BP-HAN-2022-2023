package nl.han.ica.icss.generator;


import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;

import java.util.List;
import java.util.stream.Collectors;

public class Generator {

	private final StringBuilder stringBuilder;

	public Generator() {
		this.stringBuilder = new StringBuilder();
	}

	public String generate(AST ast) {
		this.nodeGeneration(ast.root);
		return stringBuilder.toString();
	}

	private void nodeGeneration(ASTNode astNode) {
		for (var node : astNode.getChildren()) {
			if (node instanceof Stylerule) {
				this.selGeneration(node);

				this.declGeneration(node);

				this.stringBuilder.append("}\n\n ");
			}
		}

		// Remove one trailing \n character at the end of file.
		if (this.stringBuilder.length() > 1) {
			this.stringBuilder.delete(this.stringBuilder.length() - 1, this.stringBuilder.length());
		}
	}

	private void selGeneration(ASTNode astNode) {
		var rule = (Stylerule) astNode;

		var selCollection = rule.selectors.stream()
				.map(ASTNode::toString)
				.collect(Collectors.toList());

		var str = String.join(", ", selCollection);
		this.stringBuilder.append(str);

		this.stringBuilder.append(" {\n");
	}

	private void declGeneration(ASTNode astNode) {
		for (var node : astNode.getChildren()) {
			if (node instanceof Declaration) {
				var decl = (Declaration) node;
				this.stringBuilder.append("  ")
						.append(decl.property.name)
						.append(": ")
						.append(this.exprToString(decl.expression))
						.append(";\n");
			}
		}
	}

	private String exprToString(Expression expr) {
		if (expr instanceof PercentageLiteral) {
			return ((PercentageLiteral) expr).value + "%";
		}
		if (expr instanceof PixelLiteral) {
			return ((PixelLiteral) expr).value + "px";
		}
		if (expr instanceof ColorLiteral) {
			return ((ColorLiteral) expr).value + "";
		}

		return "";
	}

	
}
