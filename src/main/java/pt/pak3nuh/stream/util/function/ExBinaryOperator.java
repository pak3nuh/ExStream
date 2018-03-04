package pt.pak3nuh.stream.util.function;

import java.util.function.BinaryOperator;

/**
 * Exceptional binary operator of type {@code T}
 * @param <T> the type of the operands and result of the operator
 * @see java.util.function.BinaryOperator
 */
@FunctionalInterface
public interface ExBinaryOperator<T> extends BinaryOperator<T>, ExceptionalFunction {
    /**
     * Applies this function to the given arguments.
     *
     * @param t1 the first function argument
     * @param t2 the second function argument
     * @return the function result
     * @throws Exception if the function cannot complete
     */
    T exApply(T t1, T t2) throws Exception;

    @Override
    default T apply(T t1, T t2) {
        try {
            return exApply(t1, t2);
        } catch (Exception e) {
            throw getException(e);
        }
    }
}
