package pt.pak3nuh.stream.util;

/**
 * Transforms an exception during stream processing into another one.
 * @param <E> the mapped exception type
 */
public interface ExHandler<E extends Exception> {

    /**
     * Triggered when an exception is raised within stream processing.
     * THe exception is already unwrapped from {@link ExStreamRuntimeException}
     * @param cause the cause exception on the stream processing
     * @return the new exception to be raised by a terminal operation
     */
    E handle(Exception cause);

}
