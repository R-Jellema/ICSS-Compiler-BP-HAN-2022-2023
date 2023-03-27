package nl.han.ica.icss.generator;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;

import java.util.ArrayList;

public class Generator {
	private final StringBuilder stringBuilder;

	public Generator() {
		stringBuilder = new StringBuilder();
	}

	public String generate(AST ast) {
		nodeGeneration(ast.root);
		return stringBuilder.toString();
	}

	private void nodeGeneration(ASTNode astNode) {
		for (var node : astNode.getChildren()) {
			if (!(node instanceof StyleRule))
				continue;

			selGeneration((StyleRule) node);

			declGeneration(node);

			stringBuilder.append("}\n\n ");
		}

		// Remove one trailing \n character at the end of file.
		if (stringBuilder.length() > 1)
			stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());

	}

	private void selGeneration(StyleRule rule) {
		var selCollection = new ArrayList<String>();
		for (var selector : rule.selectors)
			selCollection.add(selector.toString());

		var str = String.join(", ", selCollection);
		stringBuilder.append(str);
		stringBuilder.append(" {\n");
	}

	private void declGeneration(ASTNode astNode) {
		for (var node : astNode.getChildren()) {
			if (!(node instanceof Declaration))
				continue;

			var decl = (Declaration) node;
			stringBuilder.append("  ")
					.append(decl.property.name)
					.append(": ")
					.append(exprToString(decl.expression))
					.append(";\n");

		}
	}

	private String exprToString(Expression expr) {
		if (expr instanceof ColorLiteral) {
			return ((ColorLiteral) expr).value + "";
		}
		if (expr instanceof PercentageLiteral) {
			return ((PercentageLiteral) expr).value + "%";
		}
		if (expr instanceof PixelLiteral) {
			return ((PixelLiteral) expr).value + "px";
		}

		return null;
	}
}