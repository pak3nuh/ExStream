package pt.pak3nuh.stream.util.function;

import java.util.function.Supplier;

/**
 * Exceptional supplier of type {@code T}
 * @param <T> the type of results supplied by this supplier
 * @see java.util.function.Supplier
 */
@FunctionalInterface
public interface ExSupplier<T> extends InterfaceAdapter<Supplier<T>> {
    /**
     * Gets a result.
     *
     * @return a result
     */
    T get() throws Exception;

    @Override
    default Supplier<T> toJava() {
        return () -> {
            try {
                return get();
            } catch (Exception e) {
                throw getException(e);
            }
        };
    }
}
