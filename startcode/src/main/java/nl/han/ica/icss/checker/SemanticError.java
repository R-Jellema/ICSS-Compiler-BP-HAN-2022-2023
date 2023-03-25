package nl.han.ica.icss.checker;

public class SemanticError {
	private final String description;
	private final ErrorType errorType;

	public SemanticError(String description, ErrorType errorType) {
		this.description = description;
		this.errorType = errorType;
	}

	public String toString() {
		return errorType.toString() + ": " + description;
	}
}
