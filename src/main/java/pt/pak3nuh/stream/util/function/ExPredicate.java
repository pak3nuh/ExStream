package pt.pak3nuh.stream.util.function;

/**
 * Exceptional predicate of type {@code T}
 * @param <T> the type of the input to the predicate
 * @see java.util.function.Predicate
 */
@FunctionalInterface
public interface ExPredicate<T> {
    /**
     * Evaluates this predicate on the given argument.
     *
     * @param t the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    boolean test(T t) throws Exception;
}
