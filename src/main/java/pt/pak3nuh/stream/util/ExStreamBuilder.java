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

    @SuppressWarnings("unchecked")
    public static <T> ExStream<T> of(Stream<T> delegate){
        if(delegate instanceof ExStreamImpl){
            return (ExStream<T>) delegate;
        }
        return new ExStreamImpl<>(delegate);
    }

    public static <T> ExStream<T> of(T element) {
        return new ExStreamImpl<>(Stream.of(element));
    }

    @SafeVarargs
    @SuppressWarnings("varargs") // Creating a stream from an array is safe
    public static <T> ExStream<T> of(T... elements) {
        return new ExStreamImpl<>(Stream.of(elements));
    }
}
