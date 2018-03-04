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
 * <br>
 * This interface holds some of the stream methods and will be added more in time.
 * @param <T> the type of the stream elements
 */
public interface ExStream<T> {

    /**
     * Filter with exception
     * @param predicate a <a href="package-summary.html#NonInterference">non-interfering</a>,
     *                  <a href="package-summary.html#Statelessness">stateless</a>
     *                  predicate to apply to each element to determine if it
     *                  should be included
     * @see Stream#filter(Predicate)
     * @return the new stream
     */
    ExStream<T> exFilter(ExPredicate<T> predicate);

    /**
     * Map with exception
     * @param <R> The element type of the new stream
     * @param mapper a <a href="package-summary.html#NonInterference">non-interfering</a>,
     *               <a href="package-summary.html#Statelessness">stateless</a>
     *               function to apply to each element
     * @see Stream#map(Function)
     * @return the new stream
     */
    <R> ExStream<R> exMap(ExFunction<T,R> mapper);

    /**
     * Collect with exception
     * @param <R> the type of the result
     * @param <A> the intermediate accumulation type of the {@code Collector}
     * @param collector the {@code Collector} describing the reduction
     * @see Stream#collect(Collector)
     * @return the new stream
     * @throws ExStreamException if some exception was thrown during stream processing
     */
    <R, A> R exCollect(Collector<? super T, A, R> collector) throws ExStreamException;

    /**
     * Flatmap with exception
     * @param <R> The element type of the new stream
     * @param mapper a <a href="package-summary.html#NonInterference">non-interfering</a>,
     *               <a href="package-summary.html#Statelessness">stateless</a>
     *               function to apply to each element which produces a stream
     *               of new values
     * @return the new stream
     * @see Stream#flatMap(Function)
     */
    <R> ExStream<R> exFlatMap(ExFunction<? super T, ? extends Stream<? extends R>> mapper);

    /**
     * ForEach with exception
     * @param action a <a href="package-summary.html#NonInterference">
     *               non-interfering</a> action to perform on the elements
     * @see Stream#forEach(Consumer)
     * @throws ExStreamException if some exception was thrown during stream processing
     */
    void exForEach(ExConsumer<? super T> action) throws ExStreamException;

    /**
     * Peek with exception
     * @param action a <a href="package-summary.html#NonInterference">
     *                 non-interfering</a> action to perform on the elements as
     *                 they are consumed from the stream
     * @return the new stream
     * @see Stream#peek(Consumer)
     */
    ExStream<T> exPeek(ExConsumer<? super T> action);

    /**
     * Limit with exception
     * @param maxSize the number of elements the stream should be limited to
     * @return the new stream
     * @see Stream#limit(long)
     */
    ExStream<T> exLimit(long maxSize);

    /**
     * Skip with exception
     * @param n the number of leading elements to skip
     * @return the new stream
     * @see Stream#skip(long)
     */
    ExStream<T> exSkip(long n);

    /**
     * Count with exception
     * @return the count of elements in this stream
     * @see Stream#count()
     * @throws ExStreamException if some exception was thrown during stream processing
     */
    long exCount() throws ExStreamException;

    /**
     * AnyMatch with exception
     * @param predicate a <a href="package-summary.html#NonInterference">non-interfering</a>,
     *                  <a href="package-summary.html#Statelessness">stateless</a>
     *                  predicate to apply to elements of this stream
     * @return {@code true} if any elements of the stream match the provided
     * @see Stream#anyMatch(Predicate)
     * @throws ExStreamException if some exception was thrown during stream processing
     */
    boolean exAnyMatch(ExPredicate<? super T> predicate) throws ExStreamException;

    /**
     * AllMatch with exception
     * @param predicate a <a href="package-summary.html#NonInterference">non-interfering</a>,
     *                  <a href="package-summary.html#Statelessness">stateless</a>
     *                  predicate to apply to elements of this stream
     * @return {@code true} if either all elements of the stream match the
     * provided predicate or the stream is empty, otherwise {@code false}
     * @see Stream#allMatch(Predicate)
     * @throws ExStreamException if some exception was thrown during stream processing
     */
    boolean exAllMatch(ExPredicate<? super T> predicate) throws ExStreamException;

    /**
     * FindFirst with exception
     * @return an {@code Optional} describing the first element of this stream,
     * or an empty {@code Optional} if the stream is empty
     * @see Stream#findFirst()
     * @throws ExStreamException if some exception was thrown during stream processing
     */
    Optional<T> exFindFirst() throws ExStreamException;

    /**
     * Reduce with exception
     * @param accumulator an <a href="package-summary.html#Associativity">associative</a>,
     *                    <a href="package-summary.html#NonInterference">non-interfering</a>,
     *                    <a href="package-summary.html#Statelessness">stateless</a>
     *                    function for combining two values
     * @return an {@link Optional} describing the result of the reduction
     * @see Stream#reduce(BinaryOperator)
     * @throws ExStreamException if some exception was thrown during stream processing
     */
    Optional<T> exReduce(ExBinaryOperator<T> accumulator) throws ExStreamException;

    /**
     * Returns the current instance as plain {@link Stream}.
     * All <a href="package-summary.html#StreamOps">terminal
     * operations</a> of the returning object can throw {@link ExStreamRuntimeException}.
     * @return a plain {@link Stream}
     */
    Stream<T> toStream();
}
