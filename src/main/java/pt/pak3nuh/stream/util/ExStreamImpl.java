package pt.pak3nuh.stream.util;

import pt.pak3nuh.stream.util.function.*;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

final class ExStreamImpl<T,E extends Exception> extends ExStreamBase<T> implements ExStream<T,E> {

    private final ExHandler<E> exHandler;

    ExStreamImpl(Stream<T> delegate, ExHandler<E> exHandler) {
        super(delegate);
        this.exHandler = exHandler;
    }

    @Override
    public ExStream<T,E> exFilter(ExPredicate<T> predicate) {
        return new ExStreamImpl<>(getDelegate().filter(predicate), exHandler);
    }

    @Override
    public <R> ExStream<R,E> exMap(ExFunction<T,R> mapper) {
        return new ExStreamImpl<>(getDelegate().map(mapper), exHandler);
    }

    @Override
    public <R, A> R exCollect(Collector<? super T, A, R> collector) throws E {
        return terminal(() -> getDelegate().collect(collector));
    }

    @Override
    public <R> ExStream<R,E> exFlatMap(ExFunction<? super T, ? extends Stream<? extends R>> mapper) {
        return new ExStreamImpl<>(getDelegate().flatMap(mapper), exHandler);
    }

    @Override
    public void exForEach(ExConsumer<? super T> action) throws E{
        terminalVoid(() -> getDelegate().forEach(action));
    }

    @Override
    public ExStream<T,E> exPeek(ExConsumer<? super T> action) {
        return new ExStreamImpl<>(getDelegate().peek(action), exHandler);
    }

    @Override
    public ExStream<T,E> exLimit(long maxSize) {
        return new ExStreamImpl<>(getDelegate().limit(maxSize), exHandler);
    }

    @Override
    public ExStream<T,E> exSkip(long n) {
        return new ExStreamImpl<>(getDelegate().skip(n), exHandler);
    }

    @Override
    public long exCount() throws E {
        return terminal(() -> getDelegate().count());
    }

    @Override
    public boolean exAnyMatch(ExPredicate<? super T> predicate) throws E {
        return terminal(() -> getDelegate().anyMatch(predicate));
    }

    @Override
    public boolean exAllMatch(ExPredicate<? super T> predicate) throws E {
        return terminal(() -> getDelegate().allMatch(predicate));
    }

    @Override
    public Optional<T> exFindFirst() throws E {
        return terminal(() -> getDelegate().findFirst());
    }

    @Override
    public Optional<T> exReduce(ExBinaryOperator<T> accumulator) throws E {
        return terminal(() -> getDelegate().reduce(accumulator));
    }

    @Override
    public ExStream<T,E> sorted(ExComparator<? super T> comparator) {
        return new ExStreamImpl<>(getDelegate().sorted(comparator), exHandler);
    }

    private <Z> Z terminal(Supplier<Z> supplier) throws E {
        try {
            return supplier.get();
        } catch (ExStreamRuntimeException e) {
            throw exHandler.handle(e.getCause());
        }
    }

    private void terminalVoid(Runnable action) throws E {
        try {
            action.run();
        } catch (ExStreamRuntimeException e) {
            throw exHandler.handle(e.getCause());
        }
    }

}
