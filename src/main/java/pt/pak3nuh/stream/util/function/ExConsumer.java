package pt.pak3nuh.stream.util.function;

import java.util.function.Consumer;

/**
 * Exceptional consumer of type {@code T}
 * @param <T> the type of the input to the operation
 * @see java.util.function.Consumer
 */
@FunctionalInterface
public interface ExConsumer<T> extends Consumer<T>, ExceptionalFunction {
    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     * @throws Exception if the function cannot complete
     */
    void exAccept(T t) throws Exception;

    @Override
    default void accept(T t) {
        try {
            exAccept(t);
        } catch (Exception e) {
            throw wrapException(e);
        }
    }

}
