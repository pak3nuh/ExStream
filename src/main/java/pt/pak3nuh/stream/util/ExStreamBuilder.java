package pt.pak3nuh.stream.util;

import java.util.stream.Stream;

public final class ExStreamBuilder {

    private ExStreamBuilder() {
    }

    public static <T> ExStream<T> of(Stream<T> delegate){
        if(delegate instanceof ExStreamImpl){
            return (ExStream<T>) delegate;
        }
        return new ExStreamImpl<>(delegate);
    }
}
