package nl.han.ica.icss.checker;

public class SemanticError {
	private final String desc;
	private final ErrorType errType;

	public SemanticError(String desc, ErrorType errType) {
		this.desc = desc;
		this.errType = errType;
	}

	public String toString() {
		return errType.toString() + ": " + desc;
	}
}
