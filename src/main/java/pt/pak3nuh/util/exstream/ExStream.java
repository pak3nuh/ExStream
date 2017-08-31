package pt.pak3nuh.util.exstream;

import pt.pak3nuh.util.exstream.function.ExConsumer;
import pt.pak3nuh.util.exstream.function.ExFunction;
import pt.pak3nuh.util.exstream.function.ExPredicate;

import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Stream;

public interface ExStream<T> {

    ExStream<T> exFilter(ExPredicate<T> predicate);

    <R> ExStream<R> exMap(ExFunction<T,R> predicate);

    <R, A> R exCollect(Collector<? super T, A, R> collector) throws ExStreamException;

    <R> ExStream<R> exFlatMap(ExFunction<? super T, ? extends Stream<? extends R>> mapper);

    void exForEach(ExConsumer<? super T> action) throws ExStreamException;

    ExStream<T> exPeek(ExConsumer<? super T> action);

    ExStream<T> exLimit(long maxSize);

    ExStream<T> exSkip(long n);

    long exCount() throws ExStreamException;

    boolean exAnyMatch(ExPredicate<? super T> predicate) throws ExStreamException;

    boolean exAllMatch(ExPredicate<? super T> predicate) throws ExStreamException;

    Optional<T> exFindFirst() throws ExStreamException;

    /**
     * Returns the current instance as plain {@link Stream}.
     * All <a href="package-summary.html#StreamOps">terminal
     * operations</a> of the returning object can throw {@link ExStreamRuntimeException}.
     */
    Stream<T> getStream();
}
