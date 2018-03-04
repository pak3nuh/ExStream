package pt.pak3nuh.stream.util.function;

import java.util.function.Supplier;

/**
 * Exceptional supplier of type {@code T}
 * @param <T> the type of results supplied by this supplier
 * @see java.util.function.Supplier
 */
@FunctionalInterface
public interface ExSupplier<T> extends Supplier<T>, ExceptionalFunction {
    /**
     * Gets a result.
     *
     * @return a result
     * @throws Exception if the function cannot complete
     */
    T exGet() throws Exception;

    @Override
    default T get() {
        try {
            return exGet();
        } catch (Exception e) {
            throw getException(e);
        }
    }
}
