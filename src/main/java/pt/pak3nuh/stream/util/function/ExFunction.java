package pt.pak3nuh.stream.util.function;

import java.util.function.Function;

/**
 * Exceptional function of type {@code T}
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 * @see java.util.function.Function
 */
@FunctionalInterface
public interface ExFunction<T,R> extends Function<T,R>, ExceptionalFunction {
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     * @throws Exception if the function cannot complete
     */
    R exApply(T t) throws Exception;

    @Override
    default R apply(T t) {
        try {
            return exApply(t);
        } catch (Exception e) {
            throw wrapException(e);
        }
    }

}
