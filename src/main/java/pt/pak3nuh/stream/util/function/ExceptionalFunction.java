package pt.pak3nuh.stream.util.function;

import pt.pak3nuh.stream.util.ExStreamRuntimeException;

/**
 * A function that produces a {@link RuntimeException}
 */
public interface ExceptionalFunction {

    /**
     * The default exception thrown. Any implementer may provide a different exception if convenient.
     * @param cause the checked exception to wrap
     * @return the wrapper exception
     */
    default ExStreamRuntimeException wrapException(Exception cause) {
        return new ExStreamRuntimeException(cause);
    }
}
