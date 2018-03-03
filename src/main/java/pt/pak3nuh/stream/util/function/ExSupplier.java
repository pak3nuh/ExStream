package pt.pak3nuh.stream.util.function;

/**
 * Exceptional supplier of type {@code T}
 * @param <T> the type of results supplied by this supplier
 * @see java.util.function.Supplier
 */
@FunctionalInterface
public interface ExSupplier<T> {
    /**
     * Gets a result.
     *
     * @return a result
     */
    T get() throws Exception;
}
