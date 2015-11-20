package web;

public class WebAccessorException extends Exception {

    public WebAccessorException(String message) {
        super(message);
    }

    public WebAccessorException(Throwable cause) {
        super(cause);
    }

    public WebAccessorException(String message, Throwable cause) {
        super(message, cause);
    }
}