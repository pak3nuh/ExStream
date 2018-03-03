package pt.pak3nuh.stream.util.function;

import java.util.function.Consumer;

/**
 * Exceptional consumer of type {@code T}
 * @param <T> the type of the input to the operation
 * @see java.util.function.Consumer
 */
@FunctionalInterface
public interface ExConsumer<T> extends InterfaceAdapter<Consumer<T>> {
    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    void accept(T t) throws Exception;

    @Override
    default Consumer<T> toJava() {
        return t -> {
            try {
                accept(t);
            } catch (Exception e) {
                throw getException(e);
            }
        };
    }
}
