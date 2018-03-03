package pt.pak3nuh.stream.util.function;

import pt.pak3nuh.stream.util.ExStreamRuntimeException;

/**
 * A bridge between a {@code java.util} function of type {@code T} and it's throwing counterpart.
 * @param <T> the {@code java.util} function to adapt
 */
public interface InterfaceAdapter<T> {
    /**
     * Obtains a function compatible in its signature but without throwing checked exception.
     * It's expected that any existing exception to be thrown as cause of {@link ExStreamRuntimeException}
     */
    T toJava();

    /**
     * The default exception thrown. Any implementer may provide a different exception if convenient.
     * @param cause the checked exception to wrap
     */
    default ExStreamRuntimeException getException(Exception cause) {
        return new ExStreamRuntimeException(cause);
    }
}
