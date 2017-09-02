package pt.pak3nuh.stream.util.function;

public interface ExFunction<T,R> {
    R apply(T t) throws Exception;
}
