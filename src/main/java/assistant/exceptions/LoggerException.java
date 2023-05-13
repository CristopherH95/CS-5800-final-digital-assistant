package assistant.exceptions;

public class LoggerException extends RuntimeException {
    public LoggerException(String message, Exception cause) {
        super(message, cause);
    }
}
