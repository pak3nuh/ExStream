package pt.pak3nuh.stream.util.function;

import java.util.function.BinaryOperator;

/**
 * Exceptional binary operator of type {@code T}
 * @param <T> the type of the operands and result of the operator
 * @see java.util.function.BinaryOperator
 */
@FunctionalInterface
public interface ExBinaryOperator<T> extends InterfaceAdapter<BinaryOperator<T>> {
    /**
     * Applies this function to the given arguments.
     *
     * @param t1 the first function argument
     * @param t2 the second function argument
     * @return the function result
     */
    T apply(T t1, T t2) throws Exception;

    @Override
    default BinaryOperator<T> toJava() {
        return (t, t2) -> {
            try {
                return apply(t, t2);
            } catch (Exception e) {
                throw getException(e);
            }
        };
    }
}
