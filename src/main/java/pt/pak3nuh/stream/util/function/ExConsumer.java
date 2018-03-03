package pt.pak3nuh.stream.util.function;

/**
 * Exceptional consumer of type {@code T}
 * @param <T> the type of the input to the operation
 * @see java.util.function.Consumer
 */
@FunctionalInterface
public interface ExConsumer<T> {
    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    void accept(T t) throws Exception;
}
