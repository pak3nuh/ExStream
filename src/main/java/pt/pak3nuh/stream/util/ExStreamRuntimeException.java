package pt.pak3nuh.stream.util;

public class ExStreamRuntimeException extends RuntimeException {

    public ExStreamRuntimeException(Exception cause) {
        super(cause);
    }

    @Override
    public synchronized Exception getCause() {
        return (Exception) super.getCause();
    }
}
