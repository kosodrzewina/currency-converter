public class ParseResult<T> {
    private final boolean succeeded;
    private final String message;
    private final T data;
    
    private ParseResult(String message) {
        this.succeeded = false;
        this.message = message;
        data = null;
    }

    private ParseResult(T data) {
        this.succeeded = true;
        message = null;
        this.data = data;
    }

    public boolean isSuccessfull() {
        return succeeded;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static <T> ParseResult<T> createSuccess(T data) {
        return new ParseResult<T>(data);
    }

    public static <T> ParseResult<T> createFailure(String message) {
        return new ParseResult<T>(message);
    }
}
