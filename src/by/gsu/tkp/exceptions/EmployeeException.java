package by.gsu.tkp.exceptions;

public class EmployeeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String message;

    public EmployeeException(String message) {
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
