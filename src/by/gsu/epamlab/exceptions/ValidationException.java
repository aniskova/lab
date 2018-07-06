package by.gsu.epamlab.exceptions;

public class ValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String message;

	public ValidationException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return message;
	}
}
