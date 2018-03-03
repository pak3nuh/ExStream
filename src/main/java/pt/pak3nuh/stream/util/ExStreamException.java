package pt.pak3nuh.stream.util;

/**
 * Exception thrown on terminal or intermediate stateful operations of {@link ExStream} objects.
 */
public final class ExStreamException extends Exception {

    public ExStreamException(Exception cause) {
        super(cause);
    }

}
