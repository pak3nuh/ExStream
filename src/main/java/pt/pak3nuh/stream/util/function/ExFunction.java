package pt.pak3nuh.stream.util.function;

import java.util.function.Function;

/**
 * Exceptional function of type {@code T}
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 * @see java.util.function.Function
 */
@FunctionalInterface
public interface ExFunction<T,R> extends InterfaceAdapter<Function<T,R>> {
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    R apply(T t) throws Exception;

    @Override
    default Function<T, R> toJava() {
        return t -> {
            try {
                return apply(t);
            } catch (Exception e) {
                throw getException(e);
            }
        };
    }
}
