package pt.pak3nuh.stream.util;

/**
 * Exception used under the hood to hide checked exceptions.
 */
public final class ExStreamRuntimeException extends RuntimeException {

    public ExStreamRuntimeException(Exception cause) {
        super(cause);
    }

    @Override
    public synchronized Exception getCause() {
        return (Exception) super.getCause();
    }
}
