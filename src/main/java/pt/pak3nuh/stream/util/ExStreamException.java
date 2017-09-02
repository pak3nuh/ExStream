package pt.pak3nuh.stream.util;

public class ExStreamException extends Exception {

    public ExStreamException(Exception cause) {
        super(cause);
    }

    /**
     * @param <T> expected exception type
     * @throws ClassCastException if T is not the actual exception type
     * @return the exception as result of the cast
     */
    @SuppressWarnings("unchecked")
    public <T extends Exception> T castCause() {
        return (T) getCause();
    }
}
