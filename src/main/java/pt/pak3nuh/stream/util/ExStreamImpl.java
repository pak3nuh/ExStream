package pt.pak3nuh.stream.util;

import pt.pak3nuh.stream.util.function.ExBinaryOperator;
import pt.pak3nuh.stream.util.function.ExComparator;
import pt.pak3nuh.stream.util.function.ExConsumer;
import pt.pak3nuh.stream.util.function.ExFunction;
import pt.pak3nuh.stream.util.function.ExPredicate;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

final class ExStreamImpl<T> extends ExStreamBase<T> implements ExStream<T> {

    ExStreamImpl(Stream<T> delegate) {
        super(delegate);
    }

    @Override
    public ExStream<T> exFilter(ExPredicate<T> predicate) {
        return new ExStreamImpl<>(getDelegate().filter(predicate));
    }

    @Override
    public <R> ExStream<R> exMap(ExFunction<T,R> mapper) {
        return new ExStreamImpl<>(getDelegate().map(mapper));
    }

    @Override
    public <R, A> R exCollect(Collector<? super T, A, R> collector) throws ExStreamException {
        return terminal(() -> getDelegate().collect(collector));
    }

    @Override
    public <R> ExStream<R> exFlatMap(ExFunction<? super T, ? extends Stream<? extends R>> mapper) {
        return new ExStreamImpl<>(getDelegate().flatMap(mapper));
    }

    @Override
    public void exForEach(ExConsumer<? super T> action) throws ExStreamException{
        terminal(() -> getDelegate().forEach(action));
    }

    @Override
    public ExStream<T> exPeek(ExConsumer<? super T> action) {
        return new ExStreamImpl<>(getDelegate().peek(action));
    }

    @Override
    public ExStream<T> exLimit(long maxSize) {
        return new ExStreamImpl<>(getDelegate().limit(maxSize));
    }

    @Override
    public ExStream<T> exSkip(long n) {
        return new ExStreamImpl<>(getDelegate().skip(n));
    }

    @Override
    public long exCount() throws ExStreamException {
        return terminal(() -> getDelegate().count());
    }

    @Override
    public boolean exAnyMatch(ExPredicate<? super T> predicate) throws ExStreamException {
        return terminal(() -> getDelegate().anyMatch(predicate));
    }

    @Override
    public boolean exAllMatch(ExPredicate<? super T> predicate) throws ExStreamException {
        return terminal(() -> getDelegate().allMatch(predicate));
    }

    @Override
    public Optional<T> exFindFirst() throws ExStreamException {
        return terminal(() -> getDelegate().findFirst());
    }

    @Override
    public Optional<T> exReduce(ExBinaryOperator<T> accumulator) throws ExStreamException {
        return terminal(() -> getDelegate().reduce(accumulator));
    }

    @Override
    public ExStream<T> sorted(ExComparator<? super T> comparator) {
        return new ExStreamImpl<>(getDelegate().sorted(comparator));
    }

    private static <T> T terminal(Supplier<T> supplier) throws ExStreamException {
        try {
            return supplier.get();
        } catch (ExStreamRuntimeException e) {
            throw new ExStreamException(e.getCause());
        }
    }

    private static void terminal(Runnable action) throws ExStreamException {
        try {
            action.run();
        } catch (ExStreamRuntimeException e) {
            throw new ExStreamException(e.getCause());
        }
    }

}
