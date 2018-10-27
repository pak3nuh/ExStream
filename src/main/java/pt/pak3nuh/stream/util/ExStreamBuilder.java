package pt.pak3nuh.stream.util;

import java.util.stream.Stream;

/**
 * Main entry point for creating {@link ExStream} instances.
 * <br>
 * Also contains some convenience methods for stream creation.
 */
public final class ExStreamBuilder {

    private ExStreamBuilder() {
    }

    public static <T> ExStream<T,ExStreamException> of(Stream<T> delegate){
        return of(delegate, ExStreamException::new);
    }

    public static <T, E extends Exception> ExStream<T,E> of(Stream<T> delegate,
                                                            ExHandler<E> exHandler){
        if(delegate instanceof ExStreamImpl){
            //must unwrap because the exception handling may be different
            //and can cause class cast exceptions
            final ExStreamImpl<T,?> ex = (ExStreamImpl<T,?>) delegate;
            return new ExStreamImpl<>(ex.getDelegate(), exHandler);
        }
        return new ExStreamImpl<>(delegate, exHandler);
    }

    public static <T> ExStream<T, ExStreamException> of(T element) {
        return of(element, ExStreamException::new);
    }

    public static <T,E extends Exception> ExStream<T, E> of(T element, ExHandler<E> exHandler) {
        return new ExStreamImpl<>(Stream.of(element), exHandler);
    }

    @SafeVarargs
    @SuppressWarnings("varargs") // Creating a stream from an array is safe
    public static <T> ExStream<T, ExStreamException> of(T... elements) {
        return of(ExStreamException::new, elements);
    }

    @SafeVarargs
    @SuppressWarnings("varargs") // Creating a stream from an array is safe
    public static <T,E extends Exception> ExStream<T, E> of(ExHandler<E> exHandler, T... elements) {
        return new ExStreamImpl<>(Stream.of(elements), exHandler);
    }
}
