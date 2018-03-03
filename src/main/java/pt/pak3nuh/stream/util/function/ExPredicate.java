package pt.pak3nuh.stream.util.function;

import java.util.function.Predicate;

/**
 * Exceptional predicate of type {@code T}
 * @param <T> the type of the input to the predicate
 * @see java.util.function.Predicate
 */
@FunctionalInterface
public interface ExPredicate<T> extends InterfaceAdapter<Predicate<T>> {
    /**
     * Evaluates this predicate on the given argument.
     *
     * @param t the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    boolean test(T t) throws Exception;

    @Override
    default Predicate<T> toJava() {
        return t -> {
            try {
                return test(t);
            } catch (Exception e) {
                throw getException(e);
            }
        };
    }
}
