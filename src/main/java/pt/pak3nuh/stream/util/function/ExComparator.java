package pt.pak3nuh.stream.util.function;

import java.util.Comparator;

/**
 * Exceptional comparator of type {@code T}
 * @param <T> the type of the operands to compare
 * @see Comparator
 */
public interface ExComparator<T> extends Comparator<T>, ExceptionalFunction {

    /**
     * Compare the given arguments
     * @see Comparator#compare(Object, Object)
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     *         first argument is less than, equal to, or greater than the
     *         second.
     * @throws Exception if the function cannot complete
     */
    int exCompare(T o1, T o2) throws Exception;

    @Override
    default int compare(T o1, T o2) {
        try {
            return exCompare(o1, o2);
        } catch (Exception e) {
            throw wrapException(e);
        }
    }
}
