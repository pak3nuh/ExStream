package pt.pak3nuh.stream.util;

import pt.pak3nuh.stream.util.function.ExBinaryOperator;
import pt.pak3nuh.stream.util.function.ExConsumer;
import pt.pak3nuh.stream.util.function.ExFunction;
import pt.pak3nuh.stream.util.function.ExPredicate;

import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * A wrapper interface for {@link Stream} that can accept intermediate functions that throw exceptions.
 * <br/>
 * This interface holds some of the stream methods and will be added more in time.
 * @param <T> the type of the stream elements
 */
public interface ExStream<T> {

    /**
     * Filter with exception
     * @see Stream#filter(Predicate)
     */
    ExStream<T> exFilter(ExPredicate<T> predicate);

    /**
     * Map with exception
     * @see Stream#map(Function)
     */
    <R> ExStream<R> exMap(ExFunction<T,R> predicate);

    /**
     * Collect with exception
     * @see Stream#collect(Collector)
     * @throws ExStreamException if some exception was thrown during stream processing
     */
    <R, A> R exCollect(Collector<? super T, A, R> collector) throws ExStreamException;

    /**
     * Flatmap with exception
     * @see Stream#flatMap(Function)
     */
    <R> ExStream<R> exFlatMap(ExFunction<? super T, ? extends Stream<? extends R>> mapper);

    /**
     * ForEach with exception
     * @see Stream#forEach(Consumer)
     * @throws ExStreamException if some exception was thrown during stream processing
     */
    void exForEach(ExConsumer<? super T> action) throws ExStreamException;

    /**
     * Peek with exception
     * @see Stream#peek(Consumer)
     */
    ExStream<T> exPeek(ExConsumer<? super T> action);

    /**
     * @see Stream#limit(long)
     */
    ExStream<T> exLimit(long maxSize);

    /**
     * @see Stream#skip(long)
     */
    ExStream<T> exSkip(long n);

    /**
     * Count with exception
     * @see Stream#count()
     * @throws ExStreamException if some exception was thrown during stream processing
     */
    long exCount() throws ExStreamException;

    /**
     * AnyMatch with exception
     * @see Stream#anyMatch(Predicate)
     * @throws ExStreamException if some exception was thrown during stream processing
     */
    boolean exAnyMatch(ExPredicate<? super T> predicate) throws ExStreamException;

    /**
     * AllMatch with exception
     * @see Stream#allMatch(Predicate)
     * @throws ExStreamException if some exception was thrown during stream processing
     */
    boolean exAllMatch(ExPredicate<? super T> predicate) throws ExStreamException;

    /**
     * FindFirst with exception
     * @see Stream#findFirst()
     * @throws ExStreamException if some exception was thrown during stream processing
     */
    Optional<T> exFindFirst() throws ExStreamException;

    /**
     * Reduce with exception
     * @see Stream#reduce(BinaryOperator)
     * @throws ExStreamException if some exception was thrown during stream processing
     */
    Optional<T> exReduce(ExBinaryOperator<T> accumulator) throws ExStreamException;

    /**
     * Returns the current instance as plain {@link Stream}.
     * All <a href="package-summary.html#StreamOps">terminal
     * operations</a> of the returning object can throw {@link ExStreamRuntimeException}.
     */
    Stream<T> toStream();
}
